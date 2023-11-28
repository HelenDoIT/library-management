<html>
<head>
  <link rel="stylesheet" href="/static/css/bootstrap.min.css">
  <%@include file="../common.jsp"%>
  <script type="text/javascript">
    $(function () {
      $("#submitBtn").on("click",function () {
        $("#addBookForm").form('submit',{
          url:"/bookInfo",
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
</head>

<body>
  <form id="addBookForm" action="<%= request.getContextPath()%>/bookInfo"  mathod='post'>
    <h3>Please Add a book</h3>
    <p>Book name: <input type='text' name='bookname'></p>
    <p>Author: <input type='text' name='author'></p>
    <p>Inventory: <input type='text' name='inventory'></p>
    <p><input id="submitBtn" type='submit' name="action" value="addBook"></p>
  </form>
</body>
</html>
