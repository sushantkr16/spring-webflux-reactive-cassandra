package com.sk.learn.sync.resource;

import com.sk.learn.sync.domain.Team;
import com.sk.learn.sync.repository.impl.TeamRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@RestController
@RequestMapping("/v1/hacks/sync/teams")
public class TeamResource {

    @Autowired
    private TeamRepositoryImpl teamRepository;

    @PostMapping
    public ResponseEntity<Team> insertTeamDetails(@RequestBody Team team) {
        Team insertedTeam = teamRepository.insert(team);
        return ResponseEntity.ok().body(insertedTeam);
    }

    @PutMapping
    public ResponseEntity<Team> updateTeamDetails(@RequestBody Team team) {
        teamRepository.save(team);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public void deleteHack(@RequestBody Team team) {
        //teamRepository.delete(team.getTeamKey().getTeamName());
    }

    @GetMapping("/name")
    public ResponseEntity<Team> findTeamByTeamName(@RequestParam String teamName) {
        List<Team> teams = teamRepository.findAll();
        Optional<Team> optTeam = teams.stream().filter(team ->
                teamName.equals(team.getTeamKey().getTeamName())).findFirst();
        if (optTeam.isPresent()) {
            return ResponseEntity.ok().body(optTeam.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Team>> findTeams() {
        List<Team> teamDtoList = teamRepository.findAll();
        System.out.println("find all Teams: " + teamDtoList);
        return ResponseEntity.ok().body(teamDtoList);
    }
}
