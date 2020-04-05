<html>
<head>
    <title>Registeration Form</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
</head>
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
<body style="background: url('Images/leb.jpg');background-repeat: no-repeat;background-size: cover;background-position:center;">
  <div class="center-block col-md-4"style="width:50%;margin-left:auto;margin-right:auto;padding:1% 2%;float: right;background-color:black;">
    <form action="register_process.php" method="post" enctype="multipart/form-data">
            <h6 style="color:white">First Name</h6>
            <div class="form-group">
                <input type="text" class="form-control" name="first_name" placeholder="First Name" aria-describedby="emailHelp" required>
            </div>
            <h6 style="color:white">Last Name</h6>
            <div class="form-group">
                <input type="text" class="form-control" name="last_name" placeholder="Last Name" aria-describedby="emailHelp" required>
            </div>
            <h6 style="color:white">Email</h6>
            <div class="form-group">
                <input type="email" class="form-control" name="email" placeholder="User Email" aria-describedby="emailHelp" required>
            </div>
            <h6 style="color:white">Password</h6>
            <div class="form-group">
                <input type="password" class="form-control" name="password" placeholder="User Password" required>
            </div>
            <h6 style="color:white">Phone Number</h6>
            <div class="form-group">
                <input type="text" class="form-control" name="phoneNumber" placeholder="Phone Number" aria-describedby="emailHelp" required>
            </div>
            <h6 style="color:white">Gender</h6>
            <div class="form-group">
              <input type="radio" id="male" name="gender" value="male">
              <label for="male" style="color:white">Male</label><br>
              <input type="radio" id="female" name="gender" value="female">
              <label for="female" style="color:white">Female</label><br>
              <input type="radio" id="other" name="gender" value="other">
              <label for="other" style="color:white">Other</label>
            </div>
            <h6 style="color:white">Address</h6>
            <div class="form-group">
              <textarea rows="3" cols="50" name="address" placeholder="Address" required></textarea>
            </div>
            <h6 style="color:white">User DP</h6>
            <div class="form-group">
              <input type="file" class="form-control-file" name="image" required>
            </div>
            <button type="submit" class="btn btn-primary btn-lg btn-block" name="add_record" style="padding:10px 0px;">Register</button>
        </form>
  </div>
</body>
</html>
