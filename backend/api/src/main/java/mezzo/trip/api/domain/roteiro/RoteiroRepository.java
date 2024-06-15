package mezzo.trip.api.domain.roteiro;

import mezzo.trip.api.domain.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoteiroRepository extends JpaRepository<Roteiro, Long> {
    List<Roteiro> findByUsuario(Usuario usuario);

    List<Roteiro> findByUsuarioId(Long usuarioId);
}
