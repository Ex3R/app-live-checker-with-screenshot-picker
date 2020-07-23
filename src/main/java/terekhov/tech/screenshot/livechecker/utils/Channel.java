/*
 * Roman Terekhov
 * terekhov_roman@mail.ru
 * Copyright (c) 2020.
 */

package terekhov.tech.screenshot.livechecker.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Channel {

    public final String CHANNEL;

    public Channel(
            @Value("${spring.slack.channel.channel}") String CHANNEL
    ) {
        this.CHANNEL = CHANNEL;
    }
}
