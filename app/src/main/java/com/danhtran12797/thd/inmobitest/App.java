package com.danhtran12797.thd.inmobitest;

import android.app.Application;

import com.inmobi.sdk.InMobiSdk;

import org.json.JSONException;
import org.json.JSONObject;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
//        JSONObject consentObject = new JSONObject();
//        try {
//            // Provide correct consent value to sdk which is obtained by User
//            consentObject.put(InMobiSdk.IM_GDPR_CONSENT_AVAILABLE, true);
//            // Provide 0 if GDPR is not applicable and 1 if applicable
//            consentObject.put("gdpr", "0");
//            // Provide user consent in IAB format
//            consentObject.put(InMobiSdk.IM_GDPR_CONSENT_IAB, "CMP");
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
        InMobiSdk.init(this, getResources().getString(R.string.publisher_id));
    }
}
