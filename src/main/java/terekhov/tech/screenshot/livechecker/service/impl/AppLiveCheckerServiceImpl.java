/*
 * Roman Terekhov
 * terekhov_roman@mail.ru
 * Copyright (c) 2020.
 */

package terekhov.tech.screenshot.livechecker.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import terekhov.tech.screenshot.livechecker.component.ImageCompareComponent;
import terekhov.tech.screenshot.livechecker.component.ScreenshotPicker;
import terekhov.tech.screenshot.livechecker.properties.AppProperties;
import terekhov.tech.screenshot.livechecker.service.AppLiveCheckerService;
import terekhov.tech.screenshot.livechecker.service.NotificationService;
import terekhov.tech.screenshot.livechecker.utils.Channel;
import terekhov.tech.screenshot.livechecker.utils.Utils;

import java.io.File;

import static java.text.MessageFormat.format;

@Service
public class AppLiveCheckerServiceImpl implements AppLiveCheckerService {
    private static final Logger log = LoggerFactory.getLogger(NotificationService.class);
    private final AppProperties appProperties;
    private final File referenceScreenShot;
    private final NotificationService notificationService;
    private final Channel channel;
    private static int xStartCoordinate = 0;
    private static int yStartCoordinate = 0;
    private static int width = 1200;
    private static int height = 160;


    public AppLiveCheckerServiceImpl(AppProperties appProperties, NotificationService notificationService, Channel channel) {
        this.appProperties = appProperties;
        this.referenceScreenShot = new File(Utils.buildFilepathForScreenshot(appProperties.getBaseFilepath(), "screenShotReference", "jpg"));
        this.notificationService = notificationService;
        this.channel = channel;
    }


    @Scheduled(fixedDelay = 60000)
    private void checkAppScheduled() {
        checkApp();
    }

    @Override
    public void checkApp() {
        File currentScreenState = ScreenshotPicker.makeScreenshot(
                appProperties.getBaseFilepath(),
                "screenShot",
                "jpg",
                xStartCoordinate,
                yStartCoordinate,
                width,
                height
        );
        boolean imageEquals = ImageCompareComponent.compareImage(referenceScreenShot, currentScreenState);

        if (!imageEquals) {
            log.error("Problem with down iqter screen, reference screenshot and screenshot are different!");
            notificationService.sendErrorNotificationSlack("Problem with down iqter screen, reference screenshot and current window screenshot are different!", channel.CHANNEL);
        }
    }

    @Override
    public void setup() {
        Utils.createDirectoriesForFilepath(Utils.buildFilepathForScreenshot(appProperties.getBaseFilepath(), "screenShot", "jpg"));
        String referenceScreenShotFilepath = Utils.buildFilepathForScreenshot(appProperties.getBaseFilepath(), "screenShotReference", "jpg");
        if (!Utils.checkIfFileExists(referenceScreenShotFilepath)) {
            log.info(format("missing reference screenshot file, so create", referenceScreenShotFilepath));
            ScreenshotPicker.makeScreenshot(
                    appProperties.getBaseFilepath(),
                    "screenShotReference",
                    "jpg",
                    xStartCoordinate,
                    yStartCoordinate,
                    width,
                    height);
        } else {
            if (appProperties.getRecreateScreenshotOnStartup() != null && appProperties.getRecreateScreenshotOnStartup().equals(true)) {
                log.info("option ");
                ScreenshotPicker.makeScreenshot(
                        appProperties.getBaseFilepath(),
                        "screenShotReference",
                        "jpg",
                        xStartCoordinate,
                        yStartCoordinate,
                        width,
                        height);
            }
        }
    }
}
