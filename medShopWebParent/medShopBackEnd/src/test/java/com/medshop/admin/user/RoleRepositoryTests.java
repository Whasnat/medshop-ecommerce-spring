package com.medshop.admin.user;

import com.medshop.common.entity.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(value = false)
public class RoleRepositoryTests {

    @Autowired
    private RoleRepository repo;

    @Test
    public void testCreateFirstRoles() {
        Role roleAdmin = new Role("Admin", "Manage all aspect of the web application");
        Role savedRole = repo.save(roleAdmin);
        assertThat(savedRole.getId()).isGreaterThan(0);
    }

    @Test
    public void testCreateRoles() {

        Role roleVendor = new Role("Vendor", "Manage product prices, customer, shipping & view sales report");
        Role roleEditor = new Role("Editor", "Manage categories, brands, products, articles and menus");
        Role roleFulfiller = new Role("Fulfiller", "View Products, view orders and change order status");
        Role roleAssistant = new Role("Assistant", "Manage questionnaires and reviews of products");
//        Role roleCustomer = new Role("Customer", "Manage all aspect of the web application");

        repo.saveAll(List.of(roleVendor, roleEditor, roleFulfiller, roleAssistant));
    }

}
