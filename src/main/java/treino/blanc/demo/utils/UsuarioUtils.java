package treino.blanc.demo.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import treino.blanc.demo.dto.usuarioDto.UsuarioCriacaoDto;
import treino.blanc.demo.entity.Usuario;
import treino.blanc.demo.repository.UsuarioRepository;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@Component
public class UsuarioUtils {
    private final UsuarioRepository repository;
    private ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public Usuario getUsuarioLogado() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (Usuario) authentication.getPrincipal();
    }

    public Usuario gerarUsuarioTemoporario() {
        int totalTempUsers = repository.countByNomeContainsIgnoreCase("tempUser");
        Usuario usuarioTemp = new Usuario();
        usuarioTemp.setEmail("tempUser" + (totalTempUsers + 1) + "@tempmail.com");
        usuarioTemp.setDtNascimento(usuarioTemp.getDtNascimento());
        usuarioTemp.setNome(usuarioTemp.getNome() + (totalTempUsers + 1));

        return usuarioTemp;
    }

    public Usuario getUsuarioLogadoCompleto() {
        return repository.findById(getUsuarioLogado().getId()).get();
    }

}
