package com.bhattaraisubash.jsonfetch;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.Toast;

import com.bhattaraisubash.jsonfetch.adapter.ListAdapter;
import com.bhattaraisubash.jsonfetch.entity.Post;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Subash Bhattarai
 * @version 1.0
 */
public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listView);
        context = this;
        new FetJson().execute("https://jsonplaceholder.typicode.com/posts");
    }

    private class FetJson extends AsyncTask<String, Void, List<Post>> {

        @Override
        protected List<Post> doInBackground(String... strings) {
            HttpURLConnection conn = null;
            try {
                URL url = new URL(strings[0]);
                conn = (HttpURLConnection) url.openConnection();
                InputStream inputStream = new BufferedInputStream(conn.getInputStream());
                return getPostList(inputStream);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (conn != null) {
                    conn.disconnect();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<Post> postList) {
            super.onPostExecute(postList);
            if (postList == null) {
                Toast.makeText(context, "Error Fetching Data !", Toast.LENGTH_LONG).show();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(4000);
                            finish();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            } else {
                listView.setAdapter(new ListAdapter(context, postList));
            }
        }

        private List<Post> getPostList(InputStream inputStream) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuffer stringBuffer = new StringBuffer();
            String temp = "";
            List<Post> postList = new LinkedList<>();
            try {
                while ((temp = reader.readLine()) != null) {
                    stringBuffer.append(temp);
                }

                JSONArray jsonArray = new JSONArray(stringBuffer.toString());
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    Post post = new Post();
                    post.setId(Integer.parseInt(jsonObject.getString("id")));
                    post.setTitle(jsonObject.getString("title"));
                    postList.add(post);
                }

            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return postList;
        }

    }

}
