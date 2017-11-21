package com.zxdmjr.newsreader.activities;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.flaviofaria.kenburnsview.KenBurnsView;
import com.github.florent37.diagonallayout.DiagonalLayout;
import com.squareup.picasso.Picasso;
import com.zxdmjr.newsreader.R;
import com.zxdmjr.newsreader.adapters.ListNewsAdapter;
import com.zxdmjr.newsreader.constants.Common;
import com.zxdmjr.newsreader.constants.Constant;
import com.zxdmjr.newsreader.interfaces.NewsService;
import com.zxdmjr.newsreader.models.Article;
import com.zxdmjr.newsreader.models.News;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListNewsActivity extends AppCompatActivity {

    @BindView(R.id.srf_list_news)
    SwipeRefreshLayout srfListNews;

    @BindView(R.id.dl_list_news)
    DiagonalLayout dlListNews;

    @BindView(R.id.kbv_top_image)
    KenBurnsView kbvTopImage;

    @BindView(R.id.tv_top_author)
    TextView tvTopAuthor;

    @BindView(R.id.tv_top_title)
    TextView tvTopTitle;

    @BindView(R.id.rv_news)
    RecyclerView rvNews;

    private AlertDialog dialog;

    private ListNewsAdapter adapter;

    private String source = "", sortBy = "", webHotUrl = "";

    private NewsService newsService;

    List<Article> articles;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_news);

        ButterKnife.bind(this);

        dialog = new SpotsDialog(this);

        //init service
        newsService = Common.getNewsService();

        rvNews.setHasFixedSize(true);
        rvNews.setLayoutManager(new LinearLayoutManager(this));

        //refresh

        srfListNews.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadNews(true);
            }
        });

        //diagonal click
        dlListNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Click to hot / latest news to read

                Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
                intent.putExtra(Constant.INTENT_WEB_URL, webHotUrl);
                startActivity(intent);
            }
        });

        if(getIntent() != null){

            source = getIntent().getStringExtra(Constant.INTENT_SOURCE_ID);
            sortBy = getIntent().getStringExtra(Constant.INTENT_SOURCE_SORT_BY);

            if(!source.isEmpty()){

                loadNews(false);

            }

        }

    }

    private void loadNews(boolean isRefreshed) {

        if(!isRefreshed){

            dialog.show();
            newsService.getNewsArticles(Constant.getApiUrl(source, sortBy))
                    .enqueue(new Callback<News>() {
                        @Override
                        public void onResponse(Call<News> call, Response<News> response) {
                            dialog.dismiss();

                            if(response.body() != null){

                                News news = response.body();

                                articles = news.getArticles();

                                if(articles != null && articles.size() > 0) {

                                    Picasso.with(getBaseContext()).load(articles.get(0).getUrlToImage()).into(kbvTopImage);

                                    tvTopAuthor.setText(articles.get(0).getAuthor());
                                    tvTopTitle.setText(articles.get(0).getTitle());

                                    webHotUrl = articles.get(0).getUrl();

                                    //remove first item
                                    articles.remove(0);
                                    setAdapter();
                                }

                            }


                        }

                        @Override
                        public void onFailure(Call<News> call, Throwable t) {

                        }
                    });

        } else{
            dialog.show();
            newsService.getNewsArticles(Constant.getApiUrl(source, sortBy))
                    .enqueue(new Callback<News>() {
                        @Override
                        public void onResponse(Call<News> call, Response<News> response) {
                            dialog.dismiss();

                            if(response.body() != null){

                                News news = response.body();

                                articles = news.getArticles();

                                if(articles != null && articles.size() > 0) {

                                    Picasso.with(getBaseContext()).load(articles.get(0).getUrlToImage()).into(kbvTopImage);

                                    tvTopAuthor.setText(articles.get(0).getAuthor());
                                    tvTopTitle.setText(articles.get(0).getTitle());

                                    webHotUrl = articles.get(0).getUrl();

                                    //remove first item
                                    articles.remove(0);
                                    setAdapter();
                                }

                            }


                        }

                        @Override
                        public void onFailure(Call<News> call, Throwable t) {

                        }
                    });

            srfListNews.setRefreshing(false);
        }

    }

    private void setAdapter() {

        adapter = new ListNewsAdapter(this, articles);
        adapter.notifyDataSetChanged();
        rvNews.setAdapter(adapter);

    }
}
