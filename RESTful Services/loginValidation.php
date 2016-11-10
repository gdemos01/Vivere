<?php

$db_name = "viveredb";
$mysql_username = "viveremaster";
$mysql_password = "Vgk2016!";
$server_name = "viveredb.csx62dscodbn.us-west-2.rds.amazonaws.com";
$conn = mysqli_connect($server_name,$mysql_username,$mysql_password,$db_name); 

$user_name = $_POST["user_name"];
$user_pass = $_POST["password"];
$mysql_qry = "select * from Patient where username = '$user_name' and password = '$user_pass' ;";
$result = mysqli_query($conn, $mysql_qry);

if(mysqli_num_rows($result)>0){
	echo "Login Succesful"	;
}else{
	echo "Login NOT Succesful";
}

?>