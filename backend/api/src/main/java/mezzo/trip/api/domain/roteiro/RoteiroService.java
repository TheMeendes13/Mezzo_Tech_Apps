package mezzo.trip.api.domain.roteiro;

import mezzo.trip.api.domain.roteiro.DadosCadastroRoteiro;
import mezzo.trip.api.domain.roteiro.Roteiro;
import mezzo.trip.api.domain.roteiro.RoteiroRepository;
import mezzo.trip.api.domain.usuario.UserRepository;
import mezzo.trip.api.domain.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoteiroService {

    @Autowired
    private RoteiroRepository roteiroRepository;

    @Autowired
    private UserRepository userRepository;

    public Roteiro criarRoteiro(DadosCadastroRoteiro dados, Long usuarioId) {
        Usuario usuario = userRepository.findById(usuarioId).orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));
        Roteiro roteiro = new Roteiro(dados, usuario);
        return roteiroRepository.save(roteiro);
    }

    public List<Roteiro> listarRoteiros(Long usuarioId) {
        Usuario usuario = userRepository.findById(usuarioId).orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));
        return roteiroRepository.findByUsuario(usuario);
    }
}
