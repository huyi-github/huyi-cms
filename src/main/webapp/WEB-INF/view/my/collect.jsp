<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>我的文章</title>
<script type="text/javascript" src="/resource/js2/jquery-3.2.1.js"></script>
<link rel="stylesheet"  href="/resource/css/bootstrap.css">
<script type="text/javascript" src="/resource/js/bootstrap.js"></script>
<script type="text/javascript">
	function deletea(id){
		
		$.post("/my/deleteCollect",{'id':id},function(flag){
			if(flag){
				alert("删除成功");
				$("#center").load("/my/goCollect");
			}
		})
	}
	$(function(){
		$(".page-link").click(function(){
			var url = $(this).attr("data");
			$("#center").load(url);
		})
		
	})
</script>
</head>
<body>
	<c:forEach items="${collects }" var="c">
		<hr>
		<div>
			<h5><a href="${c.url }">${c.text }</a></h5>
			<br><br>
			${s.username }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<fmt:formatDate value="${c.created }"  pattern="yyyy-MM-dd HH:mm:ss"/>
			<a style="float:right;" href="javascript:deletea(${c.id })">删除</a>
		</div>
	</c:forEach>
	${pages }
</body>
</html>