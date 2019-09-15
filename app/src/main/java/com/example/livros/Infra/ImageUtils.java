package com.example.livros.Infra;

import android.content.Context;
import android.webkit.URLUtil;
import android.widget.ImageView;

import com.example.livros.R;
import com.squareup.picasso.Picasso;

import java.io.File;



public class ImageUtils {
    public static void setImage(Context context, String url_img, ImageView img) {
        if(url_img != null && url_img.trim().length() > 0 && URLUtil.isValidUrl(url_img)) {
            Picasso.with(context).load(url_img).into(img);
        } else {
            img.setImageResource(R.drawable.ic_open_book_48dp);
        }
    }
}
