

package com.jayesh10.music.fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jayesh10.music.R;
import com.jayesh10.music.dataloaders.ArtistLoader;
import com.jayesh10.music.lastfmapi.LastFmClient;
import com.jayesh10.music.lastfmapi.callbacks.ArtistInfoListener;
import com.jayesh10.music.lastfmapi.models.ArtistQuery;
import com.jayesh10.music.lastfmapi.models.LastfmArtist;
import com.jayesh10.music.models.Artist;
import com.jayesh10.music.subfragments.ArtistTagFragment;
import com.jayesh10.music.utils.Constants;
import com.jayesh10.music.widgets.MultiViewPager;

public class ArtistBioFragment extends Fragment {

    long artistID = -1;

    public static ArtistBioFragment newInstance(long id) {
        ArtistBioFragment fragment = new ArtistBioFragment();
        Bundle args = new Bundle();
        args.putLong(Constants.ARTIST_ID, id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            artistID = getArguments().getLong(Constants.ARTIST_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(
                R.layout.fragment_artist_bio, container, false);

        Artist artist = ArtistLoader.getArtist(getActivity(), artistID);

        LastFmClient.getInstance(getActivity()).getArtistInfo(new ArtistQuery(artist.name), new ArtistInfoListener() {
            @Override
            public void artistInfoSucess(LastfmArtist artist) {

            }

            @Override
            public void artistInfoFailed() {
            }
        });

        final MultiViewPager pager = (MultiViewPager) rootView.findViewById(R.id.tagspager);

        final FragmentStatePagerAdapter adapter = new FragmentStatePagerAdapter(getActivity().getSupportFragmentManager()) {

            @Override
            public int getCount() {
                return 20;
            }

            @Override
            public Fragment getItem(int position) {
                return ArtistTagFragment.newInstance(position);
            }

        };
        pager.setAdapter(adapter);

        return rootView;

    }

}
