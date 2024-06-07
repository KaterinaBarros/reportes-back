package lab4.tp4.Controllers;

import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.preference.PreferenceBackUrlsRequest;
import com.mercadopago.client.preference.PreferenceClient;
import com.mercadopago.client.preference.PreferenceItemRequest;
import com.mercadopago.client.preference.PreferenceRequest;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.preference.Preference;
import lab4.tp4.DTOs.CategoriaDTO;
import lab4.tp4.DTOs.InstrumentoDTO;
import lab4.tp4.Entities.Categoria;
import lab4.tp4.Entities.Instrumento;
import lab4.tp4.Entities.PreferenceMP;
import lab4.tp4.Enums.Denominacion;
import lab4.tp4.Services.CategoriaService;
import lab4.tp4.Services.InstrumentosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/Categorias")
public class CategoriaController {

    @Autowired
    public CategoriaService service;

    @GetMapping("{id}")
    public ResponseEntity<Categoria> GetCategoria(@PathVariable("id") Integer id){
        try {
            var res = service.Get(id);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Categoria(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<?> GetAllCategoria(){
        return new ResponseEntity<>(service.GetAll(), HttpStatus.OK);
    }

    @PostMapping("Create")
    public ResponseEntity<?> CreateCategoria(@RequestParam Denominacion categoria){
        try {
            var res = categoria;
            System.out.println(categoria);
            service.Create(new Categoria(categoria));
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
