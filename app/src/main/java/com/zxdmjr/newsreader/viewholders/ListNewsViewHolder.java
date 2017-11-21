package com.zxdmjr.newsreader.viewholders;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.github.curioustechizen.ago.RelativeTimeTextView;
import com.squareup.picasso.Picasso;
import com.zxdmjr.newsreader.R;
import com.zxdmjr.newsreader.constants.ISO8601DateParser;
import com.zxdmjr.newsreader.interfaces.ItemClickListener;
import com.zxdmjr.newsreader.models.Article;

import java.text.ParseException;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by eict on 11/21/17.
 */

public class ListNewsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    @BindView(R.id.civ_article_image)
    CircleImageView civArticleImage;

    @BindView(R.id.tv_article_title)
    TextView tvArticleTitle;

    @BindView(R.id.rtv_article_time)
    RelativeTimeTextView rtvArticleTime;

    private ItemClickListener itemClickListener;

    public ListNewsViewHolder(View view) {
        super(view);

        ButterKnife.bind(this, view);
        view.setOnClickListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public void bind(Context context, Article article){

        Picasso.with(context).load(article.getUrlToImage()).into(civArticleImage);

        if(article.getTitle().length() > 65){

            tvArticleTitle.setText(article.getTitle().substring(0,65)+"...");

        } else{
            tvArticleTitle.setText(article.getTitle());
        }

        Date date = null;
        try {

            Log.d("ListNewsViewHolder", "bind: "+article.getPublishedAt());
            if(article.getPublishedAt() != null)
                date = ISO8601DateParser.parse(article.getPublishedAt());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if(date != null)
            rtvArticleTime.setReferenceTime(date.getTime());

    }

    @Override
    public void onClick(View v) {

        itemClickListener.onClick(v, getAdapterPosition(), false);

    }
}
