package com.herokuapp.tif6.mantanku.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.herokuapp.tif6.mantanku.R;
import com.herokuapp.tif6.mantanku.models.ApiClient;
import com.herokuapp.tif6.mantanku.models.ApiResult;
import com.herokuapp.tif6.mantanku.models.ApiValue;
import com.herokuapp.tif6.mantanku.services.ServiceGenerator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {
    // Get ServiceGenerator
    ApiClient apiClient = ServiceGenerator.createService(ApiClient.class);

    private Button button;
    private List<ApiResult> results = new ArrayList<>();

    @BindView(R.id.textId) TextView textId;
    @BindView(R.id.textNama) TextView textNama;
    @BindView(R.id.textAlamat) TextView textAlamat;
    @BindView(R.id.textNoHp) TextView textNoHp;
    @BindView(R.id.textPhoto) TextView textPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Detail");

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String nama = intent.getStringExtra("nama");
        String alamat = intent.getStringExtra("alamat");
        final String nohp = intent.getStringExtra("nohp");
        String photo = intent.getStringExtra("photo");

        textId.setText(id);
        textNama.setText(nama);
        textAlamat.setText(alamat);
        textNoHp.setText(nohp);
        textPhoto.setText(photo);

        loadDataApiId();

        // call button
        button = (Button) findViewById(R.id.buttonCall);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:"+nohp));

                if (ActivityCompat.checkSelfPermission(DetailActivity.this,
                        Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                startActivity(callIntent);
            }
        });

        // sms button
        button = (Button) findViewById(R.id.buttonSms);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Intent callIntent = new Intent(Intent.ACTION_VIEW);
                callIntent.setData(Uri.parse("sms:"+nohp));

                if (ActivityCompat.checkSelfPermission(DetailActivity.this,
                        Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                startActivity(callIntent);
            }
        });
    }

    private void loadDataApiId(){
        // Get Id from RecyclerViewAdapter
        Integer id = Integer.valueOf(textId.getText().toString());

        // Get data from API
        Call<ApiValue> call = apiClient.viewId(id);
        call.enqueue(new Callback<ApiValue>() {
            @Override
            public void onResponse(Call<ApiValue> call, Response<ApiValue> response) {
                // menampilkan hasil
                results = response.body().getResult();
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
