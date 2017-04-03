package com.imsp.instagramliketabview.base;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.imsp.instagramliketabview.R;
import com.imsp.instagramliketabview.base.AppConstants;
import com.imsp.instagramliketabview.base.BaseFragment;
import com.imsp.instagramliketabview.fragments.SampleFragment;

import java.util.HashMap;
import java.util.Stack;

/**
 * Created by SP on 28-02-2017.
 */

public class BaseActivity extends FragmentActivity {

    private HashMap<String, Stack<Fragment>> mFragmentHolder;
    private String mCurrentTab;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initTabs();

    }


    public String getCountForCurrentTab(){
        return "Size for " + mCurrentTab + " is " + mFragmentHolder.get(mCurrentTab).size();
    }

    public String getCurrentTab(){
        return mCurrentTab;
    }
    private void initTabs() {
        mFragmentHolder = new HashMap<String, Stack<Fragment>>();
        mFragmentHolder.put(AppConstants.TAB_A, new Stack<Fragment>());
        mFragmentHolder.put(AppConstants.TAB_B, new Stack<Fragment>());
        mFragmentHolder.put(AppConstants.TAB_C, new Stack<Fragment>());
        mFragmentHolder.put(AppConstants.TAB_D, new Stack<Fragment>());
    }

    public void changeViewTab(String tabId){
        mCurrentTab = tabId;
        if(mFragmentHolder.get(tabId).size() == 0){
            if(tabId.equals(AppConstants.TAB_A)){
                pushFragments(tabId,new SampleFragment(),false,true); // set fragment as per your initial fragment for tab a
            }
            else if(tabId.equals(AppConstants.TAB_B)){
                pushFragments(tabId,new SampleFragment(),false,true); // set fragment as per your initial fragment for tab b
            }
            else if(tabId.equals(AppConstants.TAB_C)){
                pushFragments(tabId,new SampleFragment(),false,true); // set fragment as per your initial fragment for tab c
            }
            else if(tabId.equals(AppConstants.TAB_D)){
                pushFragments(tabId,new SampleFragment(),false,true); // set fragment as per your initial fragment for tab d
            }
        }
        else{
            pushFragments(tabId, mFragmentHolder.get(tabId).lastElement(),false,false);
        }
    }
    public void pushFragments(String tab_id, Fragment fragment, boolean shouldAnimate, boolean shouldAdd) {
        mCurrentTab = tab_id;
        if (shouldAdd)
            mFragmentHolder.get(tab_id).push(fragment);
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        if (shouldAnimate)
            ft.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left);
        ft.replace(R.id.container, fragment);
        ft.commit();
    }

    public void popFragments() {
      /*
       *    Select the second last fragment in current tab's stack..
       *    which will be shown after the fragment transaction given below
       */
        Fragment fragment = mFragmentHolder.get(mCurrentTab).elementAt(mFragmentHolder.get(mCurrentTab).size() - 2);

      /*pop current fragment from stack.. */
        mFragmentHolder.get(mCurrentTab).pop();

      /* We have the target fragment in hand.. Just show it.. Show a standard navigation animation*/
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        ft.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right);
        ft.replace(R.id.container, fragment);
        ft.commit();
    }


    @Override
    public void onBackPressed() {
        if (((BaseFragment) mFragmentHolder.get(mCurrentTab).lastElement()).onBackPressed() == false) {
               /*
       		 * top fragment in current tab doesn't handles back press, we can do our thing, which is
       		 *
       		 * if current tab has only one fragment in stack, ie first fragment is showing for this tab.
       		 *        finish the activity
       		 * else
       		 *        pop to previous fragment in stack for the same tab
       		 *
       		 */
            if (mFragmentHolder.get(mCurrentTab).size() == 1) {
                super.onBackPressed();  // or call finish..
            } else {
                popFragments();
            }
        } else {
            //do nothing.. fragment already handled back button press.
        }
    }
}
