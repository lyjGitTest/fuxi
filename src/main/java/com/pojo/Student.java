package com.pojo;

import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Student implements Serializable {
    private Integer stuid;
    private String sname;
    private String sex;
    private String address;
    private Date birthday;
    private String photo;
    private Integer classid;
    private  String cname;
    private MultipartFile pic;
     private  String sdate;

    public Student() {
    }

    public Student(String sname, String sex, String address, Date birthday, String photo, Integer classid, String cname, MultipartFile pic) {
        this.sname = sname;
        this.sex = sex;
        this.address = address;
        this.birthday = birthday;
        this.photo = photo;
        this.classid = classid;
        this.cname = cname;
        this.pic = pic;
    }

    public Student(Integer stuid, String sname, String sex, String address, Date birthday, String photo, Integer classid, String cname, MultipartFile pic, String sdate) {
        this.stuid = stuid;
        this.sname = sname;
        this.sex = sex;
        this.address = address;
        this.birthday = birthday;
        this.photo = photo;
        this.classid = classid;
        this.cname = cname;
        this.pic = pic;
        this.sdate = sdate;
    }

    public MultipartFile getPic() {
        return pic;
    }

    public void setPic(MultipartFile pic) {
        this.pic = pic;
    }

    public Student(String sname, String sex, String address, String photo, Integer classid, String cname, MultipartFile pic) {
        this.sname = sname;
        this.sex = sex;
        this.address = address;
        this.photo = photo;
        this.classid = classid;
        this.cname = cname;
        this.pic = pic;
    }

    public Integer getStuid() {
        return stuid;
    }

    public void setStuid(Integer stuid) {
        this.stuid = stuid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Integer getClassid() {
        return classid;
    }

    public void setClassid(Integer classid) {
        this.classid = classid;
    }

    public String getSdate() {
        try {
            sdate=new SimpleDateFormat("yyyy-MM-dd").format(birthday);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sdate;
    }

    public void setSdate(String sdate) {
        try {
            birthday=new SimpleDateFormat("yyyy-MM-dd").parse(sdate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        this.sdate = sdate;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }
}
