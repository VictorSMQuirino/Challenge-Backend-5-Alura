package challenge.alura.api.categoria;

public record DadosDetalhamentoCategorias(Long id, String titulo, String cor) {

    public DadosDetalhamentoCategorias(Categoria categoria){
        this(categoria.getId(), categoria.getTitulo(), categoria.getCor());
    }
}
