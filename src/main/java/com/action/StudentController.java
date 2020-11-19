package com.action;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializeFilter;
import com.biz.IStudentBiz;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.pojo.Clazz;
import com.pojo.PageBean;
import com.pojo.Student;
import com.utils.AjaxUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.HttpConstraintElement;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;


@RestController
public class StudentController {
    @Autowired
    private IStudentBiz iStudentBiz;

    public IStudentBiz getiStudentBiz() {
        return iStudentBiz;
    }

    public void setiStudentBiz(IStudentBiz iStudentBiz) {
        this.iStudentBiz = iStudentBiz;
    }
    @RequestMapping(value="save.do")
    public String add(HttpServletRequest request, HttpServletResponse response, Student student){
        student.setPhoto("111111");
        boolean flag=iStudentBiz.add(student);
        if (flag){
            try {
                response.sendRedirect("findPageAll.do");
            } catch (IOException e) {
                e.printStackTrace();
            }
            //return "redirect:/add.jsp";
        }
     return "error.jsp";
    }

    @RequestMapping(value="update.do")
    public String update(HttpServletRequest request,HttpServletResponse response,Student student){
        boolean flag=iStudentBiz.update(student);
        if (flag){
            try {
                response.sendRedirect("findPageAll.do");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "error.jsp";
    }

    @RequestMapping(value="delete.do")
    public String delete(HttpServletRequest request,HttpServletResponse response,Integer sid){
        boolean flag=iStudentBiz.delete(sid);
        if (flag){
            try {
                response.sendRedirect("findPageAll.do");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "error.jsp";
    }

    @RequestMapping(value="findbyid.do")
    public String findbyid(HttpServletRequest request,HttpServletResponse response,Integer sid){
        Student oldst=iStudentBiz.findbyid(sid);
   HttpSession session=request.getSession();
    session.setAttribute("oldst",oldst);
        try {
            response.sendRedirect("stuupdate.jsp");
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*  return "redirect:/list.jsp";*/

        return "error.jsp";
    }


    @RequestMapping(value="findPageAll.do")
    public String findAll(HttpServletRequest request,HttpServletResponse response,String page,String rows){
        HttpSession session=request.getSession();
        List<Clazz> lsca=iStudentBiz.findClazzAll();
        session.setAttribute("lsca",lsca);

        PageBean pageBean=(PageBean)session.getAttribute("pageBean");
        pageBean=pageBean==null?new PageBean():pageBean;
        page=page==null?""+pageBean.getPage():page;
        rows=rows==null?""+pageBean.getRows():rows;
        int irows=Integer.parseInt(rows);
        int ipage=Integer.parseInt(page);
        if(irows>20)irows=20;
        int maxpage=iStudentBiz.maxrows(irows);
        if(ipage>maxpage)ipage=maxpage;
        List<Student> lp=iStudentBiz.findPageAll(ipage,irows);
        pageBean.setPagelist(lp);
        pageBean.setMaxpage(maxpage);
        pageBean.setPage(ipage);
        pageBean.setRows(irows);
        session.setAttribute("pageBean",pageBean);
        try {
            response.sendRedirect("stulist.jsp");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    @RequestMapping(value="findClazzAll.do")
    public String findClazzAll(HttpServletRequest request,HttpServletResponse response){
        HttpSession session=request.getSession();
        List<Clazz> lsca=iStudentBiz.findClazzAll();
        session.setAttribute("lsca",lsca);
        try {
            response.sendRedirect("add.jsp");
        } catch (IOException e) {
            e.printStackTrace();
        }
       /* String str= JSONObject.toJSONString(lsca);
       AjaxUtils.printString(response,str);*/
        return null;
    }
}
