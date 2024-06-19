package treino.blanc.demo.mapper;

import treino.blanc.demo.dto.usuarioDto.UsuarioCriacaoDto;
import treino.blanc.demo.dto.usuarioDto.UsuarioTokenDto;
import treino.blanc.demo.entity.Usuario;

public class UsuarioMapper {
    public static Usuario of(UsuarioCriacaoDto usuarioCriacaoDto) {
        Usuario usuario = new Usuario();
        usuario.setEmail(usuarioCriacaoDto.getEmail());
        usuario.setDtNascimento(usuarioCriacaoDto.getDtNascimento());
        usuario.setNome(usuarioCriacaoDto.getNome());
        usuario.setSenha(usuarioCriacaoDto.getSenha());
        return usuario;
    }

    public static UsuarioTokenDto of(Usuario usuario, String token) {
        UsuarioTokenDto usuarioTokenDto = new UsuarioTokenDto();

        usuarioTokenDto.setId(usuario.getId());
        if (usuario.getFotoPerfil() != null) {
            usuarioTokenDto.setFotoPerfil(usuario.getFotoPerfil());
        } else {
            usuarioTokenDto.setFotoPerfil(null);
        }
        usuarioTokenDto.setEmail(usuario.getEmail());
        usuarioTokenDto.setNome(usuario.getNome());
        usuarioTokenDto.setToken(token);
        usuarioTokenDto.setMoedas(usuario.getMoedas());
        usuarioTokenDto.setNivel(usuario.getNivel());
        usuarioTokenDto.setXp(usuario.getXp());
        usuarioTokenDto.setVidas(usuario.getVida());

        return usuarioTokenDto;
    }
}
