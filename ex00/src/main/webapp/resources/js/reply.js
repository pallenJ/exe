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
	
		return {add : add};
	
	})();
