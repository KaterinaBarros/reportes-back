package lab4.tp4.Controllers;
import lab4.tp4.Entities.Usuario;
import lab4.tp4.Services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.HttpStatus;


@RestController
@RequestMapping("/api/Usuario")
public class LoginController {

    @Autowired
    public UsuarioService service;

    @GetMapping("{id}")
    public ResponseEntity<?> GetUsuario(@PathVariable("id") Long id){
        try {
            return new ResponseEntity<>(service.Get(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<?> GetAllUsuario(){
        return new ResponseEntity<>(service.GetAll(), HttpStatus.OK);
    }



}



