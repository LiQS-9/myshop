<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>后台 订单列表</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
	<base href="<%=request.getContextPath()%>/"/>
<link rel="stylesheet" href="css/bootstrap.min.css">
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>

</head>
<body>
<div class="row" style="width:98%;margin-left: 1%;margin-top: 5px;">
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">
				订单列表
			</div>
			<div class="panel-body">
				<div class="row">
					<div class="col-xs-5 col-sm-5 col-md-5 col-lg-5">
						<div class="form-group form-inline">
							<span>用户姓名</span>
							<input type="text" name="username" id="uname" class="form-control">
						</div>
					</div>
					<div class="col-xs-5 col-sm-5 col-md-5 col-lg-5">
						<div class="form-group form-inline">
							<span>订单状态</span>
							<select name="orderStatus" id="ostate" class="form-control">
								<%--订单状态 0 未付款，1已经付款未发货 2发货待收货 3 收货待评价
								4订单完成 5 退货状态--%>
								<option value=" ">----------</option>
								<option value="0">未支付</option>
								<option value="1">已支付,待发货</option>
								<option value="2">已发货,待收货</option>
								<%--<option value="4">完成订单</option>--%>
							</select>
						</div>
					</div>
					<div class="col-xs-2 col-sm-2 col-md-2 col-lg-2">
						<button type="button" class="btn btn-primary" id="search"><span class="glyphicon glyphicon-search"></span></button>
					</div>
				</div>
				<div style="height: 400px;overflow: scroll;">
				<table id="tb_list" class="table table-striped table-hover table-bordered table-condensed">
					<tr>
						<td>序号</td>
						<td>订单编号</td>
						<td>总金额</td>
						<td>订单状态</td>
						<td>订单时间</td>
						<td>用户姓名</td>
						<td>操作</td>
					</tr>
					<c:forEach items="${orderList}" var="order" varStatus="i">
					<tr>
						<td>${i.count}</td>
						<td>${order.oid}</td>
						<td>${order.ocount}</td>
						<td>
								<%--订单状态 0 未付款，1已经付款未发货 2发货待收货 3 收货待评价
                                    4订单完成 5 退货状态--%>
							<c:if test="${order.ostate eq 0}">
								未支付
							</c:if>
							<c:if test="${order.ostate eq 1}">
								已支付,待发货
							</c:if>
							<c:if test="${order.ostate eq 2}">
								已发货,待收货
							</c:if>
							<%--<c:if test="${order.ostate eq 4}">--%>
								<%--订单完成--%>
							<%--</c:if>--%>
						</td>
						<td>${order.otime}</td>
						<td>${order.user.uname}</td>
						<td>
							<c:if test="${order.ostate eq 1}">
								<button type="button" class="btn btn-danger btn-sm" onclick="sendOrder('${order.oid}')">发货</button>
							</c:if>
						</td>
					</tr>
					</c:forEach>
					
				</table>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>
<script type="text/javascript">
  function sendOrder(id){
    alert("太菜，发不了货")

    // location.href = "sendOrder?oid="+id;
  }
  $(function(){
    $("#search").click(function(){
    //       uname = $("#uname").val()
    //       ostate = $("#ostate option:selected").val()
	//
    //       // alert("别点了，差不多的功能程序媛不想写le")
    //       if(uname==null || ostate ==null){
    //         alert("不可以为空")
    //       }else {
    //         location.href = "admin.do?method=search&uname="+uname+"&ostate="+ostate
    //       }
	//
    //     })

        var username = $("input[name='username']").val();
        var status = $("select[name='orderStatus'] option:selected").val();
        location.href="admin.do?method=search&uname="+username+"&ostate="+status;
      })
    })
</script>