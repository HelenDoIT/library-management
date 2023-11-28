<%@ page language="java"
         contentType="text/html; charset=UTF-8"
         isErrorPage="true"
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="static/css/bootstrap.min.css">
    <%@include file="jspviews/common.jsp"%>

<script type="text/javascript">
    $(function () {
        $("#submitBtn").on("click",function () {
            $("#loginForm").form('submit',{
                url:"/login",
                success:function (data) {
                    data =$.parseJSON(data);
                    if(data.success){
                        console.log(data);
                    }else{
                        $.messager.alert(data.errorMsg,"error");
                    }
                }
            });
        })
    });

    $(function () {
        $("#signUpBtn").on("click",function () {
            $("#registerForm").form('submit',{
                url:"/login",
                success:function (data) {
                    data =$.parseJSON(data);
                    if(data.success){
                        console.log(data);
                    }else{
                        $.messager.alert(data.errorMsg,"error");
                    }
                }
            });
        })
    });

</script>

<body>
<h2>My Library</h2>

    <form id="loginForm" action="<%= request.getContextPath()%>/login"  mathod='post'>
        <h3>Please Login</h3>
        <if test="${usernameError!=null }">
            <span style="color: red">${usernameError}</span>
        </if>
        <%--<span style="color: red">${usernameError}</span>--%>
        <p>Username: <input type='text' name='username'></p>
        <%--<span style="color: red">${passwordError}</span>--%>
        <p>Password: <input type='text' name='password'></p>
        <p><input id="submitBtn" type='submit' name="action" value="login"></p>
    </form>

<h4>No Account?</h4>

    <form id="registerForm" action="<%= request.getContextPath()%>/login" mathod='post'>
        <h3>Please Sign Up</h3>
        <p>Username: <input type='text' name='username'></p>
        <p>Role:
            <input type="radio" name="role"  value="admin"/>admin
            <input type="radio" name="role"  value="user"/>user
        </p>
        <p>Password: <input type='text' name='password'></p>
        <p><input id="signUpBtn"  type='submit' name="action" value="register"></p>
    </form>

</body>

</html>
