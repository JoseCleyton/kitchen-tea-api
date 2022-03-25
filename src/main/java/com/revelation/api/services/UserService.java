package com.revelation.api.services;

import com.revelation.api.exceptions.PasswordInvalidException;
import com.revelation.api.models.UserModel;
import com.revelation.api.repositories.UserRepository;
import lombok.NonNull;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Optional<UserModel> user = this.userRepository.findByLogin(login);

        if (!user.isPresent()) {
            throw new UsernameNotFoundException("Usuário não encontrado");
        }

        String roles[] = user.get().isAdmin() ? new String[]{"USER", "ADMIN"} : new String[]{"USER"};

        return org.springframework.security.core.userdetails.User.builder().username(user.get().getLogin())
                .password(passwordEncoder.encode(user.get().getPassword())).roles(roles).build();

    }

    public UserModel authenticate(UserModel user) throws PasswordInvalidException {
        Optional<UserModel> u = this.userRepository.findByLogin(user.getLogin());
        if (!u.isPresent()) {
            throw new UsernameNotFoundException("Usuário não encontrado");
        }
        if (passwordEncoder.matches(user.getPassword(), u.get().getPassword())) {
            return u.get();
        }
        throw new PasswordInvalidException("Senha inválida !!! ");

    }

    public UserModel save(UserModel userModel) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String passwordEnconded = passwordEncoder.encode(userModel.getPassword());
        userModel.setPassword(passwordEnconded);
        userModel.setAdmin(true);
        return this.userRepository.save(userModel);
    }

    public UserModel update(UserModel user) {
        return this.userRepository.save(user);
    }

    public Optional<UserModel> getUserByLogin(String login) {
        return this.userRepository.findByLogin(login.trim());
    }

    public Optional<UserModel> userValid(String login) {

        List<UserModel> users = (List<UserModel>) this.userRepository.findAll();

        return users.stream()
                .filter(u -> u.getLogin().equalsIgnoreCase(login.trim()))
                .findFirst();

    }

    public Optional<UserModel> findById(Long id) {
        return this.userRepository.findById(id);
    }

}
