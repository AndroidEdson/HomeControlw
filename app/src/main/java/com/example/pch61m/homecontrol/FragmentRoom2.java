package com.example.pch61m.homecontrol;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import com.flask.colorpicker.ColorPickerView;
import com.flask.colorpicker.OnColorSelectedListener;
import com.flask.colorpicker.builder.ColorPickerClickListener;
import com.flask.colorpicker.builder.ColorPickerDialogBuilder;

public class FragmentRoom2 extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    OnColorChangeListener onColorChangeListener;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public FragmentRoom2() {
        // Required empty public constructor
        //
    }


    public static FragmentRoom2 newInstance(String param1, String param2) {
        FragmentRoom2 fragment = new FragmentRoom2();
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

    private TextView txt_temperaturaroom2;
    private SeekBar seek_temperaturaroom2;
    private Switch switchvent_room2;
    private Switch switchventauto_room2;
    private Switch switch_rgbroom2;
    private Button colorrom1;
    private Button temperaturadeseadaroom2;
    private int color;
    Handler mHandler6 = new Handler();
    private String LM3="";
    int request_code=1;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        //________________________________________________________________
        // INICIALIZACION DE VARIABLES ___________________________________

        View view;
        view = inflater.inflate(R.layout.fragment_room2, container, false);

        txt_temperaturaroom2 = (TextView) view.findViewById(R.id.txt_temperaturaroom2);
        seek_temperaturaroom2 = (SeekBar) view.findViewById(R.id.seek_temperaturaroom2);
        switchvent_room2 = (Switch) view.findViewById(R.id.switchvent_room2);
        switchventauto_room2 = (Switch) view.findViewById(R.id.switchventauto_room2);
        switch_rgbroom2 = (Switch) view.findViewById(R.id.switch_rgbroom2);
        colorrom1 = (Button) view.findViewById(R.id.colorrom1);
        temperaturadeseadaroom2 = (Button) view.findViewById(R.id.temperaturadeseadaroom2);
        color = 0xffffff00;
        seek_temperaturaroom2.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });

        //________________________________________________________________
        // EVENTOS CLICK  ___________________________________

        temperaturadeseadaroom2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), TemperaturaPopup.class);
                startActivityForResult(intent, request_code);
            }
        });



        colorrom1.setEnabled(false);
        colorrom1.setBackgroundColor(0xffffff00);
        colorrom1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ColorPickerDialogBuilder
                        .with(getContext())
                        .setTitle("Seleccione el color")
                        .initialColor(0xffffff00)
                        .wheelType(ColorPickerView.WHEEL_TYPE.FLOWER)
                        .density(12)
                        .setOnColorSelectedListener(new OnColorSelectedListener() {
                            @Override
                            public void onColorSelected(int selectedColor) {
                                //Toast.makeText(getContext(),("onColorSelected: 0x" + Integer.toHexString(selectedColor)),Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setPositiveButton("ok", new ColorPickerClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int selectedColor, Integer[] allColors) {
                                colorrom1.setBackgroundColor(selectedColor);
                                color = selectedColor;
                                if(switch_rgbroom2.isChecked()){onColorChangeListener.R2("R2" + Integer.toHexString(color).substring(2) );}
                            }
                        })
                        .setNegativeButton("cancelar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                        .build()
                        .show();
            }
        });

        //________________________VENTILADOR__________________________________________


        switchventauto_room2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    switchvent_room2.setChecked(false);
                    switchvent_room2.setEnabled(false);
                    onColorChangeListener.B2("B21");
                 }
                 else{
                    onColorChangeListener.B2("B200");
                    switchvent_room2.setEnabled(true);
                }
        }});

        switchvent_room2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    switchventauto_room2.setChecked(false);
                    switchventauto_room2.setEnabled(false);
                    onColorChangeListener.B2("B201");
                }
                else{
                    switchventauto_room2.setEnabled(true);
                    onColorChangeListener.B2("B200");
                }
            }});
        //________________________RGB ILUMINACION__________________________________________
        switch_rgbroom2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    colorrom1.setEnabled(true);
                    onColorChangeListener.R2("R2" + Integer.toHexString(color).substring(2) );
                } else {
                    colorrom1.setEnabled(false);
                    onColorChangeListener.R2("R20");
                }
            }
        });


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
            mHandler6.postDelayed(runnable6, 3000);

            if(LM3!= null) {
                LM3=onColorChangeListener.LM3().substring(3);
                seek_temperaturaroom2.setProgress(Integer.valueOf(LM3));
                txt_temperaturaroom2.setText(String.valueOf(seek_temperaturaroom2.getProgress()) + "º");
            }
        }
    };


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


    public interface OnColorChangeListener {
        public void B2(String value);
        public void R2(String value);
        public void T2(String value);
        public String LM3();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == TemperaturaPopup.RESULT_OK){
                onColorChangeListener.T2("T2"+data.getStringExtra("t1"));
            }
        }

    }

}

