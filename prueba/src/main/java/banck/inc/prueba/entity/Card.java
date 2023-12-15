package banck.inc.prueba.entity;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * The type Card.
 */
@Entity
@Table(name = "Card")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Card_ID")
    private Long cardId;

    @Column(name = "Product_ID", nullable = false)
    private Long productId;

    @Column(name = "Card_Number", nullable = false, length = 16)
    private String cardNumber;

    @Column(name = "Cardholder_Name", nullable = false, length = 255)
    private String cardholderName;

    @Column(name = "Creation_Date", nullable = false)
    private Date creationDate;

    @Column(name = "Expiry_Date", nullable = false)
    private Date expiryDate;

    @Column(name = "Balance", nullable = false)
    private BigDecimal balance;

    @Column(name = "Status", nullable = false, length = 20)
    private String status;
}
