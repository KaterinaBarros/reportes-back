package lab4.tp4.Mappers;

import lab4.tp4.DTOs.InstrumentoDTO;
import lab4.tp4.Entities.Instrumento;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface InstrumentoMapper {

    Instrumento toInstrumento(InstrumentoDTO dto);
    @Mapping(source = "categoria.denominacion", target = "categoria")
    InstrumentoDTO toInstrumentoDTO(Instrumento instrumento);
}
