<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>JQuery ajaxfileupload文件上传</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link type="text/css" rel="stylesheet" href="css/ajaxfileupload.css" >
	<script type="text/javascript" src="js/jquery/jquery-1.9.1.js"></script>
	<script type="text/javascript" src="js/jquery/ajaxfileupload.js"></script>
	
	<script type="text/javascript">

	//定时器对象
	var uploadProcessTimer = null;

	//获取文件上传进度
	function getFileUploadProcess() {
		$.get('/upload/getFileProcessServlet', function(data) {
			$('#fileUploadProcess').html(data);
		});
	}

	function ajaxFileUpload()
	{
		//设置加载图标的显示
		$('#loading').show();
		uploadProcessTimer = window.setInterval(getFileUploadProcess, 20);

		$.ajaxFileUpload
		({
			url:'/upload/ajaxUploadServlet',
			secureuri:false,
			fileElementId:'fileToUpload',
			dataType: 'json',
			data:{name: $('#name').val()},
			success: function (data, status)
			{
				//清除定时器
				if(uploadProcessTimer) {
					window.clearInterval(uploadProcessTimer);
				}
				$('#loading').hide();
				var message = data['message'];
				var code = data['code'];
				if(code != 200) {
					$('#fileUploadProcess').html('0%');
				}
				if(message)
				{
					alert(data.message);
				}
			},
			error: function (data, status, e)
			{
				//清除定时器
				if(uploadProcessTimer) {
					window.clearInterval(uploadProcessTimer);
				}
				$('#loading').hide();
				//这里处理的是网络异常，返回参数解析异常，DOM操作异常
				alert("上传发生异常");
			}
		})

		return false;
	}
	</script>
</head>
<body>
<h2>JQuery ajaxfileupload文件上传</h2>
<img id="loading" src="images/loading.gif" style="display:none;">
用户信息：  <br/>
	姓名：<input id="name" name="name" type="text"> <br/>
	附件：<input id="fileToUpload", name="file1" type="file" class="input"> <br/>
	<br><br>
	<input type="button" onclick="return ajaxFileUpload();" value="上传"><br/>
上传进度：<label id="fileUploadProcess"></label>
</body>
</html>
