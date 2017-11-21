package com.zxdmjr.newsreader.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zxdmjr.newsreader.R;
import com.zxdmjr.newsreader.activities.DetailActivity;
import com.zxdmjr.newsreader.constants.Constant;
import com.zxdmjr.newsreader.interfaces.ItemClickListener;
import com.zxdmjr.newsreader.models.Article;
import com.zxdmjr.newsreader.models.News;
import com.zxdmjr.newsreader.viewholders.ListNewsViewHolder;

import java.util.List;

/**
 * Created by eict on 11/21/17.
 */

public class ListNewsAdapter extends RecyclerView.Adapter<ListNewsViewHolder> {

    private Context context;
    private List<Article> articles;

    public ListNewsAdapter(Context context, List<Article> articles) {
        this.context = context;
        this.articles = articles;
    }

    @Override
    public ListNewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.single_news_item, parent, false);

        return new ListNewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ListNewsViewHolder holder, int position) {

        final Article article = articles.get(position);

        holder.bind(context, article);

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {

                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra(Constant.INTENT_WEB_URL, article.getUrl());
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return articles.size();
    }
}
