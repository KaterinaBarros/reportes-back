package lab4.tp4.Services;

import lab4.tp4.DTOs.InstrumentoDTO;
import lab4.tp4.Entities.Instrumento;
import lab4.tp4.Mappers.InstrumentoMapper;
import lab4.tp4.Repositories.CategoriasRepository;
import lab4.tp4.Repositories.InstrumentosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Service
public class InstrumentosService {
    
    private final InstrumentoMapper instrumentoMapper;

    @Autowired
    public InstrumentosService(InstrumentoMapper instrumentoMapper) {
        this.instrumentoMapper = instrumentoMapper;
    }

    @Autowired
    public InstrumentosRepository repositoryInstrumentos;
    @Autowired
    public CategoriasRepository repositoryCategorias;


    public Instrumento Create(InstrumentoDTO dto) throws Exception {
        try {
            var categoria = repositoryCategorias.findByDenominacion(dto.getCategoria());

            var entity = instrumentoMapper.toInstrumento(dto);
            entity.setCategoria(categoria);
            return repositoryInstrumentos.save(entity);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Instrumento Update(InstrumentoDTO dto, Integer id) throws Exception {
        try {
            var categoria = repositoryCategorias.findByDenominacion(dto.getCategoria());
            var entity = repositoryInstrumentos.findById(id).orElse(null);

            entity.setInstrumento(dto.getInstrumento());
            entity.setMarca(dto.getMarca());
            entity.setModelo(dto.getModelo());
            entity.setCategoria(categoria);
            entity.setImagen(dto.getImagen());
            entity.setPrecio(dto.getPrecio());
            entity.setCostoEnvio(dto.getCostoEnvio());
            entity.setCantidadVendida(dto.getCantidadVendida());
            entity.setDescripcion(dto.getDescripcion());

            return repositoryInstrumentos.save(entity);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void CreateMassive(List<InstrumentoDTO> dtos) throws Exception {
        try {
            List<Instrumento> instrumentos = new ArrayList<>();

            for (InstrumentoDTO item : dtos) {
                var categoria = repositoryCategorias.findByDenominacion(item.getCategoria());

                var entity = instrumentoMapper.toInstrumento(item);
                entity.setCategoria(categoria);
                instrumentos.add(entity);
            }

            repositoryInstrumentos.saveAll(instrumentos);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public InstrumentoDTO Get(Integer id) throws Exception {
        var entity = repositoryInstrumentos.findById(id);
        if (!entity.isPresent()){
            throw new Exception("Empresa ["+id+"] no encontrada.");
        }
        else{
            var dto = instrumentoMapper.toInstrumentoDTO(entity.get());
            return dto;
        }
    }

    public List<InstrumentoDTO> GetAll() {
        List<InstrumentoDTO> dtos = new ArrayList<>();
        var entities = repositoryInstrumentos.findAll();
        for (Instrumento item : entities) {
            var dto = instrumentoMapper.toInstrumentoDTO(item);
            dtos.add(dto);
        }
        return dtos;
    }

    public List<InstrumentoDTO> GetAllByCategoria(Integer idCategoria) {
        var instrumentos = repositoryInstrumentos.findInstrumentosByDenominacion(idCategoria);
        List<InstrumentoDTO> listDTO = new ArrayList<>();
        for (Instrumento item : instrumentos) {
            var dto = instrumentoMapper.toInstrumentoDTO(item);
            listDTO.add(dto);
        }
        return listDTO;
    }

    public Integer Delete(Integer id) {
        var entity = repositoryInstrumentos.findById(id).orElse(null);
        repositoryInstrumentos.delete(entity);
        return entity.getId();
    }



}
