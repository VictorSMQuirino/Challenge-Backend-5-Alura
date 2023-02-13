package challenge.alura.api.dto;

import challenge.alura.api.model.Video;

public record DadosDetalhamentoVideos(Long id, Long categoriaId, String titulo, String descricao, String url) {

    public DadosDetalhamentoVideos(Video video){
        this(video.getId(), video.getCategoriaId(), video.getTitulo(), video.getDescricao(), video.getUrl());
    }
}
