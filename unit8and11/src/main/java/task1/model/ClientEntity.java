package task1.model;

import java.util.List;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedSubgraph;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity
@Table(name = "client")
@Getter
@Setter
@Accessors(chain = true)
@NamedEntityGraph(name = "ClientEntityGraph",
    attributeNodes = {
        @NamedAttributeNode("id"),
        @NamedAttributeNode("firstName"),
        @NamedAttributeNode("lastName"),
        @NamedAttributeNode("middleName"),
        @NamedAttributeNode("city"),
        @NamedAttributeNode(value = "cars", subgraph = "CarSubgraph"),
    },
    subgraphs = {
        @NamedSubgraph(
            name = "CarSubgraph",
            attributeNodes = {
                @NamedAttributeNode("licencePlate"),
                @NamedAttributeNode("region")
            }
        )
    })
public class ClientEntity {

    @Id
    private UUID id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "middle_name")
    private String middleName;
    private String city;

    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
    private List<CarEntity> cars;

    @ManyToMany
    @JoinTable(
        name = "client_insurance",
        joinColumns = @JoinColumn(name = "client_id"),
        inverseJoinColumns = @JoinColumn(name = "insurance_id"))
    private List<InsuranceEntity> listInsurance;

    @PrePersist
    public void prePersist() {
        this.id = UUID.randomUUID();
    }

}
