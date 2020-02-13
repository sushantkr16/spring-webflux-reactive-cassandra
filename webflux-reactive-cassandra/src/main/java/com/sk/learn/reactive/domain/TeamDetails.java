package com.sk.learn.reactive.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class TeamDetails implements Serializable {

    private List<Team> teams;

    private List<Idea> ideas;

}
