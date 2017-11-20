package com.zxdmjr.newsreader;


import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;
import com.zxdmjr.newsreader.adapters.SourceAdapter;
import com.zxdmjr.newsreader.constants.Common;
import com.zxdmjr.newsreader.interfaces.NewsService;
import com.zxdmjr.newsreader.models.WebSite;

import butterknife.BindView;
import butterknife.ButterKnife;
import dmax.dialog.SpotsDialog;
import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.rv_list_sources)
    RecyclerView rvSources;

    private NewsService newsService;
    private SourceAdapter adapter;
    private AlertDialog dialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        //Init Cache
        Paper.init(this);

        //Init service
        newsService = Common.getNewsService();

        //Init Views
        rvSources.setHasFixedSize(true);
        rvSources.setLayoutManager(new LinearLayoutManager(this));

        dialog = new SpotsDialog(this);

        loadWebsiteSource(false);
    }

    private void loadWebsiteSource(boolean isRefreshed) {

        if(!isRefreshed){

            String cache = Paper.book().read("cache");

            if(cache != null && !cache.isEmpty()){   //If data available

                WebSite webSite = new Gson().fromJson(cache, WebSite.class);

                adapter = new SourceAdapter(getBaseContext(), webSite);
                adapter.notifyDataSetChanged();

                rvSources.setAdapter(adapter);

            } else{

                dialog.show();

                newsService.getSources().enqueue(new Callback<WebSite>() {
                    @Override
                    public void onResponse(Call<WebSite> call, Response<WebSite> response) {

                        dialog.dismiss();

                        if(response.body() != null){

                            adapter = new SourceAdapter(getBaseContext(), response.body());
                            adapter.notifyDataSetChanged();
                            rvSources.setAdapter(adapter);

                            Paper.book().write("cache", new Gson().toJson(response.body()));
                        }
                    }

                    @Override
                    public void onFailure(Call<WebSite> call, Throwable t) {

                    }
                });

            }

        }


    }
}
