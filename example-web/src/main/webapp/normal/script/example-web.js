$(function() {

	/**
	 * Springの<f:input/>で入力NGがあった時に、Bootstrap標準スタイル Validation states (Forms) の
	 * .has-error を適用する。 <br>
	 * http://getbootstrap.com/css/#forms-control-validation
	 */
	$("input.has-error").wrap("<div class='has-error'></div>");

	/**
	 * JSP拡張タグ (pagerLink.tag) と連動し、Bootstrap標準スタイル Pagination
	 * に合わせてページネーションリンクを形成する。<br>
	 * http://getbootstrap.com/components/#pagination
	 */
	$(".pager-link").each(function(index) {
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
	 * Spring SecurityのCSRF検証対応。
	 */
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	$(document).ajaxSend(function(event, jqxhr, settings) {
		jqxhr.setRequestHeader(header, token);
	});

});
