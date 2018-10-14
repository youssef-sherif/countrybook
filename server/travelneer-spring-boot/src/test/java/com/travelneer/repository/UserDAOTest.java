//package com.travelneer.repository;
//
//import com.travelneer.repository.UserRepository;
//import com.travelneer.jooq.tables.pojos.UserEntity;
//import org.assertj.core.api.Assertions;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.sql.SQLException;
//import java.sql.Timestamp;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class UserDAOTest {
//
//	@Autowired
//	UserRepository userDAO;
//
//	@Test
//	public void creation()  {
//		for (int i = 1; i < 1000; i++) {
//			UserEntity user = new UserEntity();
//			user.setName("youssef" + i);
//			user.setEmail("youssef" + i + "@gmail.com");
//			user.setPassword("Yo654321");
//			try {
//				user.setCreatedAt(new Timestamp(System.currentTimeMillis()));
//				Assertions.assertThat(userDAO.create(user).getId()).isEqualTo(i);
//				Assertions.assertThat(userDAO.getUserByName(user.getName()).getId()).isEqualTo(i);
//			} catch (Exception e) {
//				System.out.println(e.getMessage());
//			}
//
//		}
//	}
//
//}
