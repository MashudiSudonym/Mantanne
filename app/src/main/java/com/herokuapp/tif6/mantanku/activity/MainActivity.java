package com.herokuapp.tif6.mantanku.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.herokuapp.tif6.mantanku.R;
import com.herokuapp.tif6.mantanku.adapters.RecyclerViewAdapter;
import com.herokuapp.tif6.mantanku.models.ApiClient;
import com.herokuapp.tif6.mantanku.models.ApiValue;
import com.herokuapp.tif6.mantanku.models.ApiResult;
import com.herokuapp.tif6.mantanku.services.ServiceGenerator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private List<ApiResult> results = new ArrayList<>();
    private RecyclerViewAdapter viewAdapter;

    @BindView(R.id.recyclerView) RecyclerView recyclerView;
    @BindView(R.id.progress_bar) ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        viewAdapter = new RecyclerViewAdapter(this, results);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(viewAdapter);

        loadDataApi();

        // Pull to Refresh
        final SwipeRefreshLayout dorefresh = (SwipeRefreshLayout)findViewById(R.id.swipeRefresh);
        dorefresh.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        // event ketika widget dijalankan
        dorefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener(){
            @Override
            public void onRefresh() {
                refreshItem();
            }

            void refreshItem() {
                loadDataApi();
                onItemLoad();
            }

            void onItemLoad() {
                dorefresh.setRefreshing(false);
            }

        });
    }

    private void loadDataApi() {
        // Get ServiceGenerator
        ApiClient apiClient = ServiceGenerator.createService(ApiClient.class);
        Call<ApiValue> call = apiClient.viewAll();
        call.enqueue(new Callback<ApiValue>() {
            // Load data
            @Override
            public void onResponse(Call<ApiValue> call, Response<ApiValue> response) {
                progressBar.setVisibility(View.GONE);
                results = response.body().getResult();
                viewAdapter = new RecyclerViewAdapter(MainActivity.this, results);
                recyclerView.setAdapter(viewAdapter);
            }

            // Jika gagal load data
            @Override
            public void onFailure(Call<ApiValue> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(MainActivity.this, "Jaringan Error!", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.title_activity_login) {
//            startActivity(new Intent(MainActivity.this, LoginActivity.class));
//        }
//
//        if (id == R.id.title_activity_logout) {
//            startActivity(new Intent(MainActivity.this, LogoutActivity.class));
//        }
//
//        if (id == R.id.tambah) {
//            startActivity(new Intent(MainActivity.this, TambahActivity.class));
//        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadDataApi();
    }
}
