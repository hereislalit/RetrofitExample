package com.vectorbrains.retrofitexample.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.vectorbrains.retrofitexample.R;
import com.vectorbrains.retrofitexample.appconstant.AppConstant;
import com.vectorbrains.retrofitexample.model.Owner;

import java.util.List;


public class OwnerRecyclerAdapter extends Adapter<OwnerRecyclerAdapter.OwnerViewHolder> {
    private List<Owner> ownerList;
    private RequestManager requestManager;
    private OnItemClickListener itemClickListener;

    public OwnerRecyclerAdapter(Context context, List<Owner> owners) {
        this.ownerList = owners;
        requestManager = Glide.with(context);
    }

    @Override
    public OwnerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_owner_grid_layout, null);
        return new OwnerViewHolder(v);
    }

    @Override
    public void onBindViewHolder(OwnerViewHolder holder, int position) {
        if (position >= 0 && position < ownerList.size()) {
            Owner owner = ownerList.get(position);
            holder.position = position;
            holder.tvOwnerName.setText(owner.getId() + AppConstant.SPACE_STRING + owner.getLogin());
            requestManager.load(owner.getAvatarUrl()).placeholder(R.drawable.ic_placeholder).crossFade().into(holder.ivOwnerImage);
        }
    }

    @Override
    public int getItemCount() {
        return ownerList != null ? ownerList.size() : 0;
    }

    public class OwnerViewHolder extends RecyclerView.ViewHolder {
        public ImageView ivOwnerImage;
        public TextView tvOwnerName;
        public int position;

        public OwnerViewHolder(View itemView) {
            super(itemView);
            ivOwnerImage = (ImageView) itemView.findViewById(R.id.imageview_owner_image);
            tvOwnerName = (TextView) itemView.findViewById(R.id.textview_owner_name);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (itemClickListener != null) {
                        itemClickListener.onItemClicked(position);
                    }
                }
            });
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.itemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClicked(int position);
    }
}
