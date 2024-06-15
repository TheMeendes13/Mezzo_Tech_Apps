package mezzo.trip.api.Controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import mezzo.trip.api.domain.roteiro.DadosCadastroRoteiro;
import mezzo.trip.api.domain.roteiro.Roteiro;
import mezzo.trip.api.domain.roteiro.RoteiroRepository;
import mezzo.trip.api.domain.roteiro.RoteiroService;
import mezzo.trip.api.domain.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("roteiros")
@CrossOrigin("*")
public class RoteiroController {

    @Autowired
    private RoteiroService roteiroService;

    @Autowired
    private RoteiroRepository roteiroRepository;

    @Transactional
    @PostMapping
    public ResponseEntity<Roteiro> criarRoteiro(@RequestBody DadosCadastroRoteiro dados, @RequestParam Long usuarioId) {
        Roteiro roteiro = roteiroService.criarRoteiro(dados, usuarioId);
        return ResponseEntity.ok(roteiro);
    }

    /*@GetMapping
    public ResponseEntity<List<Roteiro>> ListarRoteiros(@RequestParam Long usuarioId) {
        List<Roteiro> roteiros = roteiroService.listarRoteiros(usuarioId);
        return ResponseEntity.ok(roteiros);
    }*/

    @GetMapping
    public List<Roteiro> getRoteirosByUser(@AuthenticationPrincipal Usuario usuario) {
        return roteiroService.listarRoteiros(usuario.getId());
    }


    /*@DeleteMapping("/{id}")
    @Transactional
    public void excluir(@PathVariable Long id) { // parametro dinamico p/ excluir
        var roteiro = roteiroService.getReferenceById(id);
        roteiro.excluir();
    } */
}
