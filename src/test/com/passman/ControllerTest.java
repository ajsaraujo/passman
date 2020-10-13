package com.passman;

import com.passman.commons.Navigator;
import com.passman.commons.abstracts.FXMLFile;
import com.passman.commons.abstracts.ViewController;
import javafx.scene.Scene;
import javafx.stage.Stage;
import static org.mockito.Mockito.mock;

public abstract class ControllerTest extends UITest {
    protected Navigator navigator;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.navigator = mock(Navigator.class);

        ViewController.setNavigator(navigator);
    }

    protected void render(FXMLFile file) {
        Scene scene = file.loadScene();
        super.render(scene);
    }
}
