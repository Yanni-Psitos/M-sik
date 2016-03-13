package ypsitos.musikapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by David on 3/13/16.
 */
public class PartyAdapter extends ArrayAdapter<Party> {
    private ArrayList<Party> mParties;

    public PartyAdapter(Context context, ArrayList<Party> parties) {
        super(context, -1);

        mParties = new ArrayList<>();
        mParties.addAll(parties);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemLayout = inflater.inflate(R.layout.party_cardview_item, parent, false);

        TextView partyNameTextView = (TextView) itemLayout.findViewById(R.id.partyName);
        TextView partyCreatorTextView = (TextView) itemLayout.findViewById(R.id.partyCreator);

        Party party = mParties.get(position);

        partyNameTextView.setText(party.getName());
        partyCreatorTextView.setText(party.getCreator());

        return itemLayout;
    }

    @Override
    public int getCount() {
        return mParties.size();
    }
}
