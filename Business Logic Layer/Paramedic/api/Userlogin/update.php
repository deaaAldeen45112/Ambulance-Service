<?php
if ($_SERVER['REQUEST_METHOD'] === 'PUT') { 
  // Headers
  header('Access-Control-Allow-Origin: *');
  header('Content-Type: application/json');
  header('Access-Control-Allow-Methods: PUT');
  header('Access-Control-Allow-Headers: Access-Control-Allow-Headers,Content-Type,Access-Control-Allow-Methods, Authorization, X-Requested-With');

  include_once '../../config/Database.php';
  include_once '../../models/Userlogin.php';
  include_once '../../config/Constants.php';
  // Instantiate DB & connect
  $database = new Database();
  $db = $database->connect();

  // Instantiate blog post object
  $userlogin = new Userlogin($db);

  // Get raw posted data
  $data = json_decode(file_get_contents("php://input"));
  $userlogin->userlogin_id=$data->userloginId;
  $userlogin->name = $data->fullName;
  $userlogin->phone=$data->phone;
  $userlogin->password=$data->password;
  $userlogin->age=$data->age;
  $userlogin->email=$data->email;
  $userlogin->role_name=$data->roleName;

  // Create post
  if($userlogin->update()&&$data->userloginId!==NULL&&$data->userloginId!="") {
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
