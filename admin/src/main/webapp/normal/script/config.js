$(function() {

	$(".app-menu").menu();
	$(".app-button").button();
	$(".app-buttonset").buttonset();
	$("table.app-table-stripe tbody tr:even").addClass("app-row-even");
	$("table.app-table-stripe tbody tr:odd").addClass("app-row-odd");

	$(".app-pager").each(function(index) {
		var current = $(this).children(".app-current").attr("title");
		var pages = $(this).children(".app-page[title != '" + current + "']");
		pages.wrap("<a href='#'></a>").click(function() {
			var form = $(".app-pager-form");
			form.children("input[name='no']").val(this.title - 1);
			form.submit();
			return false;
		});
	});

});
