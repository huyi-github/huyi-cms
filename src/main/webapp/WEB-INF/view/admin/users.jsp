<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理员后台</title>
<!-- 视窗:支持缩放 -->
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- 引入css -->
<link rel="stylesheet" href="/resource/css/bootstrap.min.css">
<!-- 引入小图标  -->
<link rel="stylesheet" href="/resource/css/all.min.css">
<link rel="stylesheet" href="/resource/css/sb-admin.css">


<!-- <style type="text/css">
body{
 background:url("/resource/images/a910ad5a-4556-466f-a07c-195f8acbd13d.jpg");
		height:100%;
		width:100%;
		overflow: hidden;		
		background-size:cover;
}
</style> -->

<script type="text/javascript">
	$(function(){
		$("#lo").click(function(){
			$("#content-wrapper").load("/admin/users?username="+$("[name=username]").val());
		})
	})
	
	//修改当前用户的状态
	function updateLocked(id,own){
		//获取当前文本行的状态进行判断，如果是正常(0)就改变它的状态变为1
		var locked = $(own).text()=="正常"?1:0;
		//alert(locked);
		$.post("/admin/updateLocked",{id:id,locked:locked},function(flag){
			if(flag){
				alert("修改成功");
				//获取name值属性和修改后的状态，在修改后的状态是0的话就改成绿色
				$("[name=b1][value="+locked+"]").attr("class","btn btn-success");
				
				$("#content-wrapper").load("/admin/users");
			}else{
				alert("修改失败");
			}
		})
	}
	
	
</script>
</head>
<body>
	
	用户名：<input type="text" class="form-control-sm" placeholder="请输入用户名"  name="username" value="${username}">
	<button id="lo"  class="btn btn-primary">查询</button>
	
	<table class="table  table-bordered  table-hover">
		<tr>
			<td>序号</td>
			<td>用户名</td>
			<td>昵称</td>
			<td>生日</td>
			<td>状态</td>
			<td>角色</td>
			<td>注册日期</td>
			<td>修改日期</td>
		</tr>
		<c:forEach items="${users}" var="user" varStatus="index">
		<tr>
			<td>${index.index+1 }</td>
			<td>${user.username }</td>
			<td>${user.nickname }</td>
			<td>
				<fmt:formatDate value="${user.birthday }"/>
			</td>
			<td>
				<c:if test="${user.locked==0 }">
					<button name="b1" class="btn btn-success" onclick="updateLocked(${user.id},this)">正常</button>
				</c:if>
				<c:if test="${user.locked==1 }">
					<button name="b1" class="btn btn-danger" onclick="updateLocked(${user.id},this)">禁用</button>
				</c:if>
			</td>
			<td>${user.role==0?"普通用户":"管理员" }</td>
			<td>
				<fmt:formatDate value="${user.created }" pattern="yyyy-MM-dd HH:mm:ss"/>
			</td>
			<td>
				<fmt:formatDate value="${user.updated }" pattern="yyyy-MM-dd HH:mm:ss"/>
			</td>
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