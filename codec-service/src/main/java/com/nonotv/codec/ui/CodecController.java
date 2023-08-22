package com.nonotv.codec.ui;

import com.nonotv.codec.application.HlsConvertService;
import com.nonotv.codec.global.exception.EmptyFileException;
import com.nonotv.codec.infra.hls.HlsConvert;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class CodecController {

    private final HlsConvertService hlsConvertService;

    @PostMapping("/upload")
    public ResponseEntity<String> upload(@RequestParam("file")MultipartFile file) {
        if(file.isEmpty()) {
            throw new EmptyFileException();
        }

        hlsConvertService.convert(file);

        return ResponseEntity.ok("success");
    }

}
