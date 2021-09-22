package task1.dto.response.commoninfo;

import java.util.UUID;
import lombok.Data;

@Data
public class ClientCommonInfoResponse {

    private UUID id;
    private String firstName;
    private String lastName;
    private String middleName;
    private String city;

}
