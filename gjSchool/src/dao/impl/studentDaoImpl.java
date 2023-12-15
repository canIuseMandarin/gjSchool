package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.DbConnection;
import dao.studentDao;
import model.student;

public class studentDaoImpl implements studentDao{

	public static void main(String[] args) {
		/*add
		student s = new student("test",100,100);
		new studentDaoImpl().add(s);*/
		/*queryAll1
		System.out.println(new studentDaoImpl().queryAll1());
		*/
		//queryAll2:這個的好處是可以再做計算
		List<student> l = new studentDaoImpl().queryAll2();
		int sum = 0;
		for(student o:l)
		{
			System.out.println("id="+o.getId()+"\tname="+o.getName()+"\tchi="+o.getChi()+"\teng="+o.getEng()+"\t總分："+(o.getChi()+o.getEng()));
			sum = sum+(o.getChi()+o.getEng());
		}
		System.out.println("合計："+sum);
	}

	@Override
	public void add(student s) {
		Connection c = DbConnection.getDB();
		String sql = "INSERT INTO student(name,chi,eng) VALUES(?,?,?);";
		
		try {
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1,s.getName());
			ps.setInt(2,s.getChi());
			ps.setInt(3,s.getEng());
			
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("error preparedstatement");
			e.printStackTrace();
		}
	}

	@Override
	public String queryAll1() {
		Connection c = DbConnection.getDB();
		String sql = "SELECT * FROM student;";
		String show = "";
		
		try {
			PreparedStatement ps = c.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{
				show = show + "id="+rs.getInt("id")+"\tname="+rs.getString("name")+"\tchi="+rs.getInt("chi")+"\teng="+rs.getInt("eng")+"\n";
			}
			
		} catch (SQLException e) {
			System.out.println("error preparedstatement");
			e.printStackTrace();
		}
		
		return show;
	}

	@Override
	public List<student> queryAll2() {
		Connection c = DbConnection.getDB();
		String sql = "SELECT * FROM student;";
		List<student> l = new ArrayList();
		
		try {
			PreparedStatement ps = c.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{
				student s = new student();
				s.setId(rs.getInt("id"));
				s.setName(rs.getString("name"));
				s.setChi(rs.getInt("chi"));
				s.setEng(rs.getInt("eng"));
				
				l.add(s);
			}
			
			
	
		} catch (SQLException e) {
			System.out.println("error preparedstatement");
			e.printStackTrace();
		}
		
		return l;
	}

}
