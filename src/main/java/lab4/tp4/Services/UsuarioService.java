package lab4.tp4.Services;

import lab4.tp4.Entities.Usuario;
import lab4.tp4.Repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UsuarioService {
    @Autowired
    public UsuarioRepository repository;

    public Usuario Get(Long id) throws Exception {
        var entity = repository.findById(id);
        if (!entity.isPresent()){
            throw new Exception("Empresa ["+id+"] no encontrada.");
        }
        else return entity.get();
    }

    public List<Usuario> GetAll() {
        var res = repository.findAll();
        return res;
    }

}
