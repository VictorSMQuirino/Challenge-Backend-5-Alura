package challenge.alura.api.controller;

import challenge.alura.api.video.DadosAtualizacaoVideo;
import challenge.alura.api.video.DadosCadastroVideos;
import challenge.alura.api.video.Video;
import challenge.alura.api.video.VideoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

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
    public List<Video> listarTodos(){
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Video buscarPorId(@PathVariable Long id){
        var video = repository.findById(id);

        if(video.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return video.get();
    }

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid DadosAtualizacaoVideo dados){
        var video = repository.getReferenceById(dados.id());
        video.atualizar(dados);
    }
}
