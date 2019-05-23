/**
 * 
 */
console.log("Reply Module..................");
var replyService = (function () {
//		return {name:"AAAA"};
		function add(reply, callback,error){
			$.ajax({
				  type: "POST", 
				  url: "/replies/new",
				  data: JSON.stringify(reply),
				  contentType: "application/json; charset=utf-8",
				  success: function(result, status, xhr){
					  if(callback){
						  callback(result);
					  }
				  },
				  error : function(xhr, status, er){
					  if(error){
						  error(er);
					  }
				  }

				 });
			
		}
		///////////////////////add end
	
		function getList(param , callback, error) {
			
			var bno  = param.bno;
			var page = param.page || 1;
			
			$.getJSON("/replies/pages/"+bno+"/"+page+".json", 
		    	function(data) {
					if(callback){
					callback(data);
					}
				
		    	}).fail(function(xhr,status,err) {
		    		if(err){
		    		error();
		    		}
		    	});
			
		}
		///////////////////////////getList end
		function remove(rno, callback,error) {
			$.ajax({
				  type: "delete", 
				  url: "/replies/"+rno,
				  success: function(deleteResult, status, xhr){
					  if(callback){
						  callback(deleteResult);
					  }
				  },
				  error : function(xhr, status, er){
					  if(error){
						  error(er);
					  }
				  }

				 });
			
		}
		function update(reply, callback,error) {
			$.ajax({
				type: "put", 
				url: "/replies/"+reply.rno,
				data: JSON.stringify(reply),
				contentType: "application/json; charset=utf-8",
				
				success: function(result, status, xhr){
					if(callback){
						callback(result);
					}
				},
				error : function(xhr, status, er){
					if(error){
						error(er);
					}
				}
				
			});
			
		}
		/////////////////////////////remove end
		
		function get(rno,callback,error) {
			$.get("/replies/"+rno+".json", function (result) {
				if(callback){
					callback(result);
				}
			}).fail(function(xhr, status, er){
						if(error){
							error(er);
						}
					});
			
		}
		
		/////////////////////////////////////////////
		return {
		add : add
		,getList : getList
		,remove : remove
		,update : update
		,get : get
		};
	
	})();
