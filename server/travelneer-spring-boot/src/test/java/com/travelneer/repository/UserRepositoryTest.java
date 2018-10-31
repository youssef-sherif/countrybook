package com.travelneer.repository;

import com.travelneer.domain.user.UserEntity;
import com.travelneer.domain.user.UserFactory;
import com.travelneer.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserFactory userFactory;

	@Test
	public void creation() {

		for(int i = 0; i < 10; i++) {
			UserEntity userEntity = userFactory.createUser("name" + i, "name + i + " + "@mail", "SastocDykak8");
			try {
				userRepository.save(userEntity);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}

		for(int i = 0; i < 10; i++) {
			try {
				UserEntity userEntity = userRepository.getOneByName("name" + i);
				Assertions.assertThat(userEntity.getId()).isEqualTo(i);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
}
