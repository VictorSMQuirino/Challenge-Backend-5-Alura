package challenge.alura.api.controller;

import challenge.alura.api.categoria.CategoriaRepository;
import challenge.alura.api.categoria.DadosAtualizacaoCategoria;
import challenge.alura.api.categoria.DadosCadastroCategorias;
import challenge.alura.api.categoria.Categoria;
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
    public ResponseEntity<Page<Categoria>> listarTodos(@PageableDefault(page = 0, size = 10)Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(repository.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> buscarPorId(@PathVariable Long id){
        var categoria = repository.findById(id);

        if(categoria.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Categoria não encontrada.");
        }

        return ResponseEntity.status(HttpStatus.OK).body(categoria.get());
    }

    @PutMapping
    @Transactional
    public ResponseEntity<Object> atualizar(@RequestBody @Valid DadosAtualizacaoCategoria dados){
        if(repository.findById(dados.id()).isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Categoria não encontrada.");
        }

        var categoria = repository.getReferenceById(dados.id());
        categoria.atualizar(dados);

        return ResponseEntity.status(HttpStatus.OK).body("Categoria atualizada com sucesso!");
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> excluir(@PathVariable Long id){
        var categoria = repository.findById(id);

        if(categoria.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Categoria não encontrada");
        }

        repository.delete(categoria.get());
        
        return ResponseEntity.status(HttpStatus.OK).body("Categoria excluída com sucesso!");
    }
}
