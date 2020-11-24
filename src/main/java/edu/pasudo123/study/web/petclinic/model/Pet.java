package edu.pasudo123.study.web.petclinic.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "pets")
@JsonSerialize(using = JacksonCustomPetSerializer.class)
@JsonDeserialize(using = JacksonCustomPetDeserializer.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", columnDefinition = "BIGINT", nullable = false)
    protected Long id;

    @Column(name = "name", columnDefinition = "VARCHAR(50)", nullable = false)
    private String name;

    @Column(name = "birth_date", columnDefinition = "DATETIME", nullable = false)
    private LocalDateTime birthDate;

    @Builder
    public Pet(Long id, LocalDateTime birthDate, String name) {
        this.id = id;
        this.birthDate = birthDate;
        this.name = name;
    }
}
