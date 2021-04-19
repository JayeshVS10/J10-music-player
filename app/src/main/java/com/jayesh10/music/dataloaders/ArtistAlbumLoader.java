

package com.jayesh10.music.dataloaders;

import android.content.Context;

import com.jayesh10.music.models.Album;

import java.util.ArrayList;
import java.util.List;

public class ArtistAlbumLoader {

    public static ArrayList<Album> getAlbumsForArtist(Context context, long artistID) {

        if (artistID == -1)
            return null;

        List<Album> allAlbums = AlbumLoader.getAllAlbums(context);
        ArrayList<Album> artistAlbums = new ArrayList<>();
        for (Album album: allAlbums) {
            if (album.artistId == artistID) {
                artistAlbums.add(album);
            }
        }
        return artistAlbums;
    }

}
