

package com.jayesh10.music.subfragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jayesh10.music.R;
import com.jayesh10.music.utils.Constants;
import com.jayesh10.music.utils.NavigationUtils;
import com.jayesh10.music.utils.PreferencesUtility;

public class SubStyleSelectorFragment extends Fragment {

    private static final String ARG_PAGE_NUMBER = "pageNumber";
    private static final String WHAT = "what";
    private SharedPreferences.Editor editor;
    private SharedPreferences preferences;
    private LinearLayout currentStyle;
    private View foreground;
    private ImageView styleImage, imgLock;

    public static SubStyleSelectorFragment newInstance(int pageNumber, String what) {
        SubStyleSelectorFragment fragment = new SubStyleSelectorFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_PAGE_NUMBER, pageNumber);
        bundle.putString(WHAT, what);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_style_selector_pager, container, false);

        TextView styleName = (TextView) rootView.findViewById(R.id.style_name);
        styleName.setText(String.valueOf(getArguments().getInt(ARG_PAGE_NUMBER) + 1));
        preferences = getActivity().getSharedPreferences(Constants.FRAGMENT_ID, Context.MODE_PRIVATE);

        styleImage = (ImageView) rootView.findViewById(R.id.style_image);
        imgLock = (ImageView) rootView.findViewById(R.id.img_lock);


        switch (getArguments().getInt(ARG_PAGE_NUMBER)) {
            case 0:
                styleImage.setImageResource(R.drawable.timber_1_nowplaying_x);
                break;
            case 1:
                styleImage.setImageResource(R.drawable.timber_2_nowplaying_x);
                break;
            case 2:
                styleImage.setImageResource(R.drawable.timber_3_nowplaying_x);
                break;
            case 3:
                styleImage.setImageResource(R.drawable.timber_4_nowplaying_x);
                break;
            case 4:
                styleImage.setImageResource(R.drawable.timber_5_nowplaying_x);
                break;
            case 5:
                styleImage.setImageResource(R.drawable.timber_6_nowplaying_x);
                break;
        }

        currentStyle = (LinearLayout) rootView.findViewById(R.id.currentStyle);
        foreground = rootView.findViewById(R.id.foreground);

        setCurrentStyle();

        return rootView;
    }

    private boolean isUnlocked() {
        return getActivity() != null && PreferencesUtility.getInstance(getActivity()).fullUnlocked();
    }

    @Override
    public void onResume() {
        super.onResume();
        updateLockedStatus();
    }

    private void updateLockedStatus() {
        if (getArguments().getInt(ARG_PAGE_NUMBER) >= 4 && !isUnlocked()) {
            imgLock.setVisibility(View.VISIBLE);
            foreground.setVisibility(View.VISIBLE);
        }
        else {
            imgLock.setVisibility(View.GONE);
            foreground.setVisibility(View.GONE);
        }
    }

    public void setCurrentStyle() {
        String fragmentID = preferences.getString(Constants.NOWPLAYING_FRAGMENT_ID, Constants.TIMBER3);

        if (getArguments().getInt(ARG_PAGE_NUMBER) == NavigationUtils.getIntForCurrentNowplaying(fragmentID)) {
            currentStyle.setVisibility(View.VISIBLE);
            foreground.setVisibility(View.VISIBLE);
        } else {
            currentStyle.setVisibility(View.GONE);
            foreground.setVisibility(View.GONE);
        }

    }

    private void setPreferences() {

        if (getArguments().getString(WHAT).equals(Constants.SETTINGS_STYLE_SELECTOR_NOWPLAYING)) {
            editor = getActivity().getSharedPreferences(Constants.FRAGMENT_ID, Context.MODE_PRIVATE).edit();
            editor.putString(Constants.NOWPLAYING_FRAGMENT_ID, getStyleForPageNumber());
            editor.apply();
            if (getActivity() != null)
                PreferencesUtility.getInstance(getActivity()).setNowPlayingThemeChanged(true);
            setCurrentStyle();
            ((StyleSelectorFragment) getParentFragment()).updateCurrentStyle();
        }
    }

    private String getStyleForPageNumber() {
        switch (getArguments().getInt(ARG_PAGE_NUMBER)) {
            case 0:
                return Constants.TIMBER1;
            case 1:
                return Constants.TIMBER2;
            case 2:
                return Constants.TIMBER3;
            case 3:
                return Constants.TIMBER4;
            case 4:
                return Constants.TIMBER5;
            case 5:
                return Constants.TIMBER6;
            default:
                return Constants.TIMBER3;
        }
    }


}
