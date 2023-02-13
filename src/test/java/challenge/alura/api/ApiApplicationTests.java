package challenge.alura.api;

import challenge.alura.api.model.Categoria;
import challenge.alura.api.repository.CategoriaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class ApiApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	public void cadastroDeCategoria(){
		CategoriaRepository repository;
		Categoria categoria = new Categoria(1L, "categoria x", "Preto");

		//assertThrows(repository.save(categoria), categoria.getClass());
	}

}
