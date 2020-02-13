package com.sk.learn.reactive.repository.impl;

import com.sk.learn.reactive.domain.Hackathon;
import com.sk.learn.reactive.domain.HackathonKey;
import com.sk.learn.reactive.domain.TeamHackathon;
import com.sk.learn.reactive.domain.TeamHackathonKey;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface HackathonReactiveRepositoryImpl extends
        ReactiveCassandraRepository<Hackathon, HackathonKey> {

    Flux<Hackathon> findAll();

    Mono<Hackathon> insert(Hackathon team);

    Mono<Hackathon> save(Hackathon team);

}
