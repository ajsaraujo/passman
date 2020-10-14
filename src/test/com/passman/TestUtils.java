package com.passman;

import org.testfx.api.FxRobotContext;

public class TestUtils {
    public static int getNumberOfWindows(FxRobotContext context) {
        return context.getWindowFinder().listWindows().size();
    }
}
