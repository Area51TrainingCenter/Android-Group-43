package pe.area51.airplanemodedetector;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AirplaneModeBroadcastReceiver extends BroadcastReceiver {

    private final AirplaneModeBroadcastReceiverInterface airplaneModeBroadcastReceiverInterface;

    public AirplaneModeBroadcastReceiver(AirplaneModeBroadcastReceiverInterface airplaneModeBroadcastReceiverInterface) {
        this.airplaneModeBroadcastReceiverInterface = airplaneModeBroadcastReceiverInterface;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
            /*
            El Intent enviado por el sistema cuando se cambia el estado del modo avión,
            tiene un dato extra identificado por la llave "state" que indica cuál es el
            nuevo estado.
            https://developer.android.com/reference/android/content/Intent.html#ACTION_AIRPLANE_MODE_CHANGED
             */
        final boolean isAirplaneOn = intent.getBooleanExtra("state", false);
        airplaneModeBroadcastReceiverInterface.onAirplaneModeChanged(isAirplaneOn);
    }

    public interface AirplaneModeBroadcastReceiverInterface {

        void onAirplaneModeChanged(boolean isAirplaneModeOn);
    }
}
