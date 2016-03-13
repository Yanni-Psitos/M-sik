package ypsitos.musikapplication;

import android.content.Context;
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
    Context mContext;

    public PlaylistFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_playlist, container, false);
        Song s = new Song("Billy Joel","Storm Front","We didnt start the fire");
        Song a = new Song("Guns N' Roses","The Spaghetti Incident","Since I Dont have you");
        Song c = new Song("American Idiot","American Idiot","GreenDay");
        mSongArrayList = new ArrayList<Song>();
        mSongArrayList.add(s);

        mSongArrayList = new ArrayList<>();
        mSongArrayList.add(s);
        mSongArrayList.add(a);
        mSongArrayList.add(c);

        mPlaylistListView = (ListView)rootView.findViewById(R.id.playlist_listview);
        mSongArrayAdapter = new ArrayAdapter<Song>(getContext(),android.R.layout.simple_list_item_1,mSongArrayList);
        mPlaylistListView.setAdapter(mSongArrayAdapter);
        return rootView;

    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }
}
