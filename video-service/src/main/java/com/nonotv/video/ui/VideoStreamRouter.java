package com.nonotv.video.ui;

import com.nonotv.video.application.VideoStreamHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;

@Configuration
@RequiredArgsConstructor
public class VideoStreamRouter {

    private final VideoStreamHandler videoStreamHandler;

    @Bean
    public RouterFunction<ServerResponse> streamingRouterFunction() {
        return RouterFunctions.route(GET("/video/stream"), request -> videoStreamHandler.streaming())
                .andRoute(GET("/video/{segment}"), videoStreamHandler::streamSegment);
    }

}
