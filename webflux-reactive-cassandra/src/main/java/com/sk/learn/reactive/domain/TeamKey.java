package com.sk.learn.reactive.domain;

import lombok.Data;
import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@PrimaryKeyClass
@Data
public class TeamKey implements Serializable {

    @PrimaryKeyColumn(name = "team_name", ordinal = 1, type = PrimaryKeyType.PARTITIONED,
            ordering = Ordering.DESCENDING)
    private String teamName;

    @PrimaryKeyColumn(name = "registration_date", ordinal = 2, type = PrimaryKeyType.CLUSTERED,
            ordering = Ordering.DESCENDING)
    private LocalDate registrationDate;
}
