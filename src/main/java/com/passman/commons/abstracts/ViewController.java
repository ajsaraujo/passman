package com.passman.commons.abstracts;

import com.passman.commons.Navigator;

public abstract class ViewController {
    protected static Navigator navigator;

    public static void setNavigator(Navigator navigator) {
        ViewController.navigator = navigator;
    }
}
