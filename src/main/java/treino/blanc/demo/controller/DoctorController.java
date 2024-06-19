package treino.blanc.demo.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import treino.blanc.demo.dto.usuarioDto.UsuarioCriacaoDto;
import treino.blanc.demo.dto.usuarioDto.UsuarioDetalhesCriacaoDto;
import treino.blanc.demo.entity.Usuario;
import treino.blanc.demo.mapper.UsuarioMapper;
import treino.blanc.demo.repository.UsuarioRepository;
import treino.blanc.demo.service.UsuarioService;

import java.util.List;


@RestController
@RequestMapping("/doctors")
public class DoctorController {
    @Autowired
    private UsuarioRepository repository;
    @Autowired
    private UsuarioService service;

    @GetMapping
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<List<Usuario>> doctors(){
        return ResponseEntity.status(200).body(repository.findAll());
    }


}
