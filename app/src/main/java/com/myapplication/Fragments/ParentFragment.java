package com.myapplication.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.myapplication.Adapters.ParentPagerAdapter;
import com.myapplication.R;
import com.myapplication.ui.NoSwipeViewPager;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ParentFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ParentFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
@EFragment(R.layout.fragment_parent)
public class ParentFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    String TAG = "ParentFragment";
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    @ViewById(R.id.mainTabLayout)
    TabLayout mainTabLayout;

    @ViewById(R.id.viewpager)
    NoSwipeViewPager parentViewpager;

    ParentPagerAdapter parentPagerAdapter = null;
    private OnFragmentInteractionListener mListener;

    public ParentFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ContactsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ParentFragment newInstance(String param1, String param2) {
        ParentFragment fragment = new ParentFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return null;
    }

    @AfterViews
    public void afterViews(){
       setUpViewPager();
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    //Custom Methods
    private void setUpViewPager(){

        Log.d(TAG, "setUpViewPager enter");
        mainTabLayout.addTab(mainTabLayout.newTab().setText("Contacts"));
        mainTabLayout.addTab(mainTabLayout.newTab().setText("Gallery"));
        //    mainTabLayout.addTab(mainTabLayout.newTab().setText("Details"));

        mainTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        parentPagerAdapter = new ParentPagerAdapter(getFragmentManager(), mainTabLayout.getTabCount());

        parentViewpager.setOffscreenPageLimit(2);
        parentViewpager.setAdapter(parentPagerAdapter);
        //   parentViewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mainTabLayout));
        setTabListener();
        parentViewpager.setCurrentItem(0);
        Log.d(TAG, "setUpViewPager exit");

    }

    void setTabListener()   {
        Log.d(TAG, "setTabListener enter");

        mainTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                if (parentViewpager != null) {
                    int currentSelectedTab = tab.getPosition();
                    parentViewpager.setCurrentItem(currentSelectedTab);

                    switch (currentSelectedTab){
                        case 0:
                            setSelectedTabFragmentData();
                            break;
                        case 1:
                            setSelectedTabFragmentData();
                            break;
                        case 2:
                            break;
                        default:
                    }
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        Log.d(TAG, "setTabListener exit");

    }

    public void setSelectedTabFragmentData() {

        if (parentViewpager != null) {

            Fragment fragment = parentPagerAdapter.getRegisteredFragment(parentViewpager.getCurrentItem());

            if (fragment instanceof ContactsFragment_) {
                ((ContactsFragment_) fragment).updateFragmentData();
            } else if (fragment instanceof GeneralFragment_) {
                ((GeneralFragment_) fragment).updateFragmentData();
            }
            parentPagerAdapter.notifyDataSetChanged();
        }
    }
}
