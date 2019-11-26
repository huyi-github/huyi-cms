<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>友情链接添加</title>
<!-- 视窗:支持缩放 -->
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- 引入css -->
<link rel="stylesheet" href="/resource/css/bootstrap.min.css">
<!-- 引入小图标  -->
<link rel="stylesheet" href="/resource/css/all.min.css">
<link rel="stylesheet" href="/resource/css/sb-admin.css">
<link rel="stylesheet" href="/webapp/resource/js/jquery-3.2.1.js">

<script type="text/javascript">
	function add(){
		$("#content-wrapper").load("/admin/insert");
	}
</script>
</head>
<body>
	
	<table class="table  table-bordered  table-hover">
		<tr>
			<td>序号</td>
			<td>链接名称</td>
			<td>url</td>
			<td>创建时间</td>
			<td>操作
			<input type="button" class="btn btn-info" onclick="add()" value="增加链接">
			</td>
		</tr>
		<c:forEach items="${links}" var="links" varStatus="index">
		<tr>
			<td>${index.index+1 }</td>
			<td>${links.text }</td>
			<td>${links.url }</td>
			<td>
				<fmt:formatDate value="${links.created }"/>
			</td>
			<td>浏览</td>
		</tr>
		</c:forEach>
		
	</table>
	
	<div>${pages }</div>
	
	<script type="text/javascript">
		$(function(){
			$(".page-link").click(function(){
				var url = $(this).attr("data");
				$("#content-wrapper").load(url);
			})
		}) 		
	
	</script>

</body>
</html>