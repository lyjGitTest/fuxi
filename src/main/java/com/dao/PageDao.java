/*
package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.pojo.Student;
import com.utils.DBUtils;


public class PageDao {

	public static List<Student> findPageAll(int page, int rows) {
		List<Student> lsst=new ArrayList<Student>();
		String sql="select * from sview order by stuid limit "+(page-1)*rows+","+rows;
		Connection conn = DBUtils.open();
		try {
		PreparedStatement prtmt = conn.prepareStatement(sql);
		ResultSet rst = prtmt.executeQuery();
			while(rst.next()){
			Student st=new Student(
					rst.getInt(1)+"",
					rst.getString(2),
					rst.getString(3),
					rst.getString(4),
					rst.getString(5),
					rst.getInt(6)+"",
					rst.getString(7),
					rst.getString(8)
					);	
			lsst.add(st);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lsst;
	}

	public static int findmaxPage(int rows) {
		int maxrows=0;
		int maxpage=0;
		String sql="select count(*) from sview";
		Connection conn = DBUtils.open();
		try {
		PreparedStatement prtmt = conn.prepareStatement(sql);
		ResultSet rst = prtmt.executeQuery();
			while(rst.next()){
			maxrows=rst.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(maxrows==0){
			maxpage=1;
		}else{
			maxpage=maxrows%rows==0?maxrows/rows:maxrows/rows+1;
		}
		return maxpage;
	}
}
*/
