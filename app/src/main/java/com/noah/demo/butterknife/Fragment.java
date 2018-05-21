package com.noah.demo.butterknife;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/5/15.
 */

public class Fragment extends android.support.v4.app.Fragment {

    Unbinder unbinder;

    @BindView(R.id.listview)
    ListView listview;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_test, null);
        unbinder = ButterKnife.bind(this, view);
        List<TestAdapter.ListItem> items = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            items.add(new TestAdapter.ListItem());
        }
        listview.setAdapter(new TestAdapter(getContext(), items));
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
