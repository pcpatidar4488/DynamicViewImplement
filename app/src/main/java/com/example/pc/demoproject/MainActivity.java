package com.example.pc.demoproject;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pc.demoproject.database.DBHandler;
import com.example.pc.demoproject.database.modelClass.ClientDetailsModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Map;


public class MainActivity extends AppCompatActivity {

    Button addReference,submit;
    EditText Cname,Cmobil,Cemail,Aname,Amobile,Caddress,Cstatus;
    TextView date;
    View mConvertView;
    Context context;
    LinearLayout mAdd_view;
    LinearLayout mList;
    private SimpleDateFormat dateFormatter;
    private DatePickerDialog DatePickerDialog1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);
        dateFormatter = new SimpleDateFormat("dd-MMM-yyyy", Locale.US);
        addReference=(Button)findViewById (R.id.addReference);
        submit=(Button)findViewById (R.id.submit);
        mAdd_view=(LinearLayout)findViewById (R.id.add_view);
        Cname=(EditText)findViewById (R.id.Cname);
        Cmobil=(EditText)findViewById (R.id.Cmobil);
        Cemail=(EditText)findViewById (R.id.Cemail);
        Aname=(EditText)findViewById (R.id.Aname);
        Amobile=(EditText)findViewById (R.id.Amobile);
        Caddress=(EditText)findViewById (R.id.Caddress);
        Cstatus=(EditText)findViewById (R.id.Cstatus);
        mList=(LinearLayout)findViewById (R.id.list);

        new DBHandler(getApplicationContext()).createTable(null);
        addReference.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d ("Main"," Activity");
                Calendar newDate = Calendar.getInstance();
                String date1 = dateFormatter.format(newDate.getTime());
                addView(date1);
            }
        });

        mList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHandler db = new DBHandler(getApplicationContext());
                ArrayList<ClientDetailsModel> list = new ArrayList<>();
                list = db.getAllDataList();
                if (list.size()>0){
                    Intent intent = new Intent (MainActivity.this,DetailListActivity.class);
                    startActivity (intent);
                }else {
                    Toast.makeText(MainActivity.this, "Data list is empty!",
                            Toast.LENGTH_SHORT).show();
                }

            }
        });

        submit.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {

                if (Cname.getText().toString().equals("")){
                    Toast.makeText(MainActivity.this, "Enter Client Name!",
                            Toast.LENGTH_SHORT).show();
                    return;
                }if (Cmobil.getText().toString().equals("")){
                    Toast.makeText(MainActivity.this, "Enter Client Name!",
                            Toast.LENGTH_SHORT).show();
                    return;
                }if (Cemail.getText().toString().equals("")){
                    Toast.makeText(MainActivity.this, "Enter Client Name!",
                            Toast.LENGTH_SHORT).show();
                    return;
                }if (Aname.getText().toString().equals("")){
                    Toast.makeText(MainActivity.this, "Enter Client Name!",
                            Toast.LENGTH_SHORT).show();
                    return;
                }if (Amobile.getText().toString().equals("")){
                    Toast.makeText(MainActivity.this, "Enter Client Name!",
                            Toast.LENGTH_SHORT).show();
                    return;
                }if (Caddress.getText().toString().equals("")){
                    Toast.makeText(MainActivity.this, "Enter Client Name!",
                            Toast.LENGTH_SHORT).show();
                    return;
                }if (Cstatus.getText().toString().equals("")){
                    Toast.makeText(MainActivity.this, "Enter Client Name!",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                Boolean bool = false;
                String cName = Cname.getText().toString();
                String cMobile = Cmobil.getText().toString();
                String cEmail = Cemail.getText().toString();
                String aName = Aname.getText().toString();
                String aMobile = Amobile.getText().toString();
                String cAddress = Caddress.getText().toString();
                String cStatus = Cstatus.getText().toString();


                    for (int i = 0; i < ((ViewGroup) mAdd_view).getChildCount(); i++) {
                        View v = ((ViewGroup) mAdd_view).getChildAt(i);
                        Spinner productSpinner = ((Spinner) v.findViewById(R.id.product));
                        Spinner referenceSpinner = ((Spinner) v.findViewById(R.id.reference));
                        TextView date=(TextView) v.findViewById (R.id.date);
                        if (productSpinner.getSelectedItem().toString().equals("Product")) {
                            Toast.makeText(MainActivity.this, "Select Product!",
                                    Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if (referenceSpinner.getSelectedItem().toString().equals("Reference")) {
                            Toast.makeText(MainActivity.this, "Select Reference Name!",
                                    Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if (date.getText().toString().equals("")) {
                            Toast.makeText(MainActivity.this, "Select Date!",
                                    Toast.LENGTH_SHORT).show();
                            return;
                        }

                        ClientDetailsModel data = new ClientDetailsModel();
                        data.setC_name(cName);
                        data.setC_mobile(cMobile);
                        data.setC_email(cEmail);
                        data.setA_name(aName);
                        data.setA_mobile(aMobile);
                        data.setC_address(cAddress);
                        data.setC_status(cStatus);
                        data.setProduct_name(productSpinner.getSelectedItem().toString());
                        data.setReference_name(referenceSpinner.getSelectedItem().toString());
                        data.setDate(date.getText().toString());

                        DBHandler db = new DBHandler(getApplicationContext());
                        long result = db.insertData(data);
                        if (result!=-1){
                            bool = true;
                        }else {
                            bool = false;
                        }

                  /*  Map map = new HashMap();
                    map.put("product",productSpinner.getSelectedItem().toString());
                    map.put("reference",referenceSpinner.getSelectedItem().toString());
                    map.put("date",date.getText().toString());
                    listMap.add(map);*/
                    }
                    if (((ViewGroup) mAdd_view).getChildCount()>0){
                        if (bool){
                            Toast.makeText(MainActivity.this, "Data success fully inserted!",
                                    Toast.LENGTH_SHORT).show();
                            Cname.setText("");
                            Cmobil.setText("");
                            Cemail.setText("");
                            Aname.setText("");
                            Amobile.setText("");
                            Caddress.setText("");
                            Cstatus.setText("");
                            ((ViewGroup) mAdd_view).removeAllViews();
                            Intent intent = new Intent (MainActivity.this,DetailListActivity.class);
                            startActivity (intent);
                        }else {
                            Toast.makeText(MainActivity.this, "Error!",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(MainActivity.this, "Please add reference!",
                                Toast.LENGTH_SHORT).show();
                    }
            }
        });
    }
    private void addView(String date1) {
        mConvertView = getLayoutInflater().inflate(R.layout.daynamic_add, null);
        Spinner productSpinner = ((Spinner) mConvertView.findViewById(R.id.product));
        Spinner referenceSpinner = ((Spinner) mConvertView.findViewById(R.id.reference));
        final TextView date=(TextView) mConvertView.findViewById (R.id.date);
        ((ViewGroup) mAdd_view).addView(mConvertView);
        date.setText(date1);
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDateField(date);
            }
        });

    }

    private void setDateField(final TextView textView) {


        final Calendar newCalendar = Calendar.getInstance();


        DatePickerDialog1 = new DatePickerDialog(this, new android.app.DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                String date1 = dateFormatter.format(newDate.getTime());
                textView.setText(date1);
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        DatePickerDialog1.show();
    }


}
