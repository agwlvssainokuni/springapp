$(function() {

	$(".app-menu").menu();
	$(".app-button").button();
	$(".app-buttonset").buttonset();

	$("table.app-stripe>tbody>tr:even").addClass("app-even");
	$("table.app-stripe>tbody>tr:odd").addClass("app-odd");

	$("table.app-multihead").each(function(index) {
		var $head = $("thead", this);
		var step = $(this).attr("data-multihead");
		step = (step ? step : 10);
		$("tbody tr", this).each(function(index) {
			if (index != 0 && index % step == 0) {
				var $row = $(this);
				$("tr", $head).each(function(index) {
					var $tr = $(this).clone().insertBefore($row);
					$(":checkbox.app-checkall", $tr).each(function(index) {
						if ($(this).hasClass("app-checkall-headonly")) {
							$(this).addClass("app-displaynone");
						}
					});
				});
			}
		});
	});

	$(":checkbox.app-checkall").each(function(index) {
		var selector = $(this).attr("data-selector");
		$(selector).prop("checked", $(this).prop("checked"));
		$(this).change(function(event) {
			$(selector).prop("checked", $(this).prop("checked"));
		});
	});

	$(".pager-link").each(function(index) {
		var form = $(this).attr("data-form");
		var pno = $(this).attr("data-pno");
		var current = $(this).attr("data-current");
		$("li", this).each(function(index) {
			var pageNo = $("a", this).attr("title");
			if (pageNo == current) {
				$("a", this).removeAttr("href");
			} else {
				$("a", this).click(function(event) {
					event.preventDefault();
					$("input[name='" + pno + "']", $(form)).val(pageNo - 1);
					$(form).submit();
				});
			}
		});
	});

	$(".page-size").each(function(index) {
		var form = $("select", this).attr("data-form");
		var psz = $("select", this).attr("data-psz");
		var current = $("input[name='" + psz + "']", $(form)).val();
		$("select", this).val(current);
		$("select", this).selectmenu({
			change : function(event) {
				event.preventDefault();
				$("input[name='" + psz + "']", $(form)).val($(this).val());
				$(form).submit();
			}
		});
	});

	$(".download-button").each(function(index) {
		var form = $("button", this).attr("data-form");
		$("button", this).button().click(function(event) {
			event.preventDefault();
			$(form).append("<input type='hidden' name='download' />");
			$(form).submit();
			$("input[name='download']", $(form)).remove();
		});
	});

	$(".refresh-button").each(function(index) {
		var form = $("button", this).attr("data-form");
		var selector = $("button", this).attr("data-selector");
		$(selector).each(function(index) {
			var name = $(this).attr("name");
			var val = $("input[name='" + name + "']", $(form)).val();
			if ($(this).attr("type") == "radio") {
				$(this).attr("checked", $(this).val() == val);
			} else {
				$(this).val(val);
			}
		});
		$("button", this).button().click(function(event) {
			event.preventDefault();
			$(selector).each(function(index) {
				var name = $(this).attr("name");
				$("input[name='" + name + "']", $(form)).val($(this).val());
			});
			$(form).submit();
		});
	});

	$(".page-sorter").each(function(index) {
		$("select", this).selectmenu();
	});

	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	$(document).ajaxSend(function(event, jqxhr, settings) {
		jqxhr.setRequestHeader(header, token);
	});

});
