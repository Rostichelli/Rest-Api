package treino.blanc.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity(name = "Usuario")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonIgnore
    @Column(length = 50 * 1024 * 1024)
    private String fotoPerfil;
    private String nome;
    private String email;
    private String senha;
    private LocalDate dtNascimento;
    private Integer moedas = 0;
    private Integer xp = 0;
    private Integer nivel = 0;
    private Integer vida = 5;

    @Override
    public String toString() {
        return "Usuario:" +
                "id: " + id +
                "\n nome='" + nome + '\'' +
                "\n email='" + email + '\'' +
                "\n senha='" + senha + '\'' +
                "\n dtNascimento=" + dtNascimento +
                "\n moedas=" + moedas +
                "\n xp=" + xp +
                "\n nivel=" + nivel +
                '}';
    }
}
