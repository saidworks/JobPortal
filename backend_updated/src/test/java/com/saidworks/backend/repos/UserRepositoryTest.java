package com.saidworks.backend.repos;

import static org.assertj.core.api.Assertions.assertThat;



import com.saidworks.backend.domain.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;


@DataJpaTest
@ExtendWith(SpringExtension.class)
@Transactional
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;



    @Test
    void findByUsername() {
        User user1 = new User("saidtest1@gmail.com","12345678","saidtesting1","Said","Testing","Java","Boston");
        entityManager.persist(user1);
        User user2 = new User("saidtest2@gmail.com","12345678","saidtesting2","Said","Testing","Java","Boston");
        entityManager.persist(user2);

        User findByUsername = userRepository.findByUsername(user1.getUsername()).get();
        assertThat(findByUsername).isEqualTo(user1);
    }

    @Test
    void existsByEmail() {
        User user1 = new User("saidtest1@gmail.com","12345678","saidtesting1","Said","Testing","Java","Boston");
        entityManager.persist(user1);
        User user2 = new User("saidtest2@gmail.com","12345678","saidtesting2","Said","Testing","Java","Boston");
        entityManager.persist(user2);
        boolean existsByEmail = userRepository.existsByEmail(user1.getEmail());
        assertThat(existsByEmail).isTrue();
    }

    @Test
    void existsByUsername() {
        User user1 = new User("saidtest1@gmail.com","12345678","saidtesting1","Said","Testing","Java","Boston");
        entityManager.persist(user1);
        User user2 = new User("saidtest2@gmail.com","12345678","saidtesting2","Said","Testing","Java","Boston");
        entityManager.persist(user2);
        boolean existsByUsername = userRepository.existsByUsername(user1.getUsername());
        assertThat(existsByUsername).isTrue();
    }

    @Test
    void findAll(){
        User user1 = new User("saidtest1@gmail.com","12345678","saidtesting1","Said","Testing","Java","Boston");
        entityManager.persist(user1);
        User user2 = new User("saidtest2@gmail.com","12345678","saidtesting2","Said","Testing","Java","Boston");
        entityManager.persist(user2);
        User user3 = new User("saidtest3@gmail.com","12345678","saidtesting3","Said","Testing","Java","Boston");
        entityManager.persist(user3);
        User user4 = new User("saidtest4@gmail.com","12345678","saidtesting4","Said","Testing","Java","Boston");
        entityManager.persist(user4);
        Iterable<User> users = userRepository.findAll();
        assertThat(users).hasSize(8).contains(user1,user2,user4,user3);
    }
}