package com.rollercoaster.kacapp;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.button_nearby)
    public void nearby() {
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=" + getString(R.string.drunk)));
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }

    @OnClick(R.id.button_ambulance)
    public void ambulance() {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:112"));
        startActivity(intent);
    }

    @OnClick(R.id.button_share)
    public void share() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, "10");
        startActivity(Intent.createChooser(intent, getString(R.string.share)));
    }

    @OnClick(R.id.button_order)
    public void order() {
        String pysznePackage = getString(R.string.pyszne_package);
        Intent intent = getPackageManager().getLaunchIntentForPackage(pysznePackage);
        if (intent != null) {
            intent.addCategory(Intent.CATEGORY_LAUNCHER);
            startActivity(intent);
        } else launchMarket(pysznePackage);
    }

    @OnClick(R.id.logo_dw)
    public void dobrzewiesz() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(getString(R.string.dobrzewiesz_url)));
        startActivity(intent);
    }

    private void launchMarket(String packageName) {
        try {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + packageName)));
        } catch (ActivityNotFoundException e) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + packageName)));
        }
    }
}
