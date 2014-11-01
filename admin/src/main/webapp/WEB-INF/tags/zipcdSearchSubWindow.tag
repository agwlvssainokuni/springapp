<%@ tag language="java" pageEncoding="UTF-8" body-content="empty"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<div id="zipcdSearchSubWindow">
	<table>
		<thead>
			<tr>
				<th></th>
				<th>Zip Code</th>
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
		$("#zipcdSearchSubWindow").dialog({
			autoOpen : false,
			closeOnEscape : true,
			modal : true,
			width : 800,
			title : "ZIPCODE SEARCH"
		});
	});

	function zipcdSearchSubWindow(zipcd, callback) {

		var onSuccess = function(data, textStatus, jqXHR) {
			if (data.code != 0) {
				alert(data.message);
				return;
			}
			if (data.result.length == 0) {
				alert("NOT FOUND");
			} else if (data.result.length == 1) {
				callback(data.result[0]);
			} else {
				var dialog = $("#zipcdSearchSubWindow");
				var tbody = $("table tbody", dialog);
				tbody.empty();
				function onClick(record) {
					return function(event) {
						event.preventDefault();
						callback(record);
						dialog.dialog("close");
					}
				}
				for (var i = 0; i < data.result.length; i++) {
					var record = data.result[i];
					tbody.append("<tr><td><button>SELECT</button></td><td>"
							+ record.zipcd + "</td><td>" + record.pref
							+ "</td><td>" + record.city + "</td><td>"
							+ record.addr + "</td></tr>");
					$("tr:last button", tbody).click(onClick(record));
				}
				dialog.dialog("open");
			}
		};

		$.ajax({
			url : "${zipcdUri}",
			type : "POST",
			data : {
				zipcd : zipcd
			},
			success : onSuccess
		});
	}
</script>
