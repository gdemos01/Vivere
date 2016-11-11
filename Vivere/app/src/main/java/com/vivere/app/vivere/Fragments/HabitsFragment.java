package com.vivere.app.vivere.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vivere.app.vivere.R;

/**
 * Created by Giorgos on 11/11/2016.
 */

public class HabitsFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tab_fragment_habits, container, false);
    }
}
