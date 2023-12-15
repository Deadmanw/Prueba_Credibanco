package banck.inc.prueba.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * The type Transaction.
 */
@Entity
@Table(name = "Transaction")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Transaction_ID")
    private Long transactionId;

    @Column(name = "Card_ID", nullable = false)
    private Long card;

    @Column(name = "Transaction_Date", nullable = false)
    private LocalDateTime transactionDate;

    @Column(name = "Amount", nullable = false)
    private BigDecimal amount;

    @Column(name = "Status", nullable = false, length = 20)
    private String status;
}

