package com.yrj520.pfapp.ymjg.UI.view.base;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yrj520.pfapp.ymjg.R;



public class MyBarView extends LinearLayout {
    private View rightView;
    private Button searchView;
    private TextView leftTextView;// 左边文字
    private TextView rightTextView;// 右边文字
    private TextView centerTextView;// 中间
    private ImageView backImageView;// 左边icon
    private ImageView rightImageView;//右边iconbutton
    private View bottomLine;
    private View backView;// 左边点击view
    private View contentView;
    private OnClickListener backClickListener;
    private String leftText;
    private String rightText;
    private String centerText;
    private boolean isShowReturnIcon;
    private int bgColor;// 背景色
    private int leftColor;// 左边文字颜色
    private int centerColor;// 中间文字颜色
    private int rightColor;// 右边文字颜色
    private boolean isSearch;
    private boolean showLine;
    private Drawable rightImage;

    public MyBarView(Context context) {
        super(context);

    }

    public MyBarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.main_action_bar, this,
                true);
        initView();
        TypedArray mTypedArray = context.obtainStyledAttributes(attrs,
                R.styleable.MyActionBar1);
        // 获取自定义属性和默认值
        leftText = mTypedArray.getString(R.styleable.MyActionBar1_leftText);
        if (!TextUtils.isEmpty(leftText)) {
            leftTextView.setVisibility(View.VISIBLE);
            leftTextView.setText(leftText);
        }

        rightText = mTypedArray.getString(R.styleable.MyActionBar1_rightText);
        if (!TextUtils.isEmpty(rightText)) {
            rightTextView.setVisibility(View.VISIBLE);
            rightTextView.setText(rightText);
        }
        centerText = mTypedArray.getString(R.styleable.MyActionBar1_centerText);
        if (!TextUtils.isEmpty(centerText)) {
            centerTextView.setVisibility(View.VISIBLE);
            centerTextView.setText(centerText);
        }
        isShowReturnIcon = mTypedArray.getBoolean(
                R.styleable.MyActionBar1_isShowReturnIcon, false);
        backImageView
                .setVisibility(isShowReturnIcon ? View.VISIBLE : View.GONE);

        bgColor = mTypedArray.getColor(R.styleable.MyActionBar1_background1, -1);
        if (bgColor != -1) {
            contentView.setBackgroundColor(bgColor);
        }

        leftColor = mTypedArray.getColor(R.styleable.MyActionBar1_leftColor, -1);
        if (leftColor != -1) {
            leftTextView.setTextColor(leftColor);
        }
        centerColor = mTypedArray.getColor(R.styleable.MyActionBar1_centerColor,
                -1);
        if (centerColor != -1) {
            centerTextView.setTextColor(centerColor);
        }

        rightColor = mTypedArray.getColor(R.styleable.MyActionBar1_rightColor,
                -1);
        if (rightColor != -1) {
            rightTextView.setTextColor(rightColor);
        }

        isSearch = mTypedArray.getBoolean(R.styleable.MyActionBar1_search, false);
        if (isSearch) {
            searchView.setVisibility(VISIBLE);
        }

        rightImage = mTypedArray.getDrawable(R.styleable.MyActionBar1_rightImage);
        if (rightImage != null) {
            rightImageView.setImageDrawable(rightImage);
            rightImageView.setVisibility(VISIBLE);
        }

        showLine = mTypedArray.getBoolean(R.styleable.MyActionBar1_showLine, true);
        if (showLine) {
            bottomLine.setVisibility(VISIBLE);
        } else {
            bottomLine.setVisibility(GONE);

        }

        mTypedArray.recycle();
    }

    public MyBarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 初始化view
     */
    private void initView() {
        bottomLine = findViewById(R.id.bar_line);
        rightView = findViewById(R.id.ll_bar_rightView);
        leftTextView = (TextView) findViewById(R.id.tv_bar_left);
        rightTextView = (TextView) findViewById(R.id.tv_bar_right);
        centerTextView = (TextView) findViewById(R.id.tv_bar_center);
        backImageView = (ImageView) findViewById(R.id.ib_bar_image);
        rightImageView = (ImageView) findViewById(R.id.iv_bar_rightImage);
        searchView = (Button) findViewById(R.id.et_bar_search);
        contentView = findViewById(R.id.rl_bar_content);
        backView = findViewById(R.id.ll_bar_back);
        backClickListener = new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                ((Activity) getContext()).finish();
            }
        };
        backView.setOnClickListener(backClickListener);
    }

    public String getLeftText() {
        return leftText;
    }

    /**
     * 设置左边图片
     *
     * @param resId
     */
    public void setLeftImage(int resId) {
        if (resId != -1) {
            backImageView.setImageResource(resId);
            backImageView.setVisibility(VISIBLE);
        }
    }

    /**
     * 设置左边文字
     *
     * @param leftText
     */
    public void setLeftText(String leftText) {
        this.leftText = leftText;
        if (!TextUtils.isEmpty(leftText)) {
            leftTextView.setVisibility(View.VISIBLE);
            leftTextView.setText(leftText);
        } else {
            leftTextView.setVisibility(View.GONE);
        }
    }

    /**
     * 左边点击事件
     *
     * @param backClickListener
     */
    public void setBackListener(OnClickListener backClickListener) {
        this.backClickListener = backClickListener;
        if (backClickListener != null) {
            backView.setOnClickListener(backClickListener);
        }
    }

    public String getRightText() {
        return rightText;
    }

    /**
     * 右边文字
     *
     * @param rightText
     */
    public void setRightText(String rightText) {
        this.rightText = rightText;
        if (!TextUtils.isEmpty(rightText)) {
            rightTextView.setText(rightText);
            rightTextView.setVisibility(VISIBLE);
            rightView.setVisibility(VISIBLE);
        } else {
            rightTextView.setVisibility(View.GONE);
            rightView.setVisibility(GONE);
        }
    }

    /**
     * 右边文字,包含点击事件
     *
     * @param rightText
     * @param rightClickListener
     */
    public void setRightText(String rightText,
                             OnClickListener rightClickListener) {
        setRightText(rightText);
        if (rightClickListener != null) {
            rightView.setOnClickListener(rightClickListener);
        }
    }

    /**
     * 右边点击事件 Description: CreateTime:2015-8-10 下午3:23:13
     *
     * @param rightClickListener
     */
    public void setRightTextListener(OnClickListener rightClickListener) {
        if (rightClickListener != null) {
            rightView.setOnClickListener(rightClickListener);
        }
    }

    public String getCenterText() {
        return centerText;
    }

    /**
     * 标题
     *
     * @param centerText
     */
    public void setCenterText(String centerText) {
        this.centerText = centerText;
        if (!TextUtils.isEmpty(centerText)) {
            centerTextView.setVisibility(View.VISIBLE);
            centerTextView.setText(centerText);
        } else {
            centerTextView.setVisibility(View.GONE);
        }
    }

    /**
     * 标题,包含点击事件
     *
     * @param centerText
     * @param centerClickListener
     */
    public void setCenterText(String centerText,
                              OnClickListener centerClickListener) {
        setCenterText(centerText);
        if (centerClickListener != null) {
            centerTextView.setOnClickListener(centerClickListener);
        }
    }

    public boolean isShowReturnIcon() {
        return isShowReturnIcon;
    }

    public void setIsShowReturnIcon(boolean isShowReturnIcon) {
        this.isShowReturnIcon = isShowReturnIcon;
        backImageView
                .setVisibility(isShowReturnIcon ? View.VISIBLE : View.GONE);
        if (!isShowReturnIcon && TextUtils.isEmpty(leftText)) {
            backView.setVisibility(INVISIBLE);
        } else {
            backView.setVisibility(VISIBLE);
        }
    }

    /**
     * Description: 设置背景颜色
     * CreateTime:2015-8-11 上午10:02:17
     *
     * @param bgColor
     */
    public void setBgColor(int bgColor) {
        this.bgColor = bgColor;
        if (bgColor != -1) {
            contentView.setBackgroundColor(bgColor);
        }
    }

    /**
     * Description: 左边文字颜色
     * CreateTime:2015-8-11 下午3:13:47
     *
     * @param leftColor
     */
    public void setLeftColor(int leftColor) {
        this.leftColor = leftColor;
        if (leftColor != -1) {
            leftTextView.setTextColor(leftColor);
        }
    }

    /**
     * Description:中间文字颜色
     * CreateTime:2015-8-11 下午3:14:04
     *
     * @param centerColor
     */
    public void setCenterColor(int centerColor) {
        this.centerColor = centerColor;
        if (centerColor != -1) {
            centerTextView.setTextColor(centerColor);
        }

    }

    /**
     * Description: 右边文字颜色
     * CreateTime:2015-8-11 下午3:14:15
     *
     * @param rightColor
     */
    public void setRightColor(int rightColor) {
        this.rightColor = rightColor;
        if (rightColor != -1) {
            rightTextView.setTextColor(rightColor);
        }
    }

    /**
     * 搜索框点击事件
     *
     * @param searchTextViewListener
     */
    public void setSearchViewListener(OnClickListener searchTextViewListener) {
        if (searchTextViewListener != null) {
            searchView.setOnClickListener(searchTextViewListener);
        }
    }

    /**
     * 右边图片按钮
     */
    public void setRightImageView(OnClickListener rightImageListener) {
        if (rightImageListener != null) {
            rightView.setOnClickListener(rightImageListener);
        }
    }

    public void setRightImageView(int resId) {
        if (resId != -1) {
            rightImageView.setImageResource(resId);
            rightImageView.setVisibility(VISIBLE);
        }
    }

    /**
     * 左边文字点击
     *
     * @param clickListener
     */
    public void setLeftTextListener(OnClickListener clickListener) {
        if (clickListener != null) {
            leftTextView.setOnClickListener(clickListener);
        }
    }

    public View getRigitImageView() {
        return rightImageView;
    }
}
