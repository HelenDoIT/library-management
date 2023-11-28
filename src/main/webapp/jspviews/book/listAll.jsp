
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <%@include file="../common.jsp"%>
</head>
<body>

    <form id="book_form" method="post">
        <input type="hidden" name="id">
        <div align="center" style="margin-top: 10px;" >
            <div>
                <input type="text" name="username" class="easyui-textbox" data-options="label:'用户名:',labelPosition:'top', width:150">
            </div>
            <div>
                <input type="text" name="realname" class="easyui-textbox" data-options="label:'真实姓名:',labelPosition:'top', width:150">
            </div>
            <div>
                <input type="text" name="tel" class="easyui-textbox" data-options="label:'联系方式:',labelPosition:'top', width:150">
            </div>
            <div>
                <input type="text" name="email" class="easyui-textbox" data-options="label:'邮箱:',labelPosition:'top', width:150">
            </div>
        </div>
    </form>

</body>
</html>
