package com.example.myapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myapp.R;
import java.util.ArrayList;

public class AllMasechtotAdapter extends RecyclerView.Adapter<AllMasechtotAdapter.ViewHolder> {
        private ArrayList<String> allMasechtot;
        private ListenerNameMasechet myNameMasechet;

        public AllMasechtotAdapter(ArrayList<String> allMasechtot ,ListenerNameMasechet myNameMasechet) {
            this.myNameMasechet = myNameMasechet;
            this.allMasechtot =allMasechtot;
        }
        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_one_masechet, parent, false);
            return new AllMasechtotAdapter.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.setHolder(allMasechtot.get(position));

        }

        @Override
        public int getItemCount() {
            return allMasechtot.size();
        }


        public class ViewHolder extends RecyclerView.ViewHolder  {

            TextView masechet;
            String myMasechet;

            ViewHolder(View itemView) {
                super(itemView);
                masechet = itemView.findViewById(R.id.masechet_text);
                masechet.setOnClickListener(v -> {
                    if(myNameMasechet!=null){
                        myNameMasechet.nameMasechet(myMasechet);
                    }
                });
            }

            public void setHolder(String nameMasechet) {
                masechet.setText(nameMasechet);
                myMasechet = nameMasechet;
            }
        }
        public interface ListenerNameMasechet {
            void nameMasechet(String nameMasechet);
        }
    }

