package com.jayesh10.music.activities;

import android.media.AudioManager;
import android.os.Bundle;
import androidx.annotation.Nullable;

import com.afollestad.appthemeengine.ATEActivity;
import com.jayesh10.music.utils.Helpers;

public class BaseThemedActivity extends ATEActivity {

    @Nullable
    @Override
    public String getATEKey() {
        return Helpers.getATEKey(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //make volume keys change multimedia volume even if music is not playing now
        setVolumeControlStream(AudioManager.STREAM_MUSIC);
    }
}
