package com.herokuapp.tif6.mantanku.activity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.herokuapp.tif6.mantanku.R;
import com.herokuapp.tif6.mantanku.models.ApiClient;
import com.herokuapp.tif6.mantanku.models.ApiValue;
import com.herokuapp.tif6.mantanku.models.ApiResult;
import com.herokuapp.tif6.mantanku.services.LocalServerService;
import com.herokuapp.tif6.mantanku.services.ServiceGenerator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {
    // Local data server
    LocalServerService localServerService = new LocalServerService(this);

    // Get ServiceGenerator
    ApiClient apiClient = ServiceGenerator.createService(ApiClient.class);

    protected Cursor cursor;
    private List<ApiResult> results = new ArrayList<>();

    @BindView(R.id.textId) TextView textId;
    @BindView(R.id.textNama) TextView textNama;
    @BindView(R.id.textAlasan) TextView textAlasan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Detail");

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");

        textId.setText(id);

        loadDataApiId();
    }

    private void loadDataApiId(){
        // Get Id from RecyclerViewAdapter
        Integer id = Integer.valueOf(textId.getText().toString());

        // Get data from API
        Call<ApiValue> call = apiClient.viewId(id);
        call.enqueue(new Callback<ApiValue>() {
            @Override
            public void onResponse(Call<ApiValue> call, Response<ApiValue> response) {
                // Ambil message jika belum login
                String message = response.body().getMessage();

                // Jika belum login
                if(message.equals("Belum Login")){
                    // Kembali ke main activity
                    finish();

                    // popup toast pesan error
                    Toast.makeText(DetailActivity.this, "Belum Login", Toast.LENGTH_SHORT).show();
                } else {
                    // menampilkan hasil
                    results = response.body().getResult();
                }
            }

            @Override
            public void onFailure(Call<ApiValue> call, Throwable t) {
                // popup toast pesan error
                Toast.makeText(DetailActivity.this, "Jaringan Error!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.item_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.title_activity_logout) {
//            startActivity(new Intent(ByIdActivity.this, LogoutActivity.class));
//        }

        switch (id) {
            case android.R.id.home:
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
