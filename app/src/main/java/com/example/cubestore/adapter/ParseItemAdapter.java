package com.example.cubestore.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cubestore.BlogActivity;
import com.example.cubestore.R;
import com.example.cubestore.model.ParseItemModel;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ParseItemAdapter extends RecyclerView.Adapter<ParseItemAdapter.ViewHolder> {

    private ArrayList<ParseItemModel> parseItemModelArrayList;
    private Context context;

    public ParseItemAdapter(List<ParseItemModel> parseItemModelArrayList, Context context) {
        this.parseItemModelArrayList = (ArrayList<ParseItemModel>) parseItemModelArrayList;
        this.context = context;
    }


    @NotNull
    @Override
    public ParseItemAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.parse_item, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ParseItemAdapter.ViewHolder holder, int position) {
        ParseItemModel parseItemModel = parseItemModelArrayList.get(position);
        holder.title_txt.setText(parseItemModel.getTitle());
        holder.subTitle_txt.setText(parseItemModel.getSubtitle());
        Picasso.get().load(parseItemModel.getImageLink()).into(holder.imageView);

        Log.e("TAG", "onBindViewHolder: "+parseItemModelArrayList.toString() );


    }

    @Override
    public int getItemCount() {
        return parseItemModelArrayList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView imageView;
        TextView title_txt,subTitle_txt, price_txt;


        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_id);
            title_txt = itemView.findViewById(R.id.title_txt_id);
            subTitle_txt = itemView.findViewById(R.id.subTitle_txt_id);
            price_txt = itemView.findViewById(R.id.price_txt_id);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            ParseItemModel parseItem = parseItemModelArrayList.get(position);
            Intent intent = new Intent(context, BlogActivity.class);
            intent.putExtra("title",parseItem.getTitle().toString());
            intent.putExtra("image",parseItem.getImageLink().toString());
            intent.putExtra("postlink",parseItem.getPostLink().toString());
            intent.putExtra("subtitle",parseItem.getSubtitle().toString());
            context.startActivity(intent);

        }
    }

}
