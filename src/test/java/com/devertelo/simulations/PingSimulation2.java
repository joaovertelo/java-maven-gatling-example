package com.devertelo.simulations;

import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;

import java.time.Duration;

import static io.gatling.javaapi.core.CoreDsl.rampUsersPerSec;
import static io.gatling.javaapi.core.CoreDsl.scenario;
import static io.gatling.javaapi.http.HttpDsl.http;

public class PingSimulation2 extends Simulation {

    HttpProtocolBuilder httpProtocol = http.baseUrl("http://localhost:8080");

    ScenarioBuilder scenario = scenario("Ping 2x")
            .exec(http("get ping 2")
                    .get("/ping"));

    {
        setUp(scenario.injectOpen(
                        rampUsersPerSec(50).to(100).during(Duration.ofSeconds(3))
                )
        ).protocols(httpProtocol);
    }
}
