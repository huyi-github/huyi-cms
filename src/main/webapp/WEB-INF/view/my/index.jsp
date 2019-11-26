<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script type="text/javascript" src="/resource/js/jquery-3.2.1.js"></script>
<script type="text/javascript">
	$(function(){
		
		//页面加载是让左侧菜单默认点击我的文章
		//$("#myArticle").click();
		$("#center").load("/my/myArticles");
		
		
		$(".channel").click(function(){
			
			var li = $("ul li");
			//先移除所有的list-group-item-info样式
			li.removeClass("list-group-item-info");
			//为左侧菜单添加动态点击样式
			$(this).parent().addClass("list-group-item-info");
			
			var url = $(this).attr("data");
			$("#center").load(url);
		})
	})
</script>
</head>
<body>
	<jsp:include page="/WEB-INF/view/common/top.jsp"></jsp:include>
	<br />
	<!-- 横幅 -->
	<div class="container">
		<div class="row">
			<div class="col-md-12 my_banner"></div>
		</div>
	</div>
	
	<!-- 主体内容区 -->
	<div class="container">
		<div class="row">
			<div class="col-md-3">
				<!-- 导航条 -->
				<jsp:include page="/WEB-INF/view/my/left.jsp"></jsp:include>
				<br />
			</div>
			<!-- 中间内容区 -->
			<div class="col-md-9" id="center">
				<div style="display: none">
					<jsp:include page="/resource/kindeditor/jsp/demo.jsp"></jsp:include>
				</div>
			</div>	
			
			
		</div>
	</div>
	<jsp:include page="/WEB-INF/view/common/footer.jsp" />
</body>
</html>