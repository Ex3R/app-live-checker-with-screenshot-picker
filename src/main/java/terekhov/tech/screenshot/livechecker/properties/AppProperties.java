/*
 * Roman Terekhov
 * terekhov_roman@mail.ru
 * Copyright (c) 2020.
 */

package terekhov.tech.screenshot.livechecker.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@ConfigurationProperties(prefix = "app")
@Configuration
public class AppProperties {
    private String baseFilepath;
    private Boolean recreateScreenshotOnStartup;
}
