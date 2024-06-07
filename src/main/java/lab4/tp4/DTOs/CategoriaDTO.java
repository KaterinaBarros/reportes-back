package lab4.tp4.DTOs;

import lab4.tp4.Enums.Denominacion;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaDTO {
    private Denominacion Denominacion;

    public lab4.tp4.Enums.Denominacion getDenominacion() {
        return Denominacion;
    }

    public void setDenominacion(lab4.tp4.Enums.Denominacion denominacion) {
        Denominacion = denominacion;
    }
}
