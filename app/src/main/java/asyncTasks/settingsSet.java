package asyncTasks;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import com.example.rehas.test2.R;

/**
 * Created by Rehas on 11/15/2017.
 */

public class settingsSet extends AsyncTask<Object, String, String> {

    public Activity myActivity;

    public settingsSet(Activity _activity){
        this.myActivity = _activity;

    }

    @Override
    protected String doInBackground(Object... params) {
        String result = "Nothing";

        if (params[0].toString().contentEquals("Enable")) {

            Log.i("Settings", "Enabled");

            String[] apps = {"browser", "settings", "vending", "gallery3d"};

            for (String app : apps) {
                String cmd = "pm " + "enable" + " com.android." + app;
                try {
                    Runtime.getRuntime().exec(new String[]{"su", "-c", cmd}).waitFor();
                } catch (Exception e) {
                    e.printStackTrace();
                    e.printStackTrace();
                    Log.e("", e.getMessage());
                } finally {
                    result = "EnabledSettings";
                }

            }
        }

        if (params[0].toString().contentEquals("Disable")) {
            String[] apps2 = {"browser", "settings", "vending", "gallery3d"};

            for (String app : apps2) {
                String cmd = "pm " + "disable" + " com.android." + app;
                try {
                    Runtime.getRuntime().exec(new String[]{"su", "-c", cmd}).waitFor();
                } catch (Exception e) {
                    e.printStackTrace();
                    e.printStackTrace();
                    Log.e("", e.getMessage());
                } finally {
                    result = "DisabledSettings";
                }

            }
        }

        return result;


    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        TextView asRes = (TextView) this.myActivity.findViewById(R.id.asyncResult);
        asRes.setText(s);
    }
}

