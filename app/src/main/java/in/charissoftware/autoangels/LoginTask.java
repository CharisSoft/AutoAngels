//package in.charissoftware.autoangels;
//
//import android.os.AsyncTask;
//import android.util.Log;
//
//import com.goebl.david.Webb;
//
//import org.json.JSONException;
//import org.json.JSONObject;
//
//public class LoginTask extends AsyncTask<String, Void, JSONObject> {
//    //Initialising the Interface
//    private final OnTaskCompleted mCallback;
//
//    /**
//     * Note:Creating a constructor
//     *
//     * @param mCallback OnTaskCompleted
//     */
//    public LoginTask(OnTaskCompleted mCallback) {
//        this.mCallback = mCallback;
//    }
//
//    /**
//     * Note:Executing the login task
//     *
//     * @param strings parameters
//     * @return result
//     */
//    @Override
//    protected final JSONObject doInBackground(String... strings) {
//
//        String url = strings[0];
//        String username = strings[1];
//        String password = strings[2];
//
//        try {
//
//            Webb webb = Webb.create();
//            @SuppressWarnings("UnnecessaryLocalVariable")
//            JSONObject jsonObject = webb.post(url)
//                    .param("username", username)
//                    .param("password", password)
//                    .param("client_id", "myClient")
//                    .param("scope", "myAPIs")
//                    .param("grant_type", "password")
//                    .ensureSuccess()
//                    .asJsonObject()
//                    .getBody();
//
//            return jsonObject;
//        } catch (Exception e) {
//            Log.d("doInBackground: ", e.getMessage());
////            Crashlytics.log(Log.DEBUG, "tag", e.getMessage());
//        }
//        return null;
//    }
//
//
//    /**
//     * Note:Task execution result is received here
//     *
//     * @param jsonObject result
//     */
//    @Override
//    protected void onPostExecute(JSONObject jsonObject) {
//        if (jsonObject == null) {
//            mCallback.onTaskComplete("unable to login! Please try again later.");
//        } else {
//            String token;
//            try {
//                if (jsonObject.has("error")) {
//                    String errorMsg = jsonObject.getString("error_description");
//                    mCallback.onTaskComplete(errorMsg);
//                } else {
//                    token = jsonObject.getString("isSuccess");
////                JWT jwt = null;
//                    if (token != null) {
////                    jwt = new JWT(token);
////                }
////
////                String userFName = null;
////                String userMName = null;
////                String userLName = null;
////
////                if (jwt != null) {
////                    userFName = (jwt.getClaim("firstname")).asString();
////                    userLName = (jwt.getClaim("lastname")).asString();
////                    userMName = (jwt.getClaim("middlename")).asString();
////                }
////
////                Utils.setUserPassword(password);
////                Utils.setUserEmail(username);
////                Utils.setUserFName(userFName);
////                Utils.setUserMName(userMName);
////                Utils.setUserLName(userLName);
//                        mCallback.onTaskComplete(jsonObject);
//                    } else {
//                        mCallback.onTaskComplete("JSON error! Please try again later.");
//                    }
//                }
//            } catch (JSONException e) {
//                e.printStackTrace();
//                mCallback.onTaskComplete("unable to login! Please try again later.");
//            }
//        }
//    }
//
//    /**
//     * Note:using interface
//     */
//    public interface OnTaskCompleted {
//        void onTaskComplete(JSONObject jsonObject);
//
//        void onTaskComplete(String error);
//    }
//}