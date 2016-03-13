package ypsitos.musikapplication;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
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
import java.util.HashMap;
import java.util.Map;

import hod.api.hodclient.HODApps;
import hod.api.hodclient.HODClient;
import hod.api.hodclient.IHODClientCallback;
import hod.response.parser.HODResponseParser;
import hod.response.parser.PredictResponse;

// Activity that displays all your parties
public class MainActivity extends AppCompatActivity implements IHODClientCallback {

    private PartyAdapter mAdapter;
    private ListView mListView;
    private ArrayList<Party> mPartyArrayList;
    HODClient hodClient;
    HODResponseParser parser;
    String hodApp = "";
    String mScore;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        textView = (TextView) findViewById(R.id.stuff);
        hodClient = new HODClient("29d2d9d9-1ab8-4e47-b008-2d5d44073df4", this);
        parser = new HODResponseParser();
        mListView = (ListView) findViewById(R.id.party_listview);

        mPartyArrayList = new ArrayList<>();

        Party party = new Party(1, "White Tiger Party", "8675309");
        mPartyArrayList.add(party);

        Button updatePartyList = (Button)findViewById(R.id.updatePartyList);
        updatePartyList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"Making call to Update",Toast.LENGTH_SHORT).show();
            }
        });
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent toDetailActivity = new Intent(MainActivity.this, DetailActivity.class);
                startActivity(toDetailActivity);

            }
        });

        mAdapter = new PartyAdapter(MainActivity.this, mPartyArrayList);

        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent partyIntent = new Intent(MainActivity.this, PartyActivity.class);
                startActivity(partyIntent);
            }
        });

        CallPrediction();
    }

    private void CallPrediction() {
        hodApp = HODApps.PREDICT;
        Map<String, Object> params = new HashMap<>();
        params.put("url", "http://www.qcalendar.com/hptest/data.csv"); // /Users/David/workspace/ADI/projects/M-sik/MyApplication/app/src/main/res/raw/d
        params.put("service_name", "predictpoints");
        hodClient.PostRequest(params, hodApp, HODClient.REQ_MODE.ASYNC);
    }

    // HOD callback part
    @Override
    public void onErrorOccurred(String errorMsg) {
        // preint or handle error
    }

    @Override
    public void requestCompletedWithJobID(String response) {
        String jobID = parser.ParseJobID(response);
        if (jobID.length() > 0) {
            hodClient.GetJobResult(jobID);
        }
    }

    @Override
    public void requestCompletedWithContent(String response) {
        if (hodApp.equals(HODApps.PREDICT)) {
            PredictResponse resp = parser.ParsePredictResponse(response);
            if (resp.values.size() > 0) {
                for (PredictResponse.Values val : resp.values) {
                    mScore = val.row.get(6);
                    textView.setText(mScore);
                }
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
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

    private class UpdatePartyList extends AsyncTask<String,Void,ArrayList<Party>> {
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
                Toast.makeText(MainActivity.this, "ERROR: no connectino found ", Toast.LENGTH_SHORT).show();
            }
           mAdapter.notifyDataSetChanged();
        }
    }
}

