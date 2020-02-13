package com.sk.learn.reactive.repository.impl;

import com.sk.learn.reactive.domain.Idea;
import com.sk.learn.reactive.domain.IdeaKey;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IdeaReactiveRepositoryImpl extends ReactiveCassandraRepository<Idea, IdeaKey> {

    Flux<Idea> findAll();

    Mono<Idea> insert(Idea team);

    Mono<Idea> save(Idea team);

}
