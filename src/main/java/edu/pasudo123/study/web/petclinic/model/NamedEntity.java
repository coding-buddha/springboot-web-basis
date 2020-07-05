package edu.pasudo123.study.web.petclinic.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotEmpty;

@Setter
@Getter
@MappedSuperclass
public class NamedEntity extends BaseEntity {

    @Column(name = "name")
    @NotEmpty
    private String name;
}
