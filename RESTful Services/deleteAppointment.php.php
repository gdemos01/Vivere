<?php

$db_name = "viveredb";
$mysql_username = "viveremaster";
$mysql_password = "Vgk2016!";
$server_name = "viveredb.csx62dscodbn.us-west-2.rds.amazonaws.com";
$conn = mysqli_connect($server_name,$mysql_username,$mysql_password,$db_name); 

$pusername = $_POST["pusername"];
$msusername =$_POST["msusername"];
$date = $_POST["date"];


$sql= "DELETE FROM Appointment WHERE pusername = '$pusername' AND msusername= '$msusername' AND date= '$date'";


if(mysqli_query($conn,$sql)){
	echo "one row deleted";
}else{
	echo "failed";
}

mysqli_close($conn);
?>