/*
 * Roman Terekhov
 * terekhov_roman@mail.ru
 * Copyright (c) 2020.
 */

package terekhov.tech.screenshot.livechecker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ScreenshotLiveCheckerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScreenshotLiveCheckerApplication.class, args);
    }

}
