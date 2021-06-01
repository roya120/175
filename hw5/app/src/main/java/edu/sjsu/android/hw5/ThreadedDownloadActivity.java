package edu.sjsu.android.hw5;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ThreadedDownloadActivity extends AppCompatActivity {

    ImageView image1;
    ProgressDialog dialog;
    String error_msg="";
    private ThreadHandlerMainClass handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        image1 =findViewById(R.id.image);
        dialog =new ProgressDialog(ThreadedDownloadActivity.this);
        handler = new ThreadHandlerMainClass(image1);
    }


    public void runRunnable(View view){
        try {
            final EditText textURL = findViewById(R.id.textURL);
            if (textURL.getText().toString().length() == 0) {
                Toast.makeText(this, "Enter URL", Toast.LENGTH_SHORT).show();
                return;
            }

            dialog.setCancelable(false);
            dialog.setCanceledOnTouchOutside(false);
            dialog.setTitle("Please Wait");
            dialog.setMessage("Runnable Task");
            dialog.show();

            new Thread(new Runnable() {
                public void run() {
                    final Bitmap m_image = downloadBitmap(textURL.getText().toString());
                    handler.post(new Runnable() {
                        public void run() {
                            if (m_image != null) {
                                image1.setImageBitmap(m_image);
                            }
                            if (dialog.isShowing()){
                                dialog.dismiss();
                            }
                        }
                    });
                }
            }).start();
        }catch (Exception e){
            if (dialog.isShowing()){
                dialog.dismiss();
            }
            Toast.makeText(this, "Error: "+e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }


    public void runMessage(View view){
        try {

            dialog.setCancelable(false);
            dialog.setCanceledOnTouchOutside(false);
            dialog.setTitle("Please Wait");
            dialog.setMessage("Run Message Task");
            dialog.show();


            final EditText textURL = findViewById(R.id.textURL);
            if (textURL.getText().toString().length() == 0) {
                Toast.makeText(this, "Enter URL", Toast.LENGTH_SHORT).show();
                return;
            }

            new Thread(new Runnable() {
                public void run() {
                    Bitmap tmp = downloadBitmap(textURL.getText().toString());

                    Message msg = handler.obtainMessage(0, tmp);
                    handler.sendMessage(msg);
                    if (dialog.isShowing()){
                        dialog.dismiss();
                    }
                }
            }).start();

        }catch (Exception e){
            if (dialog.isShowing()){
                dialog.dismiss();
            }
            Toast.makeText(this, "Error: "+e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }


    public void runAsync(View view){
        EditText textURL=findViewById(R.id.textURL);
        if (textURL.getText().toString().length() == 0){
            Toast.makeText(this, "Enter URL", Toast.LENGTH_SHORT).show();
            return;
        }
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setTitle("Please Wait");
        dialog.setMessage("Async Task");
        dialog.show();
        AsyncTaskFromUrl asyncTaskFromUrl=new AsyncTaskFromUrl();
        asyncTaskFromUrl.execute(""+textURL.getText().toString());

    }


    public void resetImage(View view){
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.apple);
        image1.setImageBitmap(bitmap);
    }

    public Bitmap downloadBitmap(String urlSource){

        try {
            URL url = new URL(urlSource);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (Exception e) {
            if (dialog.isShowing()){
                dialog.dismiss();
            }


            error_msg=""+e.getMessage();


            return null;
        }

    }

    public class AsyncTaskFromUrl extends AsyncTask<String, Void, Bitmap> {


        @Override
        protected Bitmap doInBackground(String... params) {

            Bitmap bitmap = null;

            bitmap = downloadBitmap(params[0]);

            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {

            System.out.println("bitmap" + bitmap);
            if (bitmap == null){
                Toast.makeText(ThreadedDownloadActivity.this, "Error: "+error_msg, Toast.LENGTH_SHORT).show();
                return;
            }
            image1.setImageBitmap(bitmap);
            if (dialog.isShowing()){
                dialog.dismiss();
            }

        }
    }


    class ThreadHandlerMainClass extends Handler {
        private ImageView tmpImgView;

        public ThreadHandlerMainClass(ImageView imgv) {
            tmpImgView = imgv;
        }
        public void handleMessage(Message msg) {
            if((Bitmap)msg.obj!=null) {
                tmpImgView.setImageBitmap((Bitmap)msg.obj);

            }
            else{
                Toast.makeText(ThreadedDownloadActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        }
    }
}





