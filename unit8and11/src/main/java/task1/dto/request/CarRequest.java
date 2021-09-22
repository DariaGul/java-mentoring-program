package task1.dto.request;

import java.util.UUID;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CarRequest {

    private String licencePlate;
    private Integer region;
    private Long carModelId;
    private UUID clientId;
}
