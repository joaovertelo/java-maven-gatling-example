package com.devertelo.simulations;

import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;

import java.time.Duration;

import static io.gatling.javaapi.core.CoreDsl.rampUsersPerSec;
import static io.gatling.javaapi.core.CoreDsl.scenario;
import static io.gatling.javaapi.http.HttpDsl.http;

public class PingSimulation extends Simulation {

    HttpProtocolBuilder httpProtocol = http.baseUrl("http://localhost:8080");

    ScenarioBuilder scenario = scenario("Ping")
            .exec(http("get ping")
                    .get("/ping"));

    {
        setUp(scenario.injectOpen(
                        rampUsersPerSec(1).to(50).during(Duration.ofSeconds(3))
                )
        ).protocols(httpProtocol);
    }
}
