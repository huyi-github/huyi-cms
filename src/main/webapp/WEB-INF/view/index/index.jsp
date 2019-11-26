<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="/resource/js/jquery-3.2.1.js"></script>
<script type="text/javascript">
$(function(){
	$(".page-link").click(function(){
		var url = $(this).attr("data");
		location.href = url;
	})
})
</script>
<title>cms系统</title>
</head>
<body>

	<div class="container">
		<!-- top -->
		<div>
			<jsp:include page="/WEB-INF/view/common/top.jsp"></jsp:include>
		</div>
		
		<!-- 主题区 -->
		<div class="row">
			<!-- 左侧栏目 -->
			<div class="col-md-2">
					<ul class="list-group">
					  <li class="list-group-item cms-group-item-action text-center">
					  <a href="/?hot=1" class="channel">热门</a></li>
					</ul>
				<c:forEach items="${channels}" var="channel">
					<ul class="list-group">
					  <li class="list-group-item cms-group-item-action text-center">
					  <a href="?channelId=${channel.id}" 
					  class="channel">${channel.name }</a></li>
					</ul>
					
				</c:forEach>
			</div>
			
			
			<!-- 文章标题 -->
			<div class="col-md-7 split min_h_500">
				<!-- 如果分类为空就显示热点 -->
				<c:if test="${article.channelId==null}">
				
				<!-- 轮播图 -->
					<div>
						<div id="carouselExampleCaptions" class="carousel slide" data-ride="carousel">
						  <ol class="carousel-indicators">
						    <li data-target="#carouselExampleCaptions" data-slide-to="0" class="active"></li>
						    <li data-target="#carouselExampleCaptions" data-slide-to="1"></li>
						    <li data-target="#carouselExampleCaptions" data-slide-to="2"></li>
						  </ol>
						  <div class="carousel-inner">
						    <div class="carousel-item active">
						      <img src="/pic/1.jpg" class="d-block w-100" alt="...">
						      <div class="carousel-caption d-none d-md-block">
						        <h5>图片一</h5>
						      </div>
						    </div>
						    <div class="carousel-item">
						      <img src="/pic/2.jpg" class="d-block w-100" alt="...">
						      <div class="carousel-caption d-none d-md-block">
						        <h5>图片二</h5>
						      </div>
						    </div>
						    <div class="carousel-item">
						     <img src="/pic/3.jpg" class="d-block w-100" alt="...">
						      <div class="carousel-caption d-none d-md-block">
						        <h5>图片三</h5>
						      </div>
						    </div>
						  </div>
						  <a class="carousel-control-prev" href="#carouselExampleCaptions" role="button" data-slide="prev">
						    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
						    <span class="sr-only">Previous</span>
						  </a>
						  <a class="carousel-control-next" href="#carouselExampleCaptions" role="button" data-slide="next">
						    <span class="carousel-control-next-icon" aria-hidden="true"></span>
						    <span class="sr-only">Next</span>
						  </a>
						</div>
					
					</div>
					<br>
					
					<!-- 热点文章 -->
					<div>
					<c:forEach items="${hotArticles }" var="a">
							<div class="media">
					 			  <img src="/pic/${a.picture }" style="height: 85px;height: 80px;"  class="mr-3" alt="...">
								  <div class="media-body">
								   	 <h5 class="mt-0"  style="height:80px"><a href="/select?id=${a.id }" target="blank">${a.title }</a></h5>
								   	 <h5 class="mt-0">${a.user.username }&nbsp;<fmt:formatDate value="${a.created }" pattern="yyyy-MM-dd HH:mm:ss"/></h5>
								    
								  </div>
							</div>
							<hr>
						</c:forEach>
						${pages }
					</div>
				</c:if>
				<!-- 文章 -->
				<!-- 如果分类下不为空就显示分类下的文章 -->
				<c:if test="${article.channelId!=null}">
					<!-- 显示栏目下的分类 -->
					<div>
					
						<ul class="nav">
						 <li class="nav-item">
							    <a class="nav-link" href="?channelId=${article.channelId }">全部</a>
						</li>
						<c:forEach items="${categorys}" var="categorys">
							  <li class="nav-item">
							    <a class="nav-link" href="?channelId=${categorys.channelId }&categoryId=${categorys.id}">${categorys.name}</a>
							  </li>
						</c:forEach>
						</ul>
					</div>
				
				<div>
					<div>
						<hr>
						<c:forEach items="${articles }" var="a">
							<div class="media">
					 			  <img src="/pic/${a.picture }" style="height: 70px;height: 70px;"  class="mr-3" alt="...">
								  <div class="media-body">
								   	 <h5 class="mt-0"><a href="/select?id=${a.id }" target="blank">${a.title }</a></h5>
								   	 <h5 class="mt-0">${a.user.username }&nbsp;<fmt:formatDate value="${a.created }" pattern="yyyy-MM-dd HH:mm:ss"/></h5>
								    
								  </div>
							</div>
							<hr>
						</c:forEach>
					${pages }
					</div>
				</div>
				</c:if>
			</div>
			
			<!-- 右侧边栏 -->
			<div class="col-md-3 split min_h_500">
				
				<div>
					<!-- 24小时热文 -->
					<div class="card" style="width: 18rem;">
						<div class="card-header"><h4>24小时热文</h5></div>
					    <div class="card-body">
					    <c:forEach items="${article24 }" var="a">
					   		<div class="media">
							  <img src="/pic/${a.picture}" class="mr-3" alt="..." weight="60px" height="60px">
							  <div class="media-body">
							    	 <h6 class="mt-0"><a href="/select?id=${a.id }" target="blank">${a.title }</a></h6>
							  </div>
							</div>
							<br>
					    </c:forEach>
					    
					    </div>
					</div>
					
					
					<div>
						<!-- 最新文章 -->
					<div class="card" style="width: 18rem;">
						<div class="card-header"><h4>最新文章</h5></div>
					    <div class="card-body">
					    <c:forEach items="${articlehot }" var="a">
					   		<div class="media">
							  <img src="/pic/${a.picture}" class="mr-3" alt="..." weight="60px" height="60px">
							  <div class="media-body">
							    	 <h6 class="mt-0"><a href="/select?id=${a.id }" target="blank">${a.title }</a></h6>
							  </div>
							</div>
							<br>
					    </c:forEach>
					    
					    </div>
					</div>
					</div>
					
				</div>
			
			 </div>
		</div>
	</div>
	
						
					<!-- 友情链接 -->
	<div class="row">
		<div class="col-12">
			<c:forEach items="${links }" var="l">
				<a href="${l.url }" target="_blank">${l.text }</a>&nbsp;
			</c:forEach>
		</div>
	</div>
	
	<jsp:include page="/WEB-INF/view/common/footer.jsp"/>
	
</body>
</html>