package com.api.elifeconnect.common.response;

import java.util.List;

import org.springframework.http.server.reactive.ServerHttpRequest;

import reactor.core.publisher.Mono;

public class ResponseHelper {

    public static <T> Mono<ApiResponse<T>> wrap(
            Mono<T> dataMono,
            ServerHttpRequest request,
            String message) {

        return dataMono
                .map(data -> ApiResponse.success(request.getPath().toString(), message, data))
                .onErrorResume(ex ->
                        Mono.just(ApiResponse.failure(
                                request.getPath().toString(),
                                400,
                                List.of(ex.getMessage())
                        )));
    }
}
