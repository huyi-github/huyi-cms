<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="/resource/js/jquery-3.2.1.js"></script>
</head>
<body>
	
	<br>
	<form  id="form1">
		<div class="group-list">
		<button class="btn btn-primary" type="button" onclick="addCard()">添加图片</button>
		<button class="btn btn-success" type="button" onclick="insertPic()">发布图片集</button>
	</div>
	<br>
	<div class="form-group">
		<label for="exampleInputEmail1">请输入文章标题</label> <input type="text" name="title"
			class="form-control" id="exampleInputEmail1" placeholder="请输入图片集标题">
	</div>
	<div id="my">
		<div id="card" style="float: left;">
			<div class="card bg-light mb-3"
				style="max-width: 18rem; max-height: 22rem;">
				<div class="card-header">
					<input type="file" name="files">
				</div>
				<div class="card-body">
					<div class="media">
						<div class="media-body">
							<textarea name="cont" rows="10px;" cols="20px;"></textarea>
						</div>
					</div>
					<br>
				</div>
			</div>
		</div>
	</div>
	</form>
	
	
	<script type="text/javascript">
		function addCard(){
			$("#my").append($("#card").html());
		}
	</script>
</body>
</html>