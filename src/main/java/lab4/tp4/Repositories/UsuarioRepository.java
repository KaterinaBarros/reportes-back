package lab4.tp4.Repositories;

import lab4.tp4.Entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByNombreUsuarioAndClave(String nombreUsuario, String clave);
}
