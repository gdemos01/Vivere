<?php
$db_name = "viveredb";
$mysql_username = "viveremaster";
$mysql_password = "Vgk2016!";
$server_name = "viveredb.csx62dscodbn.us-west-2.rds.amazonaws.com";
$conn = mysqli_connect($server_name,$mysql_username,$mysql_password,$db_name); 

$puser_name= $_POST["puser_name"];
$msuser_name= $_POST["msuser_name"];
$type =$_POST["type"];
$date = $_POST["date"];
$res = "TBA";
$advice = "TBA";

$sql= "INSERT INTO Exam VALUES('$puser_name','$msuser_name', DEFAULT,'$type','$res','$date','$advice');";


if(mysqli_query($conn,$sql)){
	echo "one row inserted";
}else{
	echo "not inserted";
}

mysqli_close($conn);
?>