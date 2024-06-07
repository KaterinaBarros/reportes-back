package lab4.tp4.Services;

import lab4.tp4.DTOs.CategoriaDTO;
import lab4.tp4.Entities.Categoria;
import lab4.tp4.Entities.Instrumento;
import lab4.tp4.Mappers.CategoriaMapper;
import lab4.tp4.Mappers.InstrumentoMapper;
import lab4.tp4.Repositories.CategoriasRepository;
import lab4.tp4.Repositories.InstrumentosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {
    @Autowired
    public CategoriasRepository repository;

    public void Create(Categoria categoria) throws Exception {
        try {
            repository.save(categoria);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Categoria Get(Integer id) throws Exception {
        var entity = repository.findById(id);
        if (!entity.isPresent()){
            throw new Exception("Empresa ["+id+"] no encontrada.");
        }
        else return entity.get();
    }

    public List<Categoria> GetAll() {
        var res = repository.findAll();
        return res;
    }
}
