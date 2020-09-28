<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
	<base href="<%=request.getContextPath()%>/"/>
<link rel="stylesheet" href="css/bootstrap.min.css" />
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/DatePicker.js"></script>
<title>商品列表</title>

</head>
<body>
<div class="row" style="width:98%;margin-left: 1%;">
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">
				会员列表
			</div>
			<div class="panel-body">
				<div class="row">
					<div class="col-xs-5 col-sm-5 col-md-5 col-lg-5">
						<div class="form-group form-inline">
							<span>商品名称</span>
							<input type="text" name="name" id="pname" class="form-control">
						</div>
					</div>
					<div class="col-xs-5 col-sm-5 col-md-5 col-lg-5">
						<div class="form-group form-inline">
							<span>上架时间</span>
							<input type="text" readonly="readonly" id="ptime" name="pubdate" class="form-control" onclick="setday(this)">
						</div>
					</div>
					<div class="col-xs-2 col-sm-2 col-md-2 col-lg-2">
						<button type="button" class="btn btn-primary" id="search"><span class="glyphicon glyphicon-search"></span></button>
					</div>
				</div>
				<div style="height: 400px;overflow: scroll;">
					<table id="tb_list" class="table table-striped table-hover table-bordered">
						<tr>
							<td>序号</td><td>商品名称</td><td>价格</td><td>上架时间</td><td>类型</td><td>操作</td>
						</tr>
						<c:forEach items="${goodsList}" var="goods" varStatus="i">
							<tr>
								<%--<td>${i.count}</td>--%>
								<td>${goods.pid}</td>
								<td>${goods.pname}</td>
								<td>${goods.pprice}</td>
								<td>${goods.ptime}</td>
								<td>${goods.type.tname}</td>
								<td>
									<%--<button id="delete"  name="${goods.pid}">删除</button>&nbsp;--%>
									<%--<a href="goodtodo.do?method=delGoodById&pid=${goods.pid}">删除</a>&nbsp;--%>
									<a href="goodtodo.do?method=preupdateGood&pid=${goods.pid}">修改 </a>&nbsp;

									<a tabindex="0" id="example${goods.pid}" class="btn btn-primary btn-xs"
									role="button" data-toggle="popover"
									data-trigger="focus"
									data-placement="left"

									data-content="${goods.pinfo}">描述</a>

									<script type="text/javascript">
										$(function(){
											$("#example${goods.pid}").popover();
										})
									</script>
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
<script>
  $(function () {
    $("#search").click(function () {
      pname = $("#pname").val()
	  ptime = $("#ptime").val()

      // alert("别点了，差不多的功能程序媛不想写le")
	  if(pname==null || ptime ==null){
	    alert("不可以为空")
	  }else {
        location.href = "goodtodo.do?method=search&pname="+pname+"&ptime="+ptime
	  }


    })

    $("#delete").click(function () {
      // var value = $(this).attr("name"); // $(this)表示获取当前被点击元素的name值
      var pid = $(this).attr("name");
      console.log("点击")
      console.log(pid)
      $.ajax({
        url:"goodtodo.do?method=delete&pid="+pid,
        method:"post",
        success:function(data){
          if(data==1){
            alert("删除成功");
            $(this).remove();
          }else{
            alert("删除失败")
          }
        },
        error:function(XMLHttpRequest,textStatus,errorThrown){
          alert("失败"+XMLHttpRequest.status+":"+textStatus+":"+errorThrown);
        }
      })
    })
  })
</script>