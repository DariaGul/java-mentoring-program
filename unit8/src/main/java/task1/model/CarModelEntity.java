package task1.model;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity
@Table(name = "car_model")
@Getter
@Setter
@Accessors(chain = true)
public class CarModelEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String model;

    @OneToMany(mappedBy = "carModel", fetch = FetchType.LAZY)
    private List<CarEntity> cars;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private BrandEntity brand;

}
