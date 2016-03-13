package ypsitos.musikapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by David on 3/12/16.
 */
public class PlaylistFragment extends Fragment {

    ListView mPlaylistListView;
    ArrayList<Song> mSongArrayList;
    ArrayAdapter<Song> mSongArrayAdapter;

    public PlaylistFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
