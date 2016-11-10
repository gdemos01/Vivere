<?php

$db_name = "viveredb";
$mysql_username = "viveremaster";
$mysql_password = "Vgk2016!";
$server_name = "viveredb.csx62dscodbn.us-west-2.rds.amazonaws.com";
$conn = mysqli_connect($server_name,$mysql_username,$mysql_password,$db_name); 

$date_started= $_POST["date_started"];
$date_finished = $_POST["date_finished"];
//$date_started = "2016-11-08 23:59:59";
//$date_finished = "2016-11-09 23:59:59";

$sql = "SELECT * FROM Appointment WHERE date > '$date_started' AND date < '$date_finished'; ";
$result = mysqli_query($conn, $sql);
$rows = array();

While($r = mysqli_fetch_assoc($result)){
	$rows[]=$r;
}

echo json_encode(array("server_response"=>$rows));

mysqli_close($conn);
?>