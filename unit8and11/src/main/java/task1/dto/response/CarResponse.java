package task1.dto.response;

import lombok.Data;
import task1.dto.response.commoninfo.CarCommonInfoResponse;
import task1.dto.response.commoninfo.ClientCommonInfoResponse;

@Data
public class CarResponse extends CarCommonInfoResponse {
    private CarModelResponse carModel;
    private ClientCommonInfoResponse client;
}
