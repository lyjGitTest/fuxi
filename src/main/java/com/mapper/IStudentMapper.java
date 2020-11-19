package com.mapper;

import com.pojo.Clazz;
import com.pojo.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface IStudentMapper {
    public int add(Student student);
    public int update(Student student);
    public int delete(Integer sid);
    public Student findbyid(Integer sid);
    public List<Student> findAll();
    public List<Clazz> findClazzAll();
    public List<Student> findPageAll(@Param("page") int page,@Param("rows") int rows);
    public int maxRows(int rows);
}
