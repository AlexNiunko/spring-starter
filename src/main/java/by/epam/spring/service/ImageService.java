package by.epam.spring.service;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import static java.nio.file.StandardOpenOption.CREATE;
import static java.nio.file.StandardOpenOption.TRUNCATE_EXISTING;

@Service
@RequiredArgsConstructor
public class ImageService {

    @Value("${app.image.bucket:D:\\Материалы для подготовки\\training\\spring-starter\\images}")
    private final String bucket;

    @SneakyThrows
    public void upload(String image, InputStream content) {
        Path fullImagePath = Path.of(bucket);
        Files.createDirectories(fullImagePath);              // создаём папку, если нет

        Path fullPath = fullImagePath.resolve(image);
        try (content) {
            Files.write(fullPath, content.readAllBytes(), CREATE, TRUNCATE_EXISTING);

        }
    }


}
