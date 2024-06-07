package lab4.tp4.Controllers;

import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.preference.PreferenceBackUrlsRequest;
import com.mercadopago.client.preference.PreferenceClient;
import com.mercadopago.client.preference.PreferenceItemRequest;
import com.mercadopago.client.preference.PreferenceRequest;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.preference.Preference;
import lab4.tp4.Entities.Pedido;
import lab4.tp4.Entities.PedidoDTO;
import lab4.tp4.Entities.PreferenceMP;
import lab4.tp4.Services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/Preference")
public class MercadoPagoController {
    @Autowired
    public PedidoService pedidoService;

    public PreferenceMP getPreferenciaIdMercadoPago(PedidoDTO dto){
        try {
            MercadoPagoConfig.setAccessToken("TEST-843019411246492-052214-30dee06210327d6aa0cd1bbcc1744a47-272805327");
            var pedido = getPedidoCreate(dto);
            PreferenceItemRequest itemRequest = PreferenceItemRequest.builder()
                    .id("4132")
                    .title("Pedido TEST")
                    .description("Pedido realizado desde el carrito de compras")
                    .pictureUrl("https://img-global.cpcdn.com/recipes/0709fbb52d87d2d7/1200x630cq70/photo.jpg")
                    .quantity(1)
                    .currencyId("ARG")
                    .unitPrice(new BigDecimal(pedido.getCartTotalAmount()))
                    .build();
            List<PreferenceItemRequest> items = new ArrayList<>();
            items.add(itemRequest);

            PreferenceBackUrlsRequest backURL = PreferenceBackUrlsRequest.builder().success("http://localhost:5173/productos")
                    .pending("http://localhost:5173/productos").failure("http://localhost:5173/").build();

            PreferenceRequest preferenceRequest = PreferenceRequest.builder()
                    .items(items)
                    .backUrls(backURL)
                    .build();
            PreferenceClient client = new PreferenceClient();
            Preference preference = client.create(preferenceRequest);

            PreferenceMP mpPreference = new PreferenceMP();
            mpPreference.setStatusCode(preference.getResponse().getStatusCode());
            mpPreference.setId(preference.getId());
            return mpPreference;

        } catch (MPException | MPApiException e) {
            e.toString();
            return null;
        }
    }
    public Pedido getPedidoCreate(PedidoDTO pedidoDTO){
        try {
            var pedido = pedidoService.Create(pedidoDTO);
            return pedido;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping
    public PreferenceMP crearPreferenciaMercadoPago(@RequestBody PedidoDTO pedidoDTO){
        PreferenceMP preference = getPreferenciaIdMercadoPago(pedidoDTO);
        return preference;
    }
}
