package com.sk.learn.reactive.domain;

import lombok.Data;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.io.Serializable;

@Data
@Table("team_hackathon")
public class TeamHackathon implements Serializable {

    @PrimaryKey
    private TeamHackathonKey teamHackathonKey;

    @Column("team_details")
    private String teamDetails;

    @Column("idea_details")
    private String ideaDetails;
}
