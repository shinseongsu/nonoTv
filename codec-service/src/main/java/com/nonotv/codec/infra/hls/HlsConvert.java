package com.nonotv.codec.infra.hls;

import lombok.RequiredArgsConstructor;
import net.bramp.ffmpeg.FFmpeg;
import net.bramp.ffmpeg.FFmpegExecutor;
import net.bramp.ffmpeg.builder.FFmpegBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class HlsConvert {

    @Value("${video.outputPath}")
    private String outputPath;

    private final FFmpeg ffmpeg;

    public void convert(MultipartFile file) {
        try {
            String key = UUID.randomUUID().toString();
            String filename = file.getName();

            File tempFile = new File(outputPath + "/" + key, "temp.mp4");
            tempFile.mkdirs();
            file.transferTo(tempFile);

            FFmpegBuilder builder = new FFmpegBuilder()
                    .setInput(tempFile.getAbsolutePath())
                    .addOutput(String.format("%s/%s/%s.m3u8", outputPath, key, filename))
                    .setFormat("hls")
                    .addExtraArgs("-hls_time", "10")
                    .addExtraArgs("-hls_list_size", "0")
                    .addExtraArgs("-threads", "4")
                    // .addExtraArgs("-c:v", "h264_nvenc") // NVIDIA GPU 가속
                    .done();

            FFmpegExecutor executor = new FFmpegExecutor(ffmpeg);
            executor.createJob(builder).run();

            tempFile.delete();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
