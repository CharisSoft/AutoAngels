package in.charissoftware.autoangels;

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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {
//    Button buttonLogin;
//    EditText userName,password;
//    private static  final String URL="https://charislms.com/autoangels/apilogin.php";
//    private RequestQueue requestQueue;
//    private StringRequest stringRequest;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
//        buttonLogin=findViewById(R.id.loginButton);
//        userName=findViewById(R.id.username_autoangel);
//        password=findViewById(R.id.password_autoangel);
//        requestQueue= Volley.newRequestQueue(this);
//
//        buttonLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//               attemptLogin();
//
//            }
//        });

//        buttonLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                stringRequest=new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        try {
//                            JSONObject jsonObject = new JSONObject(response);
//                            if (jsonObject.names().get(1).equals("isSuccess")) {
//
//                                Toast.makeText(getApplicationContext(), jsonObject.getString("sucess"), Toast.LENGTH_SHORT).show();
//                            } else {
//                                Toast.makeText(getApplicationContext(), "Error ", Toast.LENGTH_SHORT).show();
//
//                            }
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }, new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//
//                    }
//                })
//                {
//                    @Override
//                    protected Map<String,String> getParams(){
//                        HashMap<String, String> hashMap = new HashMap<>();
//                            hashMap.put("username",userName.getText().toString());
//                            hashMap.put("password",password.getText().toString());
//                            return  hashMap;
//
//                    }
//
//                };
//                requestQueue.add(stringRequest);
////                requestQueue.getCache().clear();
////                Intent intent=new Intent(LoginActivity.this,MainActivity.class);
////                startActivity(intent);
//            }
//        });

    }
//    private  void attemptLogin(){
//
//        userName.setError(null);
//        password.setError(null);
//        String email1 = userName.getText().toString();
//        String password1 = password.getText().toString();
//
//        boolean cancel = false;
//        View focusView = null;
//
//        // Check for a valid Password, if the user entered one.
//        if (TextUtils.isEmpty(password1)) {
//            password.setError("Enter password");
//
//            focusView = password;
//            cancel = true;
//        } else if (!isPasswordValid(password1)) {
//            password.setError("Enter valid password");
//            focusView = password;
//            cancel = true;
//        }
//
//        // Check for a valid email address.
//        if (TextUtils.isEmpty(email1)) {
//            userName.setError("Enter email");
//            focusView = userName;
//            cancel = true;
//        } else if (!isEmailValid(email1)) {
//            userName.setError("Enter valid email");
//            focusView = userName;
//            cancel = true;
//        }
//
//        if (cancel) {
//            // There was an error; don't attempt login and focus the first
//            // form field with an error.
//            userName.requestFocus();
//            password.requestFocus();
////            focusView.requestFocus();
//        } else {
//        if (isNetworkAvailable()) {
//            String urlAPILogin = "https://charislms.com/autoangels/apilogin.php";
//
//            LoginTask userLoginTask = new LoginTask(this);
//            userLoginTask.execute(urlAPILogin, email1, password1);
////                showProgress(true);
//
////            progressBar.setVisibility(View.VISIBLE);
//        } else {
//            Toast.makeText(this,
//                    "No active Internet connection found, " +
//                            "Please try again.", Toast.LENGTH_SHORT).show();
////            progressBar.setVisibility(View.INVISIBLE);
////                showProgress(false);
//        }
//
//        }
//    }
//    public boolean isNetworkAvailable() {
//
//        ConnectivityManager connectivityManager;
//
//        connectivityManager =
//                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
//
//        NetworkInfo activeNetworkInfo =
//                Objects.requireNonNull(connectivityManager).getActiveNetworkInfo();
//
//        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
//    }
//        private boolean isPasswordValid(String password) {
//            //length should not be less than 4
//            return password.length() > 4;
//        }
//    private boolean isEmailValid(String email) {
//        String EMAIL_PATTERN = "^[_A-Za-z0-9-+]+(\\.[_A-Za-z0-9-]+)*@"
//                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
//
//        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
//        Matcher matcher = pattern.matcher(email);
//        return matcher.matches();
//    }
//
//    @Override
//    public void onTaskComplete(JSONObject jsonObject) {
//        String token = null;
//        System.out.println(jsonObject.toString());
//
//        String userLName = "", userMName = "", userEmail = "";
//
//        try {
//            token = jsonObject.getString("access_token");
//
//            JWT jwt = null;
//            if (token != null) {
//                jwt = new JWT(token);
//            }
//
//            String userFName = null;
//            if (jwt != null) {
//                userFName = (jwt.getClaim("firstname")).asString();
//                userLName = (jwt.getClaim("lastname")).asString();
//                userMName = (jwt.getClaim("middlename")).asString();
//                userEmail = (jwt.getClaim("name")).asString();
//            }
//            Utils.setUserEmail(userEmail);
//            Utils.setUserFName(userFName);
//            Utils.setUserMName(userMName);
//            Utils.setUserLName(userLName);
//            Utils.setUserValid(true);
//
//            // Create Share Preference to store login credentials:
//
//            SharedPreferences prefs
//                    = Armadillo.create(this, Utils.getSpFile())
//                    .encryptionFingerprint(this)
//                    .enableKitKatSupport(true) //enable optional kitkat support
//                    .build();
//
//            SharedPreferences.Editor Ed = prefs.edit();
//
//            Ed.putString("userEmail", Utils.getUserEmail());
//            Ed.putString("userPassword", Utils.getUserPassword());
//            Ed.putString("userFName", Utils.getUserFName());
//            Ed.putString("userLName", Utils.getUserLName());
//            Ed.putString("userMName", Utils.getUserMName());
//            Ed.putBoolean("userValid", Utils.isUserValid());
//
//            Ed.apply();
//
//            SharedPreferences   sharedPreferences=getSharedPreferences("isDemoUserPreferences",MODE_PRIVATE);
//            SharedPreferences.Editor myEdit = sharedPreferences.edit();
//            myEdit.putBoolean("isDemoUser", false);
//            myEdit.apply();
//            myEdit.commit();
//            // Start the main activity
//            startActivity(new Intent(this, HomeActivity.class));
//
//        } catch (NullPointerException e) {
////            Log.d("onTaskComplete");
////            Crashlytics.log(Log.DEBUG, "tag", e.getMessage());
//
//        } catch (JSONException e) {
////            Log.d("onTaskComplete: =");
////            Crashlytics.log(Log.DEBUG, "tag", e.getMessage());
//
//        } catch (Exception e) {
//            e.printStackTrace();
////            Crashlytics.log(Log.DEBUG, "tag", e.getMessage());
//            Toast.makeText(this, "Unable to save login details.",
//                    Toast.LENGTH_SHORT).show();
//        }
//
//    }
//
//    @Override
//    public void onTaskComplete(String error) {
//        Toast.makeText(this, "Credentials Are Wrong!", Toast.LENGTH_SHORT).show();
//
//    }
}
