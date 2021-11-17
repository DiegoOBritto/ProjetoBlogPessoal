package br.org.generation.blogpessoal.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import br.org.generation.blogpessoal.model.Usuario;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UsuarioRepositoryTest {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@BeforeAll
	void start() {

		usuarioRepository.save(new Usuario(0L, "Diego Oliveira", "diego@email.com.br", "12345678"));
		usuarioRepository.save(new Usuario(0L, "Raphaela Oliveira", "raphaela@email.com.br", "12345678"));
		usuarioRepository.save(new Usuario(0L, "Claudia Oliveira", "claudia@email.com.br", "12345678"));
		usuarioRepository.save(new Usuario(0L, "Paulo Henrique", "paulo@email.com.br", "12345678"));
	}

	@Test
	@DisplayName("Retorna 1 usuário")
	public void deveRetornarUmUsuario() {

		Optional<Usuario> usuario = usuarioRepository.findByUsuario("diego@email.com.br");
		assertTrue(usuario.get().getUsuario().equals("diego@email.com.br"));
	}

	@Test
	@DisplayName("Retorna 3 usuários")
	public void deveRetornarTresUsuarios() {

		List<Usuario> listaDeUsuarios = usuarioRepository.findAllByNomeContainingIgnoreCase("Oliveira");
		assertEquals(3, listaDeUsuarios.size());
		assertTrue(listaDeUsuarios.get(0).getNome().equals("Diego Oliveira"));
		assertTrue(listaDeUsuarios.get(1).getNome().equals("Raphaela Oliveira"));
		assertTrue(listaDeUsuarios.get(2).getNome().equals("Claudia Oliveira"));

	}

}
