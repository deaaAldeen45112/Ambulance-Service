<?php
if ($_SERVER['REQUEST_METHOD'] === 'PUT') { 
  // Headers
  header('Access-Control-Allow-Origin: *');
  header('Content-Type: application/json');
  header('Access-Control-Allow-Methods: PUT');
  header('Access-Control-Allow-Headers: Access-Control-Allow-Headers,Content-Type,Access-Control-Allow-Methods, Authorization, X-Requested-With');
  include_once '../../config/Constants.php';
  include_once '../../config/Database.php';
  include_once '../../models/DiseaseStates.php';

  // Instantiate DB & connect
  $database = new Database();
  $db = $database->connect();

  // Instantiate blog post object
  $diseaseStates = new DiseaseStates($db);

  // Get raw posted data
  $data = json_decode(file_get_contents("php://input"));
  $diseaseStates->disease_states_name = $data->diseaseStatesName;

  $diseaseStates->disease_states_id = $data->diseaseStatesId;

  // Create post
  if($diseaseStates->update()) {
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
