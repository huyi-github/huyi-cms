<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>单个文章查询</title>
<!-- 视窗:支持缩放 -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<script type="text/javascript">
	//返回上一层
	function back(){
		$("#content-wrapper").load("/admin/articles");
	}
	
	//审核文章
	function check(status){
		var id = '${article.id}';
		
		//alert(id);
		$.post("/admin/updateArticle",{id:id,status:status},function(flag){
			if(flag){
				alert("操作成功");
			}else{
				alert("操作失败");
			}
		})
	}
</script>
</head>
<body>
	
	<div class="container" align="center">
		<div align="right">
			<button class="btn btn-success" onclick="check(1)">同意</button>
			<button class="btn btn-warning" onclick="check(-1)">驳回</button>
			<button class="btn btn-info" onclick="back()">返回</button>
		</div>
	
		<dl>
			<dt>${article.title }</dt>
			<dd>${article.user.username } &nbsp;
			<fmt:formatDate value="${article.created }" pattern="yyyy-MM-dd HH:mm:ss"/></dd>
			<hr>
			<dd>${article.content }</dd>
		</dl>
	</div>
	
</body>
</html>