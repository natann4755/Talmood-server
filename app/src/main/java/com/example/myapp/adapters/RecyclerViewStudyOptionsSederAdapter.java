package com.example.myapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.model.shas_masechtot.Seder;
import com.example.myapp.R;

import java.util.ArrayList;

public class RecyclerViewStudyOptionsSederAdapter extends RecyclerView.Adapter<RecyclerViewStudyOptionsSederAdapter.Holder> {

    private Context mContext;
    private ArrayList<Seder> mSederOptions;
    private RecyclerViewStudyOptionsMasechetAdapter.ListenerCreateTypeOfStudy listenerCreateTypeOfStudy;

    public RecyclerViewStudyOptionsSederAdapter(Context mContext, ArrayList<Seder> sederOptions, RecyclerViewStudyOptionsMasechetAdapter.ListenerCreateTypeOfStudy listenerCreateTypeOfStudy) {
        this.mContext = mContext;
        this.mSederOptions = sederOptions;
        this.listenerCreateTypeOfStudy = listenerCreateTypeOfStudy;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_study_options, parent, false);
        return new RecyclerViewStudyOptionsSederAdapter.Holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.setHolder(mSederOptions.get(position));
    }

    @Override
    public int getItemCount() {
        return mSederOptions.size();
    }

    public void closeOthersSdarim() {
        for (Seder seder : mSederOptions) {
            seder.setOpen(false);
        }
    }


    public class Holder extends RecyclerView.ViewHolder {
        private TextView study;
        private Seder seder;
        private LinearLayout mRvLinearLayout;
        private RecyclerView mMasechtotRecyclerView;

        public Holder(@NonNull View itemView) {
            super(itemView);
            study = itemView.findViewById(R.id.item_rv_study_TV);
            mRvLinearLayout = itemView.findViewById(R.id.item_rv_study_rv_LL);
            mMasechtotRecyclerView = itemView.findViewById(R.id.item_rv_RV);

            itemView.setOnClickListener(v -> {
                if (seder.isOpen()) {
                    seder.setOpen(false);
                    mRvLinearLayout.setVisibility(View.GONE);
                } else {
                    closeOthersSdarim();
                    seder.setOpen(true);
                    notifyDataSetChanged();
                }
            });
        }

        public void setHolder(Seder seder) {
            this.seder = seder;
            study.setText(seder.getName());
            if (seder.isOpen()) {
                mRvLinearLayout.setVisibility(View.VISIBLE);
                openMasechtotRecyclerView();
            } else {
                mRvLinearLayout.setVisibility(View.GONE);
            }
        }

        private void openMasechtotRecyclerView() {
            mRvLinearLayout.setVisibility(View.VISIBLE);
            mMasechtotRecyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
            RecyclerViewStudyOptionsMasechetAdapter mRecyclerViewStudyOptionsMasechetAdapter = new RecyclerViewStudyOptionsMasechetAdapter(mContext, seder.getMasechtot(), listenerCreateTypeOfStudy);
            mMasechtotRecyclerView.setAdapter(mRecyclerViewStudyOptionsMasechetAdapter);
        }
    }
}
