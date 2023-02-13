package challenge.alura.api.dto;

import challenge.alura.api.model.Categoria;

public record DadosDetalhamentoCategorias(Long id, String titulo, String cor) {

    public DadosDetalhamentoCategorias(Categoria categoria){
        this(categoria.getId(), categoria.getTitulo(), categoria.getCor());
    }
}
