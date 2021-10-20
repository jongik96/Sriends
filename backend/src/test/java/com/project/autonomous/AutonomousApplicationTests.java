package com.project.autonomous;

import static org.assertj.core.api.Assertions.*;

import com.project.autonomous.user.entity.QUser;
import com.project.autonomous.user.entity.User;
import com.querydsl.jpa.impl.JPAQueryFactory;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class AutonomousApplicationTests {

	@Autowired
	EntityManager em;

	@Test
	void contextLoads() {
		User user = new User();
		em.persist(user);

		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		QUser qUser = QUser.user;

		User result = queryFactory
			.selectFrom(qUser)
			.fetchOne();

		assertThat(result).isEqualTo(user);
	}

}
