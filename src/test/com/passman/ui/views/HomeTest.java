package com.passman.ui.views;

import com.passman.ControllerTest;
import com.passman.commons.View;
import javafx.stage.Stage;

import static org.junit.Assert.*;

public class HomeTest extends ControllerTest {
    @Override
    public void start(Stage primaryStage) {
        super.start(primaryStage);

        render(new View("home"));
    }


}