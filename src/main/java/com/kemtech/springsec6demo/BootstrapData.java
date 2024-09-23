package com.kemtech.springsec6demo;

import com.kemtech.springsec6demo.entity.Role;
import com.kemtech.springsec6demo.entity.Student;
import com.kemtech.springsec6demo.entity.User;
import com.kemtech.springsec6demo.repository.RoleRepository;
import com.kemtech.springsec6demo.repository.StudentRepository;
import com.kemtech.springsec6demo.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Slf4j
@Component
public class BootstrapData {

    private final StudentRepository studentRepository;
    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    public BootstrapData(StudentRepository studentRepository, UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.studentRepository = studentRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        loadRoles();
        loadStudents();
        loadUsers();
    }

    private void loadRoles() {
        Role user = new Role("ROLE_USER");
        Role admin = new Role("ROLE_ADMIN");
        Role student = new Role("ROLE_STUDENT");
        roleRepository.save(user);
        roleRepository.save(admin);
        roleRepository.save(student);
        log.info("Bootstrap roles loaded");
    }

    private void loadUsers() {
        User calvin = User.builder()
                .username("calvin")
                .password(passwordEncoder.encode("123123"))
                .fullname("Calvin Chirwa")
                .isEnabled(true)
                .isAccountNonLocked(true)
                .isCredentialsNonExpired(true)
                .isAccountNonExpired(true)
                .roles(Arrays.asList(
//                        roleRepository.findByName("ROLE_STUDENT").orElseThrow(() -> new EntityNotFoundException("Role not found")),
                        roleRepository.findByName("ROLE_USER").orElseThrow(() -> new EntityNotFoundException("Role not found"))))
                .build();
        userRepository.save(calvin);
        log.info("Bootstrap user loaded");
    }

    private void loadStudents() {
        Student one = new Student();
        one.setFirstname("Cal");
        one.setLastname("Vin");
        one.setStudentNumber("76676");
        studentRepository.save(one);

        Student two = new Student();
        two.setFirstname("Sin");
        two.setLastname("Ani");
        two.setStudentNumber("76677");
        studentRepository.save(two);

        log.info("Bootstrap students loaded");

    }
}
