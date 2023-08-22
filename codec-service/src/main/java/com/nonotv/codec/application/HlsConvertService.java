package com.nonotv.codec.application;

import com.nonotv.codec.infra.hls.HlsConvert;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class HlsConvertService {

    private final HlsConvert hlsConvert;

    public void convert(MultipartFile file) {
        hlsConvert.convert(file);
    }
}
