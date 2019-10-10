package com.soundboards.alexanderpowell.ericandresoundboard;

import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;

import java.io.FileDescriptor;
import java.util.Random;

public class FavoritesTab extends Fragment implements View.OnClickListener {

    private static String[] sound_file_names;
    private MediaPlayer mediaPlayer;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.favorites_tab_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        sound_file_names = requireActivity().getResources().getStringArray(R.array.sound_file_names);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

        }
    }

    private void playSound(String filename) {
        //
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
        }
        //

        mediaPlayer = new MediaPlayer();
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
        }
        try {
            if (getActivity() != null) {
                AssetFileDescriptor assetFileDescriptor = getActivity().getAssets().openFd(filename);
                FileDescriptor fileDescriptor = assetFileDescriptor.getFileDescriptor();
                long startOffset = assetFileDescriptor.getStartOffset();
                long length = assetFileDescriptor.getLength();
                mediaPlayer.setDataSource(fileDescriptor, startOffset, length);
                mediaPlayer.prepare();
                mediaPlayer.start();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            Toast.makeText(getContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}
