<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>スタブ設定機能</title>
<link rel="stylesheet" media="screen" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css" />
<link rel="stylesheet" media="screen" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css">
<script type="text/javascript" src="https://code.jquery.com/jquery-1.11.0.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
<script type="text/javascript">
	$(function() {

		$("#registerBtn").click(function(event) {
			$.ajax($("#stubreposUri").val(), {
				method : "POST",
				data : {
					className : $("#className").val(),
					methodName : $("#methodName").val(),
					methodIndex : $("#methodIndex").val(),
					value : $("#value").val(),
					valueType : $("#valueType").val()
				},
				success : function(data, textStatus, jqXHR) {
					$("#result").val(data);
				},
				error : function(jqXHR, textStatus, errorThrown) {
					alert(errorThrown);
				}
			});
		});

		$("#className").blur(function(event) {
			var old = $(this).data("old");
			if (old == $(this).val()) {
				return;
			}
			$(this).data("old", $(this).val());
			$.ajax($("#stubreposUri").val() + "?bean", {
				method : "POST",
				data : {
					className : $("#className").val()
				},
				success : function(data, textStatus, jqXHR) {
					$("#beanName option.withValue").remove();
					for (var i = 0; i < data.length; i++) {
						var opt = $("<option/>").addClass("withValue");
						opt.attr("value", data[i]).attr("label", data[i]).text(data[i]);
						$("#beanName").append(opt);
					}
					$("#beanName option.withValue:first").attr("selected", "selected");
				},
				error : function(jqXHR, textStatus, errorThrown) {
					alert(errorThrown);
				}
			});
		});

		$("#methodName").blur(function(event) {
			var old = $(this).data("old");
			if (old == $(this).val()) {
				return;
			}
			$(this).data("old", $(this).val());
			$.ajax($("#stubreposUri").val() + "?method", {
				method : "POST",
				data : {
					className : $("#className").val(),
					methodName : $("#methodName").val()
				},
				success : function(data, textStatus, jqXHR) {
					$("#methodIndex option.withValue").remove();
					for (var i = 0; i < data.length; i++) {
						var opt = $("<option/>").addClass("withValue");
						opt.attr("value", i).attr("label", "(" + data[i] + ")").text("(" + data[i] + ")");
						$("#methodIndex").append(opt);
					}
					$("#methodIndex option.withValue:first").attr("selected", "selected");
				},
				error : function(jqXHR, textStatus, errorThrown) {
					alert(errorThrown);
				}
			});
		});

	});
</script>
</head>
<body role="document">
	<div class="navbar navbar-default" role="navigation">
		<div class="container">
			<div class="nav navbar-header">
				<div class="navbar-brand">スタブ設定機能</div>
			</div>
		</div>
	</div>
	<div class="container" role="main">
		<h2 class="page-header">スタブ設定機能</h2>
		<div role="form" class="form-horizontal">
			<div class="form-group">
				<label for="stubreposUri" class="col-sm-1 control-label">実行URI</label>
				<div class="col-sm-11">
					<input type="text" id="stubreposUri" name="stubreposUri" class="form-control" placeholder="スタブ設定機能のURIを指定してください"
						value="<c:url value="/stubrepos" />" />
				</div>
			</div>
			<div class="form-group">
				<label for="className" class="col-sm-1 control-label">クラス</label>
				<div class="col-sm-7">
					<input type="text" id="className" name="className" class="form-control" placeholder="BeanのFQCNを指定してください" />
				</div>
				<div class="col-sm-4">
					<select id="beanName" name="beanName" class="form-control">
						<option value="0" label="(参考)">(参考)</option>
					</select>
				</div>
			</div>
			<div class="form-group">
				<label for="methodName" class="col-sm-1 control-label">メソッド</label>
				<div class="col-sm-7">
					<input type="text" id="methodName" name="methodName" class="form-control" placeholder="メソッドの名称を指定してください" />
				</div>
				<div class="col-sm-4">
					<select id="methodIndex" name="methodIndex" class="form-control">
						<option value="0" label="メソッドを引数のパターンで指定">メソッドを引数のパターンで指定</option>
					</select>
				</div>
			</div>
			<div class="form-group">
				<label for="value" class="col-sm-1 control-label">返却値</label>
				<div class="col-sm-7">
					<textarea rows="3" cols="1" id="value" name="value" class="form-control" placeholder="返却値をJSON形式で指定"></textarea>
				</div>
				<div class="col-sm-4">
					<textarea rows="3" cols="1" id="valueType" name="valueType" class="form-control" placeholder="返却値の型を指定(非必須)"></textarea>
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-11 col-sm-offset-1">
					<button class="btn btn-default btn-block" type="button" id="registerBtn">登録</button>
				</div>
			</div>
		</div>
		<div class="form-horizontal" role="form">
			<div class="form-group">
				<label for="result" class="col-sm-1 control-label">登録結果</label>
				<div class="col-sm-11">
					<textarea rows="1" cols="1" id="result" name="result" class="form-control"></textarea>
				</div>
			</div>
		</div>
	</div>
	<div class="container" role="main">
		<address class="text-center">Copyright &copy;, 2015, agwlvssainokuni</address>
	</div>
</body>
</html>
