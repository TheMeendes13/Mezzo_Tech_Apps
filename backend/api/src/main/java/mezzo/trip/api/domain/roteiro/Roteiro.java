package mezzo.trip.api.domain.roteiro;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mezzo.trip.api.domain.usuario.Usuario;

import java.time.LocalDate;


@Table(name = "roteiros")
@Entity(name = "Roteiro")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Roteiro {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String origem;
    private String destino;
    private Double gasto;
    private String voo;
    private LocalDate dataSaida;
    private LocalDate dataRetorno;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    private Boolean ativo;

    public Roteiro(DadosCadastroRoteiro dados, Usuario usuario) {
        this.ativo = true;
        this.origem = dados.origem();
        this.destino = dados.destino();
        this.gasto = dados.gasto();
        this.voo = dados.voo();
        this.dataSaida = dados.dataSaida();
        this.dataRetorno = dados.dataRetorno();
        this.usuario = usuario;
    }

    public void atualizarInformacoes(DadosAtualizarRoteiro dados) {
        if(dados.origem() != null) {
            this.origem = dados.origem();
        }
        if(dados.destino() != null) {
            this.destino = dados.destino();
        }
        if(dados.gasto() != null) {
            this.gasto = dados.gasto();
        }
        if(dados.voo() != null) {
            this.voo = dados.voo();
        }
        if(dados.dataSaida() != null) {
            this.dataSaida = dados.dataSaida();
        }
        if(dados.dataRetorno() != null) {
            this.dataRetorno = dados.dataRetorno();
        }
    }

    public void excluir(){
        this.ativo = false;
    }

}
