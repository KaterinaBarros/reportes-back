package lab4.tp4.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lab4.tp4.Enums.Denominacion;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    private Denominacion Denominacion;

    @JsonIgnore
    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Instrumento> instrumentos;

    public Categoria() {
    }

    public Categoria(Integer id, Denominacion denominacion, Set<Instrumento> instrumentos) {
        Id = id;
        Denominacion = denominacion;
        this.instrumentos = instrumentos;
    }

    public Categoria(Denominacion denominacion) {
        Denominacion = denominacion;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public Denominacion getDenominacion() {
        return Denominacion;
    }

    public void setDenominacion(Denominacion denominacion) {
        Denominacion = denominacion;
    }
}
