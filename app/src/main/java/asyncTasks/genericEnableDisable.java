package asyncTasks;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

import com.example.rehas.test2.packageInformation;

/**
 * Created by Rehas on 12/7/2017.
 */

public class genericEnableDisable extends AsyncTask {

    public Activity mA;
    public packageInformation pi;

    public genericEnableDisable(Activity _activity, packageInformation pi){
        this.mA = _activity;
        this.pi = pi;
    }

    @Override
    protected Object doInBackground(Object... params) {
        String packName = pi.getName();
        String status = pi.getStatus();
        String cmd = "";

        if(params[0].toString().contentEquals("Enable")){

            cmd = "pm enable " + packName;

        } else if( params[0].toString().contentEquals("Disable")){
            cmd = "pm disable " + packName;
        }

        try {
            Runtime.getRuntime().exec(new String[]{"su", "-c", cmd}).waitFor();

        }catch (Exception e){
            e.printStackTrace();
            Log.e("Error Here: ", e.getMessage());
        }


        return pi;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);

        Log.d("OldStatus: ", pi.getStatus());
    }
}
