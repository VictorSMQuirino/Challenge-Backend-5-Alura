package challenge.alura.api.categoria;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroCategorias(
        @NotBlank
        String titulo,
        @NotBlank
        String cor) {
}
