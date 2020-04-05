<?php
    include 'Include/config.php';
    session_start();

    if(isset($_POST['add_record'])){
      $email = $_POST['email'];
      $pass = $_POST['password'];

      $query = "SELECT * FROM users WHERE Email='$email' ";
      $data = mysqli_query($com,$query);
      $row = mysqli_num_rows($data);
      if($row){
        while($result = mysqli_fetch_assoc($data)){
          if(password_verify($pass, $result['Password'])){
            $_SESSION['Email'] = $result['Email'];
            $_SESSION['FirstName'] = $result['FirstName'];
            $_SESSION['LastName'] = $result['LastName'];
            $_SESSION['PhoneNumber'] = $result['PhoneNumber'];
            $_SESSION['add'] = $result['Address'];
            $_SESSION['image'] = $result['Image'];
            header('location: index.php');
          }
        }
      }else{
        header('location: login.php');
      }
    }
?>
