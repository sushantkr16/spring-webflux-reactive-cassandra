package com.sk.learn.reactive.resource;

import com.sk.learn.reactive.domain.Hackathon;
import com.sk.learn.reactive.domain.TeamHackathon;
import com.sk.learn.reactive.repository.impl.HackathonReactiveRepositoryImpl;
import com.sk.learn.reactive.repository.impl.TeamHackathonReactiveRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/v1/hacks/hackathon")
public class ReactiveHackathonResource {

    @Autowired
    private HackathonReactiveRepositoryImpl hackathonReactiveRepository;

    @PostMapping
    public Mono<ResponseEntity<Hackathon>> insertHackathonDetails(@RequestBody Hackathon hackathon) {
        Mono<Hackathon> hackathonMono = hackathonReactiveRepository.insert(hackathon);
        if (null != hackathonMono) {
            Mono.just(ResponseEntity.ok(hackathonMono.block()));
        } else {
            return Mono.justOrEmpty(ResponseEntity.badRequest().build());
        }
        return null;
    }

    @PutMapping
    public Mono<ResponseEntity<Hackathon>> updateTeamHackathonDetails(@RequestBody Hackathon hackathon) {
        Mono<Hackathon> hackathonMono = hackathonReactiveRepository.save(hackathon);
        if (null != hackathonMono) {
            Mono.just(ResponseEntity.ok(hackathonMono.block()));
        } else {
            return Mono.justOrEmpty(ResponseEntity.badRequest().build());
        }
        return null;
    }

    @DeleteMapping
    public void deleteTeamHackathon(@RequestBody Hackathon teamHackathon) {
        //ideaReactiveRepository.delete(idea.getTeamName());
    }

    @GetMapping("/name")
    public Mono<ResponseEntity<Hackathon>> findHackathonByTeamName(@RequestParam String teamName) {
        Flux<Hackathon> selectedTeamFlux = hackathonReactiveRepository.findAll();
        selectedTeamFlux.log().map(Hackathon::getFirstContactEmail).subscribe(l -> System.out.println("findAll: " + l));
        Hackathon hackathon = selectedTeamFlux.filter(selectedHackathon ->
                teamName.equals(selectedHackathon.getHackathonKey().getHackathonName())).blockFirst();
        return Mono.just(ResponseEntity.ok().body(hackathon));
    }

    @GetMapping
    public Flux<Hackathon> findTeamHackathons() {
        Flux<Hackathon> selectedHackathonFlux = hackathonReactiveRepository.findAll();
        selectedHackathonFlux
                .log()
                .subscribe(hackathons -> System.out.println("find all Hackathons: " + hackathons));
        return selectedHackathonFlux;

    }
}
