package com.imsp.instagramliketabview.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.imsp.instagramliketabview.R;
import com.imsp.instagramliketabview.base.BaseFragment;

import java.util.Random;

/**
 * Created by SP on 28-02-2017.
 */

public class SampleFragment extends BaseFragment {

    View mView;

    TextView mText;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_sample,container,false);
        return mView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
        mText.setText(mActivity.getCountForCurrentTab());

        ((Button) mView.findViewById(R.id.goto_next)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActivity.pushFragments(mActivity.getCurrentTab(),new SampleFragment(),true,true);
            }
        });

//        Random rnd = new Random();
//        ((LinearLayout) mView.findViewById(R.id.base_layout)).setBackgroundColor(Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256)));
    }

    private void init() {
        mText = (TextView) mView.findViewById(R.id.text);
    }
}
