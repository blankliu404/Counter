package com.blank.courtcounter;

import android.app.Activity;
import android.app.Application;

import java.util.Stack;

public class App extends Application {
    //是否第一次进入MainActivity
    private static boolean newSession = true;

    public static boolean getNewSession(){
        return newSession;
    }

    public static void setNewSession(){
        newSession = false;
    }

}
