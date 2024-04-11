package com.example.todolist.services;

import com.example.todolist.dto.ResponseDTO;
import com.example.todolist.dto.authDTO.AuthResponseDTO;
import com.example.todolist.exceptions.authExceptions.InvalidPasswordException;
import com.example.todolist.exceptions.authExceptions.UserAlreadyExistsException;
import com.example.todolist.exceptions.authExceptions.UserNotFoundException;
import com.example.todolist.infra.security.TokenService;
import com.example.todolist.model.User;
import com.example.todolist.repositories.UserRepository;
import com.example.todolist.utils.EmailValidator;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, TokenService tokenService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
    }

    @Override
    public ResponseDTO register(String name, String email, String password) {
        if (name == null || email == null || password == null || name.isEmpty() || email.isEmpty() || password.isEmpty()) {
            throw new IllegalArgumentException("Todos os campos (nome, e-mail e senha) são obrigatórios!");
        }

        if (password.length() < 8) {
            throw new IllegalArgumentException("A senha deve ter pelo menos 8 caracteres.");
        }

        if (!EmailValidator.isValidEmail(email)) {
            throw new IllegalArgumentException("O formato do e-mail é inválido.");
        }

        if (userRepository.existsByEmail(email)) {
            throw new UserAlreadyExistsException("Um usuário com o e-mail " + email + " já está cadastrado.");
        }

        User newUser = new User();
        newUser.setPassword(passwordEncoder.encode(password));
        newUser.setEmail(email);
        newUser.setName(name);
        this.userRepository.save(newUser);

        return new ResponseDTO("Usuário registrado com sucesso!");
    }

    @Override
    public AuthResponseDTO login(String email, String password) {
        if (email == null || password == null || email.isEmpty() || password.isEmpty()) {
            throw new IllegalArgumentException("E-mail e/ou senha não informado!");
        }

        if (!EmailValidator.isValidEmail(email)) {
            throw new IllegalArgumentException("E-mail com formato inválido!");
        }

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("Usuário com o e-mail informado não existe!"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new InvalidPasswordException("Senha incorreta!");
        }

        String token = this.tokenService.generateToken(user);
        return new AuthResponseDTO(user.getName(), token);
    }
}
