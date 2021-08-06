package task1.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ClientRequest {

    private String firstName;
    private String lastName;
    private String middleName;
    private String city;
}
