package com.sk.learn.reactive.resource;

import com.sk.learn.reactive.domain.Idea;
import com.sk.learn.reactive.domain.TeamDetail;
import com.sk.learn.reactive.domain.Team;
import com.sk.learn.reactive.domain.TeamDetails;
import com.sk.learn.reactive.repository.impl.IdeaReactiveRepositoryImpl;
import com.sk.learn.reactive.repository.impl.TeamReactiveRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.util.List;

@RestController
@RequestMapping("/v1/hacks/teams/details")
public class ReactiveTeamHackathonDetailsResource {

    @Autowired
    private TeamReactiveRepositoryImpl teamReactiveRepository;

    @Autowired
    private IdeaReactiveRepositoryImpl ideaReactiveRepository;

    @GetMapping
    public Flux<Tuple2<Team,Idea>> findTeams() {
        TeamDetail teamDetail = new TeamDetail();
        Flux<Team> selectedTeamFlux = teamReactiveRepository.findAll();
        selectedTeamFlux
                .log()
                .subscribe(teams -> System.out.println("find all Teams: " + teams));

        Flux<Idea> selectedIdeaFlux = ideaReactiveRepository.findAll();
        selectedIdeaFlux
                .log()
                .subscribe(ideas -> System.out.println("find all ideas: " + ideas));

        Flux<Tuple2<Team,Idea>> zippedFlux = Flux.zip(selectedTeamFlux, selectedIdeaFlux);

        return zippedFlux;
    }


    @GetMapping("/object")
    public TeamDetails getTeamDetails() {
        TeamDetail teamDetail = new TeamDetail();
        TeamDetails teamDetails = new TeamDetails();
        Flux<Team> selectedTeamFlux = teamReactiveRepository.findAll();
        selectedTeamFlux
                .log()
                .subscribe(teams -> System.out.println("find all Teams: " + teams));

        Flux<Idea> selectedIdeaFlux = ideaReactiveRepository.findAll();
        selectedIdeaFlux
                .log()
                .subscribe(ideas -> System.out.println("find all ideas: " + ideas));


        selectedIdeaFlux.collectList().map(ideas -> setIdeas(teamDetails, ideas));
        selectedTeamFlux.collectList().map(teams -> setTeams(teamDetails, teams));

        return teamDetails;
    }

    private TeamDetails setIdeas(TeamDetails teamDetails, List<Idea> ideaList) {
        teamDetails.setIdeas(ideaList);
        return teamDetails;
    }

    private TeamDetails setTeams(TeamDetails teamDetails, List<Team> teamList) {
        teamDetails.setTeams(teamList);
        return teamDetails;
    }
}
