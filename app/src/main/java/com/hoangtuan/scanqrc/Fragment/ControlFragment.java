package com.hoangtuan.scanqrc.Fragment;

import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.google.zxing.client.android.Intents;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.hoangtuan.Adapter.HistoryAdapter;
import com.hoangtuan.Data.DBManager;
import com.hoangtuan.Data.DBManagerFind;
import com.hoangtuan.Interface.InterChangeData;
import com.hoangtuan.scanqrc.ActivityView.ViewFindBarCodeActivity;
import com.hoangtuan.scanqrc.ActivityView.WebViewActivity;
import com.hoangtuan.scanqrc.MainActivity;
import com.hoangtuan.scanqrc.Model.HistoryFindModel;
import com.hoangtuan.scanqrc.Model.HistoryModel;
import com.hoangtuan.scanqrc.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * Created by atbic on 30/3/2018.
 */

public class ControlFragment extends Fragment {
    private FrameLayout fragmentContainer;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private DBManager dbManager;
    public DBManagerFind dbManagerFind;
    private List<HistoryModel> historyModels;
    HistoryAdapter historyAdapter;
    ArrayList<String> test;

    /**
     * Create a new instance of the fragment
     */
    public static ControlFragment newInstance(int index) {
        ControlFragment fragment = new ControlFragment();
        Bundle b = new Bundle();
        b.putInt("index", index);
        fragment.setArguments(b);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        dbManager = new DBManager(getActivity());
        dbManagerFind = new DBManagerFind(getActivity());
        historyModels = dbManager.getAllStudent();

        Log.d("AAAAA", historyModels.size() + "");
        historyAdapter = new HistoryAdapter(historyModels, getContext());
        historyAdapter.notifyDataSetChanged();
        test = new ArrayList<>();
        View view = null;
        switch (getArguments().getInt("index", 0)) {
            case 0:
                view = inflater.inflate(R.layout.fragment_scan, container, false);
                initScan(view);
                break;
            case 1:
                view = inflater.inflate(R.layout.fragment_find, container, false);
                initFinds(view);
                break;
            case 2:
                view = inflater.inflate(R.layout.fragment_history, container, false);
                initHistory(view);

                break;
            case 3:
                view = inflater.inflate(R.layout.fragment_create, container, false);
                break;
            case 4:
                view = inflater.inflate(R.layout.fragment_setting, container, false);
                break;
            default:
                view = inflater.inflate(R.layout.fragment_scan, container, false);
                initScan(view);
                break;
        }
        return view;
    }


    private void initScan(View view) {
        Button btnScan = (Button) view.findViewById(R.id.btnScan);
        btnScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentIntegrator.forSupportFragment(ControlFragment.this)
                        .setBarcodeImageEnabled(true)
                        .setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)
                        .initiateScan();


            }
        });


    }


    private void initHistory(View view) {

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyHistory);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        recyclerView.setAdapter(historyAdapter);
        historyAdapter.notifyDataSetChanged();

    }



    private void initFinds(View view) {
        final EditText edtBarcode=(EditText) view.findViewById(R.id.edtFinds);
        Button button = (Button) view.findViewById(R.id.btnFinds);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FindCode findCode = new FindCode(new InterChangeData() {
                    @Override
                    public void processFinish(HistoryFindModel output) {
                        HistoryFindModel historyFindModel = output;
                        Toast.makeText(getActivity(), output.getName() + "o0000", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getActivity(), ViewFindBarCodeActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("His", historyFindModel);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                });
                findCode.execute("https://www.barcodelookup.com/restapi?barcode="+edtBarcode.getText().toString().trim()+"&formatted=y&key=u0dvi9of387ts6b6d3u97q1g4q6dp9");
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        historyAdapter = new HistoryAdapter(historyModels, getContext());
        historyAdapter.notifyDataSetChanged();

    }

    @Override
    public void onPause() {
        super.onPause();
        historyAdapter = new HistoryAdapter(historyModels, getContext());
        historyAdapter.notifyDataSetChanged();
    }

    public void refresh() {
        if (getArguments().getInt("index", 0) > 0 && recyclerView != null) {
            recyclerView.smoothScrollToPosition(0);
        }
    }


    public void willBeDisplayed() {
        // Do what you want here, for example animate the content
        if (fragmentContainer != null) {
            Animation fadeIn = AnimationUtils.loadAnimation(getActivity(), R.anim.fade_in);
            fragmentContainer.startAnimation(fadeIn);
        }
    }

    /**
     * Called when a fragment will be hidden
     */
    public void willBeHidden() {
        if (fragmentContainer != null) {
            Animation fadeOut = AnimationUtils.loadAnimation(getActivity(), R.anim.fade_out);
            fragmentContainer.startAnimation(fadeOut);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(getActivity(), "You cancelled the scanning", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getActivity(), result.getFormatName(), Toast.LENGTH_LONG).show();
                String ketqua = result.getContents();

                if (result.getFormatName().equals("QR_CODE") ||
                        result.getFormatName().equals("AZTEC") ||
                        result.getFormatName().equals("DATA_MATRIX") ||
                        result.getFormatName().equals("PDF_417") ||
                        result.getFormatName().equals("MAXICODE")) {
                    if (ketqua.startsWith("http") || ketqua.startsWith("www.")) {
                        dbManager.addStudent(new HistoryModel(R.drawable.ic_back, "WEB", result.getContents()));
                        Intent intent = new Intent(getActivity(), WebViewActivity.class);
                        intent.putExtra("Link", ketqua);
                        startActivity(intent);
                    } else {
                        dbManager.addStudent(new HistoryModel(R.drawable.ic_back, "TEXT", result.getContents()));
                        Toast.makeText(getActivity(), result.getContents(), Toast.LENGTH_SHORT).show();
                    }
                } else if (result.getFormatName().equals("UCP_A") ||
                        result.getFormatName().equals("UPC_E") ||
                        result.getFormatName().equals("EAN_8") ||
                        result.getFormatName().equals("EAN_13")) {
                    dbManager.addStudent(new HistoryModel(R.drawable.ic_back, "BARCODE", result.getContents()));
                } else if (result.getFormatName().equals("RSS_EXPANDED")) {
                    dbManager.addStudent(new HistoryModel(R.drawable.ic_back, "RSS", result.getContents()));
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }


    class FindCode extends AsyncTask<String, Void, String> {


        public InterChangeData interChangeData = null;

        public FindCode(InterChangeData interChangeData) {
            this.interChangeData = interChangeData;
        }

        @Override
        protected String doInBackground(String... strings) {

            String url = strings[0];
            RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url
                    , null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {

                        JSONArray feed = response.getJSONArray("result");
                        JSONObject index = feed.getJSONObject(0);
                        JSONObject details = index.getJSONObject("details");
                        String product_name = details.getString("product_name");
                        Log.d("abc", product_name);

                        String mota = details.getString("long_description");
                        JSONObject imgage = index.getJSONObject("images");
                        String url = imgage.getString("0");
                        dbManagerFind.addStudent(new HistoryFindModel(url, product_name, mota));
                        interChangeData.processFinish(new HistoryFindModel(url, product_name, mota));
                        Log.d("SIZEOLD", dbManagerFind.getAllStudent().size() + "");
//                    itemAdapter.notifyDataSetChanged();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    });
            requestQueue.add(jsonObjectRequest);
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
//        if(ControlFragment.dbManagerFind.getAllStudent().size()!=0){
//            int a=ControlFragment.dbManagerFind.getAllStudent().size();
//            HistoryFindModel historyFindModel=ControlFragment.dbManagerFind.getSdtudentById(a+1);


//        }
        }
    }
}
