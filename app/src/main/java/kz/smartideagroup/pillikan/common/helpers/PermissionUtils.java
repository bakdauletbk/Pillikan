package kz.smartideagroup.pillikan.common.helpers;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class PermissionUtils {

    public static final int REQUEST_LOCATION_PERMISSION_CODE = 100;

    public static boolean checkLocationPermission(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (activity.checkSelfPermission(ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                    && activity.checkSelfPermission(ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                return true;

            } else {
                activity.requestPermissions(new String[] { ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION }, REQUEST_LOCATION_PERMISSION_CODE);
                return false;
            }

        } else {
            return true;
        }
    }

    public static boolean isAllGranted(int[] grantResults) {
        for (int grantResult : grantResults) {
            if (grantResult != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }
}
