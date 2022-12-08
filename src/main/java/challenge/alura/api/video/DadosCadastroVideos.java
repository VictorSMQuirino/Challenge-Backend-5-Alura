package challenge.alura.api.video;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

public record DadosCadastroVideos(
        @NotBlank
        String titulo,
        @NotBlank
        String descricao,
        @NotBlank @URL(protocol = "http")
        String url) {
}
