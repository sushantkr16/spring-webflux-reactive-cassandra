package com.sk.learn.reactive.repository.impl;

import com.sk.learn.reactive.domain.Team;
import com.sk.learn.reactive.domain.TeamKey;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TeamReactiveRepositoryImpl extends ReactiveCassandraRepository<Team, TeamKey> {

    Flux<Team> findAll();

    //Mono<Team> findOneByKeyTeamName(final String teamName);

    Mono<Team> insert(Team team);

    Mono<Team> save(Team team);
}
