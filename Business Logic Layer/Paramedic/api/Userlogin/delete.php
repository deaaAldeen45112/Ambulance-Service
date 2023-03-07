<?php 
  // Headers
  header('Access-Control-Allow-Origin: *');
  header('Content-Type: application/json');
  header('Access-Control-Allow-Methods: DELETE');
  header('Access-Control-Allow-Headers: Access-Control-Allow-Headers,Content-Type,Access-Control-Allow-Methods, Authorization, X-Requested-With');
  
  include_once '../../config/Constants.php';
  include_once '../../config/Database.php';
  include_once '../../models/Userlogin.php';

  // Instantiate DB & connect
  $database = new Database();
  $db = $database->connect();

  // Instantiate blog post object
  $userlogin = new Userlogin($db);


  $userlogin->userlogin_id=$_GET['userloginId'];
  
  // Delete post
  if($userlogin->delete()&&$userlogin->userlogin_id!==NULL&&$userlogin->userlogin_id!=="") {
    echo json_encode(
      array(Constants::$nameOfstateMessge=>Constants::$successOfstateMessge)
    );
  } else {
    echo json_encode(
      array(Constants::$nameOfstateMessge=>Constants::$errorOfstateMessge)
    );
  }