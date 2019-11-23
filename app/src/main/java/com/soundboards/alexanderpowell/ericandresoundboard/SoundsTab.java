package com.soundboards.alexanderpowell.ericandresoundboard;

import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.google.android.material.button.MaterialButton;
import java.io.FileDescriptor;

public class SoundsTab extends Fragment implements View.OnClickListener, View.OnLongClickListener {

    private static String[] sound_file_names;
    private MediaPlayer mediaPlayer;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.sounds_tab_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        final int BUTTON_HEIGHT_PIXELS = 350;
        final int BUTTON_MARGIN_SMALL = 15;
        final int BUTTON_MARGIN_LARGE = 30;
        final int TABLE_ROW_WIDTH = 0;
        final int TABLE_ROW_HEIGHT = 0;
        final float TABLE_ROW_WEIGHT = 1f;

        final String[] sound_titles = requireActivity().getResources().getStringArray(R.array.sound_titles);
        sound_file_names = requireActivity().getResources().getStringArray(R.array.sound_file_names);

        TableLayout tableLayout = requireView().findViewById(R.id.tableLayout);
        tableLayout.setStretchAllColumns(true);
        tableLayout.setShrinkAllColumns(true);

        TableRow tableRow;
        MaterialButton buttonLeft, buttonRight;

        for (int i = 0; i < sound_titles.length; i+=2) {
            tableRow = new TableRow(getContext());
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            tableRow.setLayoutParams(params);
            tableRow.setGravity(Gravity.CENTER_HORIZONTAL);

            // Construct 2 buttons
            buttonLeft = new MaterialButton(requireContext(), null, R.attr.materialButtonStyle);
            buttonRight = new MaterialButton(requireContext(), null, R.attr.materialButtonStyle);

            // Left hand buttons
            TableRow.LayoutParams buttonLayoutParamsLeft = new TableRow.LayoutParams(TABLE_ROW_WIDTH, TABLE_ROW_HEIGHT, TABLE_ROW_WEIGHT);
            buttonLayoutParamsLeft.height = BUTTON_HEIGHT_PIXELS;
            buttonLayoutParamsLeft.setMarginStart(BUTTON_MARGIN_LARGE);
            buttonLayoutParamsLeft.setMarginEnd(BUTTON_MARGIN_SMALL);

            // Right hand buttons
            TableRow.LayoutParams buttonLayoutParamsRight = new TableRow.LayoutParams(TABLE_ROW_WIDTH, TABLE_ROW_HEIGHT, TABLE_ROW_WEIGHT);
            buttonLayoutParamsRight.height = BUTTON_HEIGHT_PIXELS;
            buttonLayoutParamsRight.setMarginStart(BUTTON_MARGIN_SMALL);
            buttonLayoutParamsRight.setMarginEnd(BUTTON_MARGIN_LARGE);

            buttonLeft.setLayoutParams(buttonLayoutParamsLeft);
            buttonRight.setLayoutParams(buttonLayoutParamsRight);
            buttonLeft.setOnClickListener(this);
            buttonRight.setOnClickListener(this);
            buttonLeft.setOnLongClickListener(this);
            buttonRight.setOnLongClickListener(this);

            buttonLeft.setId(i);
            buttonRight.setId(i + 1);

            buttonLeft.setText(sound_titles[i]);
            buttonRight.setText(sound_titles[i + 1]);

            // Add the buttons to the row
            tableRow.addView(buttonLeft);
            tableRow.addView(buttonRight);

            // Add the row to the table
            tableLayout.addView(tableRow);
        }

        /*FloatingActionButton floatingActionButton = requireView().findViewById(R.id.floatingActionButton);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
                }
            }
        });*/
    }

    @Override
    public void onClick(View view) {
        playSound(sound_file_names[view.getId()]);
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

    @Override
    public boolean onLongClick(View view) {
        return true;
    }

}
