package banck.inc.prueba.entity;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


/**
 * The type Product card.
 */
@Entity
@Table(name = "Product_Card")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Product_Card_ID")
    private Long productCardId;

    @ManyToOne
    @JoinColumn(name = "Product_ID", nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "Card_ID", nullable = false)
    private Card card;
}