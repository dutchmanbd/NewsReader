package com.zxdmjr.newsreader.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zxdmjr.newsreader.R;
import com.zxdmjr.newsreader.interfaces.ItemClickListener;
import com.zxdmjr.newsreader.models.Source;
import com.zxdmjr.newsreader.models.WebSite;
import com.zxdmjr.newsreader.viewholders.SourceViewHolder;

import java.util.List;

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

            }
        });

    }

    @Override
    public int getItemCount() {
        return webSite.getSources().size();
    }
}
