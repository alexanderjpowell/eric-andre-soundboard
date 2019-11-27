package com.soundboards.alexanderpowell.ericandresoundboard;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
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
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import com.google.android.material.button.MaterialButton;
import java.io.FileDescriptor;
import java.util.HashSet;
import java.util.Set;
import static com.soundboards.alexanderpowell.ericandresoundboard.MainActivity.BUTTON_HEIGHT_PIXELS;
import static com.soundboards.alexanderpowell.ericandresoundboard.MainActivity.BUTTON_MARGIN_LARGE;
import static com.soundboards.alexanderpowell.ericandresoundboard.MainActivity.BUTTON_MARGIN_SMALL;
import static com.soundboards.alexanderpowell.ericandresoundboard.MainActivity.TABLE_ROW_HEIGHT;
import static com.soundboards.alexanderpowell.ericandresoundboard.MainActivity.TABLE_ROW_WEIGHT;
import static com.soundboards.alexanderpowell.ericandresoundboard.MainActivity.TABLE_ROW_WIDTH;

public class SoundsTab extends Fragment implements View.OnClickListener, View.OnLongClickListener {

    private static String[] sound_file_names;
    private MediaPlayer mediaPlayer;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.sounds_tab_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        sound_file_names = MainActivity.filenames;

        TableLayout tableLayout = requireView().findViewById(R.id.tableLayout);
        tableLayout.setStretchAllColumns(true);
        tableLayout.setShrinkAllColumns(true);

        TableRow tableRow;
        MaterialButton buttonLeft, buttonRight;

        for (int i = 0; i < sound_file_names.length; i+=2) {
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

            // Left
            buttonLeft.setId(i);
            buttonLeft.setText(MainActivity.formatFileString(sound_file_names[i]));
            tableRow.addView(buttonLeft);

            // Right
            if ((i + 1) < sound_file_names.length) {
                buttonRight.setId(i + 1);
                buttonRight.setText(MainActivity.formatFileString(sound_file_names[i + 1]));
                tableRow.addView(buttonRight);
            }

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

    @Override
    public boolean onLongClick(final View view) {

        final SharedPreferences sharedPreferences = getContext().getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        //Toast.makeText(getContext(), sound_file_names[view.getId()], Toast.LENGTH_SHORT).show();

        //
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Add to favorites");//.setTitle("title");
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked OK button
                //SharedPreferences sharedPreferences = getContext().getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
                //SharedPreferences.Editor editor = sharedPreferences.edit();
                Set<String> favoritesSet = sharedPreferences.getStringSet("favorites", new HashSet<String>());
                //Toast.makeText(getContext(), Integer.toString(favoritesSet.size()), Toast.LENGTH_SHORT).show();
                favoritesSet.add(sound_file_names[view.getId()]);
                editor.clear();
                editor.putStringSet("favorites", favoritesSet);
                editor.apply();
            }
        });
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User cancelled the dialog
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
        //

        return true;
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
                AssetFileDescriptor assetFileDescriptor = getActivity().getAssets().openFd("sounds/" + filename);
                FileDescriptor fileDescriptor = assetFileDescriptor.getFileDescriptor();
                long startOffset = assetFileDescriptor.getStartOffset();
                long length = assetFileDescriptor.getLength();
                mediaPlayer.setDataSource(fileDescriptor, startOffset, length);
                mediaPlayer.prepare();
                mediaPlayer.start();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            //Toast.makeText(getContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
            Toast.makeText(getContext(), "ERROR", Toast.LENGTH_LONG).show();
        }
    }

}
