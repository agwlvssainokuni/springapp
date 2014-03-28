$(function() {
	$(".app-menu").menu();
	$(".app-button").button();
	$(".app-buttonset").buttonset();
	$("table.app-table-stripe tbody tr:even").addClass("app-row-even");
	$("table.app-table-stripe tbody tr:odd").addClass("app-row-odd");
});
