package com.myapplication.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.SparseArray;
import android.view.ViewGroup;

import com.myapplication.Fragments.ContactsFragment;
import com.myapplication.Fragments.ContactsFragment_;
import com.myapplication.Fragments.GeneralFragment;
import com.myapplication.Fragments.GeneralFragment_;

public class ParentPagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;
    SparseArray<Fragment> registeredFragments = new SparseArray<Fragment>();
    ContactsFragment_ contactFragment;
    GeneralFragment_ generalFragment;

    public ParentPagerAdapter(FragmentManager fm, int NumOfTabs)
    {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position)
    {
        switch (position)
        {
            case 0:
                contactFragment = new ContactsFragment_();
                return contactFragment;
            case 1:
                generalFragment = new GeneralFragment_();
                return generalFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount()
    {
        return mNumOfTabs;
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment fragment = (Fragment) super.instantiateItem(container, position);
        registeredFragments.put(position, fragment);
        return fragment;
    }

    public Fragment getRegisteredFragment(int position) {
        return registeredFragments.get(position);
    }
}
