
<?php 
  // Headers
  header('Access-Control-Allow-Origin: *');
  header('Content-Type: application/json');

  include_once '../../config/Database.php';
  include_once '../../models/Request.php';
  include_once '../../config/Constants.php';
  // Instantiate DB & connect
  $database = new Database();
  $db = $database->connect();

  // Instantiate blog post object
  $request = new Request($db);
 
  // Blog post query
  $result = $request->read_distinct_request_status_for_paramedic();

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
     
        'requestStatus' => $request_status,
 
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
  