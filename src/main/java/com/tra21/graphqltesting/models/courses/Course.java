package com.tra21.graphqltesting.models.courses;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
//@Table(uniqueConstraints = { @UniqueConstraint(name = "UniqueIdAndName", columnNames = {"id", "name"}) })
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Course implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
}
