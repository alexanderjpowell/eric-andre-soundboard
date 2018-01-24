package com.soundboards.alexanderpowell.ericandresoundboard;

import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.media.MediaPlayer;
import android.media.AudioManager;
import android.content.Context;
import android.widget.SeekBar;

import java.util.Random;

import com.google.android.gms.ads.AdView;

public class MainActivity extends AppCompatActivity implements Tab1.OnFragmentInteractionListener,Tab2.OnFragmentInteractionListener,Tab3.OnFragmentInteractionListener {

    private MediaPlayer mediaPlayer = null;
    //private AdView mAdView;

    int[] all_sounds = {
            R.raw.bases,
            R.raw.benghazi,
            R.raw.bill_cosby,
            R.raw.bird_up,
            R.raw.cameras,
            R.raw.cherokee_beer,
            R.raw.hoky_poky,
            R.raw.i_am_the_octopus,
            R.raw.legalize_ranch,
            R.raw.lsd,
            R.raw.mein_kampf,
            R.raw.mom_and_dad,
            R.raw.monkeys,
            R.raw.narcoleptic,
            R.raw.octomom,
            R.raw.on_wet,
            R.raw.oriental_background,
            R.raw.ranch_it_up,
            R.raw.societies_lies,
            R.raw.sup_mello,
            R.raw.taxes,
            R.raw.vacuum,
            R.raw.vert_horiz_mull,
            R.raw.john_wayne,
            R.raw.hebrew,
            R.raw.l_ron_hubbard,
            R.raw.pizza_ball,
            R.raw.schindlers_list,
            R.raw.well_be_right_back,
            R.raw.yahboobay_short,
            R.raw.yahboobay_long,
            R.raw.yes_guy
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TabLayout tabLayout = (TabLayout)findViewById(R.id.tablayout);
        tabLayout.addTab(tabLayout.newTab().setText("Eric-isms"));
        //tabLayout.addTab(tabLayout.newTab().setText("Other"));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_settings_white_24dp));
        //tabLayout.addTab(tabLayout.newTab().setText("Tab 3"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        setVolumeControlStream(AudioManager.STREAM_MUSIC);

        final ViewPager viewPager = (ViewPager)findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(),tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        //final AudioManager audioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
        //int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        //int curVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        //SeekBar volControl = (SeekBar)findViewById(R.id.volumeBar);
        //volControl.setMax(maxVolume);
        //volControl.setProgress(curVolume);
        /*volControl.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onStopTrackingTouch(SeekBar arg0) {
            }

            @Override
            public void onStartTrackingTouch(SeekBar arg0) {
            }

            @Override
            public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, arg1, 0);
            }
        });*/

        //MobileAds.initialize(this, "ca-app-pub-0710201817886691~3983909486");
        //mAdView = findViewById(R.id.adView);
        //AdRequest adRequest = new AdRequest.Builder().build();
        //mAdView.loadAd(adRequest);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public void playSoundEffect(View view) {

        // Needed to prevent memory errors
        if (mediaPlayer != null) {
            mediaPlayer.reset();
            mediaPlayer.release();
        }

        switch (view.getId()) {
            case R.id.button_benghazi:
                mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.benghazi);
                mediaPlayer.start();
                break;
            case R.id.button_bird_up:
                mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.bird_up);
                mediaPlayer.start();
                break;
            case R.id.button_cherokee_chicks:
                mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.cherokee_beer);
                mediaPlayer.start();
                break;
            case R.id.button_legalize_ranch:
                mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.legalize_ranch);
                mediaPlayer.start();
                break;
            case R.id.button_ranch_it_up:
                mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.ranch_it_up);
                mediaPlayer.start();
                break;
            case R.id.button_bases:
                mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.bases);
                mediaPlayer.start();
                break;
            case R.id.button_bill_cosby:
                mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.bill_cosby);
                mediaPlayer.start();
                break;
            case R.id.button_cameras:
                mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.cameras);
                mediaPlayer.start();
                break;
            case R.id.button_hoky_poky:
                mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.hoky_poky);
                mediaPlayer.start();
                break;
            case R.id.button_octopus:
                mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.i_am_the_octopus);
                mediaPlayer.start();
                break;
            case R.id.button_lsd:
                mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.lsd);
                mediaPlayer.start();
                break;
            case R.id.button_mein_kampf:
                mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.mein_kampf);
                mediaPlayer.start();
                break;
            case R.id.button_mom_and_dad:
                mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.mom_and_dad);
                mediaPlayer.start();
                break;
            case R.id.button_monkeys:
                mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.monkeys);
                mediaPlayer.start();
                break;
            case R.id.button_narcoleptic:
                mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.narcoleptic);
                mediaPlayer.start();
                break;
            case R.id.button_octomom:
                mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.octomom);
                mediaPlayer.start();
                break;
            case R.id.button_on_wet:
                mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.on_wet);
                mediaPlayer.start();
                break;
            case R.id.button_oriental:
                mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.oriental_background);
                mediaPlayer.start();
                break;
            case R.id.button_societys_lies:
                mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.societies_lies);
                mediaPlayer.start();
                break;
            case R.id.button_sup_mello:
                mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.sup_mello);
                mediaPlayer.start();
                break;
            case R.id.button_taxes:
                mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.taxes);
                mediaPlayer.start();
                break;
            case R.id.button_vacuum:
                mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.vacuum);
                mediaPlayer.start();
                break;
            case R.id.button_vertical:
                mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.vert_horiz_mull);
                mediaPlayer.start();
                break;
            case R.id.button_john_wayne:
                mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.john_wayne);
                mediaPlayer.start();
                break;
            case R.id.button_hebrew:
                mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.hebrew);
                mediaPlayer.start();
                break;
            case R.id.button_l_ron_hubbard:
                mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.l_ron_hubbard);
                mediaPlayer.start();
                break;
            case R.id.button_pizza_ball:
                mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.pizza_ball);
                mediaPlayer.start();
                break;
            case R.id.button_schindlers_list:
                mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.schindlers_list);
                mediaPlayer.start();
                break;
            case R.id.button_well_be_right_back:
                mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.well_be_right_back);
                mediaPlayer.start();
                break;
            case R.id.button_yahboobay_short:
                mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.yahboobay_short);
                mediaPlayer.start();
                break;
            case R.id.button_yahboobay_long:
                mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.yahboobay_long);
                mediaPlayer.start();
                break;
            case R.id.button_yes_guy:
                mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.yes_guy);
                mediaPlayer.start();
                break;
            case R.id.randomSoundButton:
                int max = all_sounds.length; // Generates random number between 0 and (max-1)
                int random = new Random().nextInt(max);
                mediaPlayer = MediaPlayer.create(getApplicationContext(), all_sounds[random]);
                mediaPlayer.start();
        }
    }

    /*@Override
    protected void onStop() {
        super.onStop();

        // Release the MediaPlayer
        if (mediaPlayer != null) {
            mediaPlayer.reset();
            mediaPlayer.release();
        }
    }

    @Override
    public void onResume() {
        super.onResume();


    }*/

    public void suggestNewSound(View view) {
        // Opens the google form that allows users to suggest new quote
        Uri uri = Uri.parse("https://goo.gl/forms/6WQP8mptHDA4eOR03"); // missing 'http://' will cause crashed
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    public void shareApp(View view) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        String shareBody = "Download the Eric Andre soundboard to play all your favorite Eric Andre quotes anytime and anywhere: ";
        shareBody = shareBody + "\n" + "https://play.google.com/store/apps/details?id=com.soundboards.alexanderpowell.ericandresoundboard";
        String shareSubject = "Eric Andre Soundboard";
        intent.putExtra(Intent.EXTRA_SUBJECT, shareSubject);
        intent.putExtra(Intent.EXTRA_TEXT, shareBody);
        startActivity(Intent.createChooser(intent, "Share using..."));
    }
}
