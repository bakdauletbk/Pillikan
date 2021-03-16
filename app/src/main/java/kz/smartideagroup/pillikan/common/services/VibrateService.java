package kz.smartideagroup.pillikan.common.services;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.os.Vibrator;
import androidx.annotation.RequiresApi;

public class VibrateService extends Service {

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        Vibrator vibrator = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(200);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
