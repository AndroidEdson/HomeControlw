package com.example.pch61m.homecontrol;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.Toast;

public class FragmentExterior extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    OnColorChangeListener onColorChangeListener;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public FragmentExterior() {
        // Required empty public constructor
    }


    public static FragmentExterior newInstance(String param1, String param2) {
        FragmentExterior fragment = new FragmentExterior();
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

    private Switch L1_switch_terraza;
    private Switch L2_switch_patio;
    private Switch S1_switch_garage;
    private Switch S2_switch_puerta;
    private SeekBar seekBar_terraza;
    private SeekBar seekBar_patio;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view;
        view= inflater.inflate(R.layout.fragment_exterior, container, false);
        L1_switch_terraza= (Switch) view.findViewById(R.id.switch_terraza);
        L2_switch_patio= (Switch) view.findViewById(R.id.switch_patio);
        S1_switch_garage= (Switch) view.findViewById(R.id.switch_garage);
        S2_switch_puerta= (Switch) view.findViewById(R.id.switch_puerta_principal);
        seekBar_terraza= (SeekBar) view.findViewById(R.id.seek_terraza);
        seekBar_patio= (SeekBar) view.findViewById(R.id.seek_patio);


        L1_switch_terraza.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
               if (isChecked) {
                  int value= (int) (seekBar_terraza.getProgress()*2.55);
                   String L1_string = "L1" + Integer.toString(value);

                   onColorChangeListener.L1(L1_string);

                   Toast.makeText(getContext(), L1_string, Toast.LENGTH_SHORT).show();
               }else{

                   String L1_string = "L1OFF" ;

                   onColorChangeListener.L1(L1_string);
                   Toast.makeText(getContext(), L1_string, Toast.LENGTH_SHORT).show();


               }
            }
        });


        L2_switch_patio.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    int value= (int) (seekBar_patio.getProgress()*2.55);
                    String L2_string = "L2" + Integer.toString(value);

                    onColorChangeListener.L2(L2_string);

                    //Toast.makeText(getContext(), L2_string, Toast.LENGTH_SHORT).show();
                }else{

                    String L2_string = "L2OFF" ;

                    onColorChangeListener.L2(L2_string);
                  //  Toast.makeText(getContext(), L1_string, Toast.LENGTH_SHORT).show();


                }
            }
        });


        seekBar_terraza.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                if (L1_switch_terraza.isChecked()){

                    int value= (int) (seekBar_terraza.getProgress()*2.55);
                    String L1_string = "L1" + Integer.toString(value);

                    onColorChangeListener.L1(L1_string);

                }



            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });



        seekBar_patio.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                if (L2_switch_patio.isChecked()){

                    int value= (int) (seekBar_patio.getProgress()*2.55);
                    String L2_string = "L2" + Integer.toString(value);

                    onColorChangeListener.L2(L2_string);

                }


            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });



        return view;

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

        try {
            onColorChangeListener = (OnColorChangeListener) context;
        }catch (Exception e){

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


    public interface OnColorChangeListener{
        public void L1(String value);
        public void L2(String value);
        public void S1(String value);
        public void S2(String value);



    }





}
