/*
 * Roman Terekhov
 * terekhov_roman@mail.ru
 * Copyright (c) 2020.
 */

package terekhov.tech.screenshot.livechecker.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.constraints.NotBlank;
import java.io.File;

public class Utils {
    private static final Logger log = LoggerFactory.getLogger(Utils.class);

    public static void createDirectoriesForFilepath(String filePath) {
        File file = new File(filePath);
        file.getParentFile().mkdirs();
    }

    public static boolean checkIfFileExists(String filepath) {
        File file = new File(filepath);
        return file.exists();
    }

    public static String buildFilepathForScreenshot(@NotBlank String baseFilepath, @NotBlank String filename, @NotBlank String format) {
        String filePath = String.format("%s%s%s",
                baseFilepath,
                filename,
                "." + format);
        log.debug("filepath : {}", filePath);
        return filePath;
    }
}
