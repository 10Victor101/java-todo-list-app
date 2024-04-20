package com.example.todolist.controller;

import com.example.todolist.dto.ResponseDTO;
import com.example.todolist.dto.grupoDTO.GrouperRequestDTO;
import com.example.todolist.dto.grupoDTO.GrouperResponseDTO;
import com.example.todolist.exceptions.authExceptions.UserNotFoundException;
import com.example.todolist.exceptions.groupExceptions.GrouperAlreadyExistsException;
import com.example.todolist.infra.security.TokenService;
import com.example.todolist.model.Grouper;
import com.example.todolist.services.GrouperService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/grouper")
@Tag(name = "Grouper Controller", description = "")
public class GrouperController {
    final private GrouperService grouperService;
    final private TokenService tokenService;

    public GrouperController(GrouperService grouperService, TokenService tokenService) {
        this.grouperService = grouperService;
        this.tokenService = tokenService;
    }

    @PostMapping("")
    public ResponseEntity<?> createGrouper(@RequestHeader("Authorization") String authorization,
                                           @RequestBody GrouperRequestDTO body) {
        try {
            String token = authorization.replace("Bearer ", "");
            String userId = tokenService.validateToken(token);
            GrouperResponseDTO groupResponseDTO = grouperService.createGrouper(body.name(), userId);
            return ResponseEntity.status(HttpStatus.CREATED).body(groupResponseDTO);
        } catch (IllegalArgumentException | GrouperAlreadyExistsException | UserNotFoundException e) {
            return ResponseEntity.badRequest().body(new ResponseDTO(e.getMessage()));
        }
    }

    @GetMapping("")
    public ResponseEntity<?> getGrouper(@RequestHeader("Authorization") String authorization) {
        try {
            String token = authorization.replace("Bearer ", "");
            String userId = tokenService.validateToken(token);
            List<Grouper> groupers = grouperService.getGroupers(userId);
            return ResponseEntity.status(HttpStatus.CREATED).body(groupers);
        } catch (UserNotFoundException e) {
            return ResponseEntity.badRequest().body(new ResponseDTO(e.getMessage()));
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteGrouper(@PathVariable String id) {
        try {
            grouperService.deleteGrouper(id);
            return ResponseEntity.ok().build();
        } catch (UserNotFoundException e) {
            return ResponseEntity.badRequest().body(new ResponseDTO(e.getMessage()));
        }
    }
}
