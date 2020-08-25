package edu.pasudo123.study.web.member.model;

import lombok.Getter;
import lombok.ToString;
import org.apache.logging.log4j.util.Strings;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Entity
@Table(name = "container",
        indexes = { @Index(name = "unique_name_idx", columnList = "name")
})
@ToString
public class Container {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", columnDefinition = "VARCHAR(50)", unique = true)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS", columnDefinition = "ENUM('ACTIVE', 'PENDING', 'DELETE')", nullable = false)
    private State state;

    private enum State {
        ACTIVE, PENDING, DELETE
    }

    public Container() {
        this.name = UUID.randomUUID().toString()
                .replaceAll("-", Strings.EMPTY)
                .substring(0, 9);
        this.state = State.ACTIVE;
    }

    public void stateToPending() {
        this.state = State.PENDING;
    }

    public void stateToDelete() {
        this.state = State.DELETE;
    }
}
