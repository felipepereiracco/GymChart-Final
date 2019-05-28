package gymchart.felipepereira.com.gymchart;

import android.content.Context;
import android.content.Intent;

public class ShowScreen {

    public void load(Context activity, Class new_Activity){

        Intent intent=new Intent(activity,new_Activity);
        activity.startActivity(intent);
    }
    public void loadWithExtra(Context activity, Class new_Activity, String extra){

        Intent intent=new Intent(activity,new_Activity);
        intent.putExtra("parameter", extra);
        activity.startActivity(intent);
    }
}
