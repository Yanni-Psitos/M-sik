package ypsitos.musikapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

// Activity that displays all your parties
public class MainActivity extends AppCompatActivity {

    private ArrayAdapter<Party> mAdapter;
    private ListView mListView;
    private ArrayList<Party> mPartyArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mListView = (ListView) findViewById(R.id.party_listview);
        TextView partyID = (TextView) findViewById(R.id.partyID);
        TextView partyAddress = (TextView) findViewById(R.id.partyAddress);

        mPartyArrayList = new ArrayList<>();

        Party party = new Party(1, "master", "123 Street", "3/25", "10:00PM");
        mPartyArrayList.add(party);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent toDetailActivity = new Intent(MainActivity.this,DetailActivity.class);
                startActivity(toDetailActivity);

            }
        });

        mAdapter = new ArrayAdapter<Party>(this, R.layout.cardview_item, mPartyArrayList);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent partyIntent = new Intent(MainActivity.this, PartyActivity.class);
                startActivity(partyIntent);
            }
        });
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

    }

