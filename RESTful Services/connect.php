<?php
/* Establish Connection to the Server*/



$db_name = "viveredb";
$mysql_username = "viveremaster";
$mysql_password = "Vgk2016!";
$server_name = "viveredb.csx62dscodbn.us-west-2.rds.amazonaws.com";
$conn = mysqli_connect($server_name,$mysql_username,$mysql_password,$db_name); 

if($conn){
	echo "connection successful";
}else{
	die("connection NOT succesful: " . mysqli_connect_error());
}

?>