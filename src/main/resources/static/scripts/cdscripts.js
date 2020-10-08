$(document).ready(function(){
    populateTable();
});

function populateTable(){
	$("#cds tbody").html("");
	$.ajax({
        url: '/api/compactdiscs',
        type: 'get',
        dataType: 'JSON',
        success: function(response){
            let len = response.length;
            for(let i=0; i<len; i++){
                let id = response[i].hexString;
                let title = response[i].title;
                let artist = response[i].artist;
                let price = response[i].price;
                let tr_str = "<tr>" +
                    "<td align='center' >" + id + "</td>" + 
                    "<td align='center'>" + title + "</td>" +
                    "<td align='center'>" + artist + "</td>" +
                    "<td align='center'>" + price + "</td>" +
                    "<td align='center'><button class='edit'>Edit</button>&nbsp;" + 
                    "<button class='delete'>Delete</button></td>"
                    "</tr>";
                $("#cds tbody").append(tr_str);
            }
            $(document).find('.edit').on('click',function(){
    			let cd_id = $(this).parents('tr:first').find('td:eq(0)').text();
    			populateInputs(cd_id);
			});
			$(document).find('.delete').on('click',function(){
    			let cd_id = $(this).parents('tr:first').find('td:eq(0)').text();
    			let cd = $(this).parents('tr:first').find('td:eq(1)').text();
    			if(confirm("Are you sure you want to delete " + cd)){
    				deleteCD(cd_id);
    			}
			});
        }
    });
}

function populateInputs(cd_id){
	$.ajax({
        url: '/api/compactdiscs/' + cd_id,
        type: 'get',
        dataType: 'JSON',
        success: function(response){
            $("#id").text(response.hexString);
            $("#title").val(response.title);
            $("#artist").val(response.artist);
            $("#price").val(response.price);
        }
    });
    $('#save').prop('disabled', false);
    $('#add').prop('disabled', true);
}

function saveCD(){
	let cd = { "title":$("#title").val(),"artist":$("#artist").val(), "price":parseFloat($("#price").val()) };
	let id = $("#id").text();
	$.ajax({
        url: '/api/compactdiscs/' + id,
        type: 'put',
        contentType: 'application/json',
        data: JSON.stringify(cd),
        success: function(response){
            $("#id").text("");
            $("#title").val("");
            $("#artist").val("");
            $("#price").val("");
            $('#save').prop('disabled', true);
            $('#add').prop('disabled', false);
            populateTable();
        }
    });
}

function addCD(){
	let cd = { "title":$("#title").val(),"artist":$("#artist").val(), "price":parseFloat($("#price").val()) };
	$.ajax({
        url: '/api/compactdiscs',
        type: 'post',
        contentType: 'application/json',
        data: JSON.stringify(cd),
        success: function(response){
            $("#id").text("");
            $("#title").val("");
            $("#artist").val("");
            $("#price").val("");
            $('#save').prop('disabled', true);
            populateTable();
        }
    });
}

function deleteCD(cd_id){
	$.ajax({
        url: '/api/compactdiscs/' + cd_id,
        type: 'delete',
        success: function(response){
        	alert("CD with is deleted");
            populateTable();
        }
    });
}