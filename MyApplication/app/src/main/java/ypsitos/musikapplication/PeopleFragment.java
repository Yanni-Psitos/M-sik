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
public class PeopleFragment extends Fragment {

    private ListView peopleListView;
    private ArrayList<Person> arrayList;
    private ArrayAdapter<Person> mAdapter;

    public PeopleFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_people, container, false);
        Person p = new Person("Rick","8675309");
        Person a = new Person("Bob","5555555");
        Person c = new Person("Bert","1234567");

        arrayList = new ArrayList<>();
        arrayList.add(p);
        arrayList.add(a);
        arrayList.add(c);

        peopleListView = (ListView)rootView.findViewById(R.id.people_listview);
        mAdapter = new ArrayAdapter<Person>(getContext(),android.R.layout.simple_list_item_1,arrayList);
        peopleListView.setAdapter(mAdapter);
        return rootView;
    }

}
