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


<script type="text/javascript">
//查询审核状态的文章
	$(function(){
		$("#lo").click(function(){
			//alert($("[name=status]").val())
			$("#content-wrapper").load("/admin/articles?title="+$("[name=title]").val()+"&status="+$("[name=status]").val());
		})
	})
	
	//热门状态
	function updateHot(id,own){
		//获取当前文本行的状态进行判断，如果是正常(0)就改变它的状态变为1
		var hot = $(own).text()=="否"?1:0;
		//alert(locked);
		$.post("/admin/updateArticle",{id:id,hot:hot},function(flag){
			if(flag){
				//获取name值属性和修改后的状态，在修改后的状态是0的话就改成绿色
				$("[name=b1][value="+hot+"]").attr("class","btn btn-success");
				
				$("#content-wrapper").load("/admin/articles");
			}else{
				alert("修改失败");
			}
		})
	}
	
	//单个文章详情
	function detail(id){
		//alert(id);
		$("#content-wrapper").load("/admin/article?id="+id);
	}
</script>
</head>
<body>
	
	<div class="container">
		<div class="form-group form-inline">
			<label for="title">文章标题：</label>
			<input type="text" class="form-control" name="title" value="${article.title}">&nbsp;
			
			文章状态：<select name="status" class="form-control">
				<option value="0">待审</option>
				<option value="1">已审</option>
				<option value="-1">驳回</option>
			</select>
			&nbsp;
			<button id="lo"  class="btn btn-primary">查询</button>
		</div>
	</div>
	
	<table class="table  table-bordered  table-hover">
		<tr>
			<td>序号</td>
			<td>文章标题</td>
			<td>作者</td>
			<td>昵称</td>
			<td>发布时间</td>
			<td>文章状态</td>
			<td>点击量</td>
			<td>是否热门</td>
		</tr>
		<c:forEach items="${articles }" var="articles" varStatus="index">
		<tr>
			<td>${index.index+1 }</td>
			<td><a href="javascript:detail(${articles.id })">${articles.title }</a></td>
			<td>${articles.user.username }</td>
			<td>${articles.user.nickname }</td>
			<td>
				<fmt:formatDate value="${articles.created }" pattern="yyyy-MM-dd HH:mm:ss"/>
			</td>
			<td>${articles.status==0?"待审":articles.status==1?"已审":"驳回" }</td>
			<td>${articles.hits }</td>
			<td>
				<c:if test="${articles.hot==0 }">
					<button name="b1" class="btn btn-success" onclick="updateHot(${articles.id},this)">否</button>
				</c:if>
				<c:if test="${articles.hot==1 }">
					<button name="b1" class="btn btn-danger" onclick="updateHot(${articles.id},this)">是</button>
				</c:if>
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