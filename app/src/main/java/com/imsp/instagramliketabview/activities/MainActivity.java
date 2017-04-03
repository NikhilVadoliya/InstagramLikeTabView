package com.imsp.instagramliketabview.activities;

import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.imsp.instagramliketabview.R;
import com.imsp.instagramliketabview.base.AppConstants;
import com.imsp.instagramliketabview.base.BaseActivity;
import com.imsp.instagramliketabview.fragments.SampleFragment;

public class MainActivity extends BaseActivity {

    Button mBtnA;
    Button mBtnB;
    Button mBtnC;
    Button mBtnD;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        setClickListenerToButton(mBtnA, AppConstants.TAB_A);
        setClickListenerToButton(mBtnB, AppConstants.TAB_B);
        setClickListenerToButton(mBtnC, AppConstants.TAB_C);
        setClickListenerToButton(mBtnD, AppConstants.TAB_D);

        pushFragments(AppConstants.TAB_A,new SampleFragment(),false,true);

    }


    private void setClickListenerToButton(Button button, final String tabId) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeViewTab(tabId);
//                pushFragments(tabId,new SampleFragment(),false,true);
            }
        });
    }

    private void init() {
        mBtnA = (Button) findViewById(R.id.tab_a);
        mBtnB = (Button) findViewById(R.id.tab_b);
        mBtnC = (Button) findViewById(R.id.tab_c);
        mBtnD = (Button) findViewById(R.id.tab_d);
    }
}
