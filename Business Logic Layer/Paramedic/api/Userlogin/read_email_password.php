<?php 
  // Headers
  header('Access-Control-Allow-Origin: *');
  header('Content-Type: application/json');
  header('Access-Control-Allow-Methods: POST');
  header('Access-Control-Allow-Headers: Access-Control-Allow-Headers,Content-Type,Access-Control-Allow-Methods, Authorization, X-Requested-With');
  include_once '../../config/Constants.php';
  include_once '../../config/Database.php';
  include_once '../../models/Userlogin.php';

  // Instantiate DB & connect
  $database = new Database();
  $db = $database->connect();

  // Instantiate blog post object
  $userlogin = new Userlogin($db);

  $data = json_decode(file_get_contents("php://input"));

  $userlogin->password=$data->password;
  $userlogin->email=$data->email;
  
  // Blog post query
  $result = $userlogin->read_email_password();
  // Get row count
  $num = $result->rowCount();
  $posts_arr = array();
  $posts_arr['data'] = array();
  $posts_arr['status']="";
  // Check if any posts
  if($num > 0) {
    // Post array
   
    while($row = $result->fetch(PDO::FETCH_ASSOC)) {
      extract($row);
      $post_item = array(
        'userloginId'=>$userlogin_id,
        'fullName' => $full_name,
        'password' => $password,
        'phone' => $phone,
        'age' => $age,
        'email' => $email,
        'roleName'=> $role_name
      );

      // Push to "data"
      array_push($posts_arr['data'], $post_item);
     
      // array_push($posts_arr['data'], $post_item);
    }
    $posts_arr[Constants::$nameOfstateMessge]=Constants::$successOfstateMessge;
  
    // Turn to JSON & outpu    

  } 
  else {
    // No Posts
    $posts_arr[Constants::$nameOfstateMessge]=Constants::$errorOfstateMessge;
  }
  echo json_encode($posts_arr,JSON_UNESCAPED_UNICODE);


  ?>
  