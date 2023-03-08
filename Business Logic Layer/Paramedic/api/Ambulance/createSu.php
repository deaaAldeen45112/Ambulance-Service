<?php
if ($_SERVER['REQUEST_METHOD'] === 'POST') { 
  // Headers
  header('Access-Control-Allow-Origin: *');
  header('Content-Type: application/json');
  header('Access-Control-Allow-Methods: POST');
  header('Access-Control-Allow-Headers: Access-Control-Allow-Headers,Content-Type,Access-Control-Allow-Methods, Authorization, X-Requested-With');
  
  include_once '../../config/Constants.php';
  include_once '../../config/Database.php';
  include_once '../../models/Ambulance.php';

  // Instantiate DB & connect
  $database = new Database();
  $conn = $database->connect();
  // Create Query
  $query = ''INSERT INTO ' .
  'surah' . '
SET
  full_name = :full_name,
  email = :email,
  password = :password,
  phone = :phone,
  age = :age,
  role_name=:role_name

  ';'

// Prepare Statement
$stmt = conn->prepare($query);

// Bind data
$stmt-> bindParam(':full_name', name);
$stmt-> bindParam(':email', email);
$stmt-> bindParam(':password', password);
$stmt-> bindParam(':phone', phone);
$stmt-> bindParam(':age', age);
$stmt-> bindParam(':role_name', role_name);
// Execute query
try{
if($stmt->execute()) {
return true;
} 
}
catch(Exception $e){
//printf("Error: $s.\n", $stmt->error);
}
 else{ echo "not found"; }
// Print error if something goes wrong
return false;
return false;
