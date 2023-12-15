package banck.inc.prueba.model;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * The type Add balance request.
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AddBalanceRequest {

    private String cardId;
    private BigDecimal balance;
}

