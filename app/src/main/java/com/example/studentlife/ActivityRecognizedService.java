package com.example.studentlife;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.android.gms.location.ActivityRecognitionResult;
import com.google.android.gms.location.DetectedActivity;

import java.util.List;

public class ActivityRecognizedService extends IntentService {

    public ActivityRecognizedService() {
        super("ActivityRecognizedService");
    }

    public ActivityRecognizedService(String name) {
        super(name);
    }
    private Integer vehicle = 0;
    private Integer bicycle = 0;
    private Integer foot = 0;
    private Integer running = 0;
    private Integer still = 0;
    private Integer tilting = 0;
    private Integer walking = 0;
    private Integer unknown = 0;

    @Override
    protected void onHandleIntent(Intent intent) {
        if(ActivityRecognitionResult.hasResult(intent)) {
            ActivityRecognitionResult result = ActivityRecognitionResult.extractResult(intent);
            handleDetectedActivities( result.getProbableActivities() );
        }
    }



    private void handleDetectedActivities(List<DetectedActivity> probableActivities) {


        for( DetectedActivity activity : probableActivities ) {
            switch( activity.getType() ) {
                case DetectedActivity.IN_VEHICLE: {
                    Log.e( "ActivityRecogition", "In Vehicle: " + activity.getConfidence() );
                    vehicle = activity.getConfidence();
                    break;
                }
                case DetectedActivity.ON_BICYCLE: {
                    Log.e( "ActivityRecogition", "On Bicycle: " + activity.getConfidence() );
                    bicycle = activity.getConfidence();
                    break;
                }
                case DetectedActivity.ON_FOOT: {
                    Log.e( "ActivityRecogition", "On Foot: " + activity.getConfidence() );
                    foot = activity.getConfidence();
                    break;
                }
                case DetectedActivity.RUNNING: {
                    Log.e( "ActivityRecogition", "Running: " + activity.getConfidence() );
                    running = activity.getConfidence();
                    break;
                }
                case DetectedActivity.STILL: {
                    Log.e( "ActivityRecogition", "Still: " + activity.getConfidence() );
                    still = activity.getConfidence();
                    break;
                }
                case DetectedActivity.TILTING: {
                    Log.e( "ActivityRecogition", "Tilting: " + activity.getConfidence() );
                    tilting = activity.getConfidence();
                    break;
                }
                case DetectedActivity.WALKING: {
                    Log.e( "ActivityRecogition", "Walking: " + activity.getConfidence() );
                    if( activity.getConfidence() >= 75 ) {
                        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
                        builder.setContentText( "Are you walking?" );
                        builder.setSmallIcon( R.mipmap.ic_launcher );
                        builder.setContentTitle( getString( R.string.app_name ) );
                        NotificationManagerCompat.from(this).notify(0, builder.build());
                    }
                    walking = activity.getConfidence();
                    break;
                }
                case DetectedActivity.UNKNOWN: {
                    Log.e( "ActivityRecogition", "Unknown: " + activity.getConfidence() );
                    unknown = activity.getConfidence();
                    break;
                }
            }
        }
        sendDataToActivity(vehicle, bicycle, foot, running, still, tilting, walking, unknown);
    }

    private void sendDataToActivity(Integer vehicle, Integer bicycle, Integer foot, Integer running, Integer still,
                                    Integer tilting, Integer walking, Integer unknown)
    {
        Intent sendLevel = new Intent();
        sendLevel.setAction("GET_VEHICLE_CONF");
        sendLevel.putExtra( "VEH_LEVEL_DATA","vehicle");
        sendLevel.setAction("GET_BICYCLE_CONF");
        sendLevel.putExtra( "BCL_LEVEL_DATA","bicycle");
        sendLevel.setAction("GET_FOOT_CONF");
        sendLevel.putExtra( "FT_LEVEL_DATA","foot");
        sendLevel.setAction("GET_RUNNING_CONF");
        sendLevel.putExtra( "RUN_LEVEL_DATA","running");
        sendLevel.setAction("GET_STILL_CONF");
        sendLevel.putExtra( "STL_LEVEL_DATA","still");
        sendLevel.setAction("GET_TILTING_CONF");
        sendLevel.putExtra( "TLT_LEVEL_DATA","tilting");
        sendLevel.setAction("GET_WALKING_CONF");
        sendLevel.putExtra( "WLK_LEVEL_DATA","walking");
        sendLevel.setAction("GET_UNKNOWN_CONF");
        sendLevel.putExtra( "UNK_LEVEL_DATA","unknown");
        sendBroadcast(sendLevel);

    }

}
