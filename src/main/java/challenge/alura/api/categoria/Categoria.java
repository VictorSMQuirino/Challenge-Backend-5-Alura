package challenge.alura.api.categoria;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "Categoria")
@Table(name = "categorias")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class Categoria {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String cor;

    public Categoria(DadosCadastroCategorias dados){
        this.titulo = dados.titulo();
        this.cor = dados.cor();
    }

    public void atualizar(DadosAtualizacaoCategoria dados) {
        if(dados.titulo() != null){
            this.titulo = dados.titulo();
        }
        if(dados.cor() != null){
            this.cor = dados.cor();
        }
    }
}
