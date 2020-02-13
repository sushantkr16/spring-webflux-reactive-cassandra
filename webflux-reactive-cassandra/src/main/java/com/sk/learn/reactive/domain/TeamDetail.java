package com.sk.learn.reactive.domain;

import lombok.Data;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.Serializable;
import java.util.List;

@Data
public class TeamDetail implements Serializable {

    private Team team;

    private Idea idea;

}
