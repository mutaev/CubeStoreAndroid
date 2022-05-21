package com.example.cubestore;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;


public class BlogActivity extends AppCompatActivity {
    TextView title_txt, price_txt;
    ImageView imageView;
    String title, price, tag, image, link;
    String blogDoc;
    Elements data;
    Document document;
    Button button_buy;

    ImageView backblog;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog);


        title_txt = findViewById(R.id.title_txt_id);
        imageView = findViewById(R.id.imageView);
        price_txt= findViewById(R.id.price_txt_id);
        button_buy = findViewById(R.id.button_buy);


        price = getIntent().getStringExtra("subtitle");
        title = getIntent().getStringExtra("title");
        image = getIntent().getStringExtra("image");
        link = getIntent().getStringExtra("postlink");

        title_txt.setText(title);
        price_txt.setText(price);
        Picasso.get().load(image).into(imageView);


        backblog = findViewById(R.id.backblog);



        backblog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back = new Intent(BlogActivity.this, MainActivity.class);
                startActivity(back);
                finish();
            }
        });

        button_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new
                        Intent(Intent.ACTION_VIEW, Uri.parse("https://cubemarket.ru/"));
                startActivity(browserIntent);

            }
        });




    }


}