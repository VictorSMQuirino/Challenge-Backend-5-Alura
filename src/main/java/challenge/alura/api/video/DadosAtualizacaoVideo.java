package challenge.alura.api.video;

import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.URL;

public record DadosAtualizacaoVideo(
        @NotNull
        Long id,
        String titulo,
        String descricao,
        @URL(protocol = "http")
        String url) {
}
