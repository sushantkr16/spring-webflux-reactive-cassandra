package com.sk.learn.reactive.domain;

import lombok.Data;
import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

import java.io.Serializable;

@PrimaryKeyClass
@Data
public class TeamHackathonKey implements Serializable {

    @PrimaryKeyColumn(name = "team_name", ordinal = 1, type = PrimaryKeyType.PARTITIONED,
            ordering = Ordering.DESCENDING)
    private String teamName;

}
