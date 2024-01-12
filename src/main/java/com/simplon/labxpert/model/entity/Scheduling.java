package com.simplon.labxpert.model.entity;

import com.simplon.labxpert.model.enums.Priority;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "schedulings")
public class Scheduling {
    @Id
    @SequenceGenerator(
            name = "scheduling_id_sequence",
            sequenceName = "scheduling_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "scheduling_id_sequence"
    )
    private long schedulingID;
    @Column(name = "startDateAndTime")
    private LocalDate startDateAndTime;
    @Column(name = "endDateAndTime")
    private LocalDate endDateAndTime;
    @Enumerated(EnumType.STRING)
    private Priority priority;
    @Column(name = "notes")
    private String notes;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "analysis_id")
    private Analysis analysis;
}
