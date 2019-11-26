<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- 校验提示样式 -->
<link rel="stylesheet" type="text/css" href="/webapp/resource/css/jquery/screen.css">
<script type="text/javascript" src="/resource/js/jquery-3.2.1.js"></script>
<script type="text/javascript">
$(function(){
	//追加下拉框，二级联动
	$.post("/my/getchannel",function(arr){
			for ( var i in arr) {
				$("#channel").append("<option  value='"+arr[i].id+"'>"+arr[i].name+"</option>");
			}
		},"json")
		$("#channel").change(function(){
			$("#category option:gt(0)").remove();
			$.post("/my/getcategory",{channelId:$("#channel").val()},function(arr){
				for ( var i in arr) {
					$("#category").append("<option  value='"+arr[i].id+"'>"+arr[i].name+"</option>");
				}
			},"json")
		})
})

//发布文章
function publish(){
		var formData = new FormData($("#form1")[0]); 
		//获取带html样式的文章内容，并封装到formDaya里面
		formData.set("content",editor1.html());
		//使用原生ajax可以解决一些二进制的问题
		 $.ajax({
			url:"/my/insertArticle",
			type:"post",
			data:formData,
			dataType:"json",
			//告诉jQuery不要处理发送的数据
			 processData:false,
			//  告诉jQuery不要去设置Content-Type请求头
			 contentType:false, 
			success:function(flag){
				if(flag){
					alert("发布成功");
					$("#center").load("/my/myArticles");
				}else{
					alert("发布失败,请重新登录后再试");
				}
			}
		}) 
	}
</script>
</head>
<body>
	<form id="form1">

		<div class="form-group">
			<input type="hidden" name="id" >
			<label for="title"> 文章标题:</label> <input class="form-control"
				type="text" name="title"  id="title">
		</div>
		<div class="form-group">
			<label for="trems"> 文章关键字:</label> <input class="form-control"
				type="text" name="keywords"  id="trems">
		</div>
		<div class="form-group">
			<label for="trems"> 文章来源:</label> <input class="form-control"
				type="text" name="original" id="trems">
		</div>
		<div class="form-group">
			<label for="content"> 文章内容:</label>
			<jsp:include page="/resource/kindeditor/jsp/demo.jsp" ></jsp:include>
		</div>
		<div class="form-group form-inline">
			栏目:<select class="form-control-sm" id="channel" name="channelId">
				<option>请选择</option>

			</select> &nbsp;&nbsp; 分类:
			<select class="form-control-sm" id="category" name="categoryId">
				<option>请选择</option>
			</select>
		</div>
		<div class="form-group">
			<label for="content"> 标题图片:</label> 
			<input type="file" id="content" name="file"   class="form-control">
				
		</div>
		<div class="form-group">
			<button class="btn btn-success" type="button" onclick="publish()">发布文章</button>
			<button class="btn btn-info">重置</button>
		</div>
	</form>
</body>
</html>