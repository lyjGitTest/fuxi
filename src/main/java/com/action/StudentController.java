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
import org.apache.commons.io.FileUtils;
import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.HttpConstraintElement;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;
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

    @RequestMapping(value = "save.do")
    public String add(HttpServletRequest request, HttpServletResponse response, Student student) {
        String realPath = request.getRealPath("/");
        MultipartFile multipartFile = student.getPic();
        if (multipartFile != null) {
            String fname = multipartFile.getOriginalFilename();
            if (fname.lastIndexOf(".") != -1) {
                String txt = fname.substring(fname.lastIndexOf("."));
                if (txt.equalsIgnoreCase(".jpg")) {
                    fname = new Date().getTime() + txt;
                    File file = new File(realPath + "/wenjian/" + fname);
                    try {
                        FileUtils.copyInputStreamToFile(multipartFile.getInputStream(), file);
                        student.setPhoto(fname);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        }
        boolean flag = iStudentBiz.add(student);

        if (flag){
            try {
                response.sendRedirect("stulist.jsp");
            } catch (IOException e) {
                e.printStackTrace();
            }
            //return "redirect:/add.jsp";
        }
     return "error.jsp";
    }

    @RequestMapping(value = "update.do")
    public String update(HttpServletRequest request, HttpServletResponse response, Student student) {
        //获取原来的图片，在不修改图片的时候，展示原来图片
        String oldphoto = iStudentBiz.findbyid(student.getStuid()).getPhoto();

        String realpath = request.getRealPath("/");
        //获取上传文件
        MultipartFile multipartFile = student.getPic();
        System.out.println("qqqqqqqqqqqqqqqqqqqqqq"+student.getPic());
        if (multipartFile != null && !multipartFile.isEmpty()) {
            //获取文件原始名称
            String fname = multipartFile.getOriginalFilename();
            //更名
            if (fname.lastIndexOf(".") != -1) {
                //获取后缀
                String ext = fname.substring(fname.lastIndexOf("."));
                //判断是否是jpg
                if (ext.equalsIgnoreCase(".jpg")) {
                    String newfname = new Date().getTime() + ext;
                    File dostFile = new File(realpath + "/wenjian/" + newfname);
                    //上传
                    try {
                        FileUtils.copyInputStreamToFile(multipartFile.getInputStream(), dostFile);
                        System.out.println("文件上传成功");
                        student.setPhoto(newfname);
                        //删除原来的照片
                        File oldFile = new File(realpath + "/wenjian" + oldphoto);
                        if (oldFile.exists() && !oldphoto.equals("default.jpg")) {
                            oldFile.delete();//删除
                        }
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }

        } else {
            student.setPhoto(oldphoto);
        }
        boolean flag = iStudentBiz.update(student);
           if (flag) {
               System.out.println("修改成功！");
               try {
                   response.sendRedirect("findPageAll.do");
               } catch (IOException e) {
                   e.printStackTrace();
               }
            }
        return null;
    }

    @RequestMapping(value = "delete.do")
    public String delete(HttpServletRequest request, HttpServletResponse response, Integer sid) {
        String oldphoto = iStudentBiz.findbyid(sid).getPhoto();
        String realpath = request.getRealPath("/");
        boolean flag = iStudentBiz.delete(sid);
       if (flag){
           System.out.println("删除成功！");
           File oldFile = new File(realpath + "/wenjian" + oldphoto);
           if (oldFile.exists() && !oldphoto.equals("default.jpg")) {
               oldFile.delete();//删除
           }
           try {
               response.sendRedirect("findPageAll.do");
           } catch (IOException e) {
               e.printStackTrace();
           }
        }
        return null;
    }

    @RequestMapping(value = "findbyid.do")
    public Student findbyid(HttpServletRequest request, HttpServletResponse response, Integer sid) {
        Student oldst = iStudentBiz.findbyid(sid);
        return oldst;
}
    @RequestMapping(value="findPageAll.do")
    public PageBean findAll(HttpServletRequest request,HttpServletResponse response,String page,String rows){
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
        return pageBean;
    }


    @RequestMapping(value="findClazzAll.do")
    public List<Clazz> findClazzAll(HttpServletRequest request,HttpServletResponse response){
       // HttpSession session=request.getSession();
        List<Clazz> lsca=iStudentBiz.findClazzAll();
        return lsca;
    }
}
