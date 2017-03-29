package com.yrj520.pfapp.ymjg.UI.view.base.ui;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.jock.pickerview.RegionInfo;
import com.jock.pickerview.dao.RegionDAO;
import com.jock.pickerview.view.OptionsPickerView;
import com.yrj520.pfapp.ymjg.R;
import com.yrj520.pfapp.ymjg.UI.api.UserApi;
import com.yrj520.pfapp.ymjg.UI.constant.MyConstant;
import com.yrj520.pfapp.ymjg.UI.entity.ConsigneeData;
import com.yrj520.pfapp.ymjg.UI.event.AddressEvent;
import com.yrj520.pfapp.ymjg.UI.net.HttpUtil;
import com.yrj520.pfapp.ymjg.UI.utils.StringUtils;
import com.yrj520.pfapp.ymjg.UI.utils.ToastUtils;
import com.yrj520.pfapp.ymjg.UI.view.base.BaseActivity;

import org.json.JSONObject;

import java.util.ArrayList;

import de.greenrobot.event.EventBus;
import okhttp3.Request;

import static com.yrj520.pfapp.ymjg.R.id.tv_left;

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
    private RelativeLayout rl_left;
    private TextView tv_center;
    private EditText et_lianxiren;
    private EditText et_mobile;
    private EditText et_detail_address;
    private ConsigneeData consigneeData;

    private Button btn_save;
    private TextView tv_place;
    private RelativeLayout rl_region;
    private Switch switch_btn;

    private String provience;


    //收货地址 1是默认的 0不是默认的
    private String isDefault="0";

    private String city;

    private String area;

    OptionsPickerView pvOptions;

    static ArrayList<RegionInfo> item1;

    static ArrayList<ArrayList<RegionInfo>> item2 = new ArrayList<ArrayList<RegionInfo>>();

    static ArrayList<ArrayList<ArrayList<RegionInfo>>> item3 = new ArrayList<ArrayList<ArrayList<RegionInfo>>>();

    private Handler handler = new Handler()
    {
        public void handleMessage(android.os.Message msg)
        {
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
        rl_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        switch_btn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    isDefault="1";
                }else{
                    isDefault="0";
                }
            }
        });
        pvOptions.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener()
        {

            @Override
            public void onOptionsSelect(int options1, int option2, int options3)
            {
                // 返回的分别是三个级别的选中位置
                provience=item1.get(options1).getPickerViewText();
                city=item2.get(options1).get(option2).getPickerViewText();
                area=item3.get(options1).get(option2).get(options3).getPickerViewText();
                String tx = provience+ " "+ city+" "+area;
                tv_place.setText(tx);
            }
        });
        rl_region.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pvOptions.show();
            }
        });
        rl_region.setClickable(false);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(inputCheck()){
                UserApi.UpdateAddressApi(ActivityAddAddress.this, consigneeData, new HttpUtil.RequestBack() {
                    @Override
                    public void onSuccess(JSONObject response) {
                        String code=response.optString("code");
                        String meg=response.optString("meg");
                        ToastUtils.showShort(ActivityAddAddress.this,meg);
                        if(code.equals("200")){
                            AddressEvent addressEvent=new AddressEvent(MyConstant.AddAddress);
                            EventBus.getDefault().post(addressEvent);
                            finish();
                        }
                    }

                    @Override
                    public void onError(Exception e) {

                    }

                    @Override
                    public void onBefore(Request request) {
                        super.onBefore(request);
                        showLoading("保存中");
                    }

                    @Override
                    public void onAfter() {
                        super.onAfter();
                        closeLoading();
                    }
                });
            }
        }
    });
    }

    private boolean inputCheck(){
        consigneeData=new ConsigneeData();
        if(StringUtils.isEmpty(provience)) {
            ToastUtils.showShort(this,"请选择省份！");
            return false;
        }
        if(StringUtils.isEmpty(city)) {
            ToastUtils.showShort(this,"请选择城市！");
            return false;
        }
        if(StringUtils.isEmpty(area)) {
            ToastUtils.showShort(this,"请选择区域！");
            return false;
        }
        if(StringUtils.isEmpty(area)) {
            ToastUtils.showShort(this,"请选择区域！");
            return false;
        }
        if(StringUtils.isEmpty(et_detail_address.getText())){
            ToastUtils.showShort(this,"请填写详细地址！");
            return false;
        }
        if(StringUtils.isEmpty(et_lianxiren.getText())){
            ToastUtils.showShort(this,"请填写联系人名字！");
            return false;
        }
        if(StringUtils.isEmpty(et_mobile.getText())&&!StringUtils.checkMobileNumber(et_mobile.getText().toString())){
            ToastUtils.showShort(this,"请填写正确的手机号！");
            return false;
        }
        consigneeData.setAddress_id("");
        consigneeData.setArea_id(area);
        consigneeData.setCity(city);
        consigneeData.setConsignee(et_lianxiren.getText().toString());
        consigneeData.setIsDefault(isDefault);
        consigneeData.setProvice(provience);
        consigneeData.setSh_address(et_detail_address.getText().toString());
        consigneeData.setSh_phone(et_mobile.getText().toString());
        return true;
    }

    private void initViews() {
        btn_save=(Button)findViewById(R.id.btn_save);
        rl_left=(RelativeLayout)findViewById(R.id.rl_left);
        tv_center=(TextView) findViewById(R.id.tv_center);
        et_lianxiren=(EditText) findViewById(R.id.et_lianxiren);
        et_mobile=(EditText) findViewById(R.id.et_mobile);
        et_detail_address=(EditText) findViewById(R.id.et_detail_address);
        rl_region=(RelativeLayout)findViewById(R.id.rl_region);
        switch_btn=(Switch) findViewById(R.id.switch_btn);
        tv_place=(TextView)findViewById(R.id.tv_place);
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
