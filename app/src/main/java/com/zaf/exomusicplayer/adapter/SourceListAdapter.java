package com.zaf.exomusicplayer.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zaf.exomusicplayer.R;
import com.zaf.exomusicplayer.databinding.SourceListItemBinding;
import com.zaf.exomusicplayer.model.SourceListItem;

import java.util.ArrayList;

public class SourceListAdapter extends RecyclerView.Adapter<SourceListAdapter.SourceListViewHolder>{

    private SourceListItemBinding sourceListItemBinding;
    final private SourceListAdapterListItemClickListener mOnClickListener;
    private ArrayList<SourceListItem> sourceItemList;

    public SourceListAdapter(SourceListAdapterListItemClickListener mOnClickListener,
                             ArrayList<SourceListItem> sourceItemList,
                             SourceListItemBinding sourceListItemBinding) {

        this.mOnClickListener = mOnClickListener;
        this.sourceItemList = sourceItemList;
        this.sourceListItemBinding = sourceListItemBinding;
    }

    @NonNull
    @Override
    public SourceListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.source_list_item, viewGroup, false);

        return new SourceListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SourceListViewHolder sourceListViewHolder, int position) {
        sourceListViewHolder.itemDescription.setText(sourceItemList.get(position).getName());
        sourceListViewHolder.itemImage.setImageResource(sourceItemList.get(position).getIcon());
    }

    @Override
    public int getItemCount() {
        if (null == sourceItemList) return 0;
        return sourceItemList.size();
    }

    public interface SourceListAdapterListItemClickListener {
        void onListItemClick(int item);
    }

    public class SourceListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView itemImage;
        TextView itemDescription;

        private SourceListViewHolder(View itemView) {
            super(itemView);

            itemImage = sourceListItemBinding.sourceItemIcon;
            itemDescription = sourceListItemBinding.sourceItemText;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();
            mOnClickListener.onListItemClick(adapterPosition);
        }
    }
}
