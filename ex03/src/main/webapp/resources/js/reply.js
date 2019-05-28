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
					callback(data.replyCnt, data.list);
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
		////////////////////////get end
		
		function bigger9(data){
			return (data>9?'':'0')+data;
		}
		
		function displayTime(timeValue) {
			var today = new Date();
			
			var gap = today.getTime() - timeValue;
			
			var dateObj = new Date(timeValue);
			
			var str = "";
			
			if(gap < 1000*60*60*24){
				/*var hh = dateObj.getHours();
				var mi = dateObj.getMinutes();
				var ss = dateObj.getSeconds();
				*/
				return [bigger9(dateObj.getHours()),":",bigger9(dateObj.getMinutes()),":",bigger9(dateObj.getSeconds())].join('');
			}else{
				/*var yy = dateObj.getFullYear();
				var mm = dateObj.getMonth()+1;
				var dd = dateObj.getDate();
				alert(yy+"/"+mm+"/"+dd)*/
				return [dateObj.getFullYear(),"/",bigger9(dateObj.getMonth()+1),"/",bigger9(dateObj.getDate())].join('');
				
			}
			
			
		}
		
		/////////////////////////////////////////////
		return {
		add : add
		,getList : getList
		,remove : remove
		,update : update
		,get : get
		,displayTime : displayTime
		
		};
	
	})();
