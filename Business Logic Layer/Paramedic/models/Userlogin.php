<?php
  class Userlogin {
    // DB Stuff
    private $conn;
    private $table = 'userlogin';

    // Properties
    public $id;
    public $name;
    public $email;
    public $password;
    public $phone;
    public $age;
    public $date_created;
    public $role_name;
    // Constructor with DB
    public function __construct($db) {
      $this->conn = $db;
    }
    
  public function read() {
      // Create query
      $query = 'SELECT * FROM ' . $this->table;
      // Prepare statement
      $stmt = $this->conn->prepare($query);
      // Execute query
      $stmt->execute();
      return $stmt;
  }
    
  public function read_single(){
    // Create query
    $query = 'SELECT * From ' . $this->table . ' WHERE userlogin_id = ?';

      //Prepare statement
      $stmt = $this->conn->prepare($query);

      // Bind ID
      $stmt->bindParam(1, $this->id);

      // Execute query
      $stmt->execute();

      return $stmt;

  }
  public function read_single_by_roleName(){
    // Create query
      $query = 'SELECT * From ' . $this->table . ' WHERE role_name = ?';

      //Prepare statement
      $stmt = $this->conn->prepare($query);

      // Bind ID
      $stmt->bindParam(1, $this->role_name);

      // Execute query
      $stmt->execute();

      return $stmt;

  }
 
  public function read_email_password(){
    // Create query

      
      $query = 'SELECT * From ' . $this->table . ' WHERE email = ? and password = ?';
      //Prepare statement
      $stmt = $this->conn->prepare($query);
      // Bind ID
      $stmt->bindParam(1, $this->email);
      $stmt->bindParam(2, $this->password);
      // Execute query
      $stmt->execute(); 
      return $stmt;

  }
  public function read_email(){
    // Create query
      $query = 'SELECT * From ' . $this->table . ' WHERE email = ?';
      //Prepare statement
      $stmt = $this->conn->prepare($query);
      // Bind ID
      $stmt->bindParam(1, $this->email);
      // Execute query
      $stmt->execute(); 
      return $stmt;

  }
  
  public function create() {
    // Create Query
    $query = 'INSERT INTO ' .
      $this->table . '
    SET
      full_name = :full_name,
      email = :email,
      password = :password,
      phone = :phone,
      age = :age,
      role_name=:role_name

      ';

  // Prepare Statement
  $stmt = $this->conn->prepare($query);

  // Clean data
  $this->name = htmlspecialchars(strip_tags($this->name));
  $this->email = htmlspecialchars(strip_tags($this->email));
  $this->password = htmlspecialchars(strip_tags($this->password));
  $this->age = htmlspecialchars(strip_tags($this->age));
  $this->phone = htmlspecialchars(strip_tags($this->phone));
  $this->role_name = htmlspecialchars(strip_tags($this->role_name));
  // Bind data
  $stmt-> bindParam(':full_name', $this->name);
  $stmt-> bindParam(':email', $this->email);
  $stmt-> bindParam(':password', $this->password);
  $stmt-> bindParam(':phone', $this->phone);
  $stmt-> bindParam(':age', $this->age);
  $stmt-> bindParam(':role_name', $this->role_name);
  // Execute query
  try{
  if($stmt->execute()) {
    return true;
  } 
}
  catch(Exception $e){
    //printf("Error: $s.\n", $stmt->error);
  }
  // Print error if something goes wrong
  return false;
  }

  
  public function update() {
    // Create Query
    $query = 'UPDATE ' .
      $this->table . '
      SET
      full_name = :full_name,
      email = :email,
      password = :password,
      phone = :phone,
      age = :age,
      role_name=:role_name
      WHERE
      userlogin_id = :id';

  // Prepare Statement
  $stmt = $this->conn->prepare($query);


  // Clean data
  $this->name = htmlspecialchars(strip_tags($this->name));
  $this->email = htmlspecialchars(strip_tags($this->email));
  $this->password = htmlspecialchars(strip_tags($this->password));
  $this->age = htmlspecialchars(strip_tags($this->age));
  $this->phone = htmlspecialchars(strip_tags($this->phone));
  $this->id = htmlspecialchars(strip_tags($this->id));
  $this->role_name = htmlspecialchars(strip_tags($this->role_name));
 
  // Bind data
  $stmt-> bindParam(':full_name', $this->name);
  $stmt-> bindParam(':email', $this->email);
  $stmt-> bindParam(':password', $this->password);
  $stmt-> bindParam(':phone', $this->phone);
  $stmt-> bindParam(':age', $this->age);
  $stmt-> bindParam(':id', $this->id);
  $stmt-> bindParam(':role_name', $this->role_name);
  // Execute query
  try{
    if($stmt->execute()) {
      return true;
    }
    
  }
  
    catch(Exception $e){
  
      //printf("Error: $s.\n", $stmt->error);
    }

  return false;
  }

  
  public function delete() {
    // Create query
    $query = 'DELETE FROM ' . $this->table . ' WHERE userlogin_id = :id';

    // Prepare Statement
    $stmt = $this->conn->prepare($query);

    // clean data
    $this->id = htmlspecialchars(strip_tags($this->id));

    // Bind Data
    $stmt-> bindParam(':id', $this->id);

    // Execute query
    try{
      if($stmt->execute()) {
        return true;
      }
      
    }
    
      catch(Exception $e){
    
        //printf("Error: $s.\n", $stmt->error);
      }
    return false;
    }
  }