function Bf() {
}
Bf.id = function (a) {
    return"#" + a.replace(/:/g, "\\:");
};
Bf.e = function (a) {
    return $(Bf.id(a));
};
Bf.filter = function (a) {
    var i = a + ":input-filter";
    Bf.e(i).filterTable(a + ":content:table");
    Bf.e(a + ":clear-filter").clearTable(i);
};

Bf.spin = function (data) {
    var status = data.status;
    if (status === "begin") {
        Bf.e(data.source.id).find('i').addClass('fa-spin');
    } else if (status === "success") {
        Bf.e(data.source.id).find('i').removeClass('fa-spin');
    }
};

Bf.status = function (i, t) {
    var loading;
    if (!window["bs"]) {
        var bs = {};
    }
    bs.onStatusChange = function (data) {
        var status = data.status;
        if (status === "begin") {
            loading = setTimeout(function () {
                document.body.style.cursor = 'wait';
                Bf.e(i).modal('show');
            }, t);
        }
        else if (status === "success") {
            clearTimeout(loading);
            Bf.e(i).modal('hide');
            document.body.style.cursor = 'auto';
        }
    };
    jsf.ajax.addOnEvent(bs.onStatusChange);
};

(function () {
    'use strict';
    var $ = jQuery;
    $.fn.extend({
        clearTable: function (x) {
            return this.each(function () {
                $(this).on('click', function (e) {
                    var filter = Bf.e(x), $filter = $(filter);
                    $filter.val('');
                    $filter.keyup();
                });
            });
        },
        filterTable: function (x) {
            return this.each(function () {
                $(this).on('keyup', function (e) {
                    var $this = $(this), search = $this.val().toLowerCase();
                    var target = Bf.id(x);
                    var $target = $(target), $rows = $target.find('tbody:first tr');
                    $target.find('tbody:last').hide();
                    $target.find('thead').show();
                    if (search === '') {
                        $rows.show();
                    } else {
                        $rows.each(function () {
                            var $this = $(this);
                            $this.text().toLowerCase().indexOf(search) === -1 ? $this.hide() : $this.show();
                        });
                        if ($target.find('tbody:first tr:visible').size() === 0) {
                            $target.find('tbody:last').show();
                            $target.find('thead').hide();
                        }
                    }
                });
            });
        }
    });
})(jQuery);
