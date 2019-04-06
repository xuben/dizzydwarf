package com.ben.dizzydwarf;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class UserDao {

	private static SqlSessionFactory factory;
	
	static {
		try {
			InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
			factory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private SqlSession session;
	
	public UserDao() throws IOException {
		session = factory.openSession(true);
	}
	
	public User getUser(String name) {
		return session.selectOne("com.ben.dizzydwarf.UserMapper.selectUser", name);
	}
	
	public int createUser(User user) {
		return session.insert("com.ben.dizzydwarf.UserMapper.insertUser", user);
	}
}
