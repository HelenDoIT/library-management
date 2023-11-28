
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>
    <link rel="stylesheet" href="/static/css/bootstrap.min.css">
    <%@include file="../common.jsp"%>
    <title>List All Books</title>
    <script>
        $(function () {
            $('#header').load('admin_header.jsp');
        })
    </script>
</head>

<script type="text/javascript">
    $(function () {
        $("#submitBtn").on("click",function () {
            var val=$("#search").val();
            if(val==''){
                alert("Please Enter Book Name");
                return false;
            }
            $("#searchform").form('submit',{
                url:"/queryBook",
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

<body background="img/book1.jpg" style=" background-repeat:no-repeat ;
background-size:100% 100%;
background-attachment: fixed;">

<div id="header"></div>

<div style="padding: 70px 550px 10px">
    <form   method="post" action="/querybook" class="form-inline"  id="searchform">
        <div class="input-group">
           <input type="text" placeholder="Please Enter Book Name" class="form-control" id="search" name="bookName" class="form-control">
            <span class="input-group-btn">
                <input type="submit" value="Search" class="btn btn-default">
            </span>
        </div>
    </form>
    <%--<script>
        $("#searchform").submit(function () {
            var val=$("#search").val();
            if(val==''){
                alert("请输入关键字");
                return false;
            }
        })
    </script>--%>
</div>

<div class="panel panel-default" style="width: 90%;margin-left: 5%">
    <div class="panel-heading" style="background-color: #fff">
        <h3 class="panel-title">
            All Books
        </h3>
    </div>
    <div class="panel-body">
        <table class="table table-hover">
            <thead>
            <tr>
                <th>BookName</th>
                <th>Author</th>
                <th>Inventory</th>
                <th>Operation</th>
            </tr>
            </thead>
            <tbody>
            <forEach items="${books}" var="book">
            <tr>
                <td><out value="${book.name}"></out></td>
                <td><out value="${book.author}"></out></td>
                <td><out value="${book.inventory}"></out></td>
                <td><a href="/delete?bookId=${book.bookId}">
                    <button type="button" class="btn btn-info btn-xs">delete</button></a></td>
            </tr>
            </forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
