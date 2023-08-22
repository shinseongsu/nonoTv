package com.nonotv.video.application;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class VideoStreamHandler {

    private static final String PATH = "/9ef9b468-cf18-41f7-a162-97e37012404a";

    @Value("${video.outputPath}")
    private String outputPath;

    public Mono<ServerResponse> streaming() {
        Path m3u8Path = Paths.get(outputPath + PATH + "/file.m3u8");
        return ServerResponse.ok()
                .contentType(MediaType.parseMediaType("application/vnd.apple.mpegurl"))
                .body(inputstreamResource(m3u8Path), InputStreamResource.class);
    }

    private Mono<InputStreamResource> inputstreamResource(Path path) {
        try {
            InputStreamResource inputStreamResource = new InputStreamResource(Files.newInputStream(path));
            return Mono.just(inputStreamResource);
        } catch (IOException e) {
            return Mono.error(e);
        }
    }

    public Mono<ServerResponse> streamSegment(ServerRequest request) {
        String segment = request.pathVariable("segment");
        Path segmentPath = Paths.get(outputPath + PATH, segment);

        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(streamResource(segmentPath), Resource.class)
                .onErrorResume(IOException.class,
                        ex -> ServerResponse.notFound().build());
    }

    private Mono<Resource> streamResource(Path path) {
        try {
            return Mono.just(new InputStreamResource(Files.newInputStream(path)));
        } catch (IOException e) {
            return Mono.error(e);
        }
    }

}
