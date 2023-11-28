<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav class="navbar navbar-default" role="navigation" style="background-color:#fff">
    <div class="container-fluid">
        <div class="navbar-header" style="margin-left: 8%;margin-right: 1%">
            <a class="navbar-brand " href="user_main.jsp"><p class="text-primary" style="font-family: 华文行楷; font-size: 200%; ">My Library</p></a>
        </div>
        <div class="collapse navbar-collapse" id="example-navbar-collapse">
            <ul class="nav navbar-nav navbar-left">
                <li class="active">
                    <a href="/listAllBooks">
                        List All books
                    </a>
                </li>
                <li >
                    <a href="/queryLendSerial?userId=${user.userId}" >
                        My Lend
                    </a>
                </li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="user_info.jsp">${user.name}, 已登录</a></li>
                <li><a href="../index.jsp">退出</a></li>
            </ul>
        </div>
    </div>
</nav>
