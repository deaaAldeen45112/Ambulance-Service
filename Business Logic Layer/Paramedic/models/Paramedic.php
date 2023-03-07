<?php
  class Paramedic {
    // DB Stuff
    private $conn;
    private $table = 'paramedic';

    // Properties
    public $paramedic_id;
    public $userlogin_id;
    public $paramedic_status;	
    public $paramedic_longitude;	
    public $paramedic_latitude;
   
    public function __construct($db) {
      $this->conn = $db;
    }

    // Get categories
    public function read() {
      // Create query
      $query = 'SELECT * FROM ' . $this->table;

      // Prepare statement
      $stmt = $this->conn->prepare($query);

      // Execute query
      $stmt->execute();

      return $stmt;
    }


    public function read_paramedic_join_userlogin() {
      // Create query
      $query = 'SELECT paramedic.paramedic_id,paramedic.userlogin_id,paramedic.paramedic_status,
      paramedic.paramedic_longitude,paramedic.paramedic_latitude,
      userlogin.full_name,userlogin.phone,userlogin.age,userlogin.email
      FROM paramedic INNER JOIN userlogin on paramedic.userlogin_id=userlogin.userlogin_id where paramedic_status=?';

      // Prepare statement
      $stmt = $this->conn->prepare($query);


      // Bind ID
      $stmt->bindParam(1, $this->paramedic_status);

      // Execute query
      $stmt->execute();

      return $stmt;
    }



    // Get Single Category 
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

     public function read_by_paramedic_id(){
    // Create query
      $query = 'SELECT * From ' . $this->table . ' WHERE paramedic_id = ?';

      //Prepare statement
      $stmt = $this->conn->prepare($query);

      // Bind ID
      $stmt->bindParam(1, $this->paramedic_id);

      // Execute query
      $stmt->execute();

      return $stmt;
  }

  
  // Create Category
  public function create() {
    // Create Query
    $query = 'INSERT INTO ' .$this->table . ' SET userlogin_id = :userlogin_id';
   // Prepare Statement
    $stmt = $this->conn->prepare($query);

  // Clean data
    $this->userlogin_id = htmlspecialchars(strip_tags($this->userlogin_id));

    $stmt-> bindParam(':userlogin_id', $this->userlogin_id);

  // Execute query
  if($stmt->execute()) {
    return true;
  }

  // Print error if something goes wrong
  printf("Error: $s.\n", $stmt->error);

  return false;
  }
/*


DELIMITER &&  
CREATE PROCEDURE get_userlogin_join_paramedic ()  
BEGIN  

SELECT userlogin.full_name,userlogin.email,userlogin.phone,userlogin.userlogin_id,paramedic_id,paramedic_status,paramedic.paramedic_longitude,paramedic_latitude FROM paramedic INNER JOIN userlogin on paramedic.userlogin_id=userlogin.userlogin_id; 
END &&  
DELIMITER ;  
SELECT userlogin.full_name,userlogin.email,userlogin.phone,userlogin.userlogin_id,paramedic_id,paramedic_status,paramedic.paramedic_longitude,paramedic_latitude FROM paramedic INNER JOIN userlogin on paramedic.userlogin_id=userlogin.userlogin_id */
  // Update Category
 
 
 
 
  public function read_parmedic_join_userlogin_parmedic_by_paramedic_status(){
    // Create query
      $query = 'SELECT userlogin.full_name,userlogin.email,userlogin.phone,userlogin.userlogin_id,paramedic_id,paramedic_status,paramedic.paramedic_longitude,paramedic_latitude FROM paramedic INNER JOIN userlogin on paramedic.userlogin_id=userlogin.userlogin_id WHERE paramedic_status = ?';

      //Prepare statement
      $stmt = $this->conn->prepare($query);

      // Bind ID
      $stmt->bindParam(1, $this->paramedic_status);

      // Execute query
      $stmt->execute();

      return $stmt;
  }

 
 
 
 
  public function update() {
    // Create Query
    $query = 'UPDATE ' .
      $this->table . ' SET
      userlogin_id = :userlogin_id
      WHERE
      paramedic_id = :paramedic_id';

  // Prepare Statement
  $stmt = $this->conn->prepare($query);

  // Clean data
  $this->paramedic_id = htmlspecialchars(strip_tags($this->paramedic_id));
  $this->userlogin_id = htmlspecialchars(strip_tags($this->userlogin_id));
  // Bind data
  $stmt-> bindParam(':paramedic_id', $this->paramedic_id);
  $stmt-> bindParam(':userlogin_id', $this->userlogin_id);
  // Execute query
  if($stmt->execute()) {
    return true;
  }

  // Print error if something goes wrong
  printf("Error: $s.\n", $stmt->error);

  return false;
  }



  public function update_status_by_paramedic_id() {
    // Create Query
    $query = 'UPDATE ' .
      $this->table . ' SET
      paramedic_status=:paramedic_status
      WHERE
      paramedic_id = :paramedic_id';

  // Prepare Statement
  $stmt = $this->conn->prepare($query);

  // Clean data
  $this->paramedic_id = htmlspecialchars(strip_tags($this->paramedic_id));
  $this->paramedic_status = htmlspecialchars(strip_tags($this->paramedic_status));
  // Bind data
  $stmt-> bindParam(':paramedic_id', $this->paramedic_id);
  $stmt-> bindParam(':paramedic_status', $this->paramedic_status);
  // Execute query
  if($stmt->execute()) {
    return true;
  }

  // Print error if something goes wrong
  printf("Error: $s.\n", $stmt->error);

  return false;
  }



  public function update_location_by_paramedic_id() {
    // Create Query
    $query = 'UPDATE ' .
      $this->table . ' SET
      paramedic_longitude=:paramedic_longitude,
      paramedic_latitude=:paramedic_latitude
      WHERE
      paramedic_id = :paramedic_id';

  // Prepare Statement
  $stmt = $this->conn->prepare($query);

  // Clean data
  $this->paramedic_id = htmlspecialchars(strip_tags($this->paramedic_id));
  $this->paramedic_longitude = htmlspecialchars(strip_tags($this->paramedic_longitude));
  $this->paramedic_latitude = htmlspecialchars(strip_tags($this->paramedic_latitude));
 
  // Bind data
  $stmt-> bindParam(':paramedic_id', $this->paramedic_id);
  $stmt-> bindParam(':paramedic_longitude', $this->paramedic_longitude);
  $stmt-> bindParam(':paramedic_latitude', $this->paramedic_latitude);
 
 
  // Execute query
  if($stmt->execute()) {
    return true;
  }

  // Print error if something goes wrong
  printf("Error: $s.\n", $stmt->error);

  return false;
  }















  // Delete Category
  public function delete() {
    // Create query
    $query = 'DELETE FROM ' . $this->table . ' WHERE paramedic_id = :paramedic_id';

    // Prepare Statement
    $stmt = $this->conn->prepare($query);

    // clean data
    $this->paramedic_id = htmlspecialchars(strip_tags($this->paramedic_id));

    // Bind Data
    $stmt-> bindParam(':paramedic_id', $this->paramedic_id);

    // Execute query
    if($stmt->execute()) {
      return true;
    }

    // Print error if something goes wrong
    printf("Error: $s.\n", $stmt->error);

    return false;
    }
  }