package my.spring.exhibitions.service;

import my.spring.exhibitions.dto.UserDTO;
import my.spring.exhibitions.entity.User;

import java.util.Optional;

public interface UserService {
    boolean saveUser(UserDTO userDTO);

    Optional<User> findUserByUsername(String username);
}
