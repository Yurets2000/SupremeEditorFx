package com.yube.services;

import com.yube.notifications.dto.Notification;
import com.yube.rest.JsonAdapter;
import com.yube.rest.RestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationsService {

    private final static String REQUEST_PREFIX = "http://localhost:8080/SupremeEditorServer_war_exploded/rest";
    private final static String CONTROLLER_PREFIX = REQUEST_PREFIX + "/notifications";
    private final static String GET_ALL_NOTIFICATIONS = CONTROLLER_PREFIX + "/all";
    private final static String GET_LAST_NOTIFICATIONS = CONTROLLER_PREFIX + "/last";
    private final static String GET_NOTIFICATIONS_COUNT = CONTROLLER_PREFIX + "/count";
    private final static String GET_NOTIFICATION_BY_POSITION = CONTROLLER_PREFIX;
    private RestClient restClient;
    private JsonAdapter jsonAdapter;

    @Autowired
    public NotificationsService(RestClient restClient, JsonAdapter jsonAdapter) {
        this.restClient = restClient;
        this.jsonAdapter = jsonAdapter;
    }

    public List<Notification> getAllNotifications() {
        String response = restClient.getRequest(GET_ALL_NOTIFICATIONS, null);
        return jsonAdapter.getList(response, Notification.class);
    }

    public List<Notification> getLastNotifications(int n) {
        String response = restClient.getRequest(GET_LAST_NOTIFICATIONS + "/" + n, null);
        return jsonAdapter.getList(response, Notification.class);
    }

    public int getNotificationsCount() {
        String response = restClient.getRequest(GET_NOTIFICATIONS_COUNT, null);
        return jsonAdapter.getObject(response, Integer.class);
    }

    public Notification getNotificationByPosition(int n) {
        String response = restClient.getRequest(GET_NOTIFICATION_BY_POSITION + "/" + n, null);
        return jsonAdapter.getObject(response, Notification.class);
    }
}
