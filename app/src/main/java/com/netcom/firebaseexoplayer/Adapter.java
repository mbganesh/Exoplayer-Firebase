package com.netcom.firebaseexoplayer;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.Viewholder>{

    private Class<VideoList> context;
    private List<ModelClass> modelClassList;


    public Adapter(List<ModelClass> modelClassList, Class<VideoList> videoListClass) {

        this.modelClassList = modelClassList;
        this.context = videoListClass;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_row , viewGroup , false);
        return new Viewholder( view );

    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder viewholder, int position) {
        int resource = modelClassList.get(position).getImageView();
        String title = String.valueOf(modelClassList.get(position).getTextView());
        String path = modelClassList.get(position).getLink();

        viewholder.image.setImageResource(resource);
        viewholder.text.setText(title);

        viewholder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(v.getContext(), NewPlayer.class);
                i.putExtra("Video_Link" , path);
                v.getContext().startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return modelClassList.size();
    }

    class Viewholder extends RecyclerView.ViewHolder  {
        TextView text ;
        ImageView image ;

        public Viewholder(@NonNull View itemView ) {
            super(itemView);
            image = itemView.findViewById(R.id.imageId);
            text = itemView.findViewById(R.id.titleId);

        }
    }
}
