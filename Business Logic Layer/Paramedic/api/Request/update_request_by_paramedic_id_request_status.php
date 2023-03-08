<?php
if ($_SERVER['REQUEST_METHOD'] === 'PUT') { 
  // Headers
  header('Access-Control-Allow-Origin: *');
  header('Content-Type: application/json');
  header('Access-Control-Allow-Methods: PUT');
  header('Access-Control-Allow-Headers: Access-Control-Allow-Headers,Content-Type,Access-Control-Allow-Methods, Authorization, X-Requested-With');
  include_once '../../config/Database.php';
  include_once '../../models/Request.php';
  include_once '../../config/Constants.php';
  // Instantiate DB & connect
  $database = new Database();
  $db = $database->connect();

  // Instantiate  req object
  $request = new Request($db);


  // Get raw posted data
  $data = json_decode(file_get_contents("php://input"));
  $request->request_id=$data->requestId;
  $request->request_status = $data->requestStatus;
  $request->paramedic_id=$data->paramedicId;

  // update req
  if($request->update_request_by_paramedic_id_request_status()&&$data->requestId!==NULL&&$data->requestId!="") {
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
