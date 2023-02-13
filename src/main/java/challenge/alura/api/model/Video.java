package challenge.alura.api.model;

import challenge.alura.api.dto.DadosAtualizacaoVideo;
import challenge.alura.api.dto.DadosCadastroVideos;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name="Video")
@Table(name="videos")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Video {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long categoriaId;
    private String titulo;
    private String descricao;
    private String url;

    public Video(DadosCadastroVideos dados) {
        if(dados.categoriaId() == null){
            this.categoriaId = 1L;
        } else{
            this.categoriaId = dados.categoriaId();
        }
        this.titulo = dados.titulo();
        this.descricao = dados.descricao();
        this.url = dados.url();
    }

    public void atualizar(DadosAtualizacaoVideo dados){
        if(dados.categoriaId() != null){
            this.categoriaId = dados.categoriaId();
        }
        if(dados.titulo() != null){
            this.titulo = dados.titulo();
        }
        if(dados.descricao() != null){
            this.descricao = dados.descricao();
        }
        if(dados.url() != null){
            this.url = dados.url();
        }
    }
}
