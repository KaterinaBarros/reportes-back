package lab4.tp4.Entities;

import jakarta.persistence.*;
import lab4.tp4.DTOs.InstrumentoDTO;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
public class PedidoDTO {

    private List<InstrumentoDTO> cartItems;

    private Integer cartTotalQuantity;

    private Double cartTotalAmount;

    public List<InstrumentoDTO> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<InstrumentoDTO> cartItems) {
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
