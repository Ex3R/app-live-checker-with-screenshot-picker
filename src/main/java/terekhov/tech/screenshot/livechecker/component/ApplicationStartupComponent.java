/*
 * Roman Terekhov
 * terekhov_roman@mail.ru
 * Copyright (c) 2020.
 */

package terekhov.tech.screenshot.livechecker.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import terekhov.tech.screenshot.livechecker.service.AppLiveCheckerService;

@Component
public class ApplicationStartupComponent implements ApplicationListener<ApplicationReadyEvent> {
    private Logger logger = LoggerFactory.getLogger(ApplicationStartupComponent.class);
    private final AppLiveCheckerService appLiveCheckerService;

    public ApplicationStartupComponent(AppLiveCheckerService appLiveCheckerService) {
        this.appLiveCheckerService = appLiveCheckerService;
    }


    @Override
    public void onApplicationEvent(final ApplicationReadyEvent event) {
        appLiveCheckerService.setup();
    }
}