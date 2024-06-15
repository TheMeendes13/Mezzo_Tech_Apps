package mezzo.trip.api.domain.usuario;

public record DadosDetalheUser(Long id, String nome, String email, Genero genero, Perfil perfil, Base base) {

    public DadosDetalheUser(Usuario usuario) {
        this(usuario.getId(), usuario.getNome(), usuario.getEmail(), usuario.getGenero(),  usuario.getPerfil(), usuario.getBase());
    }
}
