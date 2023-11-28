<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav style="position:fixed;z-index: 999;width: 100%;background-color: #25c6fc" class="navbar navbar-default"
     role="navigation">
    <div class="container-fluid">
        <div class="navbar-header" style="margin-left: 8%;margin-right: 1%">
            <a class="navbar-brand" href="admin_main.jsp" style="font-family: 华文行楷; font-size: 250%; color: white">My Library</a>
        </div>
        <div class="collapse navbar-collapse" >
            <ul class="nav navbar-nav navbar-left">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" style="color: white">
                        Library Management
                        <b class="caret"></b>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="/listAllBooks?">List All Books</a></li>
                        <li><a href="book_add.jsp">Add Books</a></li>
                    </ul>
                </li>

                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" style="color: white">
                        Lend Management
                        <b class="caret"></b>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="../lend/lendSerial.jsp">Lend Serial</a></li>
                    </ul>
                </li>

            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a style="color: white">Hello ${user.username}</a></li>
                <li><a href="../index.jsp" style="color: white">Log out</a></li>
            </ul>
        </div>
    </div>
</nav>
