package task1.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedSubgraph;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity
@Table(name = "car")
@Getter
@Setter
@Accessors(chain = true)
@NamedEntityGraph(name = "CarEntityGraph",
    attributeNodes = {
        @NamedAttributeNode("id"),
        @NamedAttributeNode("licencePlate"),
        @NamedAttributeNode("region"),
        @NamedAttributeNode(value = "carModel", subgraph = "CarModelSubgraph"),
    },
    subgraphs = {
        @NamedSubgraph(
            name = "CarModelSubgraph",
            attributeNodes = {
                @NamedAttributeNode("model"),
                @NamedAttributeNode("brand")
            }
        )
    })
public class CarEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "licence_plate")
    private String licencePlate;
    private Integer region;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "model_id")
    private CarModelEntity carModel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private ClientEntity client;
}
