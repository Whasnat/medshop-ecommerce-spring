package com.medshop.admin.user;

import com.medshop.common.entity.Role;
import com.medshop.common.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private RoleRepository userRoles;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<User> listAll() {

        return (List<User>) userRepo.findAll();
    }

    public List<Role> listRoles() {
        return (List<Role>) userRoles.findAll();
    }

    public void save(User user) {
        boolean isUpdatingUser = (user.getId() != null);
        if (isUpdatingUser) {
            User existingUser = userRepo.findById(user.getId()).get();
            if (user.getPassword().isEmpty()) {
                user.setPassword(existingUser.getPassword());
            }
        } else {
            encodePassword(user);
        }
        userRepo.save(user);
    }

    private void encodePassword(User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
    }

    public boolean isUniqueEmail(Integer id, String email) {
        User userByEmail = userRepo.getUserByEmail(email);
        if (userByEmail == null)
            return true;

        boolean isCreatingNew = (id == null);
        if (isCreatingNew) {
            if (userByEmail != null) return false;
            else {
                if (userByEmail.getId() != id) {
                    return false;
                }
            }
        }
//        return userByEmail == null;
        return true;
    }

    public User get(Integer id) throws UsernameNotFoundException {
        try {
            return userRepo.findById(id).get();
        } catch (NoSuchElementException e) {
            throw new UsernameNotFoundException("couldn't find user with the Id- -_-");

        }
    }


    public void delete(Integer id) {
        Long countById = userRepo.countById(id);
        if (countById == null || countById == 0) {
            throw new UsernameNotFoundException("Couldn't find user with this Id" + id);
        }
        userRepo.deleteById(id);
    }
}
