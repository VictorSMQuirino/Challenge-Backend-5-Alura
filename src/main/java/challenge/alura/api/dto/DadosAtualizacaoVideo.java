package challenge.alura.api.dto;

import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.URL;

public record DadosAtualizacaoVideo(
        @NotNull
        Long id,
        Long categoriaId,
        String titulo,
        String descricao,
        @URL(protocol = "http")
        String url) {
}
