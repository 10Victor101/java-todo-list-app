package com.example.todolist.controller;

import com.example.todolist.dto.ResponseDTO;
import com.example.todolist.dto.grupoDTO.GrouperRequestDTO;
import com.example.todolist.dto.grupoDTO.GrouperResponseDTO;
import com.example.todolist.exceptions.authExceptions.UserNotFoundException;
import com.example.todolist.exceptions.groupExceptions.GrouperAlreadyExistsException;
import com.example.todolist.infra.security.TokenService;
import com.example.todolist.services.GrouperService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/group")
@Tag(name = "Grupo Controller", description = "")
public class GrouperController {
    final private GrouperService grouperService;
    final private TokenService tokenService;

    public GrouperController(GrouperService grouperService, TokenService tokenService) {
        this.grouperService = grouperService;
        this.tokenService = tokenService;
    }

    @PostMapping("")
    public ResponseEntity<?> createGroup(@RequestHeader("Authorization") String authorization,
                                         @RequestBody GrouperRequestDTO body) {
        try {
            String token = authorization.replace("Bearer ", "");
            String userId = tokenService.validateToken(token);
            GrouperResponseDTO groupResponseDTO = grouperService.createGroup(body.name(), Long.parseLong(userId));
            return ResponseEntity.status(HttpStatus.CREATED).body(groupResponseDTO);
        } catch (IllegalArgumentException | GrouperAlreadyExistsException | UserNotFoundException e) {
            return ResponseEntity.badRequest().body(new ResponseDTO(e.getMessage()));
        }
    }
}
