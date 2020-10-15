package com.passman.commons.abstracts;

import com.passman.commons.Navigator;
import com.passman.utils.Serializer;

public abstract class ViewController {
    protected static Navigator navigator;
    protected static Serializer serializer;

    public static void setNavigator(Navigator navigator) {
        ViewController.navigator = navigator;
    }

    public static void setSerializer(Serializer serializer) {
        ViewController.serializer = serializer;
    }
}
