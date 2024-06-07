package lab4.tp4.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Instrumento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;
    private String Instrumento;
    private String Marca;
    private String Modelo;
    private String Imagen;
    private Double Precio;
    private String CostoEnvio;
    private Integer CantidadVendida;
    private String Descripcion;

    @ManyToOne()
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    @ManyToMany(mappedBy = "cartItems")
    public Set<Pedido> pedidos;

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getInstrumento() {
        return Instrumento;
    }

    public void setInstrumento(String instrumento) {
        Instrumento = instrumento;
    }

    public String getMarca() {
        return Marca;
    }

    public void setMarca(String marca) {
        Marca = marca;
    }

    public String getModelo() {
        return Modelo;
    }

    public void setModelo(String modelo) {
        Modelo = modelo;
    }

    public String getImagen() {
        return Imagen;
    }

    public void setImagen(String imagen) {
        Imagen = imagen;
    }

    public Double getPrecio() {
        return Precio;
    }

    public void setPrecio(Double precio) {
        Precio = precio;
    }

    public String getCostoEnvio() {
        return CostoEnvio;
    }

    public void setCostoEnvio(String costoEnvio) {
        CostoEnvio = costoEnvio;
    }

    public Integer getCantidadVendida() {
        return CantidadVendida;
    }

    public void setCantidadVendida(Integer cantidadVendida) {
        CantidadVendida = cantidadVendida;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}
