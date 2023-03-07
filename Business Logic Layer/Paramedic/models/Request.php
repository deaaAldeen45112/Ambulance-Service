<?php
  class Request {
    // DB Stuff
    private $conn;
    private $table = 'Request';

    // Properties
    public $request_id;
    public $request_status;
    public $patient_longitude;
    public $patient_latitude;
    public $patient_id;
    public $paramedic_id;
    public $disease_state_id;
    public $request_described;


   
    public function __construct($db) {
      $this->conn = $db;
    }

    // Get req
    public function read() {
      // Create query
      $query = 'SELECT * FROM ' . $this->table;

      // Prepare statement
      $stmt = $this->conn->prepare($query);

      // Execute query
      $stmt->execute();

      return $stmt;
    }



// ambulance two fileter and specific paramedic id 

public function read_request_join_diseaseStates_join_patient_join_userlogin_by_request_status_and_paramedic_id() {
  // Create query


  $expression=" = ? ";
  if( $this->paramedic_id==0){
    $expression=" is null ";
  }

  $query ='SELECT request.request_id,request.request_status,disease_states.disease_states_name,
  disease_states.disease_states_id, request.request_described,
  /*userlogin.userlogin_id as "patient_userlogin_id",*/
  userlogin.full_name as "patient_name",userlogin.phone as "patient_phone",
  userlogin.age as "patient_age" ,userlogin.email "patient_email",
  request.patient_longitude,request.patient_latitude,/*
  us.userlogin_id as "paramdic_userlogin_id",*/us.full_name as "paramedic_name",us.phone as "paramedic_phone",
  us.age as "paramedic_age" ,us.email "paramedic_email",request.patient_id , request.paramedic_id        
  FROM request
  inner JOIN disease_states on disease_states.disease_states_id=request.disease_state_id 
  inner JOIN patient on patient.patient_id=request.patient_id 
  INNER JOIN userlogin on userlogin.userlogin_id=patient.userlogin_id 
  left OUTER  JOIN paramedic on paramedic.paramedic_id=request.paramedic_id
  left OUTER  JOIN userlogin us on us.userlogin_id=paramedic.userlogin_id
  where request.request_status=?  and request.paramedic_id '.$expression.' ;';
   // Prepare statement
      $stmt = $this->conn->prepare($query);

      $stmt-> bindParam(1, $this->request_status);

      if( $this->paramedic_id!=0){
      
        $stmt-> bindParam(2, $this->paramedic_id);
      }

  // Execute query
  $stmt->execute();

  return $stmt;
}


// ambulance one filter and all to paramedic 
public function read_request_join_diseaseStates_join_patient_join_userlogin_by_request_status() {
  // Create query
  

  $query ='SELECT request.request_id,request.request_status,disease_states.disease_states_name,
  disease_states.disease_states_id, request.request_described,
  /*userlogin.userlogin_id as "patient_userlogin_id",*/
  userlogin.full_name as "patient_name",userlogin.phone as "patient_phone",
  userlogin.age as "patient_age" ,userlogin.email "patient_email",
  request.patient_longitude,request.patient_latitude,
  /*us.userlogin_id as "paramdic_userlogin_id",*/us.full_name as "paramedic_name",us.phone as "paramedic_phone",
  us.age as "paramedic_age" ,us.email "paramedic_email",request.patient_id , request.paramedic_id        
  FROM request
  inner JOIN disease_states on disease_states.disease_states_id=request.disease_state_id 
  inner JOIN patient on patient.patient_id=request.patient_id 
  INNER JOIN userlogin on userlogin.userlogin_id=patient.userlogin_id 
  left OUTER  JOIN paramedic on paramedic.paramedic_id=request.paramedic_id
  left OUTER  JOIN userlogin us on us.userlogin_id=paramedic.userlogin_id
  where request.request_status=? ;';

  // Prepare statement
  $stmt = $this->conn->prepare($query);

  $stmt-> bindParam(1, $this->request_status);
 
  // Execute query
  $stmt->execute();

  return $stmt;
}

// parameidc

    public function read_request_join_diseaseStates_join_patient_join_userlogin_by_request_status_and_paramedic_id_for_paramedic() {
      // Create query
      $expression=" = ? ";
      if( $this->paramedic_id==0){
        $expression=" is null ";
      }

      $query ='SELECT request.request_id,request.request_status
      ,request.patient_longitude,request.patient_latitude
      ,request.request_described,request.patient_id ,
      request.paramedic_id,request.disease_state_id ,
      userlogin.full_name,userlogin.age,userlogin.phone,
      disease_states.disease_states_name
      FROM request INNER join disease_states 
      on request.disease_state_id=disease_states.disease_states_id
      inner JOIN patient on patient.patient_id=request.patient_id 
      INNER JOIN userlogin on userlogin.userlogin_id=patient.userlogin_id
      WHERE request.request_status= ? and request.paramedic_id'.$expression.' ;';
  
      // Prepare statement
      $stmt = $this->conn->prepare($query);

      $stmt-> bindParam(1, $this->request_status);

      if( $this->paramedic_id!=0){
        $stmt-> bindParam(2, $this->paramedic_id);
      }

     
      // Execute query
      $stmt->execute();

      return $stmt;
    }






    // Get Single req 
     public function read_by_userlogin_id(){
    // Create query
      $query = 'SELECT * From ' . $this->table . ' WHERE userlogin_id = ?';

      //Prepare statement
      $stmt = $this->conn->prepare($query);

      // Bind ID
      $stmt->bindParam(1, $this->userlogin_id);

      // Execute query
      $stmt->execute();

      return $stmt;
     
  }

  // patient
  public function read_request_join_diseaseStates_join_patient_join_userlogin_by_request_status_and_patient_id(){
  

        $query ='SELECT request.request_id,request.request_status ,request.patient_longitude,request.patient_latitude 
        ,request.request_described,request.patient_id , request.paramedic_id,request.disease_state_id 
        , userlogin.full_name as"paramedic_name",userlogin.age as "paramedic_age",userlogin.phone as "paramedic_phone",
        userlogin.email as"paramedic_email", 
        disease_states.disease_states_name,request.paramedic_id FROM request 
        INNER join disease_states on request.disease_state_id=disease_states.disease_states_id
        left outer JOIN paramedic on paramedic.paramedic_id=request.paramedic_id 
        left outer JOIN userlogin on userlogin.userlogin_id=paramedic.userlogin_id 
        WHERE request.patient_id=? and request.request_status=?;';

        // Prepare statement
        $stmt = $this->conn->prepare($query);

        $stmt-> bindParam(2, $this->request_status);
        $stmt-> bindParam(1, $this->patient_id);
  
        $stmt->execute();

        return $stmt;
  }


  //paramedic
  public function read_distinct_request_status_for_paramedic(){
    // Create query
      $query = "SELECT DISTINCT (request.request_status) FROM `request` WHERE request_status!=\"waiting\" && request_status!=\"ambulance\"; ";

      //Prepare statement
      $stmt = $this->conn->prepare($query);


      // Execute query
      $stmt->execute();

      return $stmt;
     
  }

 // patient and ambulance
  public function read_distinct_request_status(){
    // Create query
      $query = "SELECT DISTINCT (request.request_status) FROM `request` ";

      //Prepare statement
      $stmt = $this->conn->prepare($query);


      // Execute query
      $stmt->execute();

      return $stmt;
     
  }



  // Create req
  public function create() {
    // Create Query
    $query = 'INSERT INTO ' .$this->table . ' SET request_status = :request_status,patient_longitude=:patient_longitude,patient_latitude=:patient_latitude,patient_id=:patient_id,paramedic_id=:paramedic_id,disease_state_id=:disease_state_id,request_described=:request_described';

   // Prepare Statement
    $stmt = $this->conn->prepare($query);

  // Clean data
   // $this->request_status = htmlspecialchars(strip_tags($this->request_status));
    //$this->patient_longitude = htmlspecialchars(strip_tags($this->patient_longitude));
   // $this->patient_latitude = htmlspecialchars(strip_tags($this->patient_latitude));
    //$this->patient_id = htmlspecialchars(strip_tags($this->patient_id));
   // $this->paramedic_id = htmlspecialchars(strip_tags($this->paramedic_id));
   // $this->disease_state_id = htmlspecialchars(strip_tags($this->disease_state_id));
    //$this->request_described = htmlspecialchars(strip_tags($this->request_described));
 
    $stmt-> bindParam(':request_status', $this->request_status);
    $stmt-> bindParam(':patient_longitude', $this->patient_longitude);
    $stmt-> bindParam(':patient_latitude', $this->patient_latitude);
    $stmt-> bindParam(':patient_id', $this->patient_id);
    $stmt-> bindParam(':paramedic_id', $this->paramedic_id);
    $stmt-> bindParam(':disease_state_id', $this->disease_state_id);
    $stmt-> bindParam(':request_described', $this->request_described);


  // Execute query
  if($stmt->execute()) {
    return true;
  }

  // Print error if something goes wrong
  printf("Error: $s.\n", $stmt->error);
 
  return false;
  }




  public function update_request_by_paramedic_id_request_status() {
    // Create Query
    $query = 'UPDATE '.$this->table . ' SET request_status = :request_status,paramedic_id=:paramedic_id  where request_id=:request_id ';

   // Prepare Statement
    $stmt = $this->conn->prepare($query);

  // Clean data
    $this->request_status = htmlspecialchars(strip_tags($this->request_status));
    $this->paramedic_id = htmlspecialchars(strip_tags($this->paramedic_id));
    $this->request_id = htmlspecialchars(strip_tags($this->request_id));

    $stmt-> bindParam(':request_status', $this->request_status);
    $stmt-> bindParam(':paramedic_id', $this->paramedic_id);
    $stmt-> bindParam(':request_id', $this->request_id);

  // Execute query
  if($stmt->execute()) {
    return true;
  }
  printf("Error: $s.\n", $stmt->error);

  return false;
}

//paramedic change his reques_status 
public function update_request_status_by_request_id() {
 // Create Query
  $query = 'UPDATE '.$this->table . ' SET request_status = :request_status  where request_id=:request_id ';

 // Prepare Statement
  $stmt = $this->conn->prepare($query);

 // Clean data
  $this->request_status = htmlspecialchars(strip_tags($this->request_status));
  $this->request_id = htmlspecialchars(strip_tags($this->request_id));

  $stmt-> bindParam(':request_status', $this->request_status);
  $stmt-> bindParam(':request_id', $this->request_id);

 // Execute query
      if($stmt->execute()) {
      return true;
    }
    printf("Error: $s.\n", $stmt->error);

    return false;
}








  // Update req
  public function update() {
    // Create Query
    $query = 'UPDATE '.$this->table . ' SET request_status = :request_status,patient_longitude=:patient_longitude,patient_latitude=:patient_latitude,
    patient_id=:patient_id,paramedic_id=:paramedic_id,disease_state_id=:disease_state_id,request_described=:request_described where request_id=:request_id';

   // Prepare Statement
    $stmt = $this->conn->prepare($query);

  // Clean data
    $this->request_status = htmlspecialchars(strip_tags($this->request_status));
    $this->patient_longitude = htmlspecialchars(strip_tags($this->patient_longitude));
    $this->patient_latitude = htmlspecialchars(strip_tags($this->patient_latitude));
    $this->patient_id = htmlspecialchars(strip_tags($this->patient_id));
    $this->paramedic_id = htmlspecialchars(strip_tags($this->paramedic_id));
    $this->disease_state_id = htmlspecialchars(strip_tags($this->disease_state_id));
    $this->request_described = htmlspecialchars(strip_tags($this->request_described));
    $this->request_id = htmlspecialchars(strip_tags($this->request_id));

    $stmt-> bindParam(':request_status', $this->request_status);
    $stmt-> bindParam(':patient_longitude', $this->patient_longitude);
    $stmt-> bindParam(':patient_latitude', $this->patient_latitude);
    $stmt-> bindParam(':patient_id', $this->patient_id);
    $stmt-> bindParam(':paramedic_id', $this->paramedic_id);
    $stmt-> bindParam(':disease_state_id', $this->disease_state_id);
    $stmt-> bindParam(':request_described', $this->request_described);
    $stmt-> bindParam(':request_id', $this->request_id);

  // Execute query
  if($stmt->execute()) {
    return true;
  }
  printf("Error: $s.\n", $stmt->error);

  return false;
}

  // Delete req
  public function delete() {
    // Create query
    $query = 'DELETE FROM ' . $this->table . ' WHERE request_id = :request_id';

    // Prepare Statement
    $stmt = $this->conn->prepare($query);

    // clean data
    $this->request_id = htmlspecialchars(strip_tags($this->request_id));

    // Bind Data
    $stmt-> bindParam(':request_id', $this->request_id);

    // Execute query
    if($stmt->execute()) {
      return true;
    }

    // Print error if something goes wrong
    printf("Error: $s.\n", $stmt->error);

    return false;
    }
  }