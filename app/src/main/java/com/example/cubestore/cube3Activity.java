package com.example.cubestore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.cubestore.adapter.CategoryAdapter;
import com.example.cubestore.adapter.ParseItemAdapter;
import com.example.cubestore.model.Category;
import com.example.cubestore.model.ParseItemModel;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class cube3Activity extends AppCompatActivity {

    private static final String TAG = "";
    private RecyclerView recyclerView;
    private ParseItemAdapter parseItemAdapter;
    private final List<ParseItemModel> parseItemModelList = new ArrayList<>();
    Elements data;
    Document document;

    ImageView backblog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cube2);

        recyclerView = findViewById(R.id.recyclerView2);
        backblog =findViewById(R.id.backblog);


        Content content = new Content();
        content.execute();


        backblog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(cube3Activity.this, AllCategory.class);
                startActivity(i);
            }
        });
    }

    //парсинг
    private class Content extends AsyncTask<Void,Void,Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            parseItemAdapter = new ParseItemAdapter(parseItemModelList, cube3Activity.this);
            recyclerView.setAdapter(parseItemAdapter);
            parseItemAdapter.notifyDataSetChanged();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                String url = "https://zakubi.com/Po_razmeru/3-na-3";
                document = Jsoup.connect(url).get();
                data = document.select("div.col-sm-12");
//              Log.e(TAG, "Get Data: "+data.toString() );

                int size = data.size();
                for (int i =0; i <size; i++){

                    String title  = data.select("div.name")
                            .eq(i)
                            .select("a").text();

                    String subTitle = data.select("div.price")
                            .eq(i)
                            .text();


                    String imagelink = data.select("div.image")
                            .eq(i).select("img").attr("src");

                    String postLink = data.select("div.name").eq(i).select("a").attr("href");

//                  Log.e(TAG, "Get Data: "+postLink.toString() );

                    parseItemModelList.add(new ParseItemModel(title,subTitle,imagelink,postLink));

                }


            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }
    }




}