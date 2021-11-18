package in.charissoftware.autoangels;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;
    TextView textView;
    Button retryBtn;
    ProgressBar mProgressBarCircle, mProgressBar;
    //    String url_b2s="https://greensarehealthy.com/b2s/m/index.php";
    TextView textViewProgressBar;
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = findViewById(R.id.webView);
//        textViewProgressBar=findViewById(R.id.progressBarMessage);
        textView = findViewById(R.id.splashscreenText);
        imageView = findViewById(R.id.charispos_image);
        retryBtn = findViewById(R.id.retry_button);

//        Log.d("TAG", "MAIN ACTITY");

        initViews();

        retryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadPage();
            }
        });

        loadPage();
//        Objects.requireNonNull(getSupportActionBar()).setTitle("Brew 2 Stew");
//        // getSupportActionBar().setSubtitle("Subjects ----- No of Questions");
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    private void loadPage() {
        if (isNetworkAvaliable()) {
            webView.loadUrl("https://charislms.com/autoangels/tech/index.php");
            retryBtn.setVisibility(View.INVISIBLE);
        } else {
            retryBtn.setVisibility(View.VISIBLE);
            Toast.makeText(this, "No Internet connection found! Please retry.", Toast.LENGTH_SHORT).show();
        }
    }

    private void initViews() {
        initWebView();
        mProgressBarCircle = findViewById(R.id.progress_blogs_circle);
        mProgressBar = findViewById(R.id.progress_blogs);
    }

    private void initWebView() {


        WebSettings webSettings = webView.getSettings();
        webSettings.setAllowContentAccess(true);
        webSettings.setAllowFileAccessFromFileURLs(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setAppCacheEnabled(true);
        webSettings.setLoadsImagesAutomatically(true);
        webSettings.setSaveFormData(true);
        webSettings.setDatabaseEnabled(true);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDisplayZoomControls(false);

//        if(!isNetworkAvaliable()){
//
//            AlertDialog.Builder builder=new AlertDialog.Builder(this);
//            builder.setMessage("Internet Connection is required for this application")
//                    .setCancelable(false)
//                    .setPositiveButton("Retry", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialogInterface, int i) {
//                            Intent intent = getIntent();
//                            finish();
//                            startActivity(intent);
//                        }
//                    });
//            AlertDialog alertDialog=builder.create();
//            alertDialog.show();
//        }
//        else {
//            webView.loadUrl(url_b2s);
        webView.clearCache(true);
        webView.clearHistory();
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                mProgressBarCircle.setVisibility(View.VISIBLE);
                mProgressBar.setVisibility(View.VISIBLE);
                if (!isNetworkAvaliable()) {
                    webView.setVisibility(View.GONE);
                    Intent intent = new Intent(getApplicationContext(), ErrorActivity.class);
                    startActivity(intent);

                }
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                Log.d("WebView", "your current url when webpage loading.. finish" + url);

                super.onPageFinished(view, url);
                mProgressBar.setVisibility(View.INVISIBLE);
                mProgressBarCircle.setVisibility(View.INVISIBLE);

                textView.setVisibility(View.INVISIBLE);
                imageView.setVisibility(View.INVISIBLE);

            }

            @Override
            public void onLoadResource(WebView view, String url) {
                super.onLoadResource(view, url);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                System.out.println("when you click on any interlink on webview that time you got url :-" + url);

                return super.shouldOverrideUrlLoading(view, url);
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                webView.stopLoading();
                ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

                NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
                if (!(networkInfo != null && networkInfo.isConnected())) {
                    showSnackBar("Please Check Internet Connection and try again");

                }
                super.onReceivedError(view, errorCode, description, failingUrl);
            }
        });

        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                mProgressBarCircle.setProgress(newProgress);
                mProgressBar.setProgress(newProgress);

                if (newProgress == 100) {
                    mProgressBarCircle.setVisibility(View.GONE);
                    mProgressBar.setVisibility(View.GONE);
//                        textViewProgressBar.setVisibility(View.GONE);
                } else {
                    mProgressBar.setVisibility(View.VISIBLE);
                    mProgressBarCircle.setVisibility(View.VISIBLE);
//                        textViewProgressBar.setVisibility(View.VISIBLE);
                }
            }
        });
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            webView.goBack();
            return true;
        } else {
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    public void showSnackBar(String msg) {
        Snackbar snackbar = Snackbar.make(webView, msg, Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    public boolean isNetworkAvaliable() {

        ConnectivityManager connectivityManager = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }



}