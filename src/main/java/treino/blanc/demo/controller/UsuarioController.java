package treino.blanc.demo.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import treino.blanc.demo.dto.usuarioDto.*;
import treino.blanc.demo.entity.Usuario;
import treino.blanc.demo.mapper.UsuarioMapper;
import treino.blanc.demo.service.UsuarioService;
import treino.blanc.demo.utils.UsuarioUtils;

import java.util.List;

@RestController
@RequestMapping("usuarios")
public class UsuarioController {
    private UsuarioService service;
    private UsuarioUtils usuarioUtils;

    public UsuarioController(UsuarioService service, UsuarioUtils usuarioUtils) {
        this.service = service;
        this.usuarioUtils = usuarioUtils;
    }

    @PostMapping("/login")
    public ResponseEntity<UsuarioTokenDto> login(@RequestBody UsuarioLoginDTO usuarioLoginDTO) {
        System.out.println("Dados do usuário toke: " + usuarioLoginDTO.getEmail() + "\n " + usuarioLoginDTO.getSenha());
        UsuarioTokenDto usuarioToken = this.service.autenticar(usuarioLoginDTO);
        System.out.println("Dados do usuário toke: " + usuarioToken.getEmail() + "\n " + usuarioToken.getNome());
        return ResponseEntity.status(200).body(usuarioToken);
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<UsuarioDetalhesCriacaoDto> cadastrar(@RequestBody @Valid UsuarioCriacaoDto usuarioCriacaoDto) {
        Usuario novoUsuario = UsuarioMapper.of(usuarioCriacaoDto);
        return ResponseEntity.status(201).body(new UsuarioDetalhesCriacaoDto(service.cadastrar(novoUsuario)));
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> getAll() {
        return ResponseEntity.status(201).body(service.getAll());
    }


}
