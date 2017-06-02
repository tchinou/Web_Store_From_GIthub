function submitter(btn) {
    var param = btn.id;
    var rowId;
    var myForm = document.forms["cartForm"];
    myForm.elements["productId"].value = param;
    
    if (myForm.elements["rowIndex"] != undefined) {
    	myForm.elements["rowIndex"].value = $(btn).closest('tr').index()-1;
    }
    
    myForm.submit();
}

function getRowIndex (td) {
	 var row_index = $(this).parent().index();
	 var col_index = $(this).index();
	 submitter();
}
