package com.sk.learn.reactive.resource;

import com.sk.learn.reactive.domain.TeamHackathon;
import com.sk.learn.reactive.repository.impl.TeamHackathonReactiveRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/v1/hacks/teamshackathon")
public class ReactiveTeamHackathonResource {

    @Autowired
    private TeamHackathonReactiveRepositoryImpl teamHackathonReactiveRepository;

    @PostMapping
    public Mono<ResponseEntity<TeamHackathon>> insertTeamHackathonDetails(@RequestBody TeamHackathon teamHackathon) {
        Mono<TeamHackathon> teamHackathonMono = teamHackathonReactiveRepository.insert(teamHackathon);
        if (null != teamHackathonMono) {
            Mono.just(ResponseEntity.ok(teamHackathonMono.block()));
        } else {
            return Mono.justOrEmpty(ResponseEntity.badRequest().build());
        }
        return null;
    }

    @PutMapping
    public Mono<ResponseEntity<TeamHackathon>> updateTeamHackathonDetails(@RequestBody TeamHackathon teamHackathon) {
        Mono<TeamHackathon> teamHackathonMono = teamHackathonReactiveRepository.save(teamHackathon);
        if (null != teamHackathonMono) {
            Mono.just(ResponseEntity.ok(teamHackathonMono.block()));
        } else {
            return Mono.justOrEmpty(ResponseEntity.badRequest().build());
        }
        return null;
    }

    @DeleteMapping
    public void deleteTeamHackathon(@RequestBody TeamHackathon teamHackathon) {
        //ideaReactiveRepository.delete(idea.getTeamName());
    }

    @GetMapping("/name")
    public Mono<ResponseEntity<TeamHackathon>> findTeamHackathonByTeamName(@RequestParam String teamName) {
        Flux<TeamHackathon> selectedTeamFlux = teamHackathonReactiveRepository.findAll();
        selectedTeamFlux.log().map(TeamHackathon::getTeamHackathonKey).subscribe(l -> System.out.println("findAll: " + l));
        TeamHackathon idea = selectedTeamFlux.filter(selectedTeam ->
                teamName.equals(selectedTeam.getTeamHackathonKey().getTeamName())).blockFirst();
        return Mono.just(ResponseEntity.ok().body(idea));
    }

    @GetMapping
    public Flux<TeamHackathon> findTeamHackathons() {
        Flux<TeamHackathon> selectedIdeaFlux = teamHackathonReactiveRepository.findAll();
        selectedIdeaFlux
                .log()
                .subscribe(ideas -> System.out.println("find all TeamHackathons: " + ideas));
        return selectedIdeaFlux;

    }
}
