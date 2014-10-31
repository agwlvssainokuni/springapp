<%@ tag language="java" pageEncoding="UTF-8" body-content="empty"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<div id="ZipcdSearchSubWindow">
	<table>
		<thead>
			<tr>
				<th></th>
				<th>Prefecture</th>
				<th>City</th>
				<th>Address</th>
			</tr>
		</thead>
		<tbody></tbody>
	</table>
</div>
<c:url var="zipcdUri" value="/secure/common/zipcd" />
<script type="text/javascript">
	$(function() {
		$("#ZipcdSearchSubWindow").dialog({
			autoOpen : false,
			closeOnEscape : true,
			closeText : "CANCEL",
			modal : true,
			title : "SELECT ADDRESS"
		});
	});

	function zipcdSearchSubWindow(zipcd) {
		$.ajax({
			url : "${zipcdUri}",
			type : "POST",
			data : {
				zipcd : zipcd
			},
			success : function(data, textStatus, jqXHR) {
				alert(textStatus);
				$("#ZipcdSearchSubWindow").open();
			}
		});
	}
</script>
