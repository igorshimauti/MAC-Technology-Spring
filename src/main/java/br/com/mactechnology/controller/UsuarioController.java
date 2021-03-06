package br.com.mactechnology.controller;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.mactechnology.controller.dto.DtoToken;
import br.com.mactechnology.controller.dto.DtoUsuario;
import br.com.mactechnology.controller.dto.input.InputLogin;
import br.com.mactechnology.controller.dto.input.InputUsuario;
import br.com.mactechnology.controller.mapper.UsuarioMapper;
import br.com.mactechnology.model.Usuario;
import br.com.mactechnology.repository.UsuarioRepository;
import br.com.mactechnology.service.TokenService;
import br.com.mactechnology.service.UsuarioService;

@CrossOrigin(origins = "https://mac-cursos.netlify.app", maxAge = 7200)
//@CrossOrigin(origins = "http://localhost:4200", maxAge = 7200)
@RestController
@RequestMapping(value = "/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private UsuarioMapper usuarioMapper;
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private TokenService tokenService;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoUsuario>> listar() {
		return ResponseEntity.ok(usuarioMapper.toCollectionDto(usuarioRepository.findAll()));
	}
	
	@GetMapping(value = "/bloqueados", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoUsuario>> listarBloqueados() {
		return ResponseEntity.ok(usuarioMapper.toCollectionDto(usuarioRepository.findByAutorizado(false)));
	}

	@GetMapping(value = "/autorizados", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoUsuario>> listarAutorizados() {
		return ResponseEntity.ok(usuarioMapper.toCollectionDto(usuarioRepository.findByAutorizado(true)));
	}
	
	@GetMapping(value = "/{usuarioId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoUsuario> buscar(@PathVariable Long usuarioId) {
		return usuarioRepository.findById(usuarioId)
				.map(usuario -> ResponseEntity.ok(usuarioMapper.toDto(usuario)))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<DtoUsuario> incluir(@Valid @RequestBody InputUsuario input) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		Usuario usuario = usuarioService.salvar(usuarioMapper.toEntity(input));
		return ResponseEntity.ok(usuarioMapper.toDto(usuario));
	}
	
	@PutMapping(value = "/{usuarioId}/autorizar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoUsuario> autorizar(@PathVariable Long usuarioId) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		Usuario usuario = usuarioService.buscar(usuarioId);
		usuario.setAutorizado(true);
		return ResponseEntity.ok(usuarioMapper.toDto(usuarioService.salvar(usuario)));
	}

	@PutMapping(value = "/{usuarioId}/setAdmin", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoUsuario> setAdmin(@PathVariable Long usuarioId) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		Usuario usuario = usuarioService.buscar(usuarioId);
		usuario.setAdmin(true);
		return ResponseEntity.ok(usuarioMapper.toDto(usuarioService.salvar(usuario)));
	}
	
	@DeleteMapping(value = "/{usuarioId}")
	public ResponseEntity<Void> excluir(@PathVariable Long usuarioId) {
		if (!usuarioRepository.existsById(usuarioId)) {
			return ResponseEntity.notFound().build();
		}
		
		usuarioService.excluir(usuarioId);
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping("/logar")
	public ResponseEntity<DtoToken> logar(@RequestBody InputLogin input) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		String hash = usuarioService.encriptarSenha(input.getSenha());
		input.setSenha(hash);
		
		try {
			UsernamePasswordAuthenticationToken dadosLogin = input.converte();
			Authentication authentication = authenticationManager.authenticate(dadosLogin);
			String token = tokenService.gerarToken(authentication);
			return ResponseEntity.ok(new DtoToken(token, "Bearer"));
		} catch (AuthenticationException e) {
			System.out.println(e.getMessage());
			return ResponseEntity.badRequest().build();
		}
	}
}