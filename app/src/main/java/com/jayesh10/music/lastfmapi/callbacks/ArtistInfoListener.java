

package com.jayesh10.music.lastfmapi.callbacks;

import com.jayesh10.music.lastfmapi.models.LastfmArtist;

public interface ArtistInfoListener {

    void artistInfoSucess(LastfmArtist artist);

    void artistInfoFailed();

}
