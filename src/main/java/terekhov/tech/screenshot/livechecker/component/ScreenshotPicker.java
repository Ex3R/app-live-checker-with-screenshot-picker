/*
 * Roman Terekhov
 * terekhov_roman@mail.ru
 * Copyright (c) 2020.
 */

package terekhov.tech.screenshot.livechecker.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import terekhov.tech.screenshot.livechecker.utils.Utils;

import javax.imageio.ImageIO;
import javax.validation.constraints.NotBlank;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static java.text.MessageFormat.format;

@Component
public class ScreenshotPicker {
    private static final Logger log = LoggerFactory.getLogger(ScreenshotPicker.class);

    /*for fixing https://stackoverflow.com/questions/21131855/could-not-initialize-class-sun-awt-x11graphicsenvironment-on-solaris */
    static {
        System.setProperty("java.awt.headless", "false");
    }

    public static File makeScreenshot(@NotBlank String baseFilepath,
                                      @NotBlank String filename,
                                      @NotBlank String format,
                                      int xStartCoordinate,
                                      int yStartCoordinate,
                                      int width,
                                      int height) {
        try {
            /* todo also can add options for choosing display, not default only*/
            Robot robot = new Robot();
            String filepath = Utils.buildFilepathForScreenshot(baseFilepath, filename, format);

            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            if (width > screenSize.width || height > screenSize.height) {
                throw new RuntimeException("width or height are higher than screen size dimension");
            }

            Rectangle captureRect = new Rectangle(xStartCoordinate, yStartCoordinate, width, height);
            BufferedImage screenFullImage = robot.createScreenCapture(captureRect);
            File file = new File(filepath);
            ImageIO.write(screenFullImage, format, file);
            log.info("new screenshot saved");
            return file;
        } catch (AWTException | IOException ex) {
            log.error(ex.getMessage());
            throw new RuntimeException(format("error on making screenshot: {0}", ex.getLocalizedMessage()));
        }
    }
}
