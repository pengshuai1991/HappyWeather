package com.daily.pengshu.happyweather.view;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
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
import com.daily.pengshu.happyweather.view.plistview.BladeView;
import com.daily.pengshu.happyweather.view.plistview.PinnedHeaderListView;

import java.util.List;
import java.util.Map;

/**
 * Created by pengshu on 16/9/29.
 */
public class SelectCityActivity extends Activity implements TextWatcher,View.OnClickListener,EventHandler{


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
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activtiy_selcetcity);
        Application.mListeners.add(this);
        initViews();
        initData();

    }

    public void initData(){

    }
    private void initViews() {
        mTitleTextView = (TextView) findViewById(R.id.title_name);
        mBackBtn = (ImageView) findViewById(R.id.title_back);
        mBackBtn.setOnClickListener(this);
        mTitleProgressBar = (ProgressBar) findViewById(R.id.title_update_progress);
        mTitleProgressBar.setVisibility(View.VISIBLE);
        mTitleTextView.setText(Application.getInstance()
                .getSharePreferenceUtil().getCity());
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {

    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onCityComplite() {

    }

    @Override
    public void onNetChange() {

    }
}
