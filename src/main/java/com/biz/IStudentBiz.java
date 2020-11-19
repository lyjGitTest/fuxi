package com.biz;

import com.pojo.Clazz;
import com.pojo.Student;

import java.util.List;

public interface IStudentBiz {
    public boolean add(Student student);
    public boolean update(Student student);
    public boolean delete(Integer sid);
    public Student findbyid(Integer sid);
    public List<Student> findAll();
    public List<Student> findPageAll(Integer rows,Integer page);
    public List<Clazz> findClazzAll();
    public int maxrows(int rows);
}
