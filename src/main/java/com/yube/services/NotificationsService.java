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
    private final static String GET_ALL_NOTIFICATIONS = REQUEST_PREFIX + "/notifications/all";
    private final static String GET_LAST_NOTIFICATIONS = REQUEST_PREFIX + "/notifications/last";
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
}
