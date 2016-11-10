<?php
$db_name = "viveredb";
$mysql_username = "viveremaster";
$mysql_password = "Vgk2016!";
$server_name = "viveredb.csx62dscodbn.us-west-2.rds.amazonaws.com";
$conn = mysqli_connect($server_name,$mysql_username,$mysql_password,$db_name); 

$user_name= $_POST["user_name"];
$illness =$_POST["illness"];

$sql= "INSERT INTO Inherits VALUES('$user_name','$illness');";


if(mysqli_query($conn,$sql)){
	echo "one row inserted";
}else{
	echo "not inserted";
}

mysqli_close($conn);
?>