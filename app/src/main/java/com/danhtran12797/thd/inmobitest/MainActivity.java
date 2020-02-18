package com.danhtran12797.thd.inmobitest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.inmobi.ads.InMobiAdRequestStatus;
import com.inmobi.ads.InMobiBanner;
import com.inmobi.ads.InMobiInterstitial;
import com.inmobi.ads.listeners.BannerAdEventListener;
import com.inmobi.ads.listeners.InterstitialAdEventListener;

import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    InMobiBanner bannerAd;
    InMobiInterstitial interstitialAd;

    BannerAdEventListener listenerBanner;
    InterstitialAdEventListener listenerInterstitial;

    Button btninterstitial, btnRewarded;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btninterstitial = findViewById(R.id.btnInterstitial);
        btnRewarded=findViewById(R.id.btnRewarded);
        bannerAd = findViewById(R.id.banner);

        btninterstitial.setOnClickListener(this);
        btnRewarded.setOnClickListener(this);

        getLocationPermission();

        listenerInterstitial = new InterstitialAdEventListener() {
            @Override
            public void onAdLoadSucceeded(InMobiInterstitial inMobiInterstitial) {
                super.onAdLoadSucceeded(inMobiInterstitial);
                Toast.makeText(MainActivity.this, "InterstitialAd Load Succeeded", Toast.LENGTH_SHORT).show();
                Log.d("AAA", "InterstitialAd Load Succeeded");
            }

            @Override
            public void onAdLoadFailed(InMobiInterstitial inMobiInterstitial, InMobiAdRequestStatus inMobiAdRequestStatus) {
                super.onAdLoadFailed(inMobiInterstitial, inMobiAdRequestStatus);
                Toast.makeText(MainActivity.this, "InterstitialAd Load Failed", Toast.LENGTH_SHORT).show();
                Log.d("AAA", "InterstitialAd Load Failed");
            }

            @Override
            public void onAdClicked(InMobiInterstitial inMobiInterstitial, Map<Object, Object> map) {
                super.onAdClicked(inMobiInterstitial, map);
                Log.d("AAA", "InterstitialAd clicked");
                Toast.makeText(MainActivity.this, "InterstitialAd clicked", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdDisplayed(InMobiInterstitial inMobiInterstitial) {
                super.onAdDisplayed(inMobiInterstitial);
                Log.d("AAA", "InterstitialAd displayed");
                Toast.makeText(MainActivity.this, "InterstitialAd displayed", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdDismissed(InMobiInterstitial inMobiInterstitial) {
                super.onAdDismissed(inMobiInterstitial);
                Log.d("AAA", "InterstitialAd Dismissed");
                Toast.makeText(MainActivity.this, "InterstitialAd Dismissed", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRewardsUnlocked(InMobiInterstitial inMobiInterstitial, Map<Object, Object> map) {
                super.onRewardsUnlocked(inMobiInterstitial, map);
                Toast.makeText(MainActivity.this, "InterstitialAd Unlocked", Toast.LENGTH_SHORT).show();
                Log.d("AAA", "InterstitialAd Unlocked");
            }
        };

        interstitialAd = new InMobiInterstitial(MainActivity.this, 1581025572883L, listenerInterstitial);
        interstitialAd.load();

        listenerBanner=new BannerAdEventListener() {
            @Override
            public void onAdLoadSucceeded(InMobiBanner inMobiBanner) {
                super.onAdLoadSucceeded(inMobiBanner);
                Toast.makeText(MainActivity.this, "BannerAd Load Succeeded", Toast.LENGTH_SHORT).show();
                Log.d("AAA", "BannerAd Load Succeeded");
            }

            @Override
            public void onAdLoadFailed(InMobiBanner inMobiBanner, InMobiAdRequestStatus inMobiAdRequestStatus) {
                super.onAdLoadFailed(inMobiBanner, inMobiAdRequestStatus);
                Toast.makeText(MainActivity.this, "BannerAd Load Failed", Toast.LENGTH_SHORT).show();
                Log.d("AAA", "BannerAd Load Failed");
            }

            @Override
            public void onAdClicked(InMobiBanner inMobiBanner, Map<Object, Object> map) {
                super.onAdClicked(inMobiBanner, map);
                Log.d("AAA", "BannerAd clicked");
                Toast.makeText(MainActivity.this, "BannerAd clicked", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdDisplayed(InMobiBanner inMobiBanner) {
                super.onAdDisplayed(inMobiBanner);
                Log.d("AAA", "BannerAd displayed");
                Toast.makeText(MainActivity.this, "BannerAd displayed", Toast.LENGTH_SHORT).show();
            }
        };
        bannerAd.setListener(listenerBanner);
    }

    private void getLocationPermission() {
        if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {

        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    1);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            } else {
                Toast.makeText(this, "Hmmmmm", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bannerAd.destroy();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnInterstitial:
                if(interstitialAd.isReady()){
                    interstitialAd.show();
                }else{
                    interstitialAd.load();
                }
            case R.id.btnRewarded:
                if(interstitialAd.isReady()){
                    interstitialAd.show();
                }else{
                    interstitialAd.load();
                }
                break;
        }
    }
}
