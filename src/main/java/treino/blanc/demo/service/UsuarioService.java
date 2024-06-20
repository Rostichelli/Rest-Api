package treino.blanc.demo.service;

import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import treino.blanc.demo.configuration.security.GerenciadorTokenJwt;
import treino.blanc.demo.dto.usuarioDto.UsuarioLoginDTO;
import treino.blanc.demo.dto.usuarioDto.UsuarioTokenDto;
import treino.blanc.demo.entity.Usuario;
import treino.blanc.demo.mapper.UsuarioMapper;
import treino.blanc.demo.repository.UsuarioRepository;
import treino.blanc.demo.utils.UsuarioUtils;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private GerenciadorTokenJwt gerenciadorTokenJwt;
    @Autowired
    private UsuarioUtils usuarioUtils;
    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private AuthenticationManager authenticationManager;

    public Usuario cadastrar(Usuario novoUsuario) {
        if (repository.existsByEmail(novoUsuario.getEmail())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email já cadastrado");
        }

        novoUsuario.setSenha(passwordEncoder.encode(novoUsuario.getSenha()));
        repository.save(novoUsuario);
        return novoUsuario;
    }

    public void removerPerfil(String senha) {
        if (passwordEncoder.matches(senha, usuarioUtils.getUsuarioLogadoCompleto().getSenha())) {
            repository.delete(usuarioUtils.getUsuarioLogadoCompleto());
            return;
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Senha incorreta");
    }

    public UsuarioTokenDto autenticar(UsuarioLoginDTO usuarioLoginDTO) {
        final UsernamePasswordAuthenticationToken credentials = new UsernamePasswordAuthenticationToken(usuarioLoginDTO.getEmail(), usuarioLoginDTO.getSenha());
        System.out.println("Credenciais: " + credentials.getPrincipal());

        final Authentication authentication = this.authenticationManager.authenticate(credentials);

        Usuario usuarioAutenticado = repository.findByEmail(usuarioLoginDTO.getEmail()).orElseThrow(() -> new ResponseStatusException(404, "Email do usuário não cadastrado", null));

        System.out.println("Usuário autenticado: " + usuarioAutenticado.getNome() + "\n" + usuarioAutenticado.getEmail());

        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = gerenciadorTokenJwt.generateToken(authentication);
        return UsuarioMapper.of(usuarioAutenticado, token);
    }
}