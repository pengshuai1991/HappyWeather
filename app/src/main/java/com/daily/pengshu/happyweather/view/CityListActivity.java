package com.daily.pengshu.happyweather.view;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.daily.pengshu.happyweather.R;
import com.daily.pengshu.happyweather.adapter.CityAdapter;
import com.daily.pengshu.happyweather.adapter.SearchCityAdapter;
import com.daily.pengshu.happyweather.bean.City;
import com.daily.pengshu.happyweather.model.Application;
import com.daily.pengshu.happyweather.model.CityDB;
import com.daily.pengshu.happyweather.model.EventHandler;
import com.daily.pengshu.happyweather.util.L;
import com.daily.pengshu.happyweather.view.plistview.BladeView;
import com.daily.pengshu.happyweather.view.plistview.PinnedHeaderListView;

import java.util.List;
import java.util.Map;

public class CityListActivity extends Activity implements View.OnClickListener,EventHandler ,TextWatcher{



    private EditText mSearchEditText;
    // private Button mCancelSearchBtn;
    private ImageButton mClearSearchBtn;
    private View mCityContainer;
    private View mSearchContainer;
    private PinnedHeaderListView mCityListView;
    private BladeView mLetter;
    private ListView mSearchListView;
    private List<City> mCities;
    private SearchCityAdapter mSearchCityAdapter;
    private CityAdapter mCityAdapter;
    // 首字母集
    private List<String> mSections;
    // 根据首字母存放数据
    private Map<String, List<City>> mMap;
    // 首字母位置集
    private List<Integer> mPositions;
    // 首字母对应的位置
    private Map<String, Integer> mIndexer;
    private CityDB mCityDB;
    private Application mApplication;
    private InputMethodManager mInputMethodManager;

    private TextView mTitleTextView;
    private ImageView mBackBtn;
    private ProgressBar mTitleProgressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_list);
        Application.mListeners.add(this);

        initView();

        initData();

    }

    public void initData(){
        mApplication = Application.getInstance();
        mCityDB = mApplication.getmCityDB();
        mInputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);

        if (mApplication.isCityListComplite()) {
            mCities = mApplication.getCityList();
            mSections = mApplication.getSections();
            mMap = mApplication.getMap();
            mPositions = mApplication.getPositions();
            mIndexer = mApplication.getIndexer();

            mCityAdapter = new CityAdapter(CityListActivity.this, mCities,
                    mMap, mSections, mPositions);
            mCityListView.setAdapter(mCityAdapter);
            mCityListView.setOnScrollListener(mCityAdapter);
            mCityListView.setPinnedHeaderView(LayoutInflater.from(
                    CityListActivity.this).inflate(
                    R.layout.biz_plugin_weather_list_group_item, mCityListView,
                    false));
            mTitleProgressBar.setVisibility(View.GONE);
            mLetter.setVisibility(View.VISIBLE);

        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Application.mListeners.remove(this);
        finish();
    }

    public void initView(){

            mTitleTextView = (TextView) findViewById(R.id.title_name);
            mBackBtn = (ImageView) findViewById(R.id.title_back);
            mBackBtn.setOnClickListener(this);
            mTitleProgressBar = (ProgressBar) findViewById(R.id.title_update_progress);
            mTitleProgressBar.setVisibility(View.VISIBLE);
            mTitleTextView.setText(Application.getInstance()
                    .getSharePreferenceUtil().getCity());

            mSearchEditText = (EditText) findViewById(R.id.search_edit);
            mSearchEditText.addTextChangedListener(this);
            mClearSearchBtn = (ImageButton) findViewById(R.id.ib_clear_text);
            mClearSearchBtn.setOnClickListener(this);

            mCityContainer = findViewById(R.id.city_content_container);
            mSearchContainer = findViewById(R.id.search_content_container);
            mCityListView = (PinnedHeaderListView) findViewById(R.id.citys_list);
            mCityListView.setEmptyView(findViewById(R.id.citys_list_empty));
            mLetter = (BladeView) findViewById(R.id.citys_bladeview);
            mLetter.setOnItemClickListener(new BladeView.OnItemClickListener() {
                @Override
                public void onItemClick(String s) {
                    if(mIndexer.get(s) != null){
                        mCityListView.setSelection(mIndexer.get(s));

                    }

                }
            });
            mLetter.setVisibility(View.GONE);
            mSearchListView = (ListView) findViewById(R.id.search_list);
            mSearchListView.setEmptyView(findViewById(R.id.search_empty));
            mSearchContainer.setVisibility(View.GONE);
            mSearchListView.setOnTouchListener(new View.OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    // TODO Auto-generated method stub
                    mInputMethodManager.hideSoftInputFromWindow(
                            mSearchEditText.getWindowToken(), 0);
                    return false;
                }
            });

            mCityListView
                    .setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {

                        @Override
                        public void onItemClick(AdapterView<?> parent, View view,
                                                int position, long id) {
                            // TODO Auto-generated method stub
                            L.i(mCityAdapter.getItem(position).toString());
                            startActivity(mCityAdapter.getItem(position));
                        }
                    });

            mSearchListView
                    .setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {

                        @Override
                        public void onItemClick(AdapterView<?> parent, View view,
                                                int position, long id) {
                            // TODO Auto-generated method stub
                            L.i(mSearchCityAdapter.getItem(position).toString());
                            startActivity(mSearchCityAdapter.getItem(position));
                        }
                    });




    }

    private void startActivity(City item) {

        Intent data = new Intent();
        data.putExtra("city",item);
        setResult(RESULT_OK,data);
        finish();

    }


    @Override
    public void onCityComplite() {

    }

    @Override
    public void onNetChange() {

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.title_back:
                finish();
                break;
        }
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
            mSearchCityAdapter = new SearchCityAdapter(CityListActivity.this,mCities);
            mSearchListView.setAdapter(mSearchCityAdapter);
            mSearchListView.setTextFilterEnabled(true);

        if(mCities.size()<1 || TextUtils.isEmpty(s)){
            mCityContainer.setVisibility(View.VISIBLE);
            mSearchContainer.setVisibility(View.INVISIBLE);
            mClearSearchBtn.setVisibility(View.GONE);

        }else{
            mClearSearchBtn.setVisibility(View.VISIBLE);
            mCityContainer.setVisibility(View.INVISIBLE);
            mSearchContainer.setVisibility(View.VISIBLE);
            mSearchCityAdapter.getFilter().filter(s);
        }


    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
}
