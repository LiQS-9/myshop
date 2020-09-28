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
<title>修改商品种类</title>
<script>

</script>
</head>
<body>
<div style="width:98%;margin-left: 1%;">
	<div class="panel panel-default">
		<div class="panel-heading">
			修改商品种类
		</div>
		<div class="panel-body">
			<form action="typetodo.do?method=updateGoodsType" method="post">
				<input type="hidden" name="tid" value="${type.tid}">
				<div class="row">
					<div class="form-group form-inline">
						<span>种类名称</span>
						<input type="text" name="typename" value="${type.tname}" class="form-control">
					</div>
				</div>
				<div class="row">
					<div class="form-group form-inline">
						<span>种类描述</span>
						<input type="text" name="typeinfo" value="${type.tinfo}" class="form-control">
					</div>
				</div>
				<div class="row">
					<div class="btn-group">
						<button type="reset" class="btn btn-default">清空</button>
						<button type="submit" class="btn btn-default">确定</button>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>
</body>
</html>