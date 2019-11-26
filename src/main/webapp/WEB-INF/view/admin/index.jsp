<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
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

<script type="text/javascript" src="/resource/js/jquery-3.2.1.js"></script>

</head>
<body>
	<!-- top -->
	<div>
	<!-- 引入top的jsp -->
		<jsp:include page="top.jsp"/>
	</div>
	
	
	<div id="wrapper">
		<!-- 后台管理系统左侧导航栏 -->
		<jsp:include page="left.jsp"/>
		<!-- 中间内容显示区域-->
		<div id=content-wrapper>
			<img alt="" src="/resource/images/a910ad5a-4556-466f-a07c-195f8acbd13d.jpg" style="width: 1675px;height: 1050px;">
		
		</div>
	</div>


	<script type="text/javascript">
		//文档就绪函数
		$(function(){
			
			//页面加载是让左侧菜单默认点击我的文章
			//$("#myArticle").click();
			//$("#center").load("/my/myArticles");
			
			
			$(".nav-link").click(function(){
				
				var li = $("ul li");
				//先移除所有的list-group-item-info样式
				li.removeClass("list-group-item-info");
				//为左侧菜单添加动态点击样式
				$(this).parent().addClass("list-group-item-info");
				
				var url = $(this).attr("data");
				//alert(url);
				$("#content-wrapper").load(url);
			})
			
			/* //在左侧页面的用户管理设置点击事件
			$(".nav-link").click(function(){
				//通过当前点击的在left.jsp中的data获取用户展示列表
				var url = $(this).attr("data");
				//获取的用户展示列表加载到中间区域
				$("#content-wrapper").load(url);
			}) */
		})
	</script>
	
</body>
</html>