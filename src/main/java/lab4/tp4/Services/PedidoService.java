package lab4.tp4.Services;

import lab4.tp4.Entities.Categoria;
import lab4.tp4.Entities.Instrumento;
import lab4.tp4.Entities.Pedido;
import lab4.tp4.Entities.PedidoDTO;
import lab4.tp4.Mappers.InstrumentoMapper;
import lab4.tp4.Mappers.PedidoMapper;
import lab4.tp4.Repositories.CategoriasRepository;
import lab4.tp4.Repositories.InstrumentosRepository;
import lab4.tp4.Repositories.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService {
    @Autowired
    public PedidoRepository repository;

    private final InstrumentoMapper instrumentoMapper;
    private final PedidoMapper pedidoMapper;
    public PedidoService(InstrumentoMapper instrumentoMapper, PedidoMapper pedidoMapper) {
        this.instrumentoMapper = instrumentoMapper;
        this.pedidoMapper = pedidoMapper;
    }

    @Autowired
    public CategoriasRepository repositoryCategorias;

    @Autowired
    public InstrumentosRepository repositoryInstrumentos;

    public Pedido Create(PedidoDTO pedidoDTO) throws Exception {
        try {
            var pedido = pedidoMapper.toPedido(pedidoDTO);
            pedido.getCartItems().clear();
            for (int i = 0; i < pedidoDTO.getCartItems().size(); i++) {
                var instrumento = repositoryInstrumentos.findById(pedidoDTO.getCartItems().get(i).getId()).orElse(null);
                pedido.getCartItems().add(instrumento);
            }

            var res =  repository.save(pedido);
            return res;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Pedido Get(Integer id) throws Exception {
        var entity = repository.findById(id);
        if (!entity.isPresent()){
            throw new Exception("Empresa ["+id+"] no encontrada.");
        }
        else return entity.get();
    }

    public List<Pedido> GetAll() {
        var res = repository.findAll();
        return res;
    }
}
