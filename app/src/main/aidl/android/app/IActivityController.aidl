// IActivityController.aidl
package android.app;
import android.content.Intent;
// Declare any non-default types here with import statements

interface IActivityController {

boolean activityResuming(String pkg);

boolean activityStarting(String pkg);

boolean appCrashed(String processName, int pid, String shortMsg, String longMsg,long timeMillis, String stackTrace);

int appEarlyNotResponding(String processName, int pid, String annotation);

int appNotResponding(String processName, int pid, String processStats);

}
