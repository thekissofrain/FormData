package com.test.formdata;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.test.formdata.common.PreferencesManager;
import com.test.formdata.common.dao.UserDAO;
import com.test.formdata.dto.UserBean;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import utils.CrossFadeUtils;


public class SearchActivity extends Activity implements View.OnClickListener {
    private static final String TAG = SearchActivity.class.getSimpleName();
    PreferencesManager mPreferencesManager;
    ArrayList<UserBean> userBeanArrayList;
    private Context mContext;
    UserListAdapter userListAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        mPreferencesManager = PreferencesManager.instance(this);
        userBeanArrayList = new ArrayList<>();
        setContentView(R.layout.activity_results);

        ListView listView = (ListView) findViewById(R.id.users_list);
        userListAdapter = new UserListAdapter(this, R.layout.item_users, userBeanArrayList, false);
        listView.setAdapter(userListAdapter);



        findViewById(R.id.search).setOnClickListener(this);
        findViewById(R.id.home).setOnClickListener(this);
        findViewById(R.id.add).setOnClickListener(this);
        findViewById(R.id.go_search_id).setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.home:
                CrossFadeUtils.crossFadeAnimation(this, MainActivity.class, null, true, true);
                break;


            case R.id.add:
                CrossFadeUtils.crossFadeAnimation(this, RegistrationActivity.class, null, true, true);
                break;


            case R.id.go_search_id:
                UserDAO userDAO = new UserDAO(mContext);

                if(((EditText) findViewById(R.id.search_txt)).getText()!=null) {
                    String search = ((EditText) findViewById(R.id.search_txt)).getText().toString();

                    List<UserBean> userBeans = userDAO.getUserDetails(search);

                    userBeanArrayList.clear();
                    userBeanArrayList.addAll(userBeans);
                    userListAdapter.notifyDataSetChanged();
                }

                break;


            default:
                break;
        }
    }


}
