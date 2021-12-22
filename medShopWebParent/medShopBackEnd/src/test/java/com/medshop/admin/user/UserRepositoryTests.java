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

    @Test
    public void testListAllUsers() {
//        List<User> listUsers = new ArrayList<>();
        Iterable<User> listUser = repo.findAll();
        listUser.forEach(user -> System.out.println(user));
//        assertThat(listUser.)
    }

    @Test
    public void testGetUserById() {
        User userName = repo.findById(1).get();
        System.out.println(userName);
        assertThat(userName).isNotNull();
    }

    @Test
    public void testUpdateUserDetails() {
        User userName = repo.findById(1).get();
        userName.setEnabled(true);
        userName.setEmail("Rwashat@emedpharm.com");
        userName.setFirstName("Rahat");
        userName.setLastName("Hasnat");
        repo.save(userName);
    }

    @Test
    public void testUpdateUserRole() {
        User user = repo.findById(2).get();
        Role roleAssistant = new Role(5);
        Role roleVendor = new Role(2);
        user.getRoles().remove(roleAssistant);
        user.addRole(roleVendor);
    }

    @Test
    public void testDeleteUser() {
        Integer userId = 2;
        repo.deleteById(userId);
//        repo.findById(userId);
    }

    @Test
    public void testGetUserByEmail() {
        String email = "walter@medshopcom";
        User user = repo.getUserByEmail(email);

        assertThat(user).isNotNull();
    }
}
