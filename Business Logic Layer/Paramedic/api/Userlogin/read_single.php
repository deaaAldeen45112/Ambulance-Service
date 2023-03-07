<?php 
  // Headers
  header('Access-Control-Allow-Origin: *');
  header('Content-Type: application/json');

  include_once '../../config/Database.php';
  include_once '../../models/Userlogin.php';
  include_once '../../config/Constants.php';
  // Instantiate DB & connect
  $database = new Database();
  $db = $database->connect();

  // Instantiate blog post object
  $userlogin = new Userlogin($db);

  //echo $data . 'aaaaaa'.$_POST['email'];
  $userlogin->userlogin_id=$_GET['userloginId'];
  
  // Blog post query
  $result=$userlogin->read_single();
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
        'created_date' => $date_created,
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
  