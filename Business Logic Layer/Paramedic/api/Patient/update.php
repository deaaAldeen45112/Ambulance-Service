<?php
if ($_SERVER['REQUEST_METHOD'] === 'PUT') { 
  // Headers
  header('Access-Control-Allow-Origin: *');
  header('Content-Type: application/json');
  header('Access-Control-Allow-Methods: PUT');
  header('Access-Control-Allow-Headers: Access-Control-Allow-Headers,Content-Type,Access-Control-Allow-Methods, Authorization, X-Requested-With');
  include_once '../../config/Constants.php';
  include_once '../../config/Database.php';
  include_once '../../models/Patient.php';

  // Instantiate DB & connect
  $database = new Database();
  $db = $database->connect();

  // Instantiate blog post object
  $patient = new Patient($db);

  // Get raw posted data
  $data = json_decode(file_get_contents("php://input"));
  $patient->patient_id = $data->patientId;
  $patient->userlogin_id = $data->userloginId;

  // Create post
  if($patient->update()) {
    echo json_encode(
      array(Constants::$nameOfstateMessge=>Constants::$successOfstateMessge)
    );
  } else {
    echo json_encode(
      array(Constants::$nameOfstateMessge=>Constants::$errorOfstateMessge)
    );
  }
  }
 else{ echo "not found"; }
