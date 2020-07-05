package edu.pasudo123.study.web.petclinic.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import edu.pasudo123.study.web.petclinic.deserialize.JacksonCustomPetDeserializer;
import edu.pasudo123.study.web.petclinic.serialize.JacksonCustomPetSerializer;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

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

}
