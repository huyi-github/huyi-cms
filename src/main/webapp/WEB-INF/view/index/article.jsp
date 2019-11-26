<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${article.title }</title>
</head>
<script type="text/javascript" src="/resource/js/jquery-3.2.1.js"></script>
<script type="text/javascript">
	function aa(id,text){
		var a = $("#a").html();
		
		if(a=="☆"){
			$("#a").html("★");http:
			$.post("/my/addCollect",{'text':text,'url':"/select?id="+id},function(flag){
				if(flag){
					alert("收藏成功");
				}else{
					alert("收藏失败");
				}
			})
		}else{
			$("#a").html("☆");
		}
	}
</script>
<body>

	<div class="container">
		<dl>
			<!-- 标题 -->
			<dt>
				<h2 align="center">${article.title }</h2>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="javascript:aa('${article.id }','${article.title }')" id="a">☆</a>
			</dt>
			
			<!-- 内容 -->
			<dd><h4 align="right">${article.user.username }&nbsp;<fmt:formatDate value="${article.created }" pattern="yyyy-MM-dd HH:mm:ss"/></h4></dd>
			<dd>${article.content }</dd>
		</dl>
	</div>

</body>
</html>