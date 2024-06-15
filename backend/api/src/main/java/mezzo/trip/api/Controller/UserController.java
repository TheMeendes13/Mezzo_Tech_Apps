package mezzo.trip.api.Controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import mezzo.trip.api.domain.usuario.DadosListarUsuario;
import mezzo.trip.api.domain.usuario.UserRepository;
import mezzo.trip.api.domain.usuario.Usuario;
import mezzo.trip.api.domain.usuario.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@CrossOrigin("*")
@RequestMapping("usuarios")
public class UserController {

    @Autowired
    private UserRepository repository;

    @Transactional
    @PostMapping
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroUser dados, UriComponentsBuilder uriBuilder) {
        var usuario = new Usuario(dados);
        repository.save(usuario);

        var uri = uriBuilder.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri(); // path original = uriBuilder.path("/usuarios/{id}")

        return ResponseEntity.created(uri).body(new DadosDetalheUser(usuario));
    }

    @GetMapping
    public ResponseEntity <Page<DadosListarUsuario>>listar(@PageableDefault(size = 5, sort = {"nome"}) Pageable paginacao) {
        var page = repository.findAllByAtivoTrue(paginacao).map(DadosListarUsuario::new); //Repository é a classe que acessa o BD
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizarUser dados) {
        var user = repository.getReferenceById(dados.id()); //busca item
        user.atualizarInformacoes(dados);

        return ResponseEntity.ok(new DadosDetalheUser(user));
    }

    //Exclusão Física
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id) {
        //Exclusão Física: repository.deleteById(id);
        var user = repository.getReferenceById(id);
        user.excluir();

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id) {
        var user = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalheUser(user));
    }

    /*@GetMapping("/me")
    public UsuarioDTO getAuthenticatedUser(@AuthenticationPrincipal Usuario usuario) {
        return new UsuarioDTO(usuario);
    }*/

}