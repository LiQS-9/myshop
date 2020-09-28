<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <base href="<%=request.getContextPath()%>/"/>
<link rel="stylesheet" type="text/css" href="css/login2.css">
<link href="css/bootstrap.min.css" rel="stylesheet">
<script type="text/javascript" src="js/jquery.min.js"></script>
<title>头部</title>
    <script type="text/javascript">
        $(document).ready(function(){
            $.ajax({
                url:"type.do",
                type:"GET",
                dataType:"json",
                success:function(data){

                  console.log(data)
                  // $.each(data, function(index, obj) {
                  //   // console.log(index) // 下标
                  //   // console.log(obj) // 数组元素
                  //   console.log(obj)
                  //   var a = $("<a href='product.do?method=list&tid="+obj.data.tid+"'>"+obj.data.tname+"</a>");
                  //   $("#goodsType").append(a);
                  // });
                    for(var i in data.data){
                      console.log(data.data[i]);
                        var a = $("<a href='product.do?method=list&tid="+data.data[i].tid+"'>"+data.data[i].tname+"</a>");
                        $("#goodsType").append(a);

                    }
                },
                error:function(){
                    alert("失败");
                }
            })
        })
    </script>
</head>
<body>
				
 <div id="top">
    	<div id="topdiv">
            <span>
                <a href="index.jsp" id="a_top" target="_blank">小米商城</a>
                <li>|</li>
                <a href="" id="a_top">小米商城移动版</a>
                <li>|</li>
                <a href="" id="a_top">问题反馈</a>
            </span>

            <span style="float:right">
           		<c:if test="${empty loginUser}">
                    <a href="login.jsp" id="a_top">登录</a>
                    <li>|</li>
                    <a href="register.jsp" id="a_top">注册</a>
                </c:if>
       			<c:if test="${not empty loginUser}">
                    <a href="address.do?method=showAddressList" id="a_top">${loginUser.uname}</a>
                    <li>|</li>
                    <a href="user.do?method=logOut" id="a_top">注销</a>
                    <li>|</li>
                    <a href="order.do?method=showMineList" id="a_top">我的订单</a>
                    <li>|</li>
                    <a href="address.do?method=showAddressList" id="a_top">地址管理</a>
                </c:if>
                <li>|</li>
                <a href="" id="a_top">消息通知</a>
                <a href="cart.do?method=list&uid=${loginUser.uid}" id="shorpcar">购物车</a>
            </span>
        </div>
 </div>
<div id="second">
	    <a href="" id="seimg" style=" margin-top:23px;"><img id="logo" src="image/logo_top.png" width="55" height="54"/></a>
        <a href="" id="seimg" style=" margin-top:17px;"><img id="gif" src="image/yyymix.gif" width="180" height="66" /></a>
        <p id="goodsType">
			<!-- 根据ajax 回调函数 填写数据 到此id中 -->

        </p>
       <form action="index.jsp" class="form-inline pull-right" style="margin-top: 40px;margin-right: 10px;">
		
		  <div class="form-group">
		    <input type="text" class="form-control" style="width: 400px"  placeholder="搜索一下好东西...">
		  </div>
		  <button type="submit" class="btn btn-warning"><span class="glyphicon glyphicon-search"></span>&nbsp;&nbsp;搜索</button>
	  </form>
</div>
</body>
</html>