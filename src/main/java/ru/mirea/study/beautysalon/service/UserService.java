package ru.mirea.study.beautysalon.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.mirea.study.beautysalon.model.User;
import ru.mirea.study.beautysalon.web.dto.UserRegistrationDto;

public interface UserService extends UserDetailsService {
    User save(UserRegistrationDto registrationDto);
}