package lab4.tp4.Entities;

import jakarta.persistence.*;
import lab4.tp4.DTOs.InstrumentoDTO;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(
            name = "pedido_instrumento",
            joinColumns = {@JoinColumn(name = "pedido_id")},
            inverseJoinColumns = {@JoinColumn(name = "instrumento_id")}
    )
    private List<Instrumento> cartItems;

    private Integer cartTotalQuantity;

    private Double cartTotalAmount;

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public List<Instrumento> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<Instrumento> cartItems) {
        this.cartItems = cartItems;
    }

    public Integer getCartTotalQuantity() {
        return cartTotalQuantity;
    }

    public void setCartTotalQuantity(Integer cartTotalQuantity) {
        this.cartTotalQuantity = cartTotalQuantity;
    }

    public Double getCartTotalAmount() {
        return cartTotalAmount;
    }

    public void setCartTotalAmount(Double cartTotalAmount) {
        this.cartTotalAmount = cartTotalAmount;
    }
}
