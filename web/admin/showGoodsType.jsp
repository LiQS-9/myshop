<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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

<title>商品分类</title>
</head>
<body>
<div class="row" style="width:98%;margin-left: 1%;">
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">
				商品类型
			</div>
			<div class="panel-body">
				<div class="row">
					<div class="col-xs-5 col-sm-5 col-md-5 col-lg-5">
						<div class="form-group form-inline">
							<span>商品名称</span>
							<input type="text" name="tname"  id= "tname""class="form-control">
						</div>
					</div>
					<div class="col-xs-2 col-sm-2 col-md-2 col-lg-2">
						<button type="button" class="btn btn-primary" id="search"><span class="glyphicon glyphicon-search"></span></button>
					</div>
				</div>
				<div style="height: 400px;overflow: scroll;">
				<table id="tb_list" class="table table-striped table-hover table-bordered">
					<tr>
						<td>序号</td><td>类型</td><td>类型描述</td><td>操作</td>
					</tr>
					<c:forEach items="${goodsTypeList}" var="gtype" varStatus="i">
					<tr>
						<td>${gtype.tid}</td>
						<td>${gtype.tname}</td>
						<td>${gtype.tinfo}</td>
						<td>
							<button><a href="typetodo.do?method=preupdateGoodType&tid=${gtype.tid}">修改</a></button>&nbsp;&nbsp;
							<%--<button id="delete"  name="${gtype.tid}">删除</button>--%>
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

        // alert("别点了，差不多的功能程序媛不想写")
        location.href = "typetodo.do?method=search&tname="+$("#tname").val()
      })
	  $("#delete").click(function () {
        // var value = $(this).attr("name"); // $(this)表示获取当前被点击元素的name值
        var tid = $(this).attr("name");
        console.log("点击")
		console.log(tid)
        $.ajax({
          url:"typetodo.do?method=delete&tid="+tid,
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
    // //条件查询
    // $(function(){
    //   //给查询按钮 添加 点击事件
    //   $("#search").click(function(){
    //     var tid = $("input[name='tname']").val();
    //
    //     //使用ajax 进行异步交互
    //     $.ajax({
    //       url:".do?method=searchUser&username="+username+"&gender="+gender,
    //       method:"post",
    //       success:function(data){
    //         if(data==0){
    //           alert("未找到指定内容");
    //           $("input[name='username']").val("");
    //           $("input[name='gender']").removeAttr("checked");
    //         }else{
    //           showMsg(data);
    //         }
    //       },
    //       error:function(XMLHttpRequest,textStatus,errorThrown){
    //         alert("失败"+XMLHttpRequest.status+":"+textStatus+":"+errorThrown);
    //       }
    //     })
    //   })
</script>