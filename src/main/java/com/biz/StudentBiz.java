package com.biz;

import com.mapper.IStudentMapper;
import com.pojo.Clazz;
import com.pojo.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class StudentBiz implements IStudentBiz {
    @Autowired
    private IStudentMapper iStudentMapper;

    public IStudentMapper getiStudentMapper() {
        return iStudentMapper;
    }

    public void setiStudentMapper(IStudentMapper iStudentMapper) {
        this.iStudentMapper = iStudentMapper;
    }

    @Override
    public boolean add(Student student) {
        int rows=iStudentMapper.add(student);
        if(rows>0){
            System.out.println("保存成功");
            return true;
        }
        System.out.println("保存失败");
        return false;
    }

    @Override
    public boolean update(Student student) {
        int rows=iStudentMapper.update(student);
        if(rows>0){
            System.out.println("修改成功");
            return true;
        }
        System.out.println("修改失败");
        return false;
    }

    @Override
    public boolean delete(Integer sid) {
        int rows=iStudentMapper.delete(sid);
        if(rows>0){
            System.out.println("删除成功");
            return true;
        }
        System.out.println("删除失败");
        return false;
    }

    @Override
    public Student findbyid(Integer sid) {
        System.out.println("查询单个成功");
        return iStudentMapper.findbyid(sid);
    }

    @Override
    public List<Student> findAll() {
        return iStudentMapper.findAll();
    }

    @Override
    public List<Student> findPageAll(Integer rows, Integer page) {
        System.out.println("查询所有成功");
        return iStudentMapper.findPageAll(rows,page);
    }

    @Override
    public List<Clazz> findClazzAll() {
        System.out.println("查询班级成功");
        return iStudentMapper.findClazzAll();
    }

    @Override
    public int maxrows(int rows) {
        int maxrows=iStudentMapper.maxRows(rows);
       int maxpage=maxrows%rows==0?maxrows/rows:maxrows/rows+1;
        return maxpage;
    }

}
