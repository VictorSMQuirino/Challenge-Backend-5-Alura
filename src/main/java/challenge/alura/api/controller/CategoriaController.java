package challenge.alura.api.controller;

import challenge.alura.api.categoria.*;
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
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    CategoriaRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroCategorias dados, UriComponentsBuilder uriBuilder){
        var categoria = new Categoria(dados);
        repository.save(categoria);

        var uri = uriBuilder.path("/categorias/{id}").buildAndExpand(categoria.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoCategorias(categoria));
    }

    @GetMapping
    public ResponseEntity<Page<Categoria>> listarTodos(@PageableDefault(page = 0, size = 10)Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(repository.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> buscarPorId(@PathVariable Long id){
       var categoria = repository.getReferenceById(id);

       return ResponseEntity.status(HttpStatus.OK).body(new DadosDetalhamentoCategorias(categoria));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<Object> atualizar(@RequestBody @Valid DadosAtualizacaoCategoria dados){
        var categoria = repository.getReferenceById(dados.id());
        categoria.atualizar(dados);

        return ResponseEntity.status(HttpStatus.OK).body(new DadosDetalhamentoCategorias(categoria));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> excluir(@PathVariable Long id){
        var categoria = repository.getReferenceById(id);
        repository.delete(categoria);

        return ResponseEntity.status(HttpStatus.OK).body(new DadosDetalhamentoCategorias(categoria));
    }
}
