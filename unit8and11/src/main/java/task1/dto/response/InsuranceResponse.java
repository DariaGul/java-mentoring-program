package task1.dto.response;

import java.util.List;
import lombok.Data;
import task1.dto.response.commoninfo.CarCommonInfoResponse;
import task1.dto.response.commoninfo.ClientCommonInfoResponse;
import task1.dto.response.commoninfo.InsuranceCommonInfoResponse;

@Data
public class InsuranceResponse extends InsuranceCommonInfoResponse {
    private CarCommonInfoResponse car;
    private List<ClientCommonInfoResponse> listClients;
}
