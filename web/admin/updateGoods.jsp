<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	<title>商品修改页面</title>
</head>
<body>
<div class="row" style="margin-left: 20px;">
	<form action="goodtodo.do?method=updateGood" method="post" enctype="multipart/form-data">
		<input type="hidden" name="pid" value="${g.pid}">
		<div>
			<h3>修改商品</h3>
		</div>
		<hr />
		<div class="row">
			<div class="col-sm-6">
				<div class="form-group form-inline">
					<label>名称:</label>
					<input type="text" name="name" class="form-control" value="${g.pname}" />
				</div>

				<div class="form-group form-inline">
					<label>分类:</label>

					<select name="typeid" class="form-control">
						<option value="0">------</option>
						<c:forEach items="${typeList}" var="t">
						<option <c:if test="${t.tid==g.tid}">selected</c:if> value="${t.tid}" >${t.tname}</option>
						</c:forEach>
					</select>
				</div>
				<div class="form-group form-inline">
					<label>时间:</label>
					<input type="text" name="pubdate" value="${g.ptime}" readonly="readonly" class="form-control" onclick="setday(this)" />
				</div>
			</div>
			<div class="col-sm-6">
				<div class="form-group form-inline">
					<label>价格:</label>
					<input type="text" name="price" value="${g.pprice}" class="form-control" />
				</div>
				<div class="form-group form-inline">
					<label>评分:</label>
					<input type="text" name="star" value="${g.pstate}" class="form-control" />
				</div>
			</div>
		</div>
		<div style="overflow: scroll;">
		<div class="row">
			<div class="form-group form-inline col-sm-6">
				<label>商品图片</label>
				<input type="hidden" name="oldPic" value="${g.pimage}">
				<input type="file" name="picture"  onchange="selImage(this)" />
			</div>
			<div class="form-group  col-sm-4">
				<img src="${g.pimage}" height="60px" id="img"/>
			</div>
			<div class="col-sm-10">
				<div class="form-group ">
					<label>商品简介</label>
					<textarea  name="intro"  class="form-control" rows="5">${g.pinfo}</textarea>
				</div>
				<div class="form-group form-inline">
					<input type="submit" value="修改" class="btn btn-primary" />
					<input type="reset" value="重置" class="btn btn-default" />
				</div>
			</div>
		</div>
		</div>
	</form>
</div>
</body>
</html>
<script type="text/javascript">
  function selImage(o) {
    document.getElementById("img").src = getFullPath(o);
  }

  function getFullPath(node){
    var imgURL = "";
    try{
      var file = null;
      if(node.files && node.files[0] ){
        file = node.files[0];
      }else if(node.files && node.files.item(0)) {
        file = node.files.item(0);
      }
      //Firefox 因安全性问题已无法直接通过input[file].value 获取完整的文件路径
      try{
        //Firefox7.0
        imgURL =  file.getAsDataURL();
        //alert("//Firefox7.0"+imgRUL);
      }catch(e){
        //Firefox8.0以上
        imgURL = window.URL.createObjectURL(file);
        //alert("//Firefox8.0以上"+imgRUL);
      }
    }catch(e){
      //支持html5的浏览器,比如高版本的firefox、chrome、ie10
      if (node.files && node.files[0]) {
        var reader = new FileReader();
        reader.onload = function (e) {
          imgURL = e.target.result;
        };
        reader.readAsDataURL(node.files[0]);
      }
    }
    return imgURL;
  }
</script>