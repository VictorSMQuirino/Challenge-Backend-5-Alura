package challenge.alura.api.controller;

import challenge.alura.api.video.DadosAtualizacaoVideo;
import challenge.alura.api.video.DadosCadastroVideos;
import challenge.alura.api.video.Video;
import challenge.alura.api.video.VideoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/videos")
public class VideoController {

    @Autowired
    VideoRepository repository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosCadastroVideos dados){
        repository.save(new Video(dados));
    }

    @GetMapping
    public ResponseEntity<Page<Video>> listarTodos(@PageableDefault(page = 0, size = 10) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(repository.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> buscarPorId(@PathVariable Long id){
        var video = repository.findById(id);

        if(video.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Video não encontrado.");
        }

       return ResponseEntity.status(HttpStatus.OK).body(video.get());
    }

    @PutMapping
    @Transactional
    public ResponseEntity<Object> atualizar(@RequestBody @Valid DadosAtualizacaoVideo dados){
        if(repository.findById(dados.id()).isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Video não encontrado.");
        }

        var video = repository.getReferenceById(dados.id());
        video.atualizar(dados);

        return ResponseEntity.status(HttpStatus.OK).body("Vídeo atualizado com sucesso!");
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> excluir(@PathVariable Long id){
        var video = repository.findById(id);

        if(video.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Video não encontrado");
        }

        repository.delete(video.get());

        return ResponseEntity.status(HttpStatus.OK).body("Vídeo excluído com sucesso!");
    }
}
