package task1.dto.request;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CarModelRequest {

    private String model;
    private Long brandId;
}
