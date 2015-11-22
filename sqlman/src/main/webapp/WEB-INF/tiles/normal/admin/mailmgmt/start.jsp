<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<h2 class="page-header">メール送信管理</h2>
<div class="form-horizontal">
	<div class="form-group">
		<div class="col-sm-8 col-sm-offset-2">
			<button id="sendBtn" class="btn btn-default btn-block" type="submit" data-action="<c:url value="/admin/mailmgmt/execute"/>">メール送信</button>
		</div>
	</div>
</div>
<div class="modal fade" id="sendResultModal" tabindex="-1" role="dialog" aria-labelledby="sendResultLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
				</button>
				<h4 class="modal-title" id="sendResultLabel">メール送信結果</h4>
			</div>
			<div class="modal-body">
				<div class="form-horizontal">
					<div class="form-group">
						<div class="col-sm-2 col-sm-offset-4">全件数</div>
						<div class="col-sm-2 text-right" id="totalCount"></div>
					</div>
					<div class="form-group">
						<div class="col-sm-2 col-sm-offset-4">OK件数</div>
						<div class="col-sm-2 text-right" id="okCount"></div>
					</div>
					<div class="form-group">
						<div class="col-sm-2 col-sm-offset-4">NG件数</div>
						<div class="col-sm-2 text-right" id="ngCount"></div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-primary" data-dismiss="modal">閉じる</button>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	$("#sendBtn").click(function(event) {
		event.preventDefault();
		$.ajax($(this).data("action"), {
			type : "POST",
			dataType : "json",
			success : function(data, textStatus, textStatus) {
				$("#sendResultModal #totalCount").text(data.totalCount);
				$("#sendResultModal #okCount").text(data.okCount);
				$("#sendResultModal #ngCount").text(data.ngCount);
				$("#sendResultModal").modal("show");
			}
		});
		return false;
	});
</script>
