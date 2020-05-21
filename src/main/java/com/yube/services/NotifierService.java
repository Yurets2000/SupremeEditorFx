package com.yube.services;

import com.yube.custom.notifier.NotifierBox;
import com.yube.notifications.dto.Notification;
import com.yube.utils.HTMLEditorHelper;
import com.yube.utils.ImageHelper;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.scene.web.HTMLEditor;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class NotifierService {

    private NotificationsService notificationsService;
    private int counter = 0;

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
            Image image = new Image("https://cdn2.iconfinder.com/data/icons/designer-skills/128/sublime-text-3-512.png");
            stage.getIcons().add(ImageHelper.cropCentralPart(image, 0.75, 0.75));
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
        Notification initNotification = notificationsService.getNotificationByPosition(counter);
        box.getBody().getTabs().add(createNotifierTab(initNotification));
        box.getLessButton().setOnAction(e -> {
            if(counter > 0) {
                box.getBody().getTabs().remove(counter--);
            }
        });
        box.getMoreButton().setOnAction(e -> {
            int max = notificationsService.getNotificationsCount();
            if(counter < max - 1) {
                Notification notification = notificationsService.getNotificationByPosition(++counter);
                box.getBody().getTabs().add(createNotifierTab(notification));
            }
        });
    }

    private Tab createNotifierTab(Notification notification) {
        HTMLEditor editor = new HTMLEditor();
        HTMLEditorHelper.hideHTMLEditorToolbars(editor);
        editor.setHtmlText(notification.getHtmlContent());
        Tab notifierTab = new Tab(notification.getDescription());
        notifierTab.setClosable(false);
        notifierTab.setContent(editor);
        return notifierTab;
    }

}
