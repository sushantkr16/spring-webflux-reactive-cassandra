package com.sk.learn.reactive.domain;

import lombok.Data;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.io.Serializable;


@Data
@Table("hackathon")
public class Hackathon implements Serializable {

    @PrimaryKey
    private HackathonKey hackathonKey;

    @Column("first_contact_email")
    private String firstContactEmail;

    @Column("second_contact_email")
    private String secondContactEmail;

    @Column("first_mentor_name")
    private String firstMentorName;

    @Column("second_mentor_name")
    private String secondMentorName;
}
