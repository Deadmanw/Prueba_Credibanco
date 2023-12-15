package banck.inc.prueba.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * The type Anulation request.
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AnulationRequest {
    private Long transactionId;
}

