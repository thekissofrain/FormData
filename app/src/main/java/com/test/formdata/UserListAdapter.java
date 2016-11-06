package com.test.formdata;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.test.formdata.dto.UserBean;

import java.util.ArrayList;
import java.util.List;

public class UserListAdapter extends ArrayAdapter<UserBean> {
    private Context mContext;
    private List<UserBean> mSearchListBeanList;
    private LayoutInflater inflater;
    private boolean sms;
    private int[] colors = new int[] { 0x30c2c6c9, 0x30FFFFFF };
    private List<String> mobileList = new ArrayList<String>();
    private List<String> emailList = new ArrayList<String>();

    public UserListAdapter(Context context, int resource, List<UserBean> objects, boolean isSms) {
        super(context, resource, objects);
        this.mContext = context;
        this.mSearchListBeanList = objects;
        sms = isSms;

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        try {

            if (convertView == null) {
                convertView = inflater.inflate(R.layout.item_users, null);
                viewHolder = new ViewHolder();
                viewHolder.formno = (TextView) convertView.findViewById(R.id.formno);
                viewHolder.mobileno = (TextView) convertView.findViewById(R.id.mobileno);
                viewHolder.address = (TextView) convertView.findViewById(R.id.address);
                viewHolder.email = (TextView) convertView.findViewById(R.id.email);
                viewHolder.fname = (TextView) convertView.findViewById(R.id.fname);
                viewHolder.lname = (TextView) convertView.findViewById(R.id.lname);
                viewHolder.gender = (TextView) convertView.findViewById(R.id.gender);
                viewHolder.dob = (TextView) convertView.findViewById(R.id.dob);

                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            final UserBean userBean = mSearchListBeanList.get(position);

            viewHolder.formno.setText("FORM NO :"+userBean.formno);
            viewHolder.mobileno.setText("MOBILE NO :"+userBean.mobileno);
            viewHolder.email.setText("EMAIL :"+userBean.email);
            viewHolder.gender.setText("GENDER :"+userBean.gender);
            viewHolder.lname.setText("LNAME :"+userBean.lname);
            viewHolder.fname.setText("FNAME :"+userBean.fname);
            viewHolder.dob.setText("DOB :"+userBean.dob);
            viewHolder.address.setText("Address :"+userBean.address);

        }catch (Exception e){
            e.printStackTrace();
        }
        return convertView;
    }

    class ViewHolder{
       TextView email;
        TextView phno;
        TextView id;
        TextView formno;
        TextView mobileno;
        TextView fname;
        TextView lname;
        TextView gender;
        TextView dob;
        TextView address;
    }
}
