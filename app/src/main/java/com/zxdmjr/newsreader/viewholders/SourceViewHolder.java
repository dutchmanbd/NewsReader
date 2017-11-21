package com.zxdmjr.newsreader.viewholders;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.zxdmjr.newsreader.R;
import com.zxdmjr.newsreader.constants.Common;
import com.zxdmjr.newsreader.interfaces.IconBetterIdeaService;
import com.zxdmjr.newsreader.interfaces.ItemClickListener;
import com.zxdmjr.newsreader.models.IconBetterIdea;
import com.zxdmjr.newsreader.models.Source;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by eict on 11/20/17.
 */

public class SourceViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    @BindView(R.id.tv_source_name)
    TextView tvSourceName;

    @BindView(R.id.civ_source_image)
    CircleImageView civSourceImage;

    private ItemClickListener itemClickListener;
    private IconBetterIdeaService iconBetterIdeaService;

    public SourceViewHolder(View view) {
        super(view);

        iconBetterIdeaService = Common.getIconBetterIdeaService();
        ButterKnife.bind(this, view);
        view.setOnClickListener(this);

    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public void bind(final Context context, Source source){

        tvSourceName.setText(source.getName());

        StringBuilder iconBetterApi = new StringBuilder("https://icons.better-idea.org/allicons.json?url=");

        iconBetterApi.append(source.getUrl());

        Log.d("SourceViewHolder", "bind: "+iconBetterApi.toString());

        iconBetterIdeaService.getIconUrl(iconBetterApi.toString())
                .enqueue(new Callback<IconBetterIdea>() {
                    @Override
                    public void onResponse(Call<IconBetterIdea> call, Response<IconBetterIdea> response) {

                        if(response.body() != null && response.body().getIcons().size() > 0){

                            Picasso.with(context)
                                    .load(response.body().getIcons().get(0).getUrl())
                                    .into(civSourceImage);
                        }
                    }
                    @Override
                    public void onFailure(Call<IconBetterIdea> call, Throwable t) {

                    }
                });



    }

    @Override
    public void onClick(View v) {

        itemClickListener.onClick(v, getAdapterPosition(), false);
    }
}
