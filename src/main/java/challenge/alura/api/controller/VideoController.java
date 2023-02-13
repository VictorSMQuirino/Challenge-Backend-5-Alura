package challenge.alura.api.controller;

import challenge.alura.api.dto.DadosAtualizacaoVideo;
import challenge.alura.api.dto.DadosCadastroVideos;
import challenge.alura.api.dto.DadosDetalhamentoVideos;
import challenge.alura.api.model.Video;
import challenge.alura.api.repository.VideoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/videos")
public class VideoController {

    @Autowired
    VideoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroVideos dados, UriComponentsBuilder uriBuilder){
        var video = new Video(dados);
        repository.save(video);

        var uri = uriBuilder.path("/videos/{id}").buildAndExpand(video.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoVideos(video));
    }

    @GetMapping
    public ResponseEntity<Page<Video>> listarTodos(@PageableDefault(page = 0, size = 10) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(repository.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> buscarPorId(@PathVariable Long id){
        var video = repository.getReferenceById(id);

       return ResponseEntity.status(HttpStatus.OK).body(new DadosDetalhamentoVideos(video));
    }

    @GetMapping("/busca-nome")
    public ResponseEntity<Object> buscarPorTitulo(@RequestParam(name = "search") String busca, @PageableDefault(page = 0, size = 10)Pageable pageable){
        var videos = repository.findAllByTituloContaining(busca, pageable);

        return ResponseEntity.status(HttpStatus.OK).body(videos);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<Object> atualizar(@RequestBody @Valid DadosAtualizacaoVideo dados){
        var video = repository.getReferenceById(dados.id());
        video.atualizar(dados);

        return ResponseEntity.status(HttpStatus.OK).body(new DadosDetalhamentoVideos(video));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> excluir(@PathVariable Long id){
        var video = repository.getReferenceById(id);
        repository.delete(video);

        return ResponseEntity.status(HttpStatus.OK).body(new DadosDetalhamentoVideos(video));
    }
}
