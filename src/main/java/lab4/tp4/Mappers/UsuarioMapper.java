package lab4.tp4.Mappers;
import lab4.tp4.DTOs.UsuarioDTO;
import lab4.tp4.Entities.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {
    Usuario toUsuario(UsuarioDTO dto);
    UsuarioDTO toUsuarioDTO(Usuario usuario);

}
