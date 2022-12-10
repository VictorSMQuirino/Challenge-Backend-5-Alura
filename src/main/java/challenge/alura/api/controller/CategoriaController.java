package challenge.alura.api.controller;

import challenge.alura.api.categoria.CategoriaRepository;
import challenge.alura.api.categoria.DadosCadastroCategorias;
import challenge.alura.api.categoria.Categoria;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    CategoriaRepository repository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosCadastroCategorias dados){
        repository.save(new Categoria(dados));
    }

    @GetMapping
    public List<Categoria> listarTodos(){
        return repository.findAll();
    }
}
