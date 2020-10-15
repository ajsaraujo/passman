package com.passman;

import com.passman.commons.Navigator;
import com.passman.commons.abstracts.FXMLFile;
import com.passman.commons.abstracts.ViewController;
import com.passman.ui.components.FileField;
import com.passman.utils.Serializer;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.Serializable;

import static org.mockito.Mockito.mock;

public abstract class ControllerTest extends UITest {
    protected Navigator navigator;
    protected Serializer serializer;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.navigator = mock(Navigator.class);
        this.serializer = mock(Serializer.class);

        ViewController.setNavigator(navigator);
        ViewController.setSerializer(serializer);

        FileField.setTestMode(true);
    }

    protected void render(FXMLFile file) {
        Scene scene = file.loadScene();
        super.render(scene);
    }
}
