package com.sk.learn.reactive.router;

import com.sk.learn.reactive.handler.TeamHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import java.util.List;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Configuration
public class TeamRouter {

    @Bean
    public RouterFunction<ServerResponse> routeEventWithEventId(TeamHandler teamHandler) {
        return RouterFunctions
                .route(GET("/router/v1/hacks/teams/{teamName}").and(accept(MediaType.APPLICATION_JSON)),
                        teamHandler::findTeamByTeamName);
    }

    @Bean
    public RouterFunction<ServerResponse> routeEvent(TeamHandler teamHandler) {

        return RouterFunctions.route(GET("/router/v1/hacks/teams/"),
                req -> ok().body(teamHandler.findTeams(), List.class));
    }
}
