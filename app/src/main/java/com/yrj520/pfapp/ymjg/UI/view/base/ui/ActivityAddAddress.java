package com.yrj520.pfapp.ymjg.UI.view.base.ui;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.jock.pickerview.RegionInfo;
import com.jock.pickerview.dao.RegionDAO;
import com.jock.pickerview.view.OptionsPickerView;
import com.yrj520.pfapp.ymjg.R;
import com.yrj520.pfapp.ymjg.UI.utils.ToastUtils;
import com.yrj520.pfapp.ymjg.UI.view.base.BaseActivity;

import java.util.ArrayList;

/**
 * Title:
 * Description:
 * Copyright:
 * Company:
 *
 * @author Rock
 * @version 1.0
 */

public class ActivityAddAddress extends BaseActivity{
    private TextView tv_left;
    private TextView tv_center;
    private EditText et_lianxiren;
    private EditText et_mobile;
    private EditText et_detail_address;
    private RelativeLayout rl_region;
    private Switch switch_btn;

    OptionsPickerView pvOptions;

    static ArrayList<RegionInfo> item1;

    static ArrayList<ArrayList<RegionInfo>> item2 = new ArrayList<ArrayList<RegionInfo>>();

    static ArrayList<ArrayList<ArrayList<RegionInfo>>> item3 = new ArrayList<ArrayList<ArrayList<RegionInfo>>>();

    private Handler handler = new Handler()
    {
        public void handleMessage(android.os.Message msg)
        {
            System.out.println(System.currentTimeMillis());
            // 三级联动效果
            pvOptions.setPicker(item1, item2, item3, true);
            pvOptions.setCyclic(true, true, true);
            pvOptions.setSelectOptions(0, 0, 0);
            rl_region.setClickable(true);
        };
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_address);
        initViews();
        initClickListenner();
    }

    private void initClickListenner() {
        tv_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        switch_btn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){

                }else{

                }
            }
        });
        pvOptions.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener()
        {

            @Override
            public void onOptionsSelect(int options1, int option2, int options3)
            {
                // 返回的分别是三个级别的选中位置
                String tx = item1.get(options1).getPickerViewText() + item2.get(options1).get(option2).getPickerViewText() + item3.get(options1).get(option2).get(options3).getPickerViewText();
                ToastUtils.showShort(ActivityAddAddress.this,tx);
            }
        });
        rl_region.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pvOptions.show();
            }
        });
        rl_region.setClickable(false);
    }

    private void initViews() {
        tv_left=(TextView) findViewById(R.id.tv_left);
        tv_center=(TextView) findViewById(R.id.tv_center);
        et_lianxiren=(EditText) findViewById(R.id.et_lianxiren);
        et_mobile=(EditText) findViewById(R.id.et_mobile);
        et_detail_address=(EditText) findViewById(R.id.et_detail_address);
        rl_region=(RelativeLayout)findViewById(R.id.rl_region);
        switch_btn=(Switch) findViewById(R.id.switch_btn);
        tv_center.setText("新增收货地址");
        initCityChoice();

    }

    private void initCityChoice() {
        // 选项选择器
        pvOptions = new OptionsPickerView(this);
        pvOptions.setTitle("选择城市");
        new Thread(new Runnable()
        {

            @Override
            public void run()
            {
                // TODO Auto-generated method stub
                System.out.println(System.currentTimeMillis());
                if (item1 != null && item2 != null && item3 != null)
                {
                    handler.sendEmptyMessage(0x123);
                    return;
                }
                item1 = (ArrayList<RegionInfo>) RegionDAO.getProvencesOrCity(1);
                for (RegionInfo regionInfo : item1)
                {
                    item2.add((ArrayList<RegionInfo>) RegionDAO.getProvencesOrCityOnParent(regionInfo.getId()));

                }

                for (ArrayList<RegionInfo> arrayList : item2)
                {
                    ArrayList<ArrayList<RegionInfo>> list2 = new ArrayList<ArrayList<RegionInfo>>();
                    for (RegionInfo regionInfo : arrayList)
                    {



                        ArrayList<RegionInfo> q = (ArrayList<RegionInfo>) RegionDAO.getProvencesOrCityOnParent(regionInfo.getId());
                        list2.add(q);

                    }
                    item3.add(list2);
                }

                handler.sendEmptyMessage(0x123);

            }
        }).start();
    }
}
