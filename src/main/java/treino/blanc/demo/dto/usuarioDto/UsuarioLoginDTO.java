package treino.blanc.demo.dto.usuarioDto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class UsuarioLoginDTO {
    @Email(message = "Campo email não foi reconhecido como email válido")
    private String email;
    @Pattern(regexp = "^(?!.*\\s)(?!.*\\s$)(?!.*\\s\\s)[\\S\\s]*$", message = "Campo senha inválida - regex")
    private String senha;
}
