package br.letscode.models;

import javax.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="item")
@NoArgsConstructor
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idItem;        
    
    @Column(name = "idPedido")
    private long idPedido;

    @Column(name = "idProduto")
    private long idProduto;

    @Column(name = "precoUnitario")
    private Double precoUnitario;

    @Column(name = "quantidade")
    private int quantidade;

    @Column(name = "valorTotal")
    private double valorTotal;

    public Item(long idItem, long idPedido, long idProduto, Double precoUnitario, int quantidade, double valorTotal) {
        this.idItem = idItem;
        this.idPedido = idPedido;
        this.idProduto = idProduto;
        this.precoUnitario = precoUnitario;
        this.quantidade = quantidade;
        this.valorTotal = this.getPrecoUnitario() * this.getQuantidade();
    }

    public Item( long idPedido, long idProduto, Double precoUnitario, int quantidade, double valorTotal) {

        this.idPedido = idPedido;
        this.idProduto = idProduto;
        this.precoUnitario = precoUnitario;
        this.quantidade = quantidade;
        this.valorTotal = this.getPrecoUnitario() * this.getQuantidade();
    }
    

    @Override
    public String toString() {
        return "{" +
            ", idPedido='" + getIdPedido() + "'" +
            ", idProduto='" + getIdProduto() + "'" +
            ", precoUnitario='" + getPrecoUnitario() + "'" +
            ", quantidade='" + getQuantidade() + "'" +
            ", valorTotal='" + getValorTotal() + "'" +
            "}";
    }

}
