<html>
<head>
    <title>Login Form</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
</head>
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
<body style="background: url('Images/back_img.jpg');background-repeat: no-repeat;background-size: cover;background-position:center;">
  <div class="center-block col-md-4" align="center" style="width:50%;margin-left:auto;margin-right:auto;padding:17% 0px;">
    <form action="login_process.php" method="post">
            <div class="form-group">
                <input type="email" class="form-control" name="email" placeholder="User Email" aria-describedby="emailHelp" required>
            </div>
            <div class="form-group">
                <input type="password" class="form-control" name="password" placeholder="User Password" required>
            </div>
            <button type="submit" class="btn btn-primary btn-lg btn-block" name="add_record">Login</button>
            <a href="register.php">
              <h4 style="color:white;padding-top:10px;">New User?</h4>
            </a>
        </form>
  </div>
</body>
</html>
