package com.hoangtuan.scanqrc.ActivityView;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;
import com.hoangtuan.Data.DBManagerFind;
import com.hoangtuan.Interface.InterChangeData;
import com.hoangtuan.scanqrc.BitmapLruCache;
import com.hoangtuan.scanqrc.Fragment.ControlFragment;
import com.hoangtuan.scanqrc.Model.HistoryFindModel;
import com.hoangtuan.scanqrc.R;

import java.util.ArrayList;
import java.util.List;

public class ViewFindBarCodeActivity extends AppCompatActivity {
private TextView txtTenSP,txtMoTa;
private NetworkImageView imgSanPham;

    HistoryFindModel historyFindModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_find_bar_code);



        ImageLoader imageLoader;
        ImageLoader.ImageCache imageCache = new BitmapLruCache();
        imageLoader = new ImageLoader(Volley.newRequestQueue(getApplicationContext()),imageCache);
        txtMoTa = (TextView)findViewById(R.id.txtMoTa);
        txtTenSP = (TextView)findViewById(R.id.txtProductName);
        imgSanPham=(NetworkImageView)findViewById(R.id.imgProduct);

       DBManagerFind dbManagerFind = new DBManagerFind(this);
//      HistoryFindModel historyFindModel=dbManagerFind.getSdtudentById(1);
//txtTenSP.setText(historyFindModel.getName());
//        Log.d("SIZENEW",dbManagerFind.getAllStudent().size()+"");

    Intent intent=getIntent();
    HistoryFindModel historyFindModel= (HistoryFindModel) intent.getSerializableExtra("His");

txtTenSP.setText(historyFindModel.getName());
txtMoTa.setText(historyFindModel.getMota());
imgSanPham.setImageUrl(historyFindModel.getUrl(),imageLoader);
    }


}
