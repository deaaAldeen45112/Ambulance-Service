<?php
if ($_SERVER['REQUEST_METHOD'] === 'GET') { 
  // Headers
  header('Access-Control-Allow-Origin: *');
  header('Content-Type: application/json');

  include_once '../../config/Database.php';
  include_once '../../models/Patient.php';
  include_once '../../config/Constants.php';
  // Instantiate DB & connect
  $database = new Database();
  $db = $database->connect();

  // Instantiate category object
  $patient = new Patient($db);
  $patient->userlogin_id=$_GET['userloginId'];
  // Category read query
  $result = $patient->read_by_userlogin_id();
  
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
            'patientId' => $patient_id,  
            'userloginId' => $userlogin_id
           
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