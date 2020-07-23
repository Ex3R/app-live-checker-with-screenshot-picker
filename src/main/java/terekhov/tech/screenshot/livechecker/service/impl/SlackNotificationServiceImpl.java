/*
 * Roman Terekhov
 * terekhov_roman@mail.ru
 * Copyright (c) 2020.
 */

package terekhov.tech.screenshot.livechecker.service.impl;

import org.apache.http.NameValuePair;
import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import terekhov.tech.screenshot.livechecker.service.NotificationService;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;

@Service
public class SlackNotificationServiceImpl implements NotificationService {
    private static final Logger log = LoggerFactory.getLogger(NotificationService.class);


    private final String token;

    public SlackNotificationServiceImpl(@Value("${spring.slack.bot.token}") String token) {
        this.token = token;
    }

    @Override
    public void sendErrorNotificationSlack(String errorMessage, String channel) {
        Collection<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("token", token));
        params.add(new BasicNameValuePair("channel", channel));
        params.add(new BasicNameValuePair("text", errorMessage));

        Content postResultForm = null;
        try {
            postResultForm = Request.Post("https://slack.com/api/chat.postMessage")
                    .bodyForm(params, Charset.defaultCharset())
                    .execute().returnContent();
        } catch (IOException e) {
            e.printStackTrace();
        }

        postResultForm.toString();
    }
}
