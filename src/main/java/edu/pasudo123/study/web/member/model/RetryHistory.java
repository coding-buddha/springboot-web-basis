package edu.pasudo123.study.web.member.model;

import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "retry_history", indexes = {
        @Index(name = "failed_container_name_idx", columnList = "failed_container_name")
})
@ToString
@EntityListeners(AuditingEntityListener.class)
public class RetryHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "failed_container_name", columnDefinition = "VARCHAR(50)", nullable = false, unique = true)
    private String failedContainerName;

    @CreatedDate
    @Column(name = "createDate", columnDefinition = "DATETIME", nullable = false)
    private LocalDateTime createDate;

    public RetryHistory(final String failedContainerName) {
        this.failedContainerName = failedContainerName;
    }
}
