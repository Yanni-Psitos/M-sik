package ypsitos.musikapplication;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by David on 3/12/16.
 */
public class PartyRecyclerViewAdapter extends RecyclerView.Adapter<PartyRecyclerViewAdapter.ViewHolder> {
    
    ArrayList<Party> mListOfParties;

    public PartyRecyclerViewAdapter(ArrayList<Party> arrayOfParties) {
        mListOfParties = arrayOfParties;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public ViewHolder(View itemView) {
            super(itemView);
        }
        
        // Clicking a card launches the Party Activity
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(v.getContext(),PartyActivity.class);
            intent.putExtra("ID", mListOfParties.get(getLayoutPosition()).getId());
            v.getContext().startActivity(intent);
        }
    }

    @Override
    public PartyRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.cardview_item,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(PartyRecyclerViewAdapter.ViewHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return mListOfParties.size();
    }
}
