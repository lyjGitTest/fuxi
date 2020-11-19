<%@ page language="java" import="java.util.*,com.pojo.*,com.biz.*" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${lsca==null&&oldst==null}">
<c:redirect url="findPageAll.do"></c:redirect>
</c:if>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>用户修改</title>
</head>
<body>
<form action="update.do" method="post">
  <table width="600px" height="600px" border="1" align="center">
  <tr align="center" bgcolor="#FFFFCC">
    <td colspan="2">学生信息修改</td>
  </tr>
  <tr>
    <td>姓名</td>
    <td>
      <input type="text" name="sname" id="sname" value="${oldst.sname}">
    </td>
  </tr>
  <tr>
     <td>性别</td>
    <td>
      <input type="radio" name="sex" id="sex1" value="男" checked="checked">男
      <input type="radio" name="sex" id="sex2" value="女" >女
    </td>
  </tr>
  <tr>
     <td>地址</td>
    <td>
      <input type="text" name="address" id="address" value="${oldst.address}">
    </td>
  </tr>
  <tr>
     <td>生日</td>
    <td>
      <input type="date" name="sdate" id="sdate" value="${oldst.sdate}">
    </td>
  </tr>
  <tr>
     <td>班级</td>
    <td>
       <select id="classid" name="classid">
      <c:forEach items="${lsca}" var="ca">
      <option  value="${ca.getCid()}"
      <c:if test="${ca.cid==oldst.classid}">
      selected="selected"
      </c:if>
      >${ca.getCname()}</option>
      </c:forEach>
      </select> 
    </td>
  </tr>
 <tr align="center" bgcolor="#FFFFCC">
    <td colspan="2">
    <input type="hidden" name="stuid" id="stuid" value="${oldst.stuid}">
    <input type="submit" value="确定"> 
    <input type="reset" value="重置">
    </td>
  </tr>
  </table>

</form>
</body>
</html>