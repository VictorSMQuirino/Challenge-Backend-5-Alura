package challenge.alura.api.video;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroVideos(
        @NotBlank
        String titulo,
        @NotBlank
        String descricao,
        @NotBlank
        String url) {
}
