package com.rika.kalenderevent;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rika.kalenderevent.Model.Event;

import java.util.List;

/**
 * Created by User on 27/12/2017.
 */

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventHolder> {
    private String mJudul[];
    private String mKet[];
    private List<Event> mData;
    private EventListener mListener;

    public EventAdapter(String judul[], String ket[], EventListener listener) {
        mJudul = judul;
        mKet = ket;
        mListener = listener;
    }

    public EventAdapter(List<Event> data, EventListener listener) {
        mData = data;
        mListener = listener;
    }

    interface EventListener {
        void onClick(int position);
    }


    @Override
    public EventHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_event, parent, false);
        return new EventHolder(view);
    }

    @Override
    public void onBindViewHolder(EventHolder holder, int position) {
        RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) holder.mItemView.getLayoutParams();
        if (position == 0) {
            params.topMargin = params.bottomMargin;
        } else {
            params.topMargin = 0;
        }
        String judul = mData.get(position).getNama();
        String ket = mData.get(position).getKeterangan();
        holder.setEvent(judul, ket);
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public String getJudul(int position) {
        return mJudul[position];
    }

    public class EventHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView textView1;
        private TextView textView2;
        private View mItemView;

        public EventHolder(View itemView) {
            super(itemView);
            textView1 = (TextView) itemView.findViewById(R.id.judul);
            textView2 = (TextView) itemView.findViewById(R.id.keterangan);
            mItemView = itemView;
            mItemView.setOnClickListener(this);

        }

        public void setEvent (String judul, String ket) {
            textView1.setText(judul);
            textView2.setText(ket);
        }

        @Override
        public void onClick(View v) {

            mListener.onClick(getAdapterPosition());
        }

    }

}
