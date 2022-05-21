package com.example.cubestore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
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

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "";
    private RecyclerView recyclerView;
    private ParseItemAdapter parseItemAdapter;
    private final List<ParseItemModel> parseItemModelList = new ArrayList<>();
    Elements data;
    Document document;

    RecyclerView  categoryRecyclerView;
    CategoryAdapter categoryAdapter;
    List<Category> categoryList;

    ImageView allCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        categoryRecyclerView = findViewById(R.id.categoryRecycler);
        allCategory= findViewById(R.id.allCategoryImage);
        recyclerView = findViewById(R.id.recyclerView2);

        Content content = new Content();
        content.execute();

        allCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, AllCategory.class);
                startActivity(i);
            }
        });


        //лист акций
        categoryList = new ArrayList<>();
        categoryList.add(new Category(2, R.drawable.cat1));
        categoryList.add(new Category(2, R.drawable.catt2));
        categoryList.add(new Category(2, R.drawable.catt2));
        setCategoryRecycler(categoryList);
    }



    private void setCategoryRecycler(List<Category> categoryDataList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        categoryRecyclerView.setLayoutManager(layoutManager);
        categoryAdapter = new CategoryAdapter(this,categoryDataList);
        categoryRecyclerView.setAdapter(categoryAdapter);
    }



    //парсинг
    private class Content extends AsyncTask<Void,Void,Void>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            parseItemAdapter = new ParseItemAdapter(parseItemModelList, MainActivity.this);
            recyclerView.setAdapter(parseItemAdapter);
            parseItemAdapter.notifyDataSetChanged();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                String url = "https://zakubi.com/";//https://rtdev.xyz/
                document = Jsoup.connect(url).get();
                data = document.select("div.col-sm-12");//div.si-blog-entry-wrapper.si-thumb-right
//              Log.e(TAG, "Get Data: "+data.toString() );

                int size = data.size();
                for (int i =0; i <size; i++){

                    String title  = data.select("div.name")//h2.entry-title
                            .eq(i)
                            .select("a").text();

                    String subTitle = data.select("div.price")//div.entry-summary.si-entry
                            .eq(i)
                            .text();


                    String imagelink = data.select("div.image")//div.post-thumb.entry-media.thumbnail
                            .eq(i).select("img").attr("src");

                    String postLink = data.select("div.name").eq(i).select("a").attr("href");//h2.entry-title

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