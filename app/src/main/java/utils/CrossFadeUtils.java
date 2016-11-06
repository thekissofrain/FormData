package utils;

/**
 * Created by Yoganand on 11-07-2015.
 */
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.test.formdata.R;


/**
 * Created by satish.gummadi on 20-08-2014.
 */
public class CrossFadeUtils {
    private static final String TAG = CrossFadeUtils.class.getSimpleName();

    public static void crossFadeAnimation(final Activity fromActivity ,final Class toActivityClass  )
    {

        crossFadeAnimation(fromActivity ,toActivityClass,50, null,true,false);
    }

    public static void crossFadeAnimation(final Activity fromActivity ,final Class toActivityClass ,Bundle b,boolean isPreviousActivityFinish )
    {

        crossFadeAnimation(fromActivity ,toActivityClass,50, b, isPreviousActivityFinish,false);
    }

    public static void crossFadeAnimation(final Activity fromActivity ,final Class toActivityClass ,Bundle b,boolean isPreviousActivityFinish,boolean isStackClear )
    {

        crossFadeAnimation(fromActivity ,toActivityClass,50, b, isPreviousActivityFinish,isStackClear);
    }

    public static void crossFadeAnimation(final Activity fromActivity ,final Class toActivityClass ,boolean isPreviousActivityFinish )
    {

        crossFadeAnimation(fromActivity ,toActivityClass,50,null, isPreviousActivityFinish,false);
    }



    public static void crossFadeAnimation(final Activity fromActivity ,final Class toActivityClass, final int millis, final Bundle b,final boolean isPreviousActivityFinish,final boolean isStackClear )
    {

        int fadeAmount=millis;
        if(fadeAmount!=0&&fadeAmount!=20)
        {
            fadeAmount=50;
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                //Create an intent that will start the main activity.
                Intent mainIntent = new Intent(fromActivity, toActivityClass);

             /*    else
                {
                    Bundle b= new Bundle();
                    b.putString(Constants.NAVIGATION_ID, fromActivity.getClass().getName());
                    mainIntent.putExtras(b);
                }

		    *//*intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);*//*
                if(isStackClear)
                {
                    mainIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                            Intent.FLAG_ACTIVITY_CLEAR_TASK |
                            Intent.FLAG_ACTIVITY_NEW_TASK);
                    // mainIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                }

                fromActivity.startActivity(mainIntent);
                //com.sonyrewards.rewardsapp.messages.ui.MessagesActivity;

                if(

                        isPreviousActivityFinish
                                ||fromActivity.getClass().toString().contains(Constants.NEWSDETAILS_PACKAGENAME)
                                ||fromActivity.getClass().toString().contains( Constants.MESSAGE_PACKAGENAME)
                                ||fromActivity.getClass().toString().contains( Constants.SCANCENTER_PACKAGENAME)


                        )
                {
                    fromActivity.finish();
                }*/


                if(isStackClear)
                {
                    mainIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                            Intent.FLAG_ACTIVITY_CLEAR_TASK |
                            Intent.FLAG_ACTIVITY_NEW_TASK);
                    // mainIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                }
                if(isPreviousActivityFinish )
                {
                    fromActivity.finish();
                }
                fromActivity.startActivity(mainIntent);

                //Apply splash exit (fade out) and main entry (fade in) animation transitions.
                if(millis!=0) {
                    fromActivity.overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                }
            }
        }, fadeAmount);

    }

    public static void crossFadeAnimation(final Activity fromActivity ,final String  toActivityClass, final boolean isfinish,  final boolean isFromLogin )
    {
        crossFadeAnimation(fromActivity ,toActivityClass,null,isfinish,isFromLogin );
    }

    public static void crossFadeAnimation(final Activity fromActivity ,final String  toActivityClass, final Bundle bundle,final boolean isfinish, final boolean isFromLogin )
    {




        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                //Create an intent that will start the main activity.
                Intent mainIntent;
                try {
                    mainIntent = new Intent(fromActivity, Class.forName(toActivityClass));
                   /* if(bundle != null)
                    {
                        mainIntent.putExtras(bundle);

                    }

                    if(isFromLogin&&(toActivityClass!=null&&!toActivityClass.equalsIgnoreCase(Constants.HOME_PACKAGENAME)))
                    {
                        Bundle lbundle=bundle;
                        if(lbundle==null)
                        {
                            lbundle =new Bundle();
                        }

                        lbundle.putString(Constants.SCREEN_NAV_ID,toActivityClass) ;

                        mainIntent= new Intent(fromActivity,HomeActivity.class);
                        mainIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                                Intent.FLAG_ACTIVITY_CLEAR_TASK |
                                Intent.FLAG_ACTIVITY_NEW_TASK);
                        mainIntent.putExtras(lbundle);
                        fromActivity.startActivity(mainIntent);
                        fromActivity.finish();

                    }else
                    {
                        fromActivity.startActivity(mainIntent);
                    }
*/

                    fromActivity.startActivity(mainIntent);

                    //Apply splash exit (fade out) and main entry (fade in) animation transitions.
                    fromActivity.overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                } catch (ClassNotFoundException e) {
                    // TODO Auto-generated catch block

                }

            }
        }, 50);

        if(isfinish)
        {
            fromActivity.finish();
        }

    }
}