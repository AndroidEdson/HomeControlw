package com.example.pch61m.homecontrol;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import static android.app.Activity.RESULT_OK;

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
    Handler mHandler6 = new Handler();
    private ImageView close;
    private ImageView open;

    private SeekBar seekBar_temperatura1;
    private TextView txt_temperatura1;


    private String LM4 = "";
    private String l1 = "";
    private String l2 = "";
    private String s1 = "";
    private String s2 = "";

    int val = 0;
    int request_code = 1;
    int door_permision=0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        //________________________________________________________________
        // INICIALIZACION DE VARIABLES ___________________________________

        View view;
        view = inflater.inflate(R.layout.fragment_exterior, container, false);
        L1_switch_terraza = (Switch) view.findViewById(R.id.switch_terraza);
        L2_switch_patio = (Switch) view.findViewById(R.id.switch_patio);
        S1_switch_garage = (Switch) view.findViewById(R.id.switch_garage);
        S2_switch_puerta = (Switch) view.findViewById(R.id.switch_puerta_principal);

        seekBar_terraza = (SeekBar) view.findViewById(R.id.seek_terraza);
        seekBar_patio = (SeekBar) view.findViewById(R.id.seek_patio);
        close= (ImageView)view.findViewById(R.id.image_close);
        open= (ImageView)view.findViewById(R.id.image_open);


        seekBar_temperatura1 = (SeekBar) view.findViewById(R.id.seek_temperatura1);
        txt_temperatura1 = (TextView) view.findViewById(R.id.txt_temperatura1);
        seekBar_temperatura1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });

        txt_temperatura1.setText(String.valueOf(seekBar_temperatura1.getProgress()) + "º");

        //________________________________________________________________
        // EVENTOS CLICK  ___________________________________


        //________________________LUCES__________________________________________

        L1_switch_terraza.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    String L1_string ;
                    if (seekBar_terraza.getProgress() <= 10 && L1_switch_terraza.isChecked() == true) {
                        L1_string = "L1000";
                        onColorChangeListener.L1(L1_string); }
                    else{
                        int value = (int) (seekBar_terraza.getProgress() * 2.55);
                        L1_string = "L1" + Integer.toString(value);
                        onColorChangeListener.L1(L1_string);
                    }
                } else {

                    String L1_string = "L1000";

                    onColorChangeListener.L1(L1_string);
                    Toast.makeText(getContext(), L1_string, Toast.LENGTH_SHORT).show();

                }
            }
        });


        L2_switch_patio.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    String L2_string ;
                    if (seekBar_patio.getProgress() <= 10 && L2_switch_patio.isChecked() == true) {
                        L2_string = "L2000";
                        onColorChangeListener.L2(L2_string); }
                    else{
                        int value = (int) (seekBar_patio.getProgress() * 2.55);
                        L2_string = "L2" + Integer.toString(value);
                        onColorChangeListener.L2(L2_string);
                    }
                }
                else {

                    String L2_string = "L2000";

                    onColorChangeListener.L2(L2_string);
                    //  Toast.makeText(getContext(), L1_string, Toast.LENGTH_SHORT).show();


                }
            }
        });


        seekBar_terraza.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                if (L1_switch_terraza.isChecked()) {
                    int value = (int) (seekBar_terraza.getProgress() * 2.50);
                    String L1_string = "L1" + String.format("%03d",value );
                    //if(L1_string.length() == 3){L1_string = "L1000";}
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

                if (L2_switch_patio.isChecked()) {

                    int value = (int) (seekBar_patio.getProgress() * 2.50);
                    String L2_string = "L2" + String.format("%03d",value );
                    //if(L2_string.length() == 3){L2_string = "L2000";}
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
        //________________________ END LUCES__________________________________________

        //________________________ SERVOS__________________________________________


        S1_switch_garage.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {


                if (isChecked) {


                    String S1_string = "S1" + "0";

                    onColorChangeListener.S1(S1_string);

                    //Toast.makeText(getContext(), L2_string, Toast.LENGTH_SHORT).show();


                } else {

                    String S1_string = "S11";

                    onColorChangeListener.S1(S1_string);
                    //  Toast.makeText(getContext(), L1_string, Toast.LENGTH_SHORT).show();

                }


            }
        });


        S2_switch_puerta.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {

                    if(door_permision==0) {
                           Intent i = new Intent(getContext(), LoginDoor.class);
                             startActivityForResult(i, request_code);
                    }else{

                        String S2_string = "S2" + "1";
                        String S_string;
                        onColorChangeListener.S2(S2_string);
                    }


                } else {

                    if(door_permision==1){

                        String S2_string = "S20";
                        onColorChangeListener.S2(S2_string);

                    }


                }


            }
        });
        s1 = onColorChangeListener.s1();
        if (s1 != null && s1 != "" ) {
            s1 = onColorChangeListener.s1().substring(2);
            if(Integer.valueOf(s1) == 1){S1_switch_garage.setChecked(true);}else{S1_switch_garage.setChecked(false);}
        }
        s2 = onColorChangeListener.s2();
        if (s2 != null && s2 != "" ) {
            s2 = onColorChangeListener.s2().substring(2);
            if(Integer.valueOf(s2) == 1){S2_switch_puerta.setChecked(true);}else{S2_switch_puerta.setChecked(false);}
        }
        l1 = onColorChangeListener.l1();
        if (l1 != null && l1 != "" ) {
            l1 = onColorChangeListener.l1().substring(2);
            if(Integer.valueOf(l1) == 000) {seekBar_terraza.setProgress(Integer.valueOf(l1));} else{L1_switch_terraza.setChecked(true);seekBar_terraza.setProgress(Integer.valueOf(l1));}
        }
        l2 = onColorChangeListener.l2();
        if (l2 != null && l2 != "" ) {
            l2 = onColorChangeListener.l2().substring(2);
            if(Integer.valueOf(l2) == 000) {seekBar_patio.setProgress(Integer.valueOf(l2));} else{L2_switch_patio.setChecked(true);seekBar_patio.setProgress(Integer.valueOf(l2));}
        }
        mHandler6.post(runnable6);

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
        } catch (Exception e) {

        }

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    Runnable runnable6 = new Runnable() {
        @Override
        public void run() {
            mHandler6.postDelayed(runnable6, 1000);

            if (LM4 != null) {
                LM4 = onColorChangeListener.LM4().substring(3);
                //   Toast.makeText(getContext(),LM4, Toast.LENGTH_SHORT).show();
                seekBar_temperatura1.setProgress(Integer.valueOf(LM4));
                txt_temperatura1.setText(String.valueOf(LM4));

            }

        }
    };


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


    public interface OnColorChangeListener {
        public void L1(String value);

        public void L2(String value);

        public void S1(String value);

        public void S2(String value);

        public String LM4();
        public String s1();
        public String s2();
        public String l1();
        public String l2();

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if ((requestCode == request_code && resultCode == RESULT_OK)) {
            door_permision=1;
            close.setVisibility(View.GONE);
            open.setVisibility(View.VISIBLE);
            S2_switch_puerta.setChecked(true);

            //Toast.makeText(getContext(), "back", Toast.LENGTH_SHORT).show();
        }

        if(door_permision==0){
            S2_switch_puerta.setChecked(false);

        }


    }
}

