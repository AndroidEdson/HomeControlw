package com.example.pch61m.homecontrol;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;


public class FragmentInterior extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public FragmentInterior() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static FragmentInterior newInstance(String param1, String param2) {
        FragmentInterior fragment = new FragmentInterior();
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

    private SeekBar seekBar_temperatura1;
    private TextView txt_temperatura1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view;
        view=  inflater.inflate(R.layout.fragment_interior, container, false);

        seekBar_temperatura1= (SeekBar) view.findViewById(R.id.seek_temperatura1);
        txt_temperatura1 = (TextView) view.findViewById(R.id.txt_temperatura1);

        seekBar_temperatura1.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });

        txt_temperatura1.setText(String.valueOf(seekBar_temperatura1.getProgress()) + "º");



        return view;
    }

}
