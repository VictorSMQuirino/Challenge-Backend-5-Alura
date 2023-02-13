package challenge.alura.api.dto;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroCategorias(
        @NotBlank
        String titulo,
        @NotBlank
        String cor) {
}
