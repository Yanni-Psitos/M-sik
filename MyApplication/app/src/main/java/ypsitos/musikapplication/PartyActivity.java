package ypsitos.musikapplication;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

// Activity for an individual party
public class PartyActivity extends AppCompatActivity {

    private PartyPagerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_party);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        tabLayout.addTab(tabLayout.newTab().setText("Playlist"));
        tabLayout.addTab(tabLayout.newTab().setText("People"));
        mAdapter = new PartyPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(mAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setHorizontalScrollBarEnabled(true);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });


    }
    private String getInputData(InputStream inStream) throws IOException{
        StringBuilder builder = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inStream));
        String data;
        while((data=reader.readLine())!=null){
            builder.append(data);
        }
        reader.close();
        return builder.toString();
    }

    private class ThePartyTask extends AsyncTask<String,Void,ArrayList<Party>> {
        String data=null;
        @Override
        protected ArrayList<Party> doInBackground(String... params){

            ArrayList<Party>theParties = new ArrayList<Party>();
            String theLink=params[0];

            try {
                ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
                if (networkInfo != null && networkInfo.isConnected()) {
                    // the connection is available
                } else {
                    // the connection is not available

                }

                URL url = new URL(theLink);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                connection.connect();

                InputStream inStream = connection.getInputStream();
                data = getInputData(inStream);


                JSONObject dataObject = new JSONObject(data);



            }catch(MalformedURLException e){
                e.printStackTrace();
            }catch(IOException ex){
                ex.printStackTrace();
            }catch(JSONException je){
                je.printStackTrace();
            }


            return theParties;
        }

        @Override
        protected void onPostExecute(ArrayList<Party> data) {
            super.onPostExecute(data);
            if(data==null){
                Toast.makeText(PartyActivity.this, "ERROR: no connectino found ", Toast.LENGTH_SHORT).show();
            }
            mAdapter.notifyDataSetChanged();
        }
    }
}
