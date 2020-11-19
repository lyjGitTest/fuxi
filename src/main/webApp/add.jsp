<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${lsca==null}">
    <c:redirect url="findClazzAll.do"/>
</c:if>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>信息录入</title>
<%-- <script type="text/javascript" src="js/jquery-1.9.1.js"></script>
 <script type="text/javascript">
 $(function(){
	 $.getJSON('findClazzAll.do',function(lsca){
		 for(var i=0;i<lsca.length;i++){
			 var arr=lsca[i];
			 $("#classid").append("<option value="+arr.cid+">"+arr.cname+"</option>");
		 }
	 });
 });
 </script>
</head>--%>
<body>
<form action="save.do" method="post">
<table border="2px" align="center">
<tr><td>学生姓名：</td><td><input type="text" id="sname" name="sname"/></td></tr>
<tr><td>学生性别：</td>
      <td><input type="radio" name="sex" id="sex1" value="男" checked="checked">男
      <input type="radio" name="sex" id="sex2" value="女" >女</td></tr>
<tr><td>学生地址：</td><td><input type="text" id="address" name="address"/></td></tr>
<tr><td>学生生日：</td><td><input type="date" id="sdate" name="sdate"/></td></tr>
<tr><td>班级：</td><td> <select id="classid" name="classid">
    <c:forEach items="${lsca}" var="ca">
    <option value="${ca.getCid()}">${ca.cname}
    </option>
    </c:forEach>
      </select> </td></tr>
<tr align="center"><td colspan="2"><input type="submit" value="提交"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<input type="reset" value="重置"/>
</td></tr>
</table>
<p align="center">返回，点击查看
<a href="findPageAll.do">列表</a>
</p>
</form>
</body>
</html>