<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${pageBean.pagelist==null}">
<c:redirect url="findPageAll.do"></c:redirect>
</c:if>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>用户登录</title>
  <script>
    function  doRows(){
      var rows=document.f1.rows.value;
      if(isNaN(rows)){
        alert("请输入正确数字");
        document.f1.rows.value=${pageBean.rows};
        return;
      }
      window.location="findPageAll.do?rows="+rows;
    }
    function  doPage(){
      var page=document.f1.page.value;
      if(isNaN(page)){
        alert("请输入正确数字");
        document.f1.page.value=${pageBean.page};
        return;
      }
      window.location="findPageAll.do?page="+page;
    }
  </script>
</head>
<body>
<table width="600px" height="600px" border="1" align="center">
  <tr align="center" bgcolor="#FFFFCC">
    <td colspan="7">学生信息展示</td>
  </tr>
  <tr>
    <td>编号</td>
    <td>姓名</td>
    <td>性别</td>
    <td>住址</td>
    <td>生日</td>
    <td>班级</td>
    <td>操作</td>
  </tr>
  <c:forEach items="${pageBean.pagelist}" var="st">
  <tr>
    <td>${st.stuid}</td>
    <td>${st.getSname()}</td>
    <td>${st.getSex()}</td>
    <td>${st.getAddress()}</td>
    <td>${st.sdate}</td>
    <td>${st.getCname()}</td>
    <td>
    <a href="delete.do?sid=${st.stuid}">删除</a>
    <a href="findbyid.do?sid=${st.stuid}">修改</a>
    </td>
  </tr>
 </c:forEach>
  </table>
<form action="" id="f1" name="f1">
  <table width="600px" align="center" border="1" bgcolor="#7fff00">
    <tr>
      <td>
        <c:if test="${pageBean.page>1}">
         <a href="findPageAll.do?page=1">
        </c:if>
        首页</a></td>
      <td><c:if test="${pageBean.page>1}">
        <a href="findPageAll.do?page=${pageBean.page-1}">
      </c:if>
        上一页</a></td>
      <td><c:if test="${pageBean.page<pageBean.maxpage}">
          <a href="findPageAll.do?page=${pageBean.page+1}">
            </c:if>
            下一页
      </a></td>
      <td>
        <c:if test="${pageBean.maxpage<1}">
          <a href="findPageAll.do?page=${pageBean.maxpage}">
        </c:if>
           尾页</a></td>
      <td>跳转到
  <a>
    <input type="text" id="page" name="page" size="2" value="${pageBean.page}">
    <input type="button" id="btpage" name="btpage" onclick="doPage()" value="确定"/>
      页</a></td>
      <td>每页
   <a>
   <input type="text" id="rows" name="rows" size="2" value="${pageBean.rows}">
  <input type="button" id="btrows" name="btrows" onclick="doRows()" value="确定"/>
      行</a></td>
      <td>${pageBean.page}/${pageBean.maxpage}</td>
    </tr>
  </table>

</form>
  <p align="center">
<a href="add.jsp">返回添加</a>
</p>
</body>
</html>