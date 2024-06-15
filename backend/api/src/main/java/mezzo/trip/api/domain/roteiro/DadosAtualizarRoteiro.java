package mezzo.trip.api.domain.roteiro;

import java.time.LocalDate;

public record DadosAtualizarRoteiro(
        String origem,
        String destino,
        Double gasto,
        String voo,
        LocalDate dataSaida,
        LocalDate dataRetorno) {
}
