<?php

$db_name = "viveredb";
$mysql_username = "viveremaster";
$mysql_password = "Vgk2016!";
$server_name = "viveredb.csx62dscodbn.us-west-2.rds.amazonaws.com";
$conn = mysqli_connect($server_name,$mysql_username,$mysql_password,$db_name); 

$user = $_POST["user"];

$sql = "SELECT username FROM Patient WHERE username ='$user' ";
$result  = mysqli_query($conn,$sql);

if(mysqli_num_rows($result)>0){
	echo "1";
}else{
	echo "0";
}

mysqli_close($conn);

?>