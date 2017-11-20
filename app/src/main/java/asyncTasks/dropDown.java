package asyncTasks;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import com.example.rehas.test2.R;

/**
 * Created by Rehas on 11/15/2017.
 */

public class dropDown extends AsyncTask<Object, String, String> {

    public Activity myActivity;

    public dropDown(Activity _activity){
        this.myActivity = _activity;

    }


    @Override
    protected String doInBackground(Object... params) {

        String result = "Nothing";

        if (params[0].toString().contentEquals("Enable")){

            Log.i("Async Do in background", "Enabled");

            String str = "settings put secure sysui_qs_tiles wifi,bt,cell,rotation,flashlight,hotspot,location";

            try {
                Runtime.getRuntime().exec(new String[]{"su", "-c", str}).waitFor();
            } catch (Exception e){
                e.printStackTrace();
                e.printStackTrace();
                Log.e("", e.getMessage());
            }finally {
                result = "EnabledDD";
            }

        }

        if (params[0].toString().contentEquals("Disable")){
            String str2 = "settings put secure sysui_qs_tiles rotation";

            try {
                Runtime.getRuntime().exec(new String[]{"su", "-c", str2}).waitFor();
            } catch (Exception e){
                e.printStackTrace();
                e.printStackTrace();
                Log.e("", e.getMessage());
            }finally {
                result = "DisabledDD";
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
