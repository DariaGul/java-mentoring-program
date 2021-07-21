package task1.dto;

import java.util.UUID;
import lombok.Data;

@Data
public class ClientRequest {

    private UUID uuid;
    private String firstName;
    private String lastName;
    private String middleName;
    private String city;
}
