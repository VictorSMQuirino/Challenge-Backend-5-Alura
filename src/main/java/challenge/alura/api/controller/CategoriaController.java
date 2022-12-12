package challenge.alura.api.controller;

import challenge.alura.api.categoria.CategoriaRepository;
import challenge.alura.api.categoria.DadosAtualizacaoCategoria;
import challenge.alura.api.categoria.DadosCadastroCategorias;
import challenge.alura.api.categoria.Categoria;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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

    @GetMapping("/{id}")
    public Categoria buscarPorId(@PathVariable Long id){
        var categoria = repository.findById(id);

        if(categoria.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return categoria.get();
    }

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid DadosAtualizacaoCategoria dados){
        var categoria = repository.getReferenceById(dados.id());
        categoria.atualizar(dados);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void excluir(@PathVariable Long id){
        var categoria = repository.findById(id);

        if(categoria.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        repository.delete(categoria.get());
    }
}
