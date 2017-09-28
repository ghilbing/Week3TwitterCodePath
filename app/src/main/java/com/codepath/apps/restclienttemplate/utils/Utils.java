package com.codepath.apps.restclienttemplate.utils;

import java.io.IOException;

/**
 * Created by gretel on 9/28/17.
 */

public class Utils {
    public static boolean checkForInternet() {
                Runtime runtime = Runtime.getRuntime();
                try {
                        Process ipProcess = runtime.exec("/system/bin/ping -c 1 8.8.8.8");
                        int     exitValue = ipProcess.waitFor();
                        return (exitValue == 0);
                    } catch (IOException e)          { e.printStackTrace(); }
                catch (InterruptedException e) { e.printStackTrace(); }
                return false;
            }
}
