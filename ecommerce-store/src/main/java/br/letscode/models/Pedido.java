package br.letscode.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.springframework.data.annotation.Reference;

import br.letscode.dto.ItemDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="pedido")
public class Pedido {
    

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "idCliente")
    private long idCliente;

    // @OneToMany
    // @JoinColumn(name = "idItem") // Esta coluna está na tabela "Items".
    @Column(name = "listItems")
    private ArrayList<Long> listItems;
    


    public Pedido(long idCliente, ArrayList<Long> listItems) {
        this.idCliente = idCliente;
        // this.listItems = new ArrayList<>();
        // for (Item item : listItems) {
        //     this.listItems.add(item);
        // }
        this.listItems = listItems;
    }

    @Override
    public String toString() {
        return "{"+
            " id='" + getId() + "'" +
            ", idCliente='" + getIdCliente() + "'" +
            ", listItems='" + getListItems() + "'" +
            "}";
    }

}
