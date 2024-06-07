package lab4.tp4.Mappers;

import lab4.tp4.DTOs.CategoriaDTO;
import lab4.tp4.Entities.Categoria;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoriaMapper {
    Categoria toCategoria(CategoriaDTO dto);
    CategoriaDTO toCategoriaDTO(Categoria categoria);
}
