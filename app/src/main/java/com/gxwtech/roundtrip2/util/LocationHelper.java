package com.gxwtech.roundtrip2.util;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.util.Log;



/**
 * Helper for checking if location services are enabled on the device.
 */
public class LocationHelper {

    final static String TAG = "LocationHelper";

    /**
     * Determine if GPS is currently enabled.
     *
     * On Android 6 (Marshmallow), location needs to be enabled for Bluetooth discovery to work.
     *
     * @param context The current app context.
     * @return true if location is enabled, false otherwise.
     */
    public static boolean isLocationEnabled(Context context) {
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }

    /**
     * Prompt the user to enable GPS location if it isn't already on.
     *
     * @param parent The currently visible activity.
     */
    public static void requestLocation(final Activity parent) {
        if (LocationHelper.isLocationEnabled(parent)) {
            return;
        }

        // Shamelessly borrowed from http://stackoverflow.com/a/10311877/868533

        Log.e(TAG, "requestLocation: N/A");

//        AlertDialog.Builder builder = new AlertDialog.Builder(parent);
//        builder.setTitle(R.string.location_not_found_title);
//        builder.setMessage(R.string.location_not_found_message);
//        builder.setPositiveButton(R.string.location_yes, new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialogInterface, int i) {
//                parent.startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
//            }
//        });
//        builder.setNegativeButton(R.string.location_no, null);
//        builder.create().show();
    }

    /**
     * Prompt the user to enable GPS location on devices that need it for Bluetooth discovery.
     *
     * Android 6 (Marshmallow) needs GPS enabled for Bluetooth discovery to work.
     *
     * @param activity The currently visible activity.
     */
    public static void requestLocationForBluetooth(Activity activity) {
        // Location needs to be enabled for Bluetooth discovery on Marshmallow.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            LocationHelper.requestLocation(activity);
        }
    }

    //public static Boolean locationPermission(ActivityWithMenu act) {
    //    return ActivityCompat.checkSelfPermission(act, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;
    //}

}