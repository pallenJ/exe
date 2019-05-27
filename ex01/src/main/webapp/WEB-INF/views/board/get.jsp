<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt"%>    
<%@include file="../include/header.jsp"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<script type="text/javascript" src="/resources/js/reply.js"></script>
<script type="text/javascript">
$(document).ready(function () {
	console.log("=====================");
	console.log("JS TEST");
	
	var bnoValue = '<c:out value="${board.bno}"/>';
	//add test
	/* replyService.add(
		{reply:"JS Test", replyer:"tester",bno:bnoValue},
			function (result) {
				alert(result);
			}
			
	);
	 */
	 
	 //list test
	/*  replyService.getList({bno:bnoValue,page:1}, function(list) {
	 	for (var i = 0,len = list.length||0; i < len; i++) {
			console.log();
			
		}
	 });
	  */
	  
	  //remove test
	  /* replyService.remove(23, function(count) {
	  	console.log(count);
	  	
	  	if(count==="success"){alert("removed");}
	  }, function(error) {
	  	alert("ERROR")
	  }) */
	  
	  //update test
	/*   replyService.update({
	  	rno : 22,
	  	bno : 293,
	  	reply : "modify test2"
	  }, function(result) {
	  	alert("수정")
	  }) 
	   */
	   //get test
	  /* replyService.get(10, function(data) {
		console.log(data)
	}); */
});
</script>

<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">Board Read</h1>
			</div>
			<!-- /.col-lg-12 -->
		</div>
<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">Board Read Page
				<button id = "regBtn" type="button" class="btn btn-xs pull-right">Register New Board</button>
			</div>
			<!-- /.panel-heading -->
			<div class="panel-body">
				<div class="form-group">
				<label>Bno</label>
				<input class = "form-control" name="bno" value='<c:out value="${board.bno}"></c:out>' readonly="readonly">
				</div>
				
				<div class="form-group">
				<label>Title</label>
				<input class = "form-control" name="title" value='<c:out value="${board.title}"></c:out>' readonly="readonly">
				</div>
				<div class="form-group">
				
				<label>TextArea</label>
				<textarea class = "form-control" name="bno" rows="3" readonly="readonly">
					<c:out value="${board.content}"></c:out>
				</textarea>
				</div>
				
				<div class="form-group">
				<label>Writer</label>
				<input class = "form-control" name="writer" value='<c:out value="${board.writer}"></c:out>' readonly="readonly">
				</div>
				
				<button data-oper="modify" class="btn btn-default">Modify</button>				
				<button data-oper="list" class="btn btn-info">List</button>				
				<form id = "operForm" action="/board/modify" method="get">
					<input type="hidden" id = "bno" name ="bno" value ='<c:out value="${board.bno}"></c:out>'>
					<input type="hidden" id = "pageNum" name ="pageNum" value ='<c:out value="${param.pageNum}"></c:out>'>
					<input type="hidden" id = "amount" name ="amount" value ='<c:out value="${param.amount}"></c:out>'>
					<input type="hidden" id = "type" name ="type" value ='<c:out value="${param.type}"></c:out>'>
					<input type="hidden" id = "keyword" name ="keyword" value ='<c:out value="${param.keyword}"></c:out>'>
				</form>
				
			</div>
			<!-- /.panel-body -->
		</div>
		<!-- /.panel -->
	</div>
	<!-- /.col-lg-12 -->
</div>

	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
						aria-labelledby="myModalLabel" aria-hidden="true">

						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-hidden="true">&times;</button>
									<h4 class="modal-title" id="myModalLabel">REPLY MODAL</h4>

								</div>

								<div class="modal-body">
									
									<div class="form-group">
										<label>Reply</label>
										<input class="form-control" name = 'reply' value = 'new reply' required="required">
									</div>
									<div class="form-group">
										<label>Replyer</label>
										<input class="form-control" name = 'replyer' value = 'replyer' required="required">
									</div>
									<div class="form-group">
										<label>Reply Date</label>
										<input class="form-control" name = 'replyDate' value = ''>
									</div>
									
									
								</div>
								
								<div class="modal-footer">
									
									<button type="button" id='modalModBtn' class="btn btn-warning">Modify</button>
									<button type="button" id='modalRemoveBtn' class="btn btn-danger">Remove</button>
									<button type="button" id='modalRegisterBtn' class="btn btn-primary">Register</button>
									<button type="button" id='modalCloseBtn' class="btn btn-default">Close</button>
								</div>
								
							</div>
							<!--/. modal-content -->
						</div>
						<!--/. modal-dialog -->
					</div>
					<!--/. modal -->



<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">
				<i class = "fa fa-comment fa-fw"></i>Reply
				<button id = 'addReplyBtn' class = 'btn btn-primary btn-xs pull-right'>New Reply</button>
			</div>
			<!-- /.panel-heading -->
			<div class="panel-body">
				<ul class="chat">
					
				</ul>
				<!-- .end ul -->
				
			</div>
			<!-- /.panel-body -->
			<div class="panel-footer"></div>
		</div>
		<!-- /.panel -->
	</div>
	<!-- /.col-lg-12 -->
</div>

				
				<!-- 페이징&서치 관련 처리 -->
				<script type="text/javascript">
					$(document).ready(function () {
						
						
						var openForm = $("#operForm")
						$("button[data-oper='modify']").click(function(e) {
							openForm.attr("action", "/board/modify").submit();
						});					
						$("button[data-oper='list']").click(function(e) {
							openForm.find("#bno").remove();
							openForm.attr("action", "/board/list");
							openForm.submit();
						});					
					});
					
				
				</script>
				
				<!-- 댓글처리 -->
				<script type="text/javascript">
					$(document).ready(function () {
						var bnoValue =	'<c:out value="${board.bno}"></c:out>';
						var replyUL  = $(".chat");
						//var pageNum = $("#pageNum").val();
						showList(1);
						
						function showList(page) {
							replyService.getList(
							{bno:bnoValue,page: page||1}		
							, function(replyCnt,list) {
								/* if(page == -1){
									pageNum = Math.ceil(replyCnt/10.0);
									alert("pnum:"+pageNum);
									//showList(pageNum);
									return;
								} */
								var str = "";
								if(list == null || list.length == 0){
									replyUL.html(""); return;
								}
								
								for (var i = 0,len= list.length||0; i < len; i++) {
								str += "<li class='left clearfix' data-rno='"+list[i].rno+"'>";	
								str += "<div><div class='header'><strong class = 'primary-font'>"+list[i].replyer+"</strong>";
								str += "<small class='pull-right text-muted'>"+replyService.displayTime(list[i].replyDate)+"</small></div>";
								str += "<p>"+list[i].reply+"</p></div></li>";
								}	
								
								replyUL.html(str);
								showReplyPage(replyCnt);
							})
						}//end showList
						
					
					var modal = $(".modal");
					var modalInputReply = modal.find("input[name='reply']");
					var modalInputReplyer = modal.find("input[name='replyer']");
					var modalInputReplyDate = modal.find("input[name='replyDate']");
					
					var modalModeBtn     = $("#modalModBtn");
					var modalRemoveBtn   = $("#modalRemoveBtn");
					var modalRegisterBtn = $("#modalRegisterBtn");
					/* add */
					$("#addReplyBtn").click(function() {
						modal.find("input").val("");
						modalInputReplyDate.closest("div").hide();
						modal.find("button[id !='modalCloseBtn']").hide();
						
						modalRegisterBtn.show();
						$(".modal").modal("show");
					});
					
					/* remove */
					$("#modalRegisterBtn").click(function() {
						var reply = {
								reply: modalInputReply.val(),
								replyer: modalInputReplyer.val(),
								bno:bnoValue
						}
						
						replyService.add(reply, function(result) {
								alert(result);
								
								modal.find("input").val("");
								$(".modal").modal("hide");
								//showList(1);
								showList(pageNum);
						});
						
					});
					
					modalModeBtn.click(function(e) {
						var reply = {rno:modal.data("rno"), reply: modalInputReplyer.val()};
						replyService.update(reply, function(result) {
							alert(result);
							modal.modal("hide");
							showList(1);
						});
					});
					
					modalRemoveBtn.click(function(e) {
						var rno = modal.data("rno");
						replyService.remove(rno, function(result) {
							
							alert(result);
							modal.modal("hide");
							showList(1);
						});
					
					});
					
					
					/* chat */
					$(".chat").on("click","li", function() {
						var rno = $(this).data("rno");
						replyService.get(rno,function(reply){
							
							modalInputReply.val(reply.reply);
							modalInputReplyer.val(reply.replyer);
							modalInputReplyDate.val(replyService.displayTime(reply.replyDate)).attr("readonly", "readonly");
							modal.data("rno", reply.rno);
							
							modal.find("button[id !='modalCloseBtn']").hide();
							modalModeBtn.show();
							modalRemoveBtn.show();
							
							$(".modal").modal("show");
							
						});
					})
					
					/* close */
					$("#modalCloseBtn").click(function() {
						$(".modal").modal("hide");
					})
					
					var pageNum = 1;
					var replyPageFooter = $(".panel-footer");
					
					function showReplyPage(replyCnt) {
						var endNum = Math.ceil(pageNum/10.0)*10;
						var startNum = endNum-9;
						
						var prev = (startNum != 1);
						var next =false;
						
						if(endNum*10>=replyCnt){
							endNum = Math.ceil(replyCnt/10.0);
						}
						if(endNum*10<replyCnt){
							next = true;
						}
						
						var str = "<ul class='pagination pull-right'>";
						if(prev){
							str+="<li class='page-item'> <a class='page-link' href='"+(startNum-1)+"'>Previous</a></li>'";
						}
						
						for (var i = startNum; i <=endNum; i++) {
							var active = pageNum==i?"active":"";
							str+= "<li class='page-item "+active+"'> <a class='page-link' href='"+i+"'>"+i+"</a></li>";
						}
						
						if(next){
							str+="<li class='page-item'> <a class='page-link' href='"+(endNum+1)+"'>Next</a></li>'";
						}
						str+="</ul>"
						
						replyPageFooter.html(str)
					}
					
					replyPageFooter.on("click","li a", function(e) {
						e.preventDefault();
						
						var targetPageNum =$(this).attr("href");
						
						pageNum = targetPageNum;
						
						showList(pageNum);
						
					});
					
					
				});
					
				</script>

</body>
</html>
<%@include file="../include/footer.jsp"%>    