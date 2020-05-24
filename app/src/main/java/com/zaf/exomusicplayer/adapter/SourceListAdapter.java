package com.zaf.exomusicplayer.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zaf.exomusicplayer.databinding.SourceListItemBinding;
import com.zaf.exomusicplayer.model.SourceListItem;

import java.util.ArrayList;

public class SourceListAdapter extends RecyclerView.Adapter<SourceListAdapter.SourceListViewHolder>{

    private SourceListItemBinding sourceListItemBinding;
    final private SourceListAdapterListItemClickListener mOnClickListener;
    private ArrayList<SourceListItem> sourceItemList;

    public SourceListAdapter(SourceListAdapterListItemClickListener mOnClickListener,
                             ArrayList<SourceListItem> sourceItemList) {

        this.mOnClickListener = mOnClickListener;
        this.sourceItemList = sourceItemList;
    }

    @NonNull
    @Override
    public SourceListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        sourceListItemBinding = SourceListItemBinding.inflate(layoutInflater, viewGroup, false);

        return new SourceListViewHolder(sourceListItemBinding.getRoot(), sourceListItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull SourceListViewHolder sourceListViewHolder, int position) {

        String songTitle = sourceItemList.get(position).getName();
        String songArtist = sourceItemList.get(position).getArtist();
        String songDisplayName = songTitle + " - " + songArtist;

        sourceListViewHolder.songImage.setImageResource(sourceItemList.get(position).getIcon());
        sourceListViewHolder.songTitle.setText(songDisplayName);
        sourceListViewHolder.songYear.setText(sourceItemList.get(position).getYear());
    }

    @Override
    public int getItemCount() {
        if (null == sourceItemList) return 0;
        return sourceItemList.size();
    }

    public interface SourceListAdapterListItemClickListener {
        void onListItemClick(String path);
    }

    public class SourceListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        SourceListItemBinding binding;

        ImageView songImage;
        TextView songTitle;
        TextView songYear;

        private SourceListViewHolder(View itemView, SourceListItemBinding binding) {
            super(itemView);
            this.binding = binding;

            songImage =  sourceListItemBinding.sourceItemIcon;
            songTitle = sourceListItemBinding.sourceItemText;
            songYear = sourceListItemBinding.sourceItemYear;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();
            String path = sourceItemList.get(adapterPosition).getPath();
            mOnClickListener.onListItemClick(path);
        }
    }
}
