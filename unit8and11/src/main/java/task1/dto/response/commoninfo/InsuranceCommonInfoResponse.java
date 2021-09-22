package task1.dto.response.commoninfo;

import java.time.LocalDateTime;
import javax.persistence.Column;
import lombok.Data;

@Data
public class InsuranceCommonInfoResponse {
    private Long id;
    private String number;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
