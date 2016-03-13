package ypsitos.musikapplication;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
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

    public class ScoreAsyncTask extends AsyncTask<String, Void, APIResults> {
        @Override
        protected APIResults doInBackground(String... params) {
            String data ="";
            try {
                URL url = new URL(params[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream inStream = connection.getInputStream();
                data = getInputData(inStream);
            } catch (Throwable e) {
                e.printStackTrace();
            }

            Gson gson = new Gson();
            APIResults result = gson.fromJson(data, APIResults.class);
            return result;
        }

        private String getInputData(InputStream inStream) throws IOException {
            StringBuilder builder = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inStream));

            String data = null;

            while ((data = reader.readLine()) != null){
                builder.append(data);
            }

            reader.close();

            return builder.toString();
        }

        @Override
        protected void onPostExecute(APIResults results) {
            super.onPostExecute(results);
        }
    }
}
