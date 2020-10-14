package com.passman;

import org.testfx.api.FxRobot;
import org.testfx.api.FxRobotContext;

public class TestUtils {
    public static int getNumberOfWindows(FxRobotContext context) {
        return context.getWindowFinder().listWindows().size();
    }

    public static void write(FxRobot robot, String field, String content) {
        robot.clickOn(field);
        robot.write(content);
    }
}
