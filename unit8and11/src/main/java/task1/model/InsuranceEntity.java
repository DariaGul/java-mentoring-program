package task1.model;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedSubgraph;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity
@Table(name = "insurance")
@Getter
@Setter
@Accessors(chain = true)
@NamedEntityGraph(name = "InsuranceEntityGraph",
    attributeNodes = {
        @NamedAttributeNode("id"),
        @NamedAttributeNode("number"),
        @NamedAttributeNode("startDate"),
        @NamedAttributeNode("endDate"),
        @NamedAttributeNode(value = "car", subgraph = "CarSubgraph"),
        @NamedAttributeNode(value = "listClients", subgraph = "ClientSubgraph"),
    },
    subgraphs = {
        @NamedSubgraph(
            name = "CarSubgraph",
            attributeNodes = {
                @NamedAttributeNode("licencePlate"),
                @NamedAttributeNode("region")
            }
        ),
        @NamedSubgraph(
            name = "ClientSubgraph",
            attributeNodes = {
                @NamedAttributeNode("id"),
                @NamedAttributeNode("firstName"),
                @NamedAttributeNode("lastName"),
                @NamedAttributeNode("middleName")
            }
        )
    })
public class InsuranceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String number;
    @Column(name = "start_date")
    private LocalDateTime startDate;
    @Column(name = "end_date")
    private LocalDateTime endDate;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private CarEntity car;

    @ManyToMany
    @JoinTable(
        name = "client_insurance",
        joinColumns = @JoinColumn(name = "insurance_id"),
        inverseJoinColumns = @JoinColumn(name = "client_id"))
    private List<ClientEntity> listClients;

}
