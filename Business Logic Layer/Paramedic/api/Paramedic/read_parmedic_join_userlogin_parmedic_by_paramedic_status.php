<?php
if ($_SERVER['REQUEST_METHOD'] === 'GET') { 
  // Headers
  header('Access-Control-Allow-Origin: *');
  header('Content-Type: application/json');

  include_once '../../config/Database.php';
  include_once '../../models/Paramedic.php';
  include_once '../../config/Constants.php';
  // Instantiate DB & connect
  $database = new Database();
  $db = $database->connect();

  // Instantiate category object
  $paramedic = new Paramedic($db);
  $paramedic->paramedic_status=$_GET['paramedicStatus'];
  // Category read query
  $result = $paramedic->read_parmedic_join_userlogin_parmedic_by_paramedic_status();
  
  // Get row count
  $num = $result->rowCount();

  // Check if any categories
  $posts_arr = array();
  $posts_arr['data'] = array();
  if($num > 0) {
      
       // $posts_arr['data'] = array();

        while($row = $result->fetch(PDO::FETCH_ASSOC)) {
          extract($row);

          $post_item = array(
            'paramedicId' => $paramedic_id,  
            'userloginId' => $userlogin_id,
            'paramedicStatus'=>$row["paramedic_status"],
            'paramedicLongitude'=>$row["paramedic_longitude"],
            'paramedicLatitude'=>$row["paramedic_latitude"],
            'paramedicLatitude'=>$row["full_name"],
            'paramedicLatitude'=>$row["phone"],
            'paramedicLatitude'=>$row["email"]
            

           
          );
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
 } else {echo "not found";}
?> 