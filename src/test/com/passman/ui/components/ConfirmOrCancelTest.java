package com.passman.ui.components;

import com.passman.UITest;
import com.passman.commons.Form;
import com.passman.commons.Navigator;
import com.passman.commons.View;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.util.NodeQueryUtils.hasText;

public class ConfirmOrCancelTest extends UITest {
    private Navigator mockNavigator;
    private View mockView;

    @Override
    public void start(Stage primaryStage) {
        mockNavigator = mock(Navigator.class);
        mockView = mock(View.class);

        ConfirmOrCancel confirmOrCancel = new ConfirmOrCancel("Sign In");

        confirmOrCancel.setNavigator(mockNavigator);
        confirmOrCancel.setOnConfirm(e -> {
            mockNavigator.push(mockView);
        });

        primaryStage.setScene(new Scene(confirmOrCancel));
        primaryStage.show();
    }

    @Test
    public void shouldSetTheActionNameProperly() {
        verifyThat("#confirmButton", hasText("Sign In"));
    }

    @Test
    public void clickingCancelShouldPopTheNavigator() {
        clickOn("#cancelButton");

        verify(mockNavigator, times(1)).pop();
    }

    @Test
    public void clickingOkShouldExecuteThePassedBehaviour() {
        clickOn("#confirmButton");

        verify(mockNavigator, times(1)).push(mockView);
    }
}