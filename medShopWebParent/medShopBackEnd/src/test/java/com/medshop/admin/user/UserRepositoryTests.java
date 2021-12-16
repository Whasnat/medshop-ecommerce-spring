package com.medshop.admin.user;

import com.medshop.common.entity.Role;
import com.medshop.common.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class UserRepositoryTests {

    @Autowired
    private UserRepository repo;

    @Autowired
    private TestEntityManager entityManager;


    @Test
    public void testCreateUser() {
        Role roleAdmin = entityManager.find(Role.class, 1);
        User userAdmin = new User("wasnat@medshop.com", "pass123", "Rahat", "Wasnat");
        userAdmin.addRole(roleAdmin);
        User savedUser = repo.save(userAdmin);
        assertThat(savedUser.getId()).isGreaterThan(0);
    }

    @Test
    public void testUserWithMultipleRole() {
        User userMultiRole = new User("walter@medshopcom", "walter123", "Walter", "White");
        userMultiRole.addRole(new Role(3));
        userMultiRole.addRole(new Role(5));
        User savedUser = repo.save(userMultiRole);
        assertThat(savedUser.getId()).isGreaterThan(0);
    }
}
