package com.sk.learn.reactive.domain;

import lombok.Data;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.io.Serializable;

@Data
@Table("team")
public class Team implements Serializable {

    @PrimaryKey
    private TeamKey teamKey;

    @Column("team_members_name")
    private String teamMembersName;

    @Column("contact_email")
    private String contactEmail;

    @Column("contact_phone")
    private String contactPhone;

}
