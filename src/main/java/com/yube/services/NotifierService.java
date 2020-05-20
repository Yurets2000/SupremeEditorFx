package com.yube.services;

import com.yube.custom.notifier.NotifierBox;
import com.yube.notifications.dto.Notification;
import com.yube.utils.HTMLEditorHelper;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.web.HTMLEditor;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotifierService {

    protected NotificationsService notificationsService;

    @Autowired
    public NotifierService(NotificationsService notificationsService) {
        this.notificationsService = notificationsService;
    }

    public Stage createNotifierStage() {
        Stage stage;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/notifier.fxml"));
            Parent root = loader.load();
            stage = new Stage();
            stage.setTitle("SupremeEditorFx Notifier");
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getClassLoader().getResource("styles/main.css").toExternalForm());
            scene.getStylesheets().add(getClass().getClassLoader().getResource("styles/themes/darkula.css").toExternalForm());
            stage.setScene(scene);
            NotifierBox box = (NotifierBox) scene.lookup("#notifier");
            initNotifierBox(box);
        }
        catch (IOException e) {
            throw new RuntimeException("Can't create Notifier Stage", e);
        }
        return stage;
    }

    private void initNotifierBox(NotifierBox box) {
        box.getBody().getTabs().clear();
        box.getBody().getTabs().addAll(createNotifierTabs(box.getNotificationsCount().get()));
        box.getNotificationsCount().addListener(((observable, oldValue, newValue) -> {
            List<Tab> tabs = createNotifierTabs(newValue.intValue());
            box.getBody().getTabs().clear();
            box.getBody().getTabs().addAll(tabs);
        }));
    }

    private List<Tab> createNotifierTabs(int n) {
        List<Notification> notifications = notificationsService.getLastNotifications(n);
        return notifications.stream().map(this::createNotifierTab).collect(Collectors.toList());
    }

    private Tab createNotifierTab(Notification notification) {
        HTMLEditor editor = new HTMLEditor();
        HTMLEditorHelper.hideHTMLEditorToolbars(editor);
        editor.setHtmlText(notification.getHtmlContent());
        Tab notifierTab = new Tab(notification.getDescription());
        notifierTab.setContent(editor);
        return notifierTab;
    }

}
