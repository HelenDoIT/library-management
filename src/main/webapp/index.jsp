<%@ page language="java"
         contentType="text/html; charset=UTF-8"
         isErrorPage="true"
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<body>
<h2>My Library</h2>

    <form action='/login' mathod='post'>
        <h3>Please Login</h3>
        <p>Username: <input type='text' name='username'></p>
        <p>Password: <input type='text' name='password'></p>
        <p><input type='submit' name='login'></p>
        <a href='/param/list'></a>
    </form>
<h3>No Account?</h3>

    <form action='/register' mathod='post'>
        <h3>Please Sign Up</h3>
        <p>Username: <input type='text' name='username'></p>
        <p>Role:
            <input type="radio" name="role"  value="admin"/>admin
            <input type="radio" name="role"  value="user"/>user
        </p>
        <p>Password: <input type='text' name='password'></p>
        <p><input type='submit' name='register'></p>
        <a href='/param/list'></a>
    </form>

</body>
</html>
