<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>友情链接</title>
<!-- 视窗:支持缩放 -->
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- 引入css -->
<link rel="stylesheet" href="/resource/css/bootstrap.min.css">
<!-- 引入小图标  -->
<link rel="stylesheet" href="/resource/css/all.min.css">
<link rel="stylesheet" href="/resource/css/sb-admin.css">

<script type="text/javascript" src="/resource/js/jquery-3.2.1.js"></script>
<script type="text/javascript">
	function save(){
		var param = $("#form1").serialize();
		//alert(param);
		$.post("/admin/insertlinks",param,function(flag){
			if(flag){
				alert("添加链接成功");
			}else{
				alert("添加链接失败");
			}
		},"json")
	}
</script>
</head>
<body>

	<div>
		<form id="form1">
		<div class="form-group">
			<label>链接名称:</label>
			<input type="text" name="text" class="form-control">
		</div>
		<div class="form-group">
			<label>链接url:</label>
			<input type="text" name="url" class="form-control">
		</div>
		
		<div class="form-group">
			<button class="btn btn-success" type="button" onclick="save()">保存</button>
			<button class="btn btn-info" type="reset">重置</button>
		</div>
		</form>
	</div>
	
</body>
</html>