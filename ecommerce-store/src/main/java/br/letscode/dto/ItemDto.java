package br.letscode.dto;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import br.letscode.models.Item;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class ItemDto {

    private long idPedido;
    private long idProduto;
    private Double precoUnitario;
    private int quantidade;
    private double valorTotal;

    ItemDto() {

    }

    public ItemDto(long idPedido, long idProduto, Double precoUnitario, int quantidade, double valorTotal) {
        this.idPedido = idPedido;
        this.idProduto = idProduto;
        this.precoUnitario = precoUnitario;
        this.quantidade = quantidade;
        this.valorTotal = valorTotal;
    }

}
