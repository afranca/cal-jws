/**
 * js file for post.html
 * Please use modern web browser as this file will not attempt to be
 * compatible with older browsers. Use Chrome and open javascript console
 * or Firefox with developer console.
 * 
 * jquery is required
 */
$(document).ready(function() {
	
	var $put_example = $('#put_example');
		 //, $NAME = $('#NAME'); Fixed Data, if needed
	
	getInventory();
	
	$(document.body).on('click', ':button, .UPDATE_BTN', function(e) {
		//console.log(this);
		var $this = $(this)
			, CALIENTE_ID = $this.val()
			, $tr = $this.closest('tr')
			, CALIENTE_NAME = $tr.find('.caliente_name').text();
		
		$('#caliente_id').val(CALIENTE_ID);
		$('#display_id').text("(id:"+CALIENTE_ID+")");  // just to display id on html <tr>
		$('#caliente_name').text(CALIENTE_NAME);
				
		$('#update_response').text("");
	});
	
	$put_example.submit(function(e) {
		e.preventDefault(); //cancel form submit
		
		var obj = $put_example.serializeObject()
			, cal_id = $('#caliente_id').text(); //, NAME = $('#caliente_name').text();
		
		updateInventory(obj, cal_id);
	});
});

function updateInventory(obj, caliente_id) {
	
	ajaxObj = {  
			type: "PUT",
			url: "http://localhost:8080/cal-jws/api/v1/caliente/" + caliente_id,
			data: JSON.stringify(obj), 
			contentType:"application/json",
			error: function(jqXHR, textStatus, errorThrown) {
				console.log(jqXHR.responseText);
			},
			success: function(data) {
				//console.log(data);
				$('#update_response').text( data[0].MSG );
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
	
	var d = new Date()
		, n = d.getTime();
	
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
				'<td class="caliente_name">' + param.name + '</td>' +
				'<td class="CL_PC_PARTS_BTN"> <button class="UPDATE_BTN" value="' + param.id + '" type="button">Update</button> </td>' +
			'</tr>';
}

