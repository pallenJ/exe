<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js" 
integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
crossorigin="anonymous"></script> -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<head>
<meta charset="UTF-8">
<title>Upload with Ajax</title>
<style>
	.uploadResult{
		width: 100%;
		background-color: gray;
	}
	
	.uploadResult ul{
		display: flex;
		flex-flow: row;
		justify-content: center;
		align-items: center;
	}
	
	.uploadResult ul li{
		list-style: none;
		padding: 10px;
	}
	.uploadResult ul li img{
		width: 20px;
	}
	

</style>


</head>
<body>

	<h1>Upload with Ajax</h1>
	<div class = 'uploadDiv'>
		<input type="file" name="uploadFile" multiple>
		<button id="uploadBtn">Upload</button>
	</div>
	<div class="uploadResult">
		<ul></ul>
	</div>
</body>
<script type="text/javascript">
	
	$(document).ready(
		function () {
			
			var regex = new RegExp("(.*?)\.(exe|sh|zip|alz)$");
			var maxSize = 5242880; //5MB
			
			function checkExtension(fileName,fileSize) {
				if(fileSize>=maxSize){
					alert("파일 사이즈 초과");
					return false;
				}
				if(regex.test(fileName)){
					alert("해당 파일은 업로드 할 수 없습니다");
					return false;
				}
				return true;
			}
			
		$("#uploadBtn").click(function(e) {
			var formData = new FormData();
			
			var inputFile = $("input[name='uploadFile']");
			
			var files = inputFile[0].files;
			
			console.log(files);
			
			for (var i = 0; i < files.length; i++) {
				if(!checkExtension(files[i].name,files[i].size)){
					return false;
				}
				formData.append("uploadFile",files[i])
			}
			
			$.ajax({
				
				url:'/uploadAjaxAction',
				processData: false,
				contentType: false,
				data : formData,
				type: 'POST',
				dataType: 'json',
				success: function (result) {
					//alert("uploaded")
					console.log(result);
					showUploadedFile(result);
				}
			});//$.ajax
		});
		
		var uploadResult = $(".uploadResult ul");
		
		function showUploadedFile(uploadResultArr) {
			var str = "";
			$(uploadResultArr).each(function(i, obj) {
				
				var fileCallPath = encodeURIComponent(obj.uploadPath+"/"+obj.uuid+"_"+obj.fileName);
				
				var fileLink = fileCallPath.replace(new RegExp(/\\/g),"/");
				str +="<div><li><a href='/download?fileName="+obj.fileName+"'>"+obj.fileName+"</a>";
				str +="<span data-file=\'"+fileCallPath+"\' data-type='file'>x</span></li></div>"
			});
			alert(str);
			uploadResult.append(str)
		}
		
			$(".uploadResult").on("click","span", function(e) {
			var targetFile = $(this).data("file");
			var type       = $(this).data("type");
			//alert($(this).data("file"));
			console.log(targetFile);
			
			$.ajax({
				url:'/deleteFile',
				data : {fileName: targetFile, type: type},
				dataType:'text',
				type: 'POST',
				success: function (result) {
					alert(result);
				}
			});
			
			});
		
		});

</script>

</html>