package com.sk.learn.reactive.repository.impl;


import com.sk.learn.reactive.domain.TeamHackathon;
import com.sk.learn.reactive.domain.TeamHackathonKey;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TeamHackathonReactiveRepositoryImpl extends
        ReactiveCassandraRepository<TeamHackathon, TeamHackathonKey> {

    Flux<TeamHackathon> findAll();

    Mono<TeamHackathon> insert(TeamHackathon team);

    Mono<TeamHackathon> save(TeamHackathon team);

}
