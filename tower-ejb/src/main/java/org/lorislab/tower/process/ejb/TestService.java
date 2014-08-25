/*
 * Copyright 2014 lorislab.org.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.lorislab.tower.process.ejb;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import org.lorislab.barn.api.service.ConfigurationService;
import org.lorislab.jel.ejb.exception.ServiceException;
import org.lorislab.postman.api.model.Email;
import org.lorislab.postman.api.model.EmailConfig;
import org.lorislab.postman.api.service.EmailService;
import org.lorislab.tower.agent.ejb.AgentClientService;
import org.lorislab.tower.bts.model.BtsCriteria;
import org.lorislab.tower.process.resources.ErrorKeys;
import org.lorislab.tower.process.util.LinkUtil;
import org.lorislab.tower.scm.model.ScmCriteria;
import org.lorislab.tower.store.ejb.AgentService;
import org.lorislab.tower.store.ejb.BTSystemService;
import org.lorislab.tower.store.ejb.SCMSystemService;
import org.lorislab.tower.store.model.Agent;
import org.lorislab.tower.store.model.BTSystem;
import org.lorislab.tower.store.model.Build;
import org.lorislab.tower.store.model.SCMSystem;
import org.lorislab.treasure.api.factory.PasswordServiceFactory;
import org.lorislab.treasure.api.service.PasswordService;

/**
 * The test service.
 *
 * @author Andrej Petras
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class TestService {

    /**
     * The test email template.
     */
    private static final String MAIL_TEST_TEMPLATE = "test";

    /**
     * The mail service.
     */
    @EJB
    private EmailService mailService;

    /**
     * The store SCM system service.
     */
    @EJB
    private SCMSystemService scmService;

    /**
     * The store bug tracking system service.
     */
    @EJB
    private BTSystemService btsService;

    /**
     * The agent client service.
     */
    @EJB
    private AgentClientService agentClientService;

    /**
     * The store agent service.
     */
    @EJB
    private AgentService agentService;

    /**
     * The external system service.
     */
    @EJB
    private ExternalSystemService externalService;

    @EJB
    private ConfigurationService configService;

    /**
     * Gets the agent services list.
     *
     * @param guid the GUID of the agent.
     *
     * @return the build of the agent.
     * @throws ServiceException if the method fails.
     */
    public List<Build> getAgentServices(String guid) throws ServiceException {
        List<Build> result = null;
        Agent agent = agentService.getAgent(guid);
        if (agent == null) {
            throw new ServiceException(ErrorKeys.NO_AGENT_FOUND, guid);
        }

        try {
            result = agentClientService.getBuilds(agent);
        } catch (Exception ex) {
            throw new ServiceException(ErrorKeys.ERROR_CREATE_AGENT_CONNECTION, guid, ex, agent.getUrl(), ex.getMessage());
        }
        return result;
    }

    /**
     * Tests the agent connection.
     *
     * @param guid the GUID of the agent.
     *
     * @return the build of the agent.
     * @throws ServiceException if the method fails.
     */
    public Build testAgent(String guid) throws ServiceException {
        Build result = null;
        Agent agent = agentService.getAgent(guid);
        if (agent == null) {
            throw new ServiceException(ErrorKeys.NO_AGENT_FOUND, guid);
        }

        try {
            result = agentClientService.getAgentBuild(agent);
        } catch (Exception ex) {
            throw new ServiceException(ErrorKeys.ERROR_CREATE_AGENT_CONNECTION, guid, ex, agent.getUrl(), ex.getMessage());
        }
        return result;
    }

    /**
     * Tests the BTS system connection.
     *
     * @param guid the GUID of the BTS system.
     * @throws ServiceException if the method fails.
     */
    public void testBTS(String guid) throws ServiceException {
        BTSystem bts = btsService.getBTSystem(guid);
        if (bts == null) {
            throw new ServiceException(ErrorKeys.NO_BT_SYSTEM_FOUND, guid);
        }

        try {

            PasswordService pswd = PasswordServiceFactory.getService();
            char[] tmp = pswd.getPassword(bts.getPassword());

            BtsCriteria bc = new BtsCriteria();
            bc.setServer(bts.getServer());
            bc.setUser(bts.getUser());
            bc.setPassword(tmp);
            bc.setAuth(bts.isAuth());
            bc.setType(bts.getType());
            externalService.testConnection(bc);
        } catch (Exception ex) {
            throw new ServiceException(ErrorKeys.ERROR_CREATE_BT_CONNECTION, guid, ex, bts.getServer(), ex.getMessage());
        }
    }

    /**
     * Tests the SCM system connection.
     *
     * @param guid the GUID of the SCM system.
     * @throws ServiceException if the method fails.
     */
    public void testSCM(String guid) throws ServiceException {
        SCMSystem scm = scmService.getSCMSystem(guid);
        if (scm == null) {
            throw new ServiceException(ErrorKeys.NO_SCM_SYSTEM_FOUND, guid);
        }

        try {
            PasswordService pswd = PasswordServiceFactory.getService();
            char[] tmp = pswd.getPassword(scm.getPassword());
            
            ScmCriteria criteria = new ScmCriteria();
            criteria.setType(scm.getType());
            criteria.setServer(scm.getServer());
            criteria.setAuth(scm.isAuth());
            criteria.setUser(scm.getUser());
            criteria.setPassword(tmp);
            criteria.setReadTimeout(scm.getReadTimeout());
            criteria.setConnectionTimeout(scm.getConnectionTimeout());
            externalService.testConnection(criteria);
        } catch (Exception ex) {
            throw new ServiceException(ErrorKeys.ERROR_CREATE_SCM_CONNECTION, guid, ex, scm.getServer(), ex.getMessage());
        }
    }

    /**
     * Sends the test email to the mail address.
     *
     * @param email the email address.
     * @throws ServiceException if the method fails.
     */
    public void sendTestEmail(String email) throws ServiceException {
        if (email == null || email.isEmpty()) {
            throw new ServiceException(ErrorKeys.ERROR_EMAIL_IS_NULL);
        }
        try {
            Email mail = new Email();
            mail.getTo().add(email);
            mail.setTemplate(MAIL_TEST_TEMPLATE);

            EmailConfig config = configService.getConfiguration(EmailConfig.class);

            mailService.sendEmail(mail, config);
        } catch (Exception ex) {
            throw new ServiceException(ErrorKeys.ERROR_SEND_EMAIL, ex, email);
        }
    }

    public void testBTSAccess(String guid, String project) throws ServiceException {
        BTSystem bts = btsService.getBTSystem(guid);
        if (bts == null) {
            throw new ServiceException(ErrorKeys.NO_BT_SYSTEM_FOUND, guid);
        }

        try {
            PasswordService pswd = PasswordServiceFactory.getService();
            char[] tmp = pswd.getPassword(bts.getPassword());
            
            BtsCriteria bc = new BtsCriteria();
            bc.setProject(project);
            bc.setServer(bts.getServer());
            bc.setUser(bts.getUser());
            bc.setPassword(tmp);
            bc.setAuth(bts.isAuth());
            bc.setType(bts.getType());
            externalService.testProjectAccess(bc);
        } catch (Exception ex) {
            throw new ServiceException(ErrorKeys.ERROR_CREATE_BT_CONNECTION, guid, ex, bts.getServer(), ex.getMessage());
        }
    }

    public void testSCMRepository(String guid, String repository) throws ServiceException {
        SCMSystem scm = scmService.getSCMSystem(guid);
        if (scm == null) {
            throw new ServiceException(ErrorKeys.NO_SCM_SYSTEM_FOUND, guid);
        }

        String server = LinkUtil.createLink(repository, scm);

        try {
            PasswordService pswd = PasswordServiceFactory.getService();
            char[] tmp = pswd.getPassword(scm.getPassword());
                        
            ScmCriteria criteria = new ScmCriteria();
            criteria.setType(scm.getType());
            criteria.setServer(server);
            criteria.setAuth(scm.isAuth());
            criteria.setUser(scm.getUser());
            criteria.setPassword(tmp);
            criteria.setReadTimeout(scm.getReadTimeout());
            criteria.setConnectionTimeout(scm.getConnectionTimeout());
            externalService.testRepository(criteria);
        } catch (Exception ex) {
            throw new ServiceException(ErrorKeys.ERROR_CREATE_SCM_CONNECTION, guid, ex, scm.getServer(), ex.getMessage());
        }
    }
}
