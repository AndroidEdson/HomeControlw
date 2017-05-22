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
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.flask.colorpicker.ColorPickerView;
import com.flask.colorpicker.OnColorSelectedListener;
import com.flask.colorpicker.builder.ColorPickerClickListener;
import com.flask.colorpicker.builder.ColorPickerDialogBuilder;

import static android.app.Activity.RESULT_OK;

public class FragmentRoom1 extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    OnColorChangeListener onColorChangeListener;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public FragmentRoom1() {
        // Required empty public constructor
    }


    public static FragmentRoom1 newInstance(String param1, String param2) {
        FragmentRoom1 fragment = new FragmentRoom1();
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

    private TextView txt_temperaturaroom1;
    private SeekBar seek_temperaturaroom1;
    private Switch switchvent_room1;
    private Switch switchventauto_room1;
    private Switch switch_rgbroom1;
    private Button colorrom1;
    private Button temperaturadeseadaroom1;
    private int color;
    Handler mHandler6 = new Handler();
    private String LM2="";
    int request_code=1;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        //________________________________________________________________
        // INICIALIZACION DE VARIABLES ___________________________________

        View view;
        view = inflater.inflate(R.layout.fragment_room1, container, false);

        txt_temperaturaroom1 = (TextView) view.findViewById(R.id.txt_temperaturaroom1);
        seek_temperaturaroom1 = (SeekBar) view.findViewById(R.id.seek_temperaturaroom1);
        switchvent_room1 = (Switch) view.findViewById(R.id.switchvent_room1);
        switchventauto_room1 = (Switch) view.findViewById(R.id.switchventauto_room1);
        switch_rgbroom1 = (Switch) view.findViewById(R.id.switch_rgbroom1);
        colorrom1 = (Button) view.findViewById(R.id.colorrom1);
        temperaturadeseadaroom1 = (Button) view.findViewById(R.id.temperaturadeseadaroom1);
        color = 0xffffff00;
        seek_temperaturaroom1.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });

        //________________________________________________________________
        // EVENTOS CLICK  ___________________________________

        temperaturadeseadaroom1.setOnClickListener(new View.OnClickListener() {
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
                                if(switch_rgbroom1.isChecked()){onColorChangeListener.R1("R1" + Integer.toHexString(color).substring(2) );}
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


        switchventauto_room1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    switchvent_room1.setChecked(false);
                    switchvent_room1.setEnabled(false);
                    onColorChangeListener.B1("B10");
                 }
                 else{
                    onColorChangeListener.B1("B110");
                    switchvent_room1.setEnabled(true);
                }
        }});

        switchvent_room1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    switchventauto_room1.setChecked(false);
                    switchventauto_room1.setEnabled(false);
                    onColorChangeListener.B1("B111");
                }
                else{
                    switchventauto_room1.setEnabled(true);
                    onColorChangeListener.B1("B110");
                }
            }});
        //________________________RGB ILUMINACION__________________________________________
        switch_rgbroom1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    colorrom1.setEnabled(true);
                    onColorChangeListener.R1("R1" + Integer.toHexString(color).substring(2) );
                } else {
                    colorrom1.setEnabled(false);
                    onColorChangeListener.R1("R10");
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

            if(LM2!= null) {
                LM2=onColorChangeListener.LM2().substring(3, 5);
                seek_temperaturaroom1.setProgress(Integer.valueOf(LM2));
                txt_temperaturaroom1.setText(String.valueOf(seek_temperaturaroom1.getProgress()) + "º");
            }
        }
    };


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


    public interface OnColorChangeListener {
        public void B1(String value);
        public void R1(String value);
        public void T1(String value);
        public String LM2();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == TemperaturaPopup.RESULT_OK){
                onColorChangeListener.T1("T1"+data.getStringExtra("t1"));
                //
            }
        }

    }

}

