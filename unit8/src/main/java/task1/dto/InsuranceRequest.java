package task1.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import lombok.Data;

@Data
public class InsuranceRequest {

    private String number;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Long carId;

    private List<UUID> listClients;
}
