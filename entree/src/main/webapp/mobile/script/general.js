$(function() {

	$(".app-pager-link").each(function(index) {
		var current = $(".app-current", this).attr("title");
		var pages = $(".app-page[title != '" + current + "']", this);
		pages.removeClass("ui-disabled");
		pages.click(function() {
			var form = $(".app-pager-form");
			$("input[name='no']", form).val(this.title - 1);
			form.submit();
			return false;
		});
	});

});
