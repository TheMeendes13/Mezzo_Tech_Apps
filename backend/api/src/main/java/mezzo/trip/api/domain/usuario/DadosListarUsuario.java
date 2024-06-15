package mezzo.trip.api.domain.usuario;

public record DadosListarUsuario(Long id, String nome, Perfil perfil, Base base) {  //DTO

    public DadosListarUsuario(Usuario usuario) {
        this(usuario.getId(), usuario.getNome(), usuario.getPerfil() , usuario.getBase());
    }
}
