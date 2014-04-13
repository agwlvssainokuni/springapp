$(function() {

	$(".app-menu").menu();
	$(".app-button").button();
	$(".app-buttonset").buttonset();
	$("table.app-stripe>tbody>tr:even").addClass("app-even");
	$("table.app-stripe>tbody>tr:odd").addClass("app-odd");

	$(".app-pager-link").each(function(index) {
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
