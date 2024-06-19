package treino.blanc.demo.dto.usuarioDto;

import treino.blanc.demo.entity.Usuario;

public record UsuarioDetalhesCriacaoDto(String nome, String email) {
    public UsuarioDetalhesCriacaoDto(Usuario usuario) {
        this(usuario.getNome(), usuario.getEmail());
    }
}
