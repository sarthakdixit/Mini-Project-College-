<?php
    include 'Include/config.php';
    session_start();
?>
<html>
<head>
    <title>Edit Profile Form</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
</head>
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
<body style="background: url('Images/army_boots.jpg');background-repeat: no-repeat;background-size: cover;background-position:center;">
  <div class="center-block col-md-4"style="width:50%;margin-left:auto;margin-right:auto;padding:9% 2%;background-color:black;float: left;">
    <h3 style="color:white;">Edit Details</h3>
    <form action="edit_profile_process.php" method="post">
            <h6 style="color:white">First Name</h6>
            <div class="form-group">
                <input type="text" class="form-control" name="first_name" placeholder="First Name" aria-describedby="emailHelp" required>
            </div>
            <h6 style="color:white">Last Name</h6>
            <div class="form-group">
                <input type="text" class="form-control" name="last_name" placeholder="Last Name" aria-describedby="emailHelp" required>
            </div>
            <h6 style="color:white">Phone Number</h6>
            <div class="form-group">
                <input type="text" class="form-control" name="phoneNumber" placeholder="Phone Number" aria-describedby="emailHelp" required>
            </div>
            <h6 style="color:white">Address</h6>
            <div class="form-group">
              <textarea rows="3" cols="50" name="address" placeholder="Address" required></textarea>
            </div>
            <button type="submit" class="btn btn-primary btn-lg btn-block" name="add_record" style="padding:10px 0px;">Edit Details</button>
        </form>
  </div>
</body>
</html>
