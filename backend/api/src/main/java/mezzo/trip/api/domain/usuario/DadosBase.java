package mezzo.trip.api.domain.usuario;

import jakarta.validation.constraints.NotBlank;

public record DadosBase(

        @NotBlank
        String cidade,

        @NotBlank
        String estado,

        @NotBlank
        String pais) {
}
