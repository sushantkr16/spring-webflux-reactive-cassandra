package com.sk.learn.reactive.domain;


import lombok.Data;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.io.Serializable;

@Data
@Table("idea")
public class Idea implements Serializable {

    @PrimaryKey
    private IdeaKey ideaKey;

    @Column("title")
    private String title;

    @Column("idea_description")
    private String ideaDescription;

    @Column("contact_email")
    private String contactEmail;
}
