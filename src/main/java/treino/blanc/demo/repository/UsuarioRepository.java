package treino.blanc.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import treino.blanc.demo.entity.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    @Query("SELECT u FROM Usuario u WHERE u.nome LIKE :nome% AND u.id != :usuarioLogadoID")
    List<Usuario> buscarPorNome(String nome, Integer usuarioLogadoID);

    @Query("SELECT u FROM Usuario u ORDER BY u.xp DESC LIMIT 100")
    List<Usuario> buscarRanking();

    Optional<Usuario> findByEmail(String email);
    boolean existsByEmail(String email);
    boolean existsByNome(String nome);

    Optional<Usuario> findByNomeIgnoreCase(String nome);

    int countByNomeContainsIgnoreCase(String nome);
    @Modifying
    @Transactional
    @Query("update Usuario u set u.fotoPerfil = ?2 where u.id = ?1")
    void setFoto(Integer id, String foto);

//    @Query("select p.foto from Planta p where p.id = ?1")
//    byte[] getFoto(Integer id);
//
//    @Modifying
//    @Transactional
//    @Query("update Planta p set p.relatorioExcel = ?2 where p.id = ?1")
//    void setRelatorio(Integer id, byte[] foto);
//
//    @Query("select p.relatorioExcel from Planta p where p.id = ?1")
//    byte[] getRelatorio(Integer id);

}
