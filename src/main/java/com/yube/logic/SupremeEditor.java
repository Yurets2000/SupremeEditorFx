package com.yube.logic;

import com.yube.configuration.factory.XmlConfigurationFactory;
import com.yube.configuration.hierarchy.XmlConfiguration;
import com.yube.configuration.models.actions.Action;
import com.yube.configuration.models.sessions.Session;
import com.yube.configuration.models.sessions.SessionFile;
import com.yube.configuration.processors.actions.ActionsProcessor;
import com.yube.configuration.processors.sessions.SessionFileProcessor;
import com.yube.configuration.processors.sessions.SessionProcessor;
import com.yube.custom.SupremeMenuItem;
import com.yube.custom.SupremeTab;
import com.yube.services.SupremeMenuItemService;
import com.yube.services.SupremeSceneService;
import com.yube.services.SupremeTabService;
import com.yube.utils.ImageHelper;
import javafx.application.Application;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@ComponentScan("com.yube.services")
public class SupremeEditor extends Application {

    @Autowired
    private SupremeTabService supremeTabService;
    @Autowired
    private SupremeSceneService supremeSceneService;
    @Autowired
    private SupremeMenuItemService supremeMenuItemService;

    private ConfigurableApplicationContext springContext;
    private Parent root;

    @Override
    public void init() throws Exception {
        springContext = SpringApplication.run(SupremeEditor.class);
        springContext.getAutowireCapableBeanFactory()
                .autowireBeanProperties(this, AutowireCapableBeanFactory.AUTOWIRE_BY_TYPE, true);
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/main.fxml"));
        loader.setControllerFactory(springContext::getBean);
        root = loader.load();
    }

    @Override
    public void start(Stage stage) throws Exception {
        StageContainer stageContainer = new StageContainer(stage, "main");
        StageContainersRegistry.getInstance().registerStageContainer(stageContainer);

        stage.setTitle("Supreme Editor");
        Image image = new Image("https://cdn2.iconfinder.com/data/icons/designer-skills/128/sublime-text-3-512.png");
        stage.getIcons().add(ImageHelper.cropCentralPart(image, 0.75, 0.75));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getClassLoader().getResource("styles/main.css").toExternalForm());
        scene.getStylesheets().add(getClass().getClassLoader().getResource("styles/darkula.css").toExternalForm());
        stage.setScene(scene);

        XmlConfiguration actionsConfiguration = XmlConfigurationFactory.getConfiguration("actions");
        ActionsProcessor actionsProcessor = new ActionsProcessor(actionsConfiguration.getDocument());
        List<Action> definedActions = actionsProcessor.getDefinedActions("main");
        Map<String, BooleanProperty> actionsMap = stageContainer.getActionsMap();
        definedActions.forEach(a -> actionsMap.put(a.getName(), new SimpleBooleanProperty(false)));
        List<Action> implementedActions = actionsProcessor.getImplementedActions("Stage", "main");
        implementedActions.forEach(a -> actionsMap.put(a.getName(), new SimpleBooleanProperty(true)));
        List<SupremeMenuItem> supremeMenuItems = supremeSceneService.getSupremeMenuItems(stageContainer.getStage().getScene());
        supremeMenuItemService.bindMenuItemsToActions(supremeMenuItems, stageContainer.getActionsMap());

        XmlConfiguration sessionsConfiguration = XmlConfigurationFactory.getConfiguration("sessions");
        SessionProcessor sessionProcessor = new SessionProcessor(sessionsConfiguration.getDocument());
        SessionFileProcessor sessionFileProcessor = new SessionFileProcessor(sessionsConfiguration.getDocument());
        Session session = sessionProcessor.getActiveSession();

        List<SessionFile> sessionFiles = sessionFileProcessor.getSessionFiles(session.getName());
        TabPane tabPane = (TabPane) scene.lookup("#supremeTabPane");
        List<SupremeTab> supremeTabs = sessionFiles.stream()
                .map(sf -> supremeTabService.createTab(sf, stageContainer)).collect(Collectors.toList());
        tabPane.getTabs().addAll(supremeTabs);

        stage.show();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
    }

    public static class StageContainersRegistry {

        private final ObservableList<StageContainer> openStageContainers = FXCollections.observableArrayList();
        private final ObjectProperty<StageContainer> currentStageContainer = new SimpleObjectProperty<>(null);
        private static StageContainersRegistry instance = new StageContainersRegistry();

        public static StageContainersRegistry getInstance() {
            return instance;
        }

        public ObservableList<StageContainer> getOpenStageContainers() {
            return openStageContainers ;
        }

        public ObjectProperty<StageContainer> currentStageContainerProperty() {
            return currentStageContainer;
        }

        public StageContainer getCurrentStageContainer() {
            return currentStageContainerProperty().get();
        }

        public void setCurrentStageContainer(StageContainer currentStage) {
            currentStageContainerProperty().set(currentStage);
        }

        public void registerStageContainer(StageContainer stageContainer) {
            stageContainer.getStage().addEventHandler(WindowEvent.WINDOW_SHOWN, e -> openStageContainers.add(stageContainer));
            stageContainer.getStage().addEventHandler(WindowEvent.WINDOW_HIDDEN, e -> openStageContainers.remove(stageContainer));
            stageContainer.getStage().focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
                if (isNowFocused) {
                    currentStageContainerProperty().set(stageContainer);
                } else {
                    currentStageContainerProperty().set(null);
                }
            });
        }
    }
}
