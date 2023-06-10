package com.example.myprojectstudy.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myprojectstudy.api.model.SeeAll;
import com.example.myprojectstudy.databinding.ViewHolderSeeAllBinding;
import com.squareup.picasso.Picasso;

public class SeeAllAdapter extends ListAdapter<SeeAll, SeeAllAdapter.SeeAllViewHolder> {

    public SeeAllAdapter() {
        super(new DiffUtil.ItemCallback<SeeAll>() {
            @Override
            public boolean areItemsTheSame(@NonNull SeeAll oldItem, @NonNull SeeAll newItem) {
                return oldItem == newItem;
            }

            @Override
            public boolean areContentsTheSame(@NonNull SeeAll oldItem, @NonNull SeeAll newItem) {
                return oldItem.getId() == newItem.getId();
            }
        });
    }


    @NonNull
    @Override
    public SeeAllViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ViewHolderSeeAllBinding binding = ViewHolderSeeAllBinding.inflate(layoutInflater, parent,true);
        return new SeeAllViewHolder(binding);
    }


    @Override
    public void onBindViewHolder(@NonNull SeeAllViewHolder holder, int position) {
        SeeAll item = getItem(position);
        holder.bind(item);
    }

    // Create view holder
    public static class SeeAllViewHolder extends RecyclerView.ViewHolder{
        private final  ViewHolderSeeAllBinding itemBinding;

        public SeeAllViewHolder(ViewHolderSeeAllBinding itemBinding) {
            super(itemBinding.getRoot());
            this.itemBinding = itemBinding;
        }

        public void bind(SeeAll seeAll) {
            Picasso.get().load(seeAll.getImageUrl()).into(itemBinding.imageLogo);

            itemBinding.textTitle.setText(seeAll.getTitle());
            itemBinding.textBody.setText(seeAll.getDescription());

        }
    }

}
