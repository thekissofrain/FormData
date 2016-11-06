package com.test.formdata;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.test.formdata.R;
import com.test.formdata.common.PreferencesManager;
import com.test.formdata.common.dao.UserDAO;
import com.test.formdata.dto.UserBean;

import utils.Constants;
import utils.CrossFadeUtils;


public class RegistrationActivity extends Activity {
Activity mActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        mActivity = this;
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CrossFadeUtils.crossFadeAnimation(mActivity, MainActivity.class, null, true, true);
            }
        });

        findViewById(R.id.confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveValuesIntoDB();
            }
        });




    }

    public  void saveValuesIntoDB()
    {

try {
    UserBean userBean = new UserBean();
    userBean.formno = getTextValue(R.id.formno);
    userBean.mobileno = getTextValue(R.id.mobileno);
    userBean.fname = getTextValue(R.id.fname);
    userBean.lname = getTextValue(R.id.lname);
    userBean.dob = getTextValue(R.id.birth);
    userBean.gender = getTextValue(R.id.gender);
    userBean.email = getTextValue(R.id.email);
    userBean.address = getTextValue(R.id.address);
    UserDAO userDAO = new UserDAO(this);
   userBean.id = String.valueOf(System.currentTimeMillis());
     userDAO.insertUserDetails(userBean);

    Toast.makeText(this," inserted successfully",Toast.LENGTH_SHORT).show();

}catch (Exception e)
{
    Toast.makeText(this," error in insertion ",Toast.LENGTH_SHORT).show();
    e.printStackTrace();
}




    }

    private  String getTextValue(int id)
    {
        String value = Constants.EMPTY_VAL;

        Editable e = ((EditText) findViewById(id)).getText();
        if(e!=null)
        {

            value = e.toString();
        }

        return value;
    }


}
