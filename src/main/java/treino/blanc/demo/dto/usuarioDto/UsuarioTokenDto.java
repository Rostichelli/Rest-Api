package treino.blanc.demo.dto.usuarioDto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UsuarioTokenDto {
    private Integer id;
    private String fotoPerfil;
    private String nome;
    private String email;
    private String token;
    private Integer moedas;
    private Integer nivel;
    private Integer xp;
    private Integer vidas;
}
