package challenge.alura.api.controller;

import challenge.alura.api.video.DadosCadastroVideos;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/videos")
public class VideoController {

    @PostMapping
    public void cadastrar(@RequestBody DadosCadastroVideos dados){
        System.out.println(dados);
    }
}
