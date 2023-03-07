
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

  $request->request_status=$_GET['requestStatus'];
  $request->patient_id=$_GET['patientId'];

   

  // Blog post query
  $result = $request->read_request_join_diseaseStates_join_patient_join_userlogin_by_request_status_and_patient_id();
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
        'requestId'=>$request_id,
        'requestStatus' => $request_status,
        'patientLongitude' => $patient_longitude,
        'patientLatitude' => $patient_latitude,
        'patientId' => $patient_id,
        'paramedicId' => $paramedic_id,
        'diseaseStateId'=> $disease_state_id,
        'diseaseStatesName'=> $disease_states_name,
        'requestDescribed'=> $request_described,
        'paramedicName'=>$row['paramedic_name'],
        'paramedicAge'=>$row['paramedic_age'],
        'paramedicPhone'=>$row['paramedic_phone'],
        'paramedicEmail'=>$row['paramedic_email']
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
  