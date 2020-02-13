package com.sk.learn.reactive.handler;

import com.sk.learn.reactive.domain.Team;
import com.sk.learn.reactive.repository.impl.TeamReactiveRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Component
public class TeamHandler {

    @Autowired
    private TeamReactiveRepositoryImpl teamReactiveRepository;


    public Mono<ServerResponse> findTeamByTeamName(ServerRequest request) {
        Flux<Team> selectedTeamFlux = teamReactiveRepository.findAll();
        selectedTeamFlux.log().map(Team::getContactEmail).subscribe(l -> System.out.println("findAll: " + l));
        Team team = selectedTeamFlux.filter(selectedTeam ->
                request.queryParam("teamName").get().equals(
                        selectedTeam.getTeamKey().getTeamName())).blockFirst();
        return ok().contentType(MediaType.APPLICATION_JSON).bodyValue(team);
    }

    public Flux<Team> findTeams() {
        Flux<Team> selectedTeamFlux = teamReactiveRepository.findAll();
        selectedTeamFlux
                .log()
                .subscribe(teams -> System.out.println("find all Teams: " + teams));
        return selectedTeamFlux;

    }
}
