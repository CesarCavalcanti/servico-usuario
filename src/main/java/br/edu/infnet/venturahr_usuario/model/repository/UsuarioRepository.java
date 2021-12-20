package br.edu.infnet.venturahr_usuario.model.repository;

import br.edu.infnet.venturahr_usuario.model.domain.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario,Integer> {
    @Query ("from Usuario u where u.email=:email")
    Usuario findByEmail(String email);
}
