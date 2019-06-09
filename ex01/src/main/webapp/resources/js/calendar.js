/**
 * 
 */
			
		
	function getList(date) {
		
		$.ajax(
		{
			url: "/calendar/"+date,
			type:"GET",
			data: {'date':date},
			dataType:"json",
			success :
				function(data) {
				alert(data.list)
			/*	$.each(data.json, function(idx, val) {
					alert(idx + " " + val.title);
				});*/
				alert(date);
						
				},
			error : function() {
				alert("error");
			}
		})
		
	}