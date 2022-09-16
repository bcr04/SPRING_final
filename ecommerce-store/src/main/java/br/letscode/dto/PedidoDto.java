package br.letscode.dto;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import br.letscode.models.Item;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class PedidoDto {

    private long id;
    private long idCliente;
    private ArrayList<ItemDto> listItems;

    PedidoDto() {

    }

    public PedidoDto( long idCliente, ArrayList<ItemDto> listItems) {
        this.idCliente = idCliente;
        // this.listItems = new ArrayList<>();
        // for (Item item : listItems) {
        //     this.listItems.add(item);
        // }
        this.listItems = listItems;
    }

}
