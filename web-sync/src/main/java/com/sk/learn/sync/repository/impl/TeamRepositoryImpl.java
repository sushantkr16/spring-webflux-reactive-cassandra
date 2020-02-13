package com.sk.learn.sync.repository.impl;


import com.sk.learn.sync.domain.Team;
import com.sk.learn.sync.domain.TeamKey;
import org.springframework.data.cassandra.repository.CassandraRepository;

import java.util.List;

public interface TeamRepositoryImpl extends CassandraRepository<Team, TeamKey> {

    List<Team> findAll();

    //Team findOneByKeyTeamName(final String teamName);

    Team insert(Team team);

    Team save(Team team);

}
