package com.example.iheath.tintuc_007;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.iheath.M001_menu;
import com.example.iheath.R;
import com.example.iheath.entities.RSSsuckhoe.RSSObject;
import com.example.iheath.entities.RoomDatabase.RSS;
import com.example.iheath.entities.RoomDatabase.UserManager;
import com.example.iheath.tintuc_007.danhsachchitiet.M007_1_DanhSachTinTuc;
import com.example.iheath.tintuc_007.rssNews.M007_FeedAdapter;
import com.example.iheath.tintuc_007.rssNews.M007_HTTPDataHandler;
import com.google.gson.Gson;

import java.util.ArrayList;

public class M007_TinTuc extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView recyclerView;
    RSSObject rssObject;
    ArrayList<RSS> listRSS;
    ArrayList<RSS> listData;

    //RSS link
//    private final String RSS_link="http://rss.nytimes.com/services/xml/rss/nyt/Science.xml";
    private final String RSS_link="https://vietnamnet.vn/rss/suc-khoe.rss";
    private final String RSS_to_Json_API = "https://api.rss2json.com/v1/api.json?rss_url=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m007__tin_tuc);

        UserManager.getInstance().getAllRSS(data -> {
            ArrayList<RSS> list= (ArrayList<RSS>) data;
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Log.i("TAG", list.toString());
                }
            });
        });


        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Tin mới nhất");
        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);

        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager  = new LinearLayoutManager(getBaseContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);

        loadRSS();
    }

    private void loadRSS() {
        AsyncTask<String,String,String> loadRSSAsync = new AsyncTask<String, String, String>() {

            ProgressDialog mDialog = new ProgressDialog(M007_TinTuc.this);

            @Override
            protected void onPreExecute() {
                mDialog.setMessage("Please wait...");
                mDialog.show();
            }

            @Override
            protected String doInBackground(String... params) {
                String result;
                M007_HTTPDataHandler http = new M007_HTTPDataHandler();
                result = http.GetHTTPData(params[0]);
                return  result;
            }

            @Override
            protected void onPostExecute(String s) {
                mDialog.dismiss();
                rssObject = new Gson().fromJson(s, RSSObject.class);
                M007_FeedAdapter adapter = new M007_FeedAdapter(rssObject,getBaseContext());

                listRSS= new ArrayList<>();
                UserManager.getInstance().getAllRSS(data -> {
                    ArrayList<RSS> list= (ArrayList<RSS>) data;
                    if(list.size()==0){
                        for (int i=0;i<rssObject.getItems().size();i++) {
                            String tilte = rssObject.getItems().get(i).getTitle();
                            String url = rssObject.getItems().get(i).getLink();
                            String thumnail = rssObject.getItems().get(i).getThumbnail();
                            String date = rssObject.getItems().get(i).getPubDate();
                            listRSS.add(new RSS(i,tilte, url, thumnail,date));
                        }
                    }
                    else {
                        for (int i=0;i<rssObject.getItems().size();i++) {
                            String tilte = rssObject.getItems().get(i).getTitle();
                            String url = rssObject.getItems().get(i).getLink();
                            String thumnail = rssObject.getItems().get(i).getThumbnail();
                            String date = rssObject.getItems().get(i).getPubDate();
                            listRSS.add(new RSS(list.size()+i,tilte, url, thumnail,date));
                        }
                    }
                });

                UserManager.getInstance().getAllRSS(data -> {
                    ArrayList<RSS> rsss= (ArrayList<RSS>) data;
                    if (rsss.size()==0){
                        for(int i=0;i<listRSS.size();i++){
                            UserManager.getInstance().themRSS(data1 -> {

                            },listRSS.get(i));
                        }
                    }
                    else{
                        UserManager.getInstance().getAllRSS(data1->{
                            listData= (ArrayList<RSS>) data1;
                            for(int i=0;i<listRSS.size();i++){
                                int x=0;
                                for(int j=0;j<listData.size();j++){
                                    if(listRSS.get(i).getTitle().equals(listData.get(j).getTitle())){
                                        x++;
                                    }
                                }
                                if(x==0){
                                    UserManager.getInstance().themRSS(data2 -> {

                                    }, listRSS.get(i));
                                }
                            }
                        });
                    }
                });

                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                Log.i("TAG", adapter.getItemCount()+"");
            }
        };

        StringBuilder url_get_data = new StringBuilder(RSS_to_Json_API);
        url_get_data.append(RSS_link);
        loadRSSAsync.execute(url_get_data.toString());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_tintuc_m007,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent= new Intent();
                intent.setClass(M007_TinTuc.this, M001_menu.class);
                startActivity(intent);
                return true;
            case R.id.menu_refresh:
                Intent intent2= new Intent();
                intent2.setClass(M007_TinTuc.this, M007_1_DanhSachTinTuc.class);
                startActivity(intent2);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}