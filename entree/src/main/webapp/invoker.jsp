<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true" session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>汎用起動機能</title>
<link rel="stylesheet" media="screen"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css" />
<link rel="stylesheet" media="screen"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css">
<script type="text/javascript"
	src="https://code.jquery.com/jquery-1.11.0.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
<script type="text/javascript">
	$(function() {

		$("#invokeBtn").click(function(event) {
			var args = [];
			$("textarea[name='args']").each(function(index) {
				args.push($(this).val());
			});
			var argTypes = [];
			$("input[name='argTypes']").each(function(index) {
				argTypes.push($(this).val());
			});
			$.ajax($("#invokerUri").val(), {
				method : "POST",
				data : {
					beanName : $("#beanName").val(),
					className : $("#className").val(),
					methodName : $("#methodName").val(),
					methodIndex : $("#methodIndex").val(),
					args : args,
					argTypes : argTypes
				},
				traditional : true,
				success : function(data, textStatus, jqXHR) {
					$("#result").val(data);
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
				<div class="navbar-brand">汎用実行機能</div>
			</div>
		</div>
	</div>
	<div class="container" role="main">
		<h2 class="page-header">汎用実行機能</h2>
		<div role="form" class="form-horizontal">
			<div class="form-group">
				<label for="invokerUri" class="col-sm-2 control-label">実行URI</label>
				<div class="col-sm-10">
					<input type="text" id="invokerUri" name="invokerUri"
						class="form-control" placeholder="汎用実行機能のURIを指定してください"
						value="<c:url value="/invoker" />" />
				</div>
			</div>
			<div class="form-group">
				<label for="className" class="col-sm-2 control-label">クラス名称</label>
				<div class="col-sm-7">
					<input type="text" id="className" name="className"
						class="form-control" placeholder="呼び出すBeanのFQCNを指定してください" />
				</div>
				<div class="col-sm-3">
					<input type="text" id="beanName" name="beanName"
						class="form-control" placeholder="Bean名称(非必須)" />
				</div>
			</div>
			<div class="form-group">
				<label for="methodName" class="col-sm-2 control-label">メソッド名称</label>
				<div class="col-sm-7">
					<input type="text" id="methodName" name="methodName"
						class="form-control" placeholder="呼び出すメソッドの名称を指定してください" />
				</div>
				<div class="col-sm-3">
					<input type="text" id="methodIndex" name="methodIndex"
						class="form-control" placeholder="同名のメソッドがある場合に指定" />
				</div>
			</div>
			<div class="form-group">
				<label for="args1" class="col-sm-2 control-label">引数</label>
				<div class="col-sm-7">
					<textarea rows="1" cols="1" id="arg1" name="args"
						class="form-control"
						placeholder="ConversionServiceで変換、または、JSONからDTOにマップ"></textarea>
				</div>
				<div class="col-sm-3">
					<input type="text" id="argType1" name="argTypes"
						class="form-control" placeholder="引数のFQCN(非必須)">
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-7 col-sm-offset-2">
					<textarea rows="1" cols="1" id="arg2" name="args"
						class="form-control"
						placeholder="ConversionServiceで変換、または、JSONからDTOにマップ"></textarea>
				</div>
				<div class="col-sm-3">
					<input type="text" id="argType2" name="argTypes"
						class="form-control" placeholder="引数のFQCN(非必須)">
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-7 col-sm-offset-2">
					<textarea rows="1" cols="1" id="arg3" name="args"
						class="form-control"
						placeholder="ConversionServiceで変換、または、JSONからDTOにマップ"></textarea>
				</div>
				<div class="col-sm-3">
					<input type="text" id="argType3" name="argTypes"
						class="form-control" placeholder="引数のFQCN(非必須)">
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-7 col-sm-offset-2">
					<textarea rows="1" cols="1" id="arg4" name="args"
						class="form-control"
						placeholder="ConversionServiceで変換、または、JSONからDTOにマップ"></textarea>
				</div>
				<div class="col-sm-3">
					<input type="text" id="argType4" name="argTypes"
						class="form-control" placeholder="引数のFQCN(非必須)">
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-7 col-sm-offset-2">
					<textarea rows="1" cols="1" id="arg5" name="args"
						class="form-control"
						placeholder="ConversionServiceで変換、または、JSONからDTOにマップ"></textarea>
				</div>
				<div class="col-sm-3">
					<input type="text" id="argType5" name="argTypes"
						class="form-control" placeholder="引数のFQCN(非必須)">
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-10 col-sm-offset-2">
					<button class="btn btn-default btn-block" type="button"
						id="invokeBtn">実行</button>
				</div>
			</div>
		</div>
		<div class="form-horizontal" role="form">
			<div class="form-group">
				<label for="result" class="col-sm-2 control-label">実行結果</label>
				<div class="col-sm-10 ">
					<textarea rows="5" cols="1" id="result" name="result"
						class="form-control"></textarea>
				</div>
			</div>
		</div>
	</div>
	<div class="container" role="main">
		<address class="text-center">Copyright &copy;, 2015,
			agwlvssainokuni</address>
	</div>
</body>
</html>
