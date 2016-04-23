/**
 * js file for post.html
 * Please use modern web browser as this file will not attempt to be
 * compatible with older browsers. Use Chrome and open javascript console
 * or Firefox with developer console.
 * 
 * jquery is required
 */
$(document).ready(function() {
	
	getInventory();
	
	$(document.body).on('click', ':button, .DELETE_BTN', function(e) {
		//console.log(this);
		var $this = $(this)
			, CALIENTE_ID = $this.val()
			, obj = {CALIENTE_ID : CALIENTE_ID}
			, $tr = $this.closest('tr')
			, CALIENTE_NAME = $tr.find('.CL_PC_PARTS_MAKER').text();
		
		deleteInventory(obj, CALIENTE_NAME, CALIENTE_ID);
	});
});

function deleteInventory(obj, name, id) {
	
	ajaxObj = {  
			type: "DELETE",
			url: "http://localhost:8080/cal-jws/v1/caliente/" + id,
			data: JSON.stringify(obj), 
			contentType:"application/json",
			error: function(jqXHR, textStatus, errorThrown) {
				console.log(jqXHR.responseText);
			},
			success: function(data) {
				//console.log(data);
				$('#delete_response').text( data[0].MSG );
			},
			complete: function(XMLHttpRequest) {
				//console.log( XMLHttpRequest.getAllResponseHeaders() );
				getInventory();
			}, 
			dataType: "json" //request JSON
		};
		
	return $.ajax(ajaxObj);
}

function getInventory() {
	
	var d = new Date(), n = d.getTime();
	
	ajaxObj = {  
			type: "GET",
			url: "http://localhost:8080/cal-jws/api/v1/caliente", 
			data: "ts="+n,
			contentType:"application/json",
			error: function(jqXHR, textStatus, errorThrown) {
				console.log(jqXHR.responseText);
			},
			success: function(data) { 
				//console.log(data);
				var html_string = "";
				
				$.each(data, function(index1, val1) {
					//console.log(val1);
					html_string = html_string + templateGetInventory(val1);
				});
				
				$('#get_inventory').html("<table border='1'>" + html_string + "</table>");
			},
			complete: function(XMLHttpRequest) {
				//console.log( XMLHttpRequest.getAllResponseHeaders() );
			}, 
			dataType: "json" //request JSON
		};
		
	return $.ajax(ajaxObj);
}

function templateGetInventory(param) {
	return '<tr>' +
				'<td class="CL_PC_PARTS_MAKER">' + param.name + '</td>' +

				'<td class="CL_PC_PARTS_BTN"> <button class="DELETE_BTN" value="'+param.id+'" type="button">Delete</button> </td>' +
			'</tr>';
}