package com.company.testproject.ui.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.company.testproject.R;
import com.company.testproject.utils.Const;

import java.util.ArrayList;
import java.util.List;

public class PointListAdapter extends RecyclerView.Adapter<PointListAdapter.ViewHolder> {

    private Context context;
    private List<String> dataList = new ArrayList<>();
    private PointListAdapterListener listener;

    public PointListAdapter(Context context, PointListAdapterListener listener) {
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_point, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        if (position == dataList.size() - 1) {
            listener.loadNextPointData();
        }
        holder.constraintLayout.setAnimation(AnimationUtils.loadAnimation(context, R.anim.show_point_item_animation));
        holder.textTextView.setText(dataList.get(position));
        Glide.with(context)
                .load(Uri.parse(Const.DEFAULT_IMAGE_URL))
                .into(holder.imageView);
        holder.constraintLayout.setOnClickListener((View v) -> {
            listener.onPointListItemClicked(position);
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public void updateDataList(List<String> dataList) {
        this.dataList.addAll(dataList);
        notifyItemChanged(dataList.size());
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView textTextView;
        private ConstraintLayout constraintLayout;

        ViewHolder(@NonNull View view) {
            super(view);
            constraintLayout = itemView.findViewById(R.id.cl_item_point);
            imageView = itemView.findViewById(R.id.iv_image);
            textTextView = itemView.findViewById(R.id.tv_text);
        }
    }
}
