package mezzo.trip.api.domain.usuario;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizarUser(

        @NotNull
        Long id,
        String nome,
        DadosBase base) {
}
