package task1.dto.request;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class InsuranceRequest {

    private String number;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Long carId;
    private List<UUID> listClients;
}
