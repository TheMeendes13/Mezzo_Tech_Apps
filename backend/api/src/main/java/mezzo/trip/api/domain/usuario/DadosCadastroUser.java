package mezzo.trip.api.domain.usuario;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroUser(


        @NotBlank
        String nome,

        @NotBlank
        @Email
        String email,

        @NotBlank
        String senha,

        @NotNull
        Genero genero,

        @NotNull
        Perfil perfil,

        @NotNull
        @Valid
        DadosBase base){
}
