package com.sk.learn.reactive.resource;

import com.sk.learn.reactive.domain.Team;
import com.sk.learn.reactive.domain.TeamKey;
import com.sk.learn.reactive.repository.impl.TeamReactiveRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/v1/hacks/teams")
public class ReactiveTeamResource {

    @Autowired
    private TeamReactiveRepositoryImpl teamReactiveRepository;

    @PostMapping
    public Mono<ResponseEntity<Team>> insertTeamDetails(@RequestBody Team team) {
        Mono<Team> insertedTeamMono = teamReactiveRepository.insert(team);
        if (null != insertedTeamMono) {
            Mono.just(ResponseEntity.ok(insertedTeamMono.block()));
        } else {
            return Mono.justOrEmpty(ResponseEntity.badRequest().build());
        }
        return null;
    }

    @PutMapping
    public Mono<ResponseEntity<Team>> updateTeamDetails(@RequestBody Team team) {
        Mono<Team> updatedTeamMono = teamReactiveRepository.save(team);
        if (null != updatedTeamMono) {
            Mono.just(ResponseEntity.ok(updatedTeamMono.block()));
        } else {
            return Mono.justOrEmpty(ResponseEntity.badRequest().build());
        }
        return null;
    }

    @DeleteMapping
    public void deleteHack(@RequestBody Team teamDto) {
        //teamReactiveRepository.delete(teamDto.getTeamName());
    }

    @GetMapping("/name")
    public Mono<ResponseEntity<Team>> findTeamByTeamName(@RequestParam String teamName) {
        Flux<Team> selectedTeamFlux = teamReactiveRepository.findAll();
        selectedTeamFlux.log().map(Team::getContactEmail).subscribe(l -> System.out.println("findAll: " + l));
        Team team = selectedTeamFlux.filter(selectedTeam -> teamName.equals(selectedTeam.getTeamKey().getTeamName())).blockFirst();
        return Mono.just(ResponseEntity.ok().body(team));
    }

    @GetMapping
    public Flux<Team> findTeams() {
        Flux<Team> selectedTeamFlux = teamReactiveRepository.findAll();
        selectedTeamFlux
                .log()
                .subscribe(teams -> System.out.println("find all Teams: " + teams));
        return selectedTeamFlux;

    }
}


