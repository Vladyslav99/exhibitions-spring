package my.spring.exhibitions.service;

import my.spring.exhibitions.dto.UserDTO;
import my.spring.exhibitions.entity.Role;
import my.spring.exhibitions.entity.RoleType;
import my.spring.exhibitions.entity.User;
import my.spring.exhibitions.repository.RoleRepository;
import my.spring.exhibitions.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public boolean saveUser(UserDTO userDTO) {
        Optional<Role> roleOptional = roleRepository.findByName(RoleType.CUSTOMER.name());
        if (!roleOptional.isPresent()){
            return false;
        }
        if (findUserByUsername(userDTO.getUsername()).isPresent()){
            System.out.println("user exists");
            return false;
        }

        User user = User.builder()
                .username(userDTO.getUsername())
                .email(userDTO.getEmail())
                .password(passwordEncoder.encode(userDTO.getPassword()))
                .role(roleOptional.get())
                .build();
        userRepository.save(user);
        return true;
    }

    @Override
    public Optional<User> findUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }
}
