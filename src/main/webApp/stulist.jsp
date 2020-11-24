<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${pageBean.pagelist==null}">
<c:redirect url="findPageAll.do"></c:redirect>
</c:if>--%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>用户登录</title>
    <script src="js/jquery-1.9.1.js"></script>
    <script>
        $(function () {
            show();
            $("#xgtable").hide();
        });
        var page=1;
        var rows=5;
        var maxpage=1;
        function show() {
          $.getJSON("findPageAll.do?page="+page+"&rows="+rows,function (pagebean) {
              page=pagebean.page;
              rows=pagebean.rows;
              maxpage=pagebean.maxpage;
             var pagelist=pagebean.pagelist;
           var header="<table width='600px' border='2' align='center'>"
        +"<tr align='center' bgcolor='#FFFFCC'>"
        +"<td colspan='8'>学生信息展示</td>"
        +"</tr>"
       +"<tr align='center'>"
        +"<td>编号</td>"
        +"<td>姓名</td>"
        +"<td>性别</td>"
        +"<td>住址</td>"
        +"<td>生日</td>"
        +"<td>图片</td>"
        +"<td>班级</td>"
        +"<td>操作</td>"
        +"</tr>";
         var nr="";
              for(var i=0;i<pagelist.length;i++){
                  var st=pagelist[i];
        nr+= "<tr align='center'>"
        +"<td>"+st.stuid+"</td>"
        +"<td>"+st.sname+"</td>"
        +"<td>"+st.sex+"</td>"
        +"<td>"+st.address+"</td>"
        +"<td>"+st.sdate+"</td>"
        +"<td><a href=wenjian/"+st.photo+"><img src=wenjian/"+st.photo+" alt='图片不存在' width='80px' height='80px'/></a></td>"
        +"<td>"+st.cname+"</td>"
        +"<td><input type='button' onclick='dosc("+st.stuid+")' value='删除'><input type='button' onclick='doxg("+st.stuid+")' value='修改'></td>"
            + "</tr>"
    }
        var table=header+nr+"</table>";
              $("#page").val(page);
              $("#rows").val(rows);
        $("#table1").html(table);
        $("#sym").html(page+"/"+maxpage);

        if(page<maxpage){
            $("#sy").attr("disabled",false);
            $("#syy").attr("disabled",false);
            $("#xyy").attr("disabled",false);
            $("#wy").attr("disabled",false);
        }
        if(page==maxpage){
            $("#sy").attr("disabled",false);
            $("#syy").attr("disabled",false);
            $("#xyy").attr("disabled",true);
            $("#wy").attr("disabled",true);
        }
        if(page==1){
            $("#sy").attr("disabled",true);
            $("#syy").attr("disabled",true);
            $("#xyy").attr("disabled",false);
            $("#wy").attr("disabled",false);
        }
});
        }
        function dosc(sx) {
            if(window.confirm("确定删除编号为"+sx+"的学生吗？")){
                $.get('delete.do?sid='+sx,function(date) {
                        alert("删除成功！")
                        show();
                });
            }
        }
        function chaclazz(cid) {
         $.getJSON("findClazzAll.do",function (lsca) {
             for (var i=0;i<lsca.length;i++){
                 var arr=lsca[i];
            $("#classid").append("<option value="+arr.cid+">"+arr.cname+"</option>");
             }
             $("#classid").val(cid);
         });
        }
        function doxg(sx) {
            $.get('findbyid.do?sid='+sx,function (lsst) {
                    $("#stuid").val(lsst.stuid);
                    $("#sname").val(lsst.sname);
                    $("#sex").val(lsst.sex);
                    $("#address").val(lsst.address);
                    $("#sdate").val(lsst.sdate);
                    $("#img").attr('src','wenjian/'+lsst.photo);
                    $("#a").attr('href','wenjian/'+lsst.photo);
                    alert(lsst.pic);
                    $("#classid").val(lsst.classid);
                   chaclazz(lsst.classid);
                   $("#xgtable").show();
                   $("#db").hide();
                });
        }

    $(function () {
        $("#btupdate").click(function(){
            var stuid=$("#stuid").val();
            var sname=$("#sname").val();
            var sex=$("#sex").val();
            var address=$("#address").val();
            var sdate=$("#sdate").val();
            var classid=$("#classid").val();
            var photo=$("#img")[0].src;
            //var pic=document.getElementById('#pic');
            var date={
                'stuid':stuid,
                'sname':sname,
                'sex':sex,
                'address':address,
                'sdate':sdate,
                'classid':classid,
                'photo':photo,
            };

            $.getJSON("update.do",date,function(code) {
                    if(code!='0'){
                        alert("修改成功！");
                        $("#xgtable")[0].reset;
                        $("#xgtable").hide();
                        $("#db").show();
                        show();
                    }
            })
        })
    })

        $(function () {
            $("#sy").click(function () {
                page=1;
                show();
            });
            $("#syy").click(function () {
                page=page-1;
                if(page<1){
                    page=1;
                }
                show();
            });
            $("#xyy").click(function () {
                page=page+1;
                if(page>maxpage){
                    page=maxpage;
                }
                show();
            });
            $("#wy").click(function () {
                page=maxpage;
                show();
            });
            $("#btpage").click(function () {
                var pvl=$("#page").val();
                if(isNaN(pvl)){
                    alert("请输入数字！！！");
                 $("#page").val(page);
                 return;
                }
                page=pvl;
                show();
            })
            $("#btrows").click(function () {
                var rvl=$("#rows").val();
                if(isNaN(rvl)){
                    alert("请输入数字！！！");
                    $("#rows").val(rows);
                    return;
                }
                rows=rvl;
                show();
            })
        })
    </script>

