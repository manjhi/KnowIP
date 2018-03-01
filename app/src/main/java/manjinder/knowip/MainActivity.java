package manjinder.knowip;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
         TextView textView1,textView2;
         Button button;
    String s;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView1= (TextView) findViewById(R.id.text1);
        textView2=(TextView)findViewById(R.id.textip);
        button=(Button)findViewById(R.id.click);

        final ProgressDialog progressDialog = new ProgressDialog(this);
        Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl("https://jpss.herokuapp.com").build();

        DataInterface dataInterface = retrofit.create(DataInterface.class);

        final Call<IpAddress> getIp = dataInterface.getMyIp();


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.setMessage("Please Wait...");
                progressDialog.show();
                getIp.enqueue(new Callback<IpAddress>() {
                    @Override
                    public void onResponse(Call<IpAddress> call, Response<IpAddress> response) {
                        s=response.body().getAddress();
                        Log.d("ipAddr", s);
                        textView2.setText(s);
                        progressDialog.dismiss();
                    }

                    @Override
                    public void onFailure(Call<IpAddress> call, Throwable t) {

                    }
                });


            }
        });
    }
}
