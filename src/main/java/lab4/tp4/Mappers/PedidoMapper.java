package lab4.tp4.Mappers;

import lab4.tp4.Entities.Pedido;
import lab4.tp4.Entities.PedidoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PedidoMapper {
    Pedido toPedido(PedidoDTO dto);

}
