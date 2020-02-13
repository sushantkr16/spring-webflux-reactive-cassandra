package com.sk.learn.reactive.resource;

import com.sk.learn.reactive.domain.Idea;
import com.sk.learn.reactive.repository.impl.IdeaReactiveRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/v1/hacks/ideas")
public class ReactiveIdeaResource {

    @Autowired
    private IdeaReactiveRepositoryImpl ideaReactiveRepository;

    @PostMapping
    public Mono<ResponseEntity<Idea>> insertIdeaDetails(@RequestBody Idea idea) {
        Mono<Idea> insertedIdeaMono = ideaReactiveRepository.insert(idea);
        if (null != insertedIdeaMono) {
            Mono.just(ResponseEntity.ok(insertedIdeaMono.block()));
        } else {
            return Mono.justOrEmpty(ResponseEntity.badRequest().build());
        }
        return null;
    }

    @PutMapping
    public Mono<ResponseEntity<Idea>> updateIdeaDetails(@RequestBody Idea idea) {
        Mono<Idea> updatedIdeaMono = ideaReactiveRepository.save(idea);
        if (null != updatedIdeaMono) {
            Mono.just(ResponseEntity.ok(updatedIdeaMono.block()));
        } else {
            return Mono.justOrEmpty(ResponseEntity.badRequest().build());
        }
        return null;
    }

    @DeleteMapping
    public void deleteHack(@RequestBody Idea idea) {
        //ideaReactiveRepository.delete(idea.getTeamName());
    }

    @GetMapping("/name")
    public Mono<ResponseEntity<Idea>> findIdeaByTeamName(@RequestParam String teamName) {
        Flux<Idea> selectedTeamFlux = ideaReactiveRepository.findAll();
        selectedTeamFlux.log().map(Idea::getContactEmail).subscribe(l -> System.out.println("findAll: " + l));
        Idea idea = selectedTeamFlux.filter(selectedTeam ->
                teamName.equals(selectedTeam.getIdeaKey().getTeamName())).blockFirst();
        return Mono.just(ResponseEntity.ok().body(idea));
    }

    @GetMapping
    public Flux<Idea> findIdeas() {
        Flux<Idea> selectedIdeaFlux = ideaReactiveRepository.findAll();
        selectedIdeaFlux
                .log()
                .subscribe(ideas -> System.out.println("find all ideas: " + ideas));
        return selectedIdeaFlux;

    }


}
