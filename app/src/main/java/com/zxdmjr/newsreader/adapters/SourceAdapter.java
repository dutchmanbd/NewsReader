package com.zxdmjr.newsreader.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;
import com.zxdmjr.newsreader.R;
import com.zxdmjr.newsreader.activities.ListNewsActivity;
import com.zxdmjr.newsreader.constants.Common;
import com.zxdmjr.newsreader.constants.Constant;
import com.zxdmjr.newsreader.interfaces.IconBetterIdeaService;
import com.zxdmjr.newsreader.interfaces.ItemClickListener;
import com.zxdmjr.newsreader.models.IconBetterIdea;
import com.zxdmjr.newsreader.models.Source;
import com.zxdmjr.newsreader.models.WebSite;
import com.zxdmjr.newsreader.viewholders.SourceViewHolder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by eict on 11/20/17.
 */

public class SourceAdapter extends RecyclerView.Adapter<SourceViewHolder> {

    private Context context;
    private WebSite webSite;

    public SourceAdapter(Context context, WebSite webSite) {
        this.context = context;
        this.webSite = webSite;

    }

    @Override
    public SourceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.single_source_item, parent, false);

        return new SourceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SourceViewHolder holder, int position) {

        final Source source = webSite.getSources().get(position);

        holder.bind(context, source);

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {

                Intent intent = new Intent(context, ListNewsActivity.class);
                intent.putExtra(Constant.INTENT_SOURCE_ID, source.getId());
                intent.putExtra(Constant.INTENT_SOURCE_SORT_BY, source.getSortBysAvailable().get(0));
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return webSite.getSources().size();
    }
}
