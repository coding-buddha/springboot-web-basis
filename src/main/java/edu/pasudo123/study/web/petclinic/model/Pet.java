package edu.pasudo123.study.web.petclinic.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import edu.pasudo123.study.web.petclinic.deserialize.JacksonCustomPetDeserializer;
import edu.pasudo123.study.web.petclinic.serialize.JacksonCustomPetSerializer;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;

import javax.persistence.*;
import java.security.acl.Owner;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Setter
@Getter
@Entity
@Table(name = "pet")
@JsonSerialize(using = JacksonCustomPetSerializer.class)
@JsonDeserialize(using = JacksonCustomPetDeserializer.class)
public class Pet extends NamedEntity {

    @Column(name = "birth_date")
    private LocalDateTime birthDate;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private PetType petType;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pet", fetch = FetchType.LAZY)
    private Set<Visit> visits;

    @JsonIgnore
    protected Set<Visit> getVisitsInternal() {
        if(this.visits == null) {
            this.visits = new HashSet<>();
        }

        return this.visits;
    }

    protected void setVisitsInternal(final Set<Visit> visits) {
        this.visits = visits;
    }

    public List<Visit> getVisits() {
        List<Visit> sortedVisits = new ArrayList<>(getVisitsInternal()).stream()
                .sorted(Comparator.comparing(Visit::getDate))
                .collect(Collectors.toList());
        return Collections.unmodifiableList(sortedVisits);
    }

    public void addVisit(Visit visit) {
        getVisitsInternal().add(visit);
        visit.setPet(this);
    }
}
