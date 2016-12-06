<?php
$db_name = "viveredb";
$mysql_username = "viveremaster";
$mysql_password = "Vgk2016!";
$server_name = "viveredb.csx62dscodbn.us-west-2.rds.amazonaws.com";
$conn = mysqli_connect($server_name,$mysql_username,$mysql_password,$db_name); 

$username= $_POST["username"];
$name= $_POST["name"];
$duration = $_POST["duration"];
$frequency = $_POST["frequency"];
$dose = $_POST["dose"];

$sql= "INSERT INTO Medication VALUES('$username','$name', '$duration','$frequency','$dose');";


if(mysqli_query($conn,$sql)){
	echo "one row inserted";
}else{
	echo "not inserted";
}

mysqli_close($conn);
?>