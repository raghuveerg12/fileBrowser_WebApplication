<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>WKST</title>
</head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<style>
	.rTable {
  	display: table;
  	width: 100%;
}
.rTableRow {
  	display: table-row;
}
.rTableHeading {
  	display: table-header-group;
  	background-color: #696969;
}
.rTableCell, .rTableHead {
  	display: table-cell;
  	padding: 3px 10px;
  	border: 1px solid #999999;
}
.rTableHeading {
  	display: table-header-group;
  	background-color: #696969;
  	font-weight: bold;
}
.rTableFoot {
  	display: table-footer-group;
  	font-weight: bold;
  	background-color: #696969;
}
.rTableBody {
  	display: table-row-group;
}
</style>
<script>

/* 
function dosomething(val){
window.open("/Isonsoft-web-application/cello/"+val, "_blank", "toolbar=yes,scrollbars=yes,resizable=yes,top=500,left=500,width=400,height=400");
} */


$( document ).ready(function() {
	
	// SUBMIT FORM
    $("button").click(function(e) {
		// Prevent the form from submitting via the browser.
		
   
	var valueId=$(this).val();
		e.preventDefault();
		 $('#myform').attr('action', "/Isonsoft-web-application/sello?name="+valueId).submit();
		/* 
		$.ajax({
        type: "GET",
        url: "/Isonsoft-web-application/cello/"+valueId,
        
        success: function(response) {
          
           var trHTML= '';
          
		$.each($.parseJSON(response), function(idx, obj) {
	            trHTML += '<tr><td><button  value="${obj.fileName}" >' + obj.fileName + '</button></td><td>' + obj.fileSize + '</td></tr>';
				$("#fileName").html(obj.fileName);
				//documet.getElementById("")
			});
              $('#location').append(trHTML);
            console.log(response);
        },
        error: function(response) {
            alert('error');
        }
    });*/
    }); 
	});
	
	
	
</script>



<body>
	
 <form id="myform" method="post"> 		
		<ul>
			<div class="rTable">
				<div class="rTableRow">
					<div class="rTableCell" style="background-color:#C0C0C0;"><strong>Name Of Drive</strong></div>
					<div class="rTableCell" style="background-color:#C0C0C0;"><span style="font-weight: bold;">fileSize</span></div>
					<div class="rTableCell" style="background-color:#C0C0C0;"><span style="font-weight: bold;">fileCreationDate</span></div>
					<div class="rTableCell" style="background-color:#C0C0C0;"><span style="font-weight: bold;">fileLastModified</span></div>
					<div class="rTableCell" style="background-color:#C0C0C0;"><span style="font-weight: bold;">fileLastOpened</span></div>
					<div class="rTableCell" style="background-color:#C0C0C0;"><span style="font-weight: bold;">fileExtension</span></div>
					<div class="rTableCell" style="background-color:#C0C0C0;"><span style="font-weight: bold;">filePath</span></div>
					<div class="rTableCell" style="background-color:#C0C0C0;"><span style="font-weight: bold;">freeSpaceOnDrive</span></div>
					
				</div>
				<c:forEach var="categoryName" items="${message}" varStatus="loop">
				<div class="rTableRow">
					<div class="rTableCell"><button  value="${categoryName.filePath}" >${categoryName.filePath}</button></div>
					<div class="rTableCell">${categoryName.fileSize}</div>
					<div class="rTableCell">${categoryName.fileCreationDate}</div>
					<div class="rTableCell">${categoryName.fileLastModified}</div>
					<div class="rTableCell">${categoryName.fileLastOpened}</div>
					<div class="rTableCell">${categoryName.fileExtension}</div>
					<div class="rTableCell">${categoryName.filePath}</div>
					<div class="rTableCell">${categoryName.freeSpaceOnDrive}</div>
					
				</div>
				</c:forEach>
				
			</div>
<!-- <table id="location" border='1'>
    <tr>
        <th> Name Of Drive</th>
         <th>fileSize</th>
         <th>fileCreationDate</th>
         <th>fileLastModified</th>
         <th>fileLastOpened</th>
         <th>fileExtension</th>
         <th>filePath</th>
         <th>freeSpaceOnDrive</th>
         
    </tr>
</table>
			
    			<p id="demo"></p> -->
			
		</ul>
</form>  
</body>
</html>