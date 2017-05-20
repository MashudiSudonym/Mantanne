package com.herokuapp.tif6.mantanku.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.herokuapp.tif6.mantanku.R;
import com.herokuapp.tif6.mantanku.activity.DetailActivity;
import com.herokuapp.tif6.mantanku.models.ProjectRepo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sudonym on 19/05/2017.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private Context context;
    private List<ProjectRepo> results;

    public RecyclerViewAdapter(Context context, List<ProjectRepo> results) {
        this.context = context;
        this.results = results;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_view, parent, false);
        ViewHolder holder = new ViewHolder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ProjectRepo result = results.get(position);
        holder.textViewId.setText(Integer.toString(result.getId()));
        holder.textViewNama.setText(result.getName());
//        holder.textViewAlasan.setText(result.getAlasan());
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.textId) TextView textViewId;
        @BindView(R.id.textNama) TextView textViewNama;
//        @BindView(R.id.textAlasan) TextView textViewAlasan;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            String id = textViewId.getText().toString();
//            String nama = textViewNama.getText().toString();
//            String alasan = textViewAlasan.getText().toString();
//
            Intent i = new Intent(context, DetailActivity.class);
            i.putExtra("id", id);
//            i.putExtra("nama", nama);
//            i.putExtra("alasan", alasan);
            context.startActivity(i);
        }
    }
}
