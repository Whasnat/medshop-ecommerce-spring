package com.medshop.admin.user;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PasswordEncryptorTest {

    @Test
    public void testEncodePassword() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPass = "abd123";
        String encodedPassword = encoder.encode(rawPass);
        System.out.println(encodedPassword);
        boolean match = encoder.matches(rawPass, encodedPassword);
        assertThat(match).isTrue();
    }
}
