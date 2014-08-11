tower = {
	id: function(id) {
		return "#" + id.replace(/:/g, "\\:");
	},
	closeModal: function(id, check) {
		if ($(this.id(check)).length === 0) {
			$(this.id(id)).modal('hide');
		}
	}	
};

(function() {
	'use strict';
	var $ = jQuery;
	$.fn.extend({
		clearTable: function() {
			return this.each(function() {
				$(this).on('click', function(e) {
					var filter = $('[data-action="tableFilter"]'), $filter = $(filter);
					$filter.val('');
					$filter.keyup();
				});
			});
		},
		filterTable: function() {
			return this.each(function() {
				$(this).on('keyup', function(e) {
					$('.filterTable_no_results').remove();
					var $this = $(this), search = $this.val().toLowerCase(), target = $this.attr('data-filters'), $target = $(target), $rows = $target.find('tbody tr');
					$target.find('thead').show();
					if (search === '') {
						$rows.show();
					} else {
						$rows.each(function() {
							var $this = $(this);
							$this.text().toLowerCase().indexOf(search) === -1 ? $this.hide() : $this.show();
						});
						if ($target.find('tbody tr:visible').size() === 0) {
							var col_count = $target.find('tr').first().find('td').size();
							var no_results = $('<tr class="filterTable_no_results"><td colspan="' + col_count + '">No results found</td></tr>');
							$target.find('tbody').append(no_results);
							$target.find('thead').hide();
						}
					}
				});
			});
		}
	});
	$('[data-action="tableFilter"]').filterTable();
	$('[data-action="tableFiltetClear"]').clearTable();
})(jQuery);

$(function() {
	$('#side-menu').metisMenu();
	$(window).bind("load resize", function() {
		topOffset = 30;
		width = (this.window.innerWidth > 0) ? this.window.innerWidth : this.screen.width;
		if (width < 768) {
			$('div.navbar-collapse').addClass('collapse');
			topOffset = 100; // 2-row-menu
		} else {
			$('div.navbar-collapse').removeClass('collapse');
		}
	});
});
