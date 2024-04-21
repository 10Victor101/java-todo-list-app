package com.example.todolist.controller;

import com.example.todolist.dto.ResponseDTO;
import com.example.todolist.dto.authDTO.AuthResponseDTO;
import com.example.todolist.dto.authDTO.LoginRequestDTO;
import com.example.todolist.dto.authDTO.RegisterRequestDTO;
import com.example.todolist.exceptions.authExceptions.InvalidPasswordException;
import com.example.todolist.exceptions.authExceptions.UserAlreadyExistsException;
import com.example.todolist.exceptions.authExceptions.UserNotFoundException;
import com.example.todolist.services.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Tag(name = "Auth Controller", description = "Endpoints para autenticação de usuários")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    @Operation(summary = "Autenticar usuário", description = "Endpoint para autenticar um usuário")
    @ApiResponse(responseCode = "200", description = "Usuário autenticado com sucesso",
            content = @Content(schema = @Schema(implementation = AuthResponseDTO.class)))
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado",
            content = @Content(schema = @Schema(implementation = ResponseDTO.class)))
    @ApiResponse(responseCode = "401", description = "Senha inválida",
            content = @Content(schema = @Schema(implementation = ResponseDTO.class)))
    @ApiResponse(responseCode = "400", description = "Requisição inválida",
            content = @Content(schema = @Schema(implementation = ResponseDTO.class)))
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO body) {
        try {
            AuthResponseDTO authResponseDTO = authService.login(body.email(), body.password());
            return ResponseEntity.status(HttpStatus.OK).body(authResponseDTO);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDTO(e.getMessage()));
        } catch (InvalidPasswordException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseDTO(e.getMessage()));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ResponseDTO(e.getMessage()));
        }
    }

    @PostMapping("/register")
    @Operation(summary = "Registrar novo usuário", description = "Endpoint para registrar um novo usuário")
    @ApiResponse(responseCode = "201", description = "Usuário registrado com sucesso",
            content = @Content(schema = @Schema(implementation = ResponseDTO.class)))
    @ApiResponse(responseCode = "400", description = "Requisição inválida ou usuário já existente",
            content = @Content(schema = @Schema(implementation = ResponseDTO.class)))
    public ResponseEntity<?> register(@RequestBody RegisterRequestDTO body) {
        try {
            ResponseDTO responseDTO = authService.register(body.name(), body.email(), body.password());
            return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
        } catch (IllegalArgumentException | UserAlreadyExistsException e) {
            return ResponseEntity.badRequest().body(new ResponseDTO(e.getMessage()));
        }
    }
}
