<?php

$db_name = "viveredb";
$mysql_username = "viveremaster";
$mysql_password = "Vgk2016!";
$server_name = "viveredb.csx62dscodbn.us-west-2.rds.amazonaws.com";
$conn = mysqli_connect($server_name,$mysql_username,$mysql_password,$db_name); 

$user= $_POST["username"];
$pass= $_POST["password"];
$name= $_POST["name"];
$surname = $_POST["surname"];
$gender = $_POST["gender"];
$nationality = $_POST["nationality"];
$country = $_POST["country"];
$age = $_POST["age"];

$sql ="INSERT INTO Patient VALUES('$user','$pass', '$name', '$surname','$gender','$nationality','$country','$age');";

if(mysqli_query($conn,$sql)){
	echo "1";
}else{
	echo "0";
}

?>