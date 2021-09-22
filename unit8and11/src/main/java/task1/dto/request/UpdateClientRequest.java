package task1.dto.request;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UpdateClientRequest {

    private String firstName;
    private String lastName;
    private String middleName;
    private String city;

}
