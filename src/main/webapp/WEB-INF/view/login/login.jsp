<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
<script type="text/javascript" src="/resource/js/jquery-3.2.1.js"></script>
<script type="text/javascript" src="/resource/js/jquery.validate.js"></script>
<script type="text/javascript">
</script>
<title>欢迎回来</title>
</head>
<body>
	<jsp:include page="/WEB-INF/view/common/top.jsp"></jsp:include>
	<div class="container">
		<div class="row passport_bg">
			<div class="col-md-6">
			<div>
				<br>
			</div>
			<br><br>
			<span  style="color: red;">${error }</span>
				<div class="card">
					<div >
						<form  id="form" action="/login/login" method="post">
							<div class="form-group">
							<label for="username">用户名：</label>
								<input  class="form-control"  value="${username }"  id="username" type="text" name="username">
							</div>
							<div class="form-group">
							<label for="password">密码：</label>
								<input  class="form-control"   id="password" type="password" name="password">
							</div>
							<div class="form-group">
                              <button type="submit" class="btn btn-success">登录</button>
                            </div>
							
						</form>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="passport_r">
					<h3>最新加入的用户：</h3>
					<p align="center">
						<img src="/resource/images/guest.jpg" alt="..."
							class="rounded-circle img-thumbnail" /> &nbsp;&nbsp;&nbsp;&nbsp;
						<img src="/resource/images/guest1.jpg" alt="..."
							class="rounded-circle img-thumbnail" />
					</p>
				</div>
			</div>
		</div>
	</div>
	<div>
		<br /> <br />
	</div>
	<jsp:include page="/WEB-INF/view/common/footer.jsp" />
</body>
</html>