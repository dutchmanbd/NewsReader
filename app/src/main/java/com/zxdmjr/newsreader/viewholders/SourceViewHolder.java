package com.zxdmjr.newsreader.viewholders;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.zxdmjr.newsreader.R;
import com.zxdmjr.newsreader.interfaces.ItemClickListener;
import com.zxdmjr.newsreader.models.Source;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by eict on 11/20/17.
 */

public class SourceViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    @BindView(R.id.tv_source_name)
    TextView tvSourceName;

    @BindView(R.id.civ_source_image)
    CircleImageView civSourceImage;

    private ItemClickListener itemClickListener;

    public SourceViewHolder(View view) {
        super(view);

        ButterKnife.bind(this, view);

        view.setOnClickListener(this);

    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public void bind(Context context, Source source){

        tvSourceName.setText(source.getName());



    }

    @Override
    public void onClick(View v) {

        itemClickListener.onClick(v, getAdapterPosition(), false);
    }
}
