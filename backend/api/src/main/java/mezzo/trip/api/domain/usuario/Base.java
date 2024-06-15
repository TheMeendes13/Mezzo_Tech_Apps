package mezzo.trip.api.domain.usuario;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Base {

    private String cidade;
    private String estado;
    private String pais;

    public Base(DadosBase dados) {
        this.cidade = dados.cidade();
        this.estado = dados.estado();
        this.pais = dados.pais();

    }

    public void atualizarInformacoes(DadosBase dados) {
        if (dados.cidade() != null) {
            this.cidade = dados.cidade();
        }

        if (dados.estado() != null) {
            this.estado = dados.estado();
        }

        if (dados.pais() != null) {
            this.pais = dados.pais();
        }
    }
}