</head>
<body>
<div id="table1" align="center"></div>

  <table width="600px" id="db" align="center" border="1" bgcolor="#7fff00">
    <tr>
      <td><input type="button" value="首页" id="sy" name="sy"></td>
      <td><input type="button" value="上一页" id="syy" name="syy"></td>
      <td><input type="button" value="下一页" id="xyy" name="xyy"></td>
      <td><input type="button" value="尾页" id="wy" name="wy"></td>
      <td>跳转到<input type="text" id="page" name="page" size="2" >
    <input type="button" id="btpage" name="btpage" value="确定"/>页</td>
      <td>每页<input type="text" id="rows" name="rows" size="2">
      <input type="button" id="btrows" name="btrows" value="确定"/>行</td>
      <td><span id="sym"></span></td>
    </tr>
  </table>

<form action="update.do" method="post" id="xgtable" name="xgtable">
    <table width="600px" height="600px" border="2px" align="center">
        <tr align="center" bgcolor="#FFFFCC">
            <td colspan="2">学生信息修改</td>
        </tr>
        <tr>
            <td>姓名</td>
            <td><input type="hidden" name="stuid" id="stuid">
                <input type="text" name="sname" id="sname"></td>
        </tr>
        <tr>
            <td>性别</td>
            <td><input type="text" name="sex" id="sex"></td>
        </tr>
        <tr>
            <td>地址</td>
            <td><input type="text" name="address" id="address"></td>
        </tr>
        <tr>
            <td>生日</td>
            <td><input type="date" name="sdate" id="sdate"></td>
        </tr>
        <tr>
            <td>图片</td>
            <td>
                <a id="a" href=""><img id="img" src="" width="120px" height="110px" alt="图片没有上传" /></a>
                <%--<input type="file" id="pic" name="pic">--%>
            </td>
        </tr>
        <tr><td>班级</td>
            <td><select id="classid" name="classid"></select> </td></tr>

        <tr align="center" bgcolor="#FFFFCC">
            <td colspan="2">
                <input type="hidden" name="sx" id="sx">
                <input type="button" id="btupdate" value="确定">
                <input type="reset" value="重置">
            </td>
        </tr>
    </table>
</form>
  <p align="center"><a href="add.jsp">返回添加</a></p>
</body>
</html>