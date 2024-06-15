package mezzo.trip.api.domain.roteiro;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record DadosCadastroRoteiro(
        @NotBlank
        String origem,

        @NotBlank
        String destino,

        @NotBlank
        Double gasto,

        String voo,

        LocalDate dataSaida,

        LocalDate dataRetorno) {
}
