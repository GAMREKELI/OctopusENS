package ru.gamrekeli.APIGateway.configurations;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Predicate;

@Service
public class RouterValidator {

    public static final List<String> openEndPoints = List.of(
            "/auth/registration"
    );

    public Predicate<ServerHttpRequest> isSecured =
            request -> openEndPoints.stream()
                    .noneMatch(uri -> request.getURI().getPath().contains(uri));
}
