package in.charissoftware.autoangels;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.auth0.android.jwt.JWT;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {
    Button buttonLogin;
    EditText userName,password;
    SharedPreferences sharedPreferences;
    public  static  final  String MY_PREFS_NAME="MYPrefsFileAutoAngles";
//  boolean isDemoUser;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        buttonLogin=findViewById(R.id.loginButton);
        userName=findViewById(R.id.username_autoangel);
        password=findViewById(R.id.password_autoangel);

        sharedPreferences=getSharedPreferences(MY_PREFS_NAME,Context.MODE_PRIVATE);
//       isDemoUser=sharedPreferences.getBoolean("iSDEMOUSER",true);
        if (saveSharedPrefernces.getLoggedStatus(getApplicationContext())) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(getApplicationContext(), "Please Login ", Toast.LENGTH_SHORT).show();
        }
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isNetworkAvaliable()){
                    postDataUsingVolley(userName.getText().toString(),password.getText().toString());

                }else {
                    Toast.makeText(getApplicationContext(), "No Internet connection found! Please retry.", Toast.LENGTH_SHORT).show();

                }

            }
        });

    }

    private  void postDataUsingVolley(String name,String password1 ){
        String url="https://charislms.com/autoangels/apilogin.php";
        RequestQueue queue=Volley.newRequestQueue(LoginActivity.this);
        StringRequest request=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @SuppressLint("LogNotTimber")
            @Override
            public void onResponse(String response) {
                userName.setText("");
                password.setText("");
                //Toast.makeText(LoginActivity.this, "Data added to api", Toast.LENGTH_LONG).show();
                try {
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    JSONObject resobj = new JSONObject(response);
                    Log.d("TAG", String.valueOf(resobj));


                    String bool=resobj.getString("isSuccess");
                    Log.d("TAG BOO",bool);

                    if (bool.equals("true")){
                        JSONArray jsonArray=resobj.getJSONArray("result");

                        for (int i=0;i<jsonArray.length();i++){
                            JSONObject jsonObject=jsonArray.getJSONObject(i);
                            String id1=jsonObject.getString("id");
                            editor.putString("autoanglesid",id1);
                            Log.d("TAG ID",id1);
                            String name2=jsonObject.getString("name");
                            editor.putString("autoanglesname",name2);
                            Log.d("TAG NAME",name2);
                            String url1=jsonObject.getString("url");
                            editor.putString("autoanglesurl",url1);
                            Log.d("TAG URL",url1);
                            String logoutUrl=jsonObject.getString("logout");
                            editor.putString("autoangelslogout",logoutUrl);
                         //   editor.putBoolean("iSDEMOUSER",true);
                            editor.apply();

                        }
                        startActivity(new Intent(LoginActivity.this,MainActivity.class));
                        saveSharedPrefernces.setLoggedIn(getApplicationContext(), true);

                        Toast.makeText(LoginActivity.this, "LOGIN DETAILS ARE VALID ", Toast.LENGTH_SHORT).show();

                    }
                    else {
                        Toast.makeText(LoginActivity.this, "INVALID LOGIN DETAILS ", Toast.LENGTH_SHORT).show();
                    }

                }
                    catch (JSONException exception) {
                    exception.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(LoginActivity.this, "Fail to get response = " + error, Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String,String> getParams(){
                Map<String, String> params = new HashMap<String, String>();
                params.put("username", name);
                params.put("password", password1);
                return  params;
            }
        };
queue.add(request);
    }

    public boolean isNetworkAvaliable() {

        ConnectivityManager connectivityManager = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }


}
