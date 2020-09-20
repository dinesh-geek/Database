package com.appstone.database;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DynamicActivity extends AppCompatActivity {
private LinearLayout mLinearLayout;
private  Button mAddListItem;
private Button mAddData;

private int itemId;
ArrayList<ItemValue> items;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic);

        mLinearLayout = findViewById(R.id.ll_dynamic);

        mAddListItem = findViewById(R.id.btn_add_new_list);

        mAddData = findViewById(R.id.btn_add);

        mAddListItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNewListItem();
            }
        });
        mAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addData();
            }
        });
        items = new ArrayList<>();
    }
    private void addData(){
        if(items.size()>0){
            JSONArray itemArray = new JSONArray();
            for (ItemValue itemValue : items){
                JSONObject itemObject = new JSONObject();
                try {
                    itemObject.put("itemID", itemValue.itemId);
                    itemObject.put("itemName", itemValue.itemName);
                    itemObject.put("isChecked", itemValue.isChecked);
                    itemArray.put(itemObject);
                }catch (JSONException e){
                    e.printStackTrace();
                }
            }
            itemArray.toString();
        }
    }
    private void addNewListItem(){
     mAddListItem.setEnabled(false);
     mAddListItem.setAlpha((float)0.51);
        mAddData.setEnabled(false);
        mAddData.setAlpha((float) 0.51);
    itemId++;

        View newView = LayoutInflater.from(DynamicActivity.this).inflate(R.layout.cell_insert_data,null);
        CheckBox ch = newView.findViewById(R.id.ch_insert);
        final EditText mEtListItem =  newView.findViewById(R.id.et_list_item);
        final ImageView MIvDone = newView.findViewById(R.id.iv_done);
        MIvDone.setVisibility(View.GONE);
        mEtListItem.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
          if(editable.length()>0){
              MIvDone.setVisibility(View.VISIBLE);
          }else{
              MIvDone.setVisibility(View.GONE);
          }
            }
        });
        MIvDone.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("Range")
            @Override
            public void onClick(View view) {
               MIvDone.setVisibility(View.GONE);
               mAddListItem.setEnabled(true);
               mAddData.setEnabled(true);
                mAddData.setAlpha((float) 1.01);
                mAddListItem.setAlpha((float) 1.01);
                ItemValue value = new ItemValue();
                value.itemId = itemId;
                value.itemName = mEtListItem.getText().toString();
                items.add(value);
            }
        });
        mLinearLayout.addView(newView);
    }
    class ItemValue{
    public int itemId;
    public  String itemName;
    public Boolean isChecked;
    }
}