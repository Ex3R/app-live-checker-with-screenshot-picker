/*
 * Roman Terekhov
 * terekhov_roman@mail.ru
 * Copyright (c) 2020.
 */

package terekhov.tech.screenshot.livechecker.service;

public interface NotificationService {

    void sendErrorNotificationSlack(String errorMessage, String channel);
}
