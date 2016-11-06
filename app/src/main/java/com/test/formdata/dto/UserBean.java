package com.test.formdata.dto;

import java.util.ArrayList;

/**
 * Created by illa on 10-07-2016.
 */
public class UserBean {


    public  String email;
    public  String id;
    public  String formno;
    public  String mobileno;
    public  String fname;
    public  String lname;
    public  String gender;
    public  String dob;
    public  String address;


    @Override
    public String toString() {
        return "Fname "+fname+" ph "+mobileno+" gender "+gender+" formid "+formno;

    }
}
