package banck.inc.prueba.entity;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * The type Card transaction.
 */
@Entity
@Table(name = "Card_Transaction")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CardTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Card_Transaction_ID")
    private Long cardTransactionId;

    @ManyToOne
    @JoinColumn(name = "Card_ID", nullable = false)
    private Card card;

    @ManyToOne
    @JoinColumn(name = "Transaction_ID", nullable = false)
    private Transaction transaction;
}