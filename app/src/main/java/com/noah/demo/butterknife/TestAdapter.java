package com.noah.demo.butterknife;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/5/15.
 */

public class TestAdapter extends BaseAdapter {
    private Context context;
    List<ListItem> items;

    public TestAdapter(Context context, List<ListItem> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHold;
        if (null == convertView) {
            //在list_item上右键，Generate-》Generate Butterknife Injections  勾选create ViewHolder可自动生成ViewHolder
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item, null);
            viewHold = new ViewHolder(convertView);
            convertView.setTag(viewHold);
        } else {
            viewHold = (ViewHolder) convertView.getTag();
        }
        viewHold.textView.setText("item" + getItemId(position));
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.imageView)
        ImageView imageView;
        @BindView(R.id.button)
        Button button;
        @BindView(R.id.textView)
        TextView textView;

        @OnClick(R.id.button)
        void onClick() {

        }

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    static class ListItem {
        public int imgResId;
        public String text;
    }
}
