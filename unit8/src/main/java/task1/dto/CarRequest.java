package task1.dto;

import lombok.Data;

@Data
public class CarRequest {

    private String licencePlate;
    private Integer region;
    private Long carModelId;
    private Long clientId;

}
