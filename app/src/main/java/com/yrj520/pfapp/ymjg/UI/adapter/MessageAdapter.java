package com.yrj520.pfapp.ymjg.UI.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yrj520.pfapp.ymjg.R;
import com.yrj520.pfapp.ymjg.UI.entity.MessageData;

import java.util.ArrayList;
import java.util.List;

/**
 * Title:
 * Description:
 * Copyright:
 * Company:
 *
 * @author Rock
 * @version 1.0
 */

public class MessageAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater mInflater;
    private List<MessageData> mListData = new ArrayList<>();

    public MessageAdapter(Context context) {
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
    }

    public void removeAll() {
        mListData.clear();
        notifyDataSetChanged();
    }
    public void addAll(List<MessageData> list) {
        mListData.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mListData.size();
    }

    @Override
    public MessageData getItem(int position) {
        return mListData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.message_item, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final MessageData data = getItem(position);
        holder.tv_title.setText(data.getTitle().toString());
        holder.tv_time.setText(data.getAdd_time().toString());
        holder.tv_content.setText(data.getContent().toString());
        return convertView;
    }

    private class ViewHolder {
        TextView tv_title;
        TextView tv_content;
        TextView tv_time;
        final View root;
        ViewHolder(View root) {
            this.root = root;
            tv_title = (TextView) root.findViewById(R.id.tv_title);
            tv_content = (TextView) root.findViewById(R.id.tv_content);
            tv_time = (TextView) root.findViewById(R.id.tv_time);
        }
    }
}
