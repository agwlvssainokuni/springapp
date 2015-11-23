$(function() {

	/**
	 * Springの<f:input/>で入力NGがあった時に、Bootstrap標準スタイル Validation states (Forms) の
	 * .has-error を適用する。 <br>
	 * http://getbootstrap.com/css/#forms-control-validation
	 */
	$("input.has-error").closest("div").addClass("has-error");
	$("textarea.has-error").closest("div").addClass("has-error");
	$("label.has-error").closest("div").addClass("has-error");

	/**
	 * JSP拡張タグ (pagerLink.tag) と連動し、Bootstrap標準スタイル Pagination
	 * に合わせてページネーションリンクを形成する。<br>
	 * http://getbootstrap.com/components/#pagination
	 */
	$(".ex-pager-link").each(function(index) {
		var form = $(this).attr("data-form");
		var pno = $(this).attr("data-pno");
		var current = $(this).attr("data-current");
		$("li", this).each(function() {
			var pageNo = $("a", this).attr("title");
			if (pageNo == current) {
				if ($(this).hasClass("edge")) {
					$(this).addClass("disabled");
				} else {
					$(this).addClass("active");
				}
				$("a", this).removeAttr("href");
			} else {
				$("a", this).click(function(event) {
					event.preventDefault();
					$("input[name='" + pno + "']", $(form)).val(pageNo - 1);
					$(form).submit();
				});
			}
		})
	});

	/**
	 * Bootstrap標準スタイル Tabs をインプレースで開閉させる。<br>
	 * http://getbootstrap.com/components/#nav-tabs
	 * http://getbootstrap.com/javascript/#tabs
	 */
	$("ul.togglable-tabs>li>a").click(function(event) {
		event.preventDefault();
		$(this).tab("show");
	});
	$("ul.togglable-tabs>li:first>a").each(function(index) {
		$(this).tab("show");
	});

	$(".ex-page-size").each(function(index) {
		var form = $("select", this).data("form");
		var psz = $("select", this).data("psz");
		var current = $("input[name='" + psz + "']", $(form)).val();
		$("select", this).val(current);
		$("select", this).change(function(event) {
			event.preventDefault();
			$("input[name='" + psz + "']", $(form)).val($(this).val());
			$(form).submit();
		});
	});

	$(".ex-submit-button").click(function(event) {
		event.preventDefault();
		var form = $(this).data("form");
		$(form).submit();
	});

	$(".ex-refresh-button").each(function(index) {
		var form = $("button", this).data("form");
		var selector = $("button", this).data("selector");
		$(selector).each(function(index) {
			var name = $(this).attr("name");
			var val = $("input[name='" + name + "']", $(form)).val();
			if ($(this).attr("type") == "radio") {
				$(this).prop("checked", $(this).val() == val);
			} else {
				$(this).val(val);
			}
		});
		$("button", this).click(function(event) {
			event.preventDefault();
			$(selector).each(function(index) {
				var name = $(this).attr("name");
				var val = $(this).val();
				if ($(this).attr("type") == "radio") {
					if ($(this).prop("checked")) {
						$("input[name='" + name + "']", $(form)).val(val);
					}
				} else {
					$("input[name='" + name + "']", $(form)).val(val);
				}
			});
			$(form).submit();
		});
	});

	$(".ex-download-button").each(function(index) {
		var form = $("button", this).data("form");
		$("button", this).click(function(event) {
			event.preventDefault();
			$(form).append("<input type='hidden' name='download' />");
			$(form).submit();
			$("input[name='download']", $(form)).remove();
		});
	});

	/**
	 * Spring SecurityのCSRF検証対応。
	 */
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	$(document).ajaxSend(function(event, jqxhr, settings) {
		jqxhr.setRequestHeader(header, token);
	});

});
