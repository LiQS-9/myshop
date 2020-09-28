<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <base href="<%=request.getContextPath()%>/"/>
    <link rel="stylesheet" href="css/bootstrap.min.css" />
    <title>下单成功</title>
</head>
<body>
<%@ include file="header.jsp" %>

<div class="container">
    <hr>
    <div class="row" style="width: 30%;margin: 0 auto;padding-top: 20px">
        <div class="panel panel-success">
            <div class="panel-heading">
                <h3 class="panel-title">订单提示</h3>
            </div>
            <div class="panel-body">
                <h3 class="text-default"><span class="glyphicon glyphicon-ok-sign"></span>&nbsp;&nbsp;&nbsp;&nbsp${param.oid}下单成功!!</h3>
                <hr>
                <a href="order.do?method=showMineList&uid=${loginUser.uid}" class="btn btn-primary">查看全部订单</a>&nbsp;&nbsp;&nbsp;&nbsp;
                <a href="index.jsp" class="btn btn-default">继续购物</a>
            </div>
        </div>

    </div>

</div>

<%@ include file="footer.jsp" %>
</body>
</html>