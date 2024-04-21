package com.example.todolist.controller;

import com.example.todolist.dto.ResponseDTO;
import com.example.todolist.dto.grupoDTO.GrouperRequestDTO;
import com.example.todolist.dto.grupoDTO.GrouperResponseDTO;
import com.example.todolist.exceptions.authExceptions.UserNotFoundException;
import com.example.todolist.exceptions.groupExceptions.GrouperAlreadyExistsException;
import com.example.todolist.infra.security.TokenService;
import com.example.todolist.model.Grouper;
import com.example.todolist.services.GrouperService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/grouper")
@Tag(name = "Grouper Controller", description = "Endpoints para operações dos agrupadores das tarefas")
public class GrouperController {
    final private GrouperService grouperService;
    final private TokenService tokenService;

    public GrouperController(GrouperService grouperService, TokenService tokenService) {
        this.grouperService = grouperService;
        this.tokenService = tokenService;
    }

    @PostMapping("")
    @Operation(summary = "Criar um novo grupo", description = "Endpoint para criar um novo grupo de tarefas")
    @ApiResponse(responseCode = "201", description = "Grupo criado com sucesso",
            content = @Content(schema = @Schema(implementation = GrouperResponseDTO.class)))
    @ApiResponse(responseCode = "400", description = "Requisição inválida ou grupo já existente",
            content = @Content(schema = @Schema(implementation = ResponseDTO.class)))
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
    @Operation(summary = "Obter grupos do usuário", description = "Endpoint para obter todos os grupos de tarefas do usuário")
    @ApiResponse(responseCode = "200", description = "Grupos obtidos com sucesso",
            content = @Content(schema = @Schema(implementation = Grouper.class)))
    @ApiResponse(responseCode = "400", description = "Requisição inválida",
            content = @Content(schema = @Schema(implementation = ResponseDTO.class)))
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
    @Operation(summary = "Excluir grupo", description = "Endpoint para excluir um grupo de tarefas pelo ID")
    @ApiResponse(responseCode = "200", description = "Grupo excluído com sucesso")
    @ApiResponse(responseCode = "400", description = "Requisição inválida",
            content = @Content(schema = @Schema(implementation = ResponseDTO.class)))
    public ResponseEntity<?> deleteGrouper(@PathVariable String id) {
        try {
            grouperService.deleteGrouper(id);
            return ResponseEntity.ok().build();
        } catch (UserNotFoundException e) {
            return ResponseEntity.badRequest().body(new ResponseDTO(e.getMessage()));
        }
    }
}
