package challenge.alura.api.controller;

import challenge.alura.api.video.DadosCadastroVideos;
import challenge.alura.api.video.Video;
import challenge.alura.api.video.VideoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/videos")
public class VideoController {

    @Autowired
    VideoRepository repository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody DadosCadastroVideos dados){
        repository.save(new Video(dados));
    }
}
