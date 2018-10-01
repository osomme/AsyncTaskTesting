package no.hiof.oskaras.asynctesting;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private Button btn;

    private MyViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.txtView);
        btn = findViewById(R.id.btn);

        viewModel = ViewModelProviders.of(this).get(MyViewModel.class);

        textView.setText(viewModel.textContent);
    }

    public void startTask(View view) {
        textView.setText("Sleeping... zzz...");

        MyAsyncTask myAsyncTask = new MyAsyncTask(textView);
        myAsyncTask.execute();
    }


    private class MyAsyncTask extends AsyncTask<Void, Integer, String>{
        private TextView txtView;
        private int timeRemaining;

        public MyAsyncTask(TextView txtView) {
            this.txtView = txtView;
        }

        @Override
        protected String doInBackground(Void... voids) {
            Random rng = new Random();
            int randomNumber = rng.nextInt(11) * 400;

            try {
                Thread.sleep(randomNumber);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return "Awake after sleeping for " + randomNumber + " ms.";
        }



        @Override
        protected void onPostExecute(String results) {
            Log.d("MainActivity", "onPostExecute running");
            txtView.setText(results);
        }
    }

    private class MyViewModel extends ViewModel {
        public String textContent;
    }
}
