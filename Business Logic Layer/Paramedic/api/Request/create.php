<?php 
  // Headers
  header('Access-Control-Allow-Origin: *');
  header('Content-Type: application/json');
  header('Access-Control-Allow-Methods: POST');
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


  
  if($data->paramedicId==0){

    $data->paramedicId=null;
  }
  

  $request->request_status = $data->requestStatus;
  $request->patient_longitude=$data->patientLongitude;
  $request->patient_latitude=$data->patientLatitude;
  $request->patient_id=$data->patientId;
  $request->paramedic_id=$data->paramedicId;
  $request->disease_state_id=$data->diseaseStateId;
  $request->request_described=$data->requestDescribed;

 /* file_put_contents("C:\\Users\\user\\Desktop\\asd.txt", $data->requestDescribed,FILE_APPEND);
  file_put_contents("C:\\Users\\user\\Desktop\\asd.txt", 
  is_null($data->paramedicId),FILE_APPEND);

  ini_set('display_errors', 1);
  ini_set('display_startup_errors', 1);
  error_reporting(E_ALL);
 */
  // Create req
  if($request->create()) {
   
    echo json_encode(
      array(Constants::$nameOfstateMessge=>Constants::$successOfstateMessge)
    );
  } else {
    echo json_encode(
      array(Constants::$nameOfstateMessge=>Constants::$errorOfstateMessge)
    );
  }