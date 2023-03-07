<?php
  class DiseaseStates {
    // DB Stuff
    private $conn;
    private $table = 'disease_states';

    // Properties
    public $disease_states_id;
    public $disease_states_name;
   
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

    // Get Single Category 
     public function read_by_disease_states_id(){
    // Create query
      $query = 'SELECT * From ' . $this->table . ' WHERE disease_states_id = ?';

      //Prepare statement
      $stmt = $this->conn->prepare($query);

      // Bind ID
      $stmt->bindParam(1, $this->disease_states_id);

      // Execute query
      $stmt->execute();

      return $stmt;
     
  }

  /*   public function read_by_admin_id(){
    // Create query
      $query = 'SELECT * From ' . $this->table . ' WHERE admin_id = ?';

      //Prepare statement
      $stmt = $this->conn->prepare($query);

      // Bind ID
      $stmt->bindParam(1, $this->admin_id);

      // Execute query
      $stmt->execute();

      return $stmt;
  }*/

  
  // Create Category
  public function create() {
    // Create Query
    $query = 'INSERT INTO ' .$this->table . ' SET disease_states_name = :disease_states_name';
   // Prepare Statement
    $stmt = $this->conn->prepare($query);

  // Clean data
    $this->disease_states_name = htmlspecialchars(strip_tags($this->disease_states_name));

    $stmt-> bindParam(':disease_states_name', $this->disease_states_name);

  // Execute query
  if($stmt->execute()) {
    return true;
  }

  // Print error if something goes wrong
  printf("Error: $s.\n", $stmt->error);

  return false;
  }

  // Update Category
  public function update() {
    // Create Query
    $query = 'UPDATE ' .
      $this->table . ' SET
      disease_states_name = :disease_states_name
      WHERE
      disease_states_id = :disease_states_id';

  // Prepare Statement
  $stmt = $this->conn->prepare($query);

  // Clean data
  $this->disease_states_name = htmlspecialchars(strip_tags($this->disease_states_name));
  $this->disease_states_id = htmlspecialchars(strip_tags($this->disease_states_id));
  // Bind data
  $stmt-> bindParam(':disease_states_id', $this->disease_states_id);
  $stmt-> bindParam(':disease_states_name', $this->disease_states_name);
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
    $query = 'DELETE FROM ' . $this->table . ' WHERE disease_states_id = :disease_states_id';

    // Prepare Statement
    $stmt = $this->conn->prepare($query);

    // clean data
    $this->disease_states_id = htmlspecialchars(strip_tags($this->disease_states_id));

    // Bind Data
    $stmt-> bindParam(':disease_states_id', $this->disease_states_id);

    // Execute query
    if($stmt->execute()) {
      return true;
    }

    // Print error if something goes wrong
    printf("Error: $s.\n", $stmt->error);

    return false;
    }
  }