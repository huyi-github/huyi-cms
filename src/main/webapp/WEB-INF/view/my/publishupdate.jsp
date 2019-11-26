<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="/resource/js/jquery-3.2.1.js"></script>
<script type="text/javascript">
$(function(){		
		$.post("/my/getchannel",function(arr){
			for ( var i in arr) {
				if('${article.channelId}'==arr[i].id){
					$.post("/my/getcategory",{channelId:'${article.channelId}'},function(arr){
						//回显栏目id
						//第二个框是改变事件，所以需要自己手动去回显第二个框，
						//在根据回显来的栏目id去查找下面的分类id，在拿回显来的分类id进行比较，相同选中
						for ( var i in arr) {
							if('${article.categoryId}'==arr[i].id){
								$("#category").append("<option  selected='selected'  value='"+arr[i].id+"'>"+arr[i].name+"</option>");
							}else{
								$("#category").append("<option  value='"+arr[i].id+"'>"+arr[i].name+"</option>");
							}
						}
					},"json")
					//如果相同默认选中
					$("#channel").append("<option  selected='selected'  value='"+arr[i].id+"'>"+arr[i].name+"</option>");
				}else{
					$("#channel").append("<option    value='"+arr[i].id+"'>"+arr[i].name+"</option>");
				}
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
			url:"/my/updateArticle",
			type:"post",
			data:formData,
			dataType:"json",
			//告诉jQuery不要处理发送的数据
			 processData:false,
			//  告诉jQuery不要去设置Content-Type请求头
			 contentType:false, 
			success:function(flag){
				if(flag){
					alert("修改成功");
					$("#center").load("/my/myArticles");
				}else{
					alert("修改失败,请重新登录后再试");
				}
			}
		})
	}
	
</script>
</head>
<body>
	<form id="form1">

		<div class="form-group">
			<input type="hidden" name="id" value="${article.id }">
			<label for="title"> 文章标题:</label> <input class="form-control"
				type="text" name="title"  id="title" value="${article.title }">
		</div>
		
		
		<!-- <div class="form-group">
			<label for="trems"> 文章关键字:</label> <input class="form-control"
				type="text" name="keywords"  id="trems">
		</div>
		<div class="form-group">
			<label for="trems"> 文章来源:</label> <input class="form-control"
				type="text" name="original" id="trems">
		</div> -->
		
		
		<div class="form-group">
			<label for="content"> 文章内容:</label>
			<jsp:include page="/resource/kindeditor/jsp/demo.jsp" />
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