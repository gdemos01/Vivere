<?php

$db_name = "viveredb";
$mysql_username = "viveremaster";
$mysql_password = "Vgk2016!";
$server_name = "viveredb.csx62dscodbn.us-west-2.rds.amazonaws.com";
$conn = mysqli_connect($server_name,$mysql_username,$mysql_password,$db_name); 

$name= $_POST["name"];

$sql = "SELECT * FROM Illnesses WHERE name LIKE '$name'; ";
$result = mysqli_query($conn, $sql);
$rows = array();

While($r = mysqli_fetch_assoc($result)){
	$rows[]=$r;
}

echo json_encode(array("server_response"=>$rows));

mysqli_close($conn);
?>