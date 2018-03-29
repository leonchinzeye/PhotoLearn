package com.mtech.parttimeone.photolearn.Adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.mtech.parttimeone.photolearn.R;
import com.mtech.parttimeone.photolearn.bo.ItemBO;
import com.mtech.parttimeone.photolearn.dummyModel.Item;

import java.util.List;

/**
 * Created by Zhikai on 17/3/2018.
 */

public class ItemRecyclerAdapter extends RecyclerView.Adapter<ItemRecyclerAdapter.ItemViewHolder> {
    private List<ItemBO> ItemList;

    public ItemRecyclerAdapter(List<ItemBO> itemList) {
        ItemList = itemList;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_layout, parent, false);

        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
//        holder.title.setText(ItemList.get(position).getTitle());
//        holder.creator.setText(ItemList.get(position).getCreator());
//        holder.location.setText(ItemList.get(position).getLocation());
//        holder.date.setText(ItemList.get(position).getDate());
//        holder.description.setText(ItemList.get(position).getDescription());

        holder.title.setText(ItemList.get(position).getItemtitle());
        //holder.creator.setText(ItemList.get(position).getUserID());
        //holder.location.setText(ItemList.get(position).getLocation());
        //holder.date.setText(ItemList.get(position).getDate());
        holder.description.setText(ItemList.get(position).getItemDesc());

        String mDrawableName = ItemList.get(position).getPhotoURL();
        Context c = holder.dispimage.getContext();
        int resID = c.getResources().getIdentifier(mDrawableName , "drawable", c.getPackageName());
        holder.dispimage.setImageResource(resID);
        //holder.dispimage.ItemList.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return ItemList.size();

    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView creator;
        public TextView location;
        public TextView date;
        public TextView description;
        public ImageView dispimage;

        public ItemViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.item_title);
            creator = (TextView) view.findViewById(R.id.item_creator);
            date = (TextView) view.findViewById(R.id.item_date);
            location = (TextView) view.findViewById(R.id.item_location);
            description = (TextView) view.findViewById(R.id.item_description);
            dispimage = (ImageView) view.findViewById(R.id.item_image);
        }
    }
}
