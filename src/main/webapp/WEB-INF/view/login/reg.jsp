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
	$(function(){
		$("#form").validate({
			//定义规则
			rules:{
				username:{
					required:true,//用户名不能为空
					rangelength:[2,10]//长度必须在2-10位
				},
				password:{
					required:true,//密码不能为空
					rangelength:[3,15]//长度必须在3-15位
				},
				regpassword:{
					equalTo:"#password"//输入值必须和 #password 相同
				}
			},
			//提示信息
			messages:{
				username:{
					required:"用户名不能为空",
					rangelength:"长度必须在2-10位"
				},
				password:{
					required:"密码不能为空",
					rangelength:"长度必须在6-15位"
				},
				regpassword:{
					equalTo:"两次密码必须一致"
				}
			}
		})
	})
</script>
<title>欢迎注册</title>
</head>
	<jsp:include page="/WEB-INF/view/common/top.jsp"></jsp:include>
	<div class="container">
		<div class="row passport_bg">
			<div class="col-md-6">
			<div>
				<br>
			</div>
				<div class="card">
					<div >
						<form  id="form" action="/login/reg" method="post">
							<div class="form-group">
							<label for="username">用户名：</label>
								<input id="username"  class="form-control"   type="text" name="username">
							</div>
							<div class="form-group">
							<label for="password">密码：</label>
								<input  id="password"   class="form-control" type="password" name="password">
							</div>
							<div class="form-group">
							<label for="regpassword">请确认密码：</label>
								<input id="regpassword"   class="form-control" type="password" name="regpassword">
							</div>
							<div class="form-group form-check">
							<label for="gender">性别:</label>
								<div class="form-check-inline">
									<input   class="form-check-input" type="radio"  value="1"  checked="checked" name="gender">男
									<input   class="form-check-input" type="radio" value="2"  name="gender">女
								</div>
							</div>
							<div class="form-group">
                              <button type="submit" class="btn btn-success">注册</button>
                              <button type="reset" class="btn btn-warning">重置</button>
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