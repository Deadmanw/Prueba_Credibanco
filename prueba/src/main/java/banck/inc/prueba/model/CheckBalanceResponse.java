package banck.inc.prueba.model;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * The type Check balance response.
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CheckBalanceResponse {
    private BigDecimal balance;
}
