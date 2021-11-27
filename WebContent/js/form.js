/**
 * 
 */
var form = $('#attribForm');
console.log("gi");
form.submit(function(){
	$.ajax({
		url: 'ReportServlet',
		data: form.serialize(),
		type: 'post',
		dataType: 'text/plain',
		success: function(data){
			console.log("Process completed");
			$('#completionMessage').text(data);
		}
	});
});