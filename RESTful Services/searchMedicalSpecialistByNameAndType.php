<?php

$db_name = "viveredb";
$mysql_username = "viveremaster";
$mysql_password = "Vgk2016!";
$server_name = "viveredb.csx62dscodbn.us-west-2.rds.amazonaws.com";
$conn = mysqli_connect($server_name,$mysql_username,$mysql_password,$db_name); 

$name = $_POST["name"];
$type = $_POST["type"];



$sql = "SELECT username,name,surname,speciality,address,telephone,type FROM MedicalSpecialist WHERE name LIKE '$name%' AMD type LIKE '$type' LIMIT 5;";
$result = mysqli_query($conn, $sql);
$rows = array();

While($r = mysqli_fetch_assoc($result)){
	$rows[]=$r;
}

echo json_encode(array("server_response"=>$rows));

mysqli_close($conn);
?>