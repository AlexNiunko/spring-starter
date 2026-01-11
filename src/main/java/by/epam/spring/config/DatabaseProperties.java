package by.epam.spring.config;

import lombok.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

import java.util.List;
import java.util.Map;

@ConstructorBinding
@ConfigurationProperties(prefix = "db")
public record DatabaseProperties(
        String username,
        String password,
        String driver,
        String url,
        String hosts,
        PoolProperties pool,
        List<PoolProperties> pools,
        Map<String, Object> properties
) {

    public record PoolProperties(
            Integer size,
            Integer timeout

    ) {

    }
}
