package banck.inc.prueba.model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * The type Generate card number response.
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class GenerateCardNumberResponse {
    private String cardId;
    private String message;
}
