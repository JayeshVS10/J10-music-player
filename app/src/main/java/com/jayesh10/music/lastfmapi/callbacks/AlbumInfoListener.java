

package com.jayesh10.music.lastfmapi.callbacks;

import com.jayesh10.music.lastfmapi.models.LastfmAlbum;

public interface AlbumInfoListener {

    void albumInfoSuccess(LastfmAlbum album);

    void albumInfoFailed();

}
