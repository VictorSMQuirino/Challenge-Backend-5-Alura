package challenge.alura.api.controller;

import challenge.alura.api.video.DadosCadastroVideos;
import challenge.alura.api.video.Video;
import challenge.alura.api.video.VideoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public List<Video> listarTodos(){
        return repository.findAll();
    }
}
