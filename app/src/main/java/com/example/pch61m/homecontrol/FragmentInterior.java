package com.example.pch61m.homecontrol;

import android.content.Context;
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
import android.widget.Toast;

public class FragmentInterior extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    OnColorChangeListener onColorChangeListener;




    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public FragmentInterior() {
        // Required empty public constructor
    }


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


    private SeekBar seekInterior_temperaturaCocina;
    private TextView txtInteriorMovimiento;
    private TextView InteriorVentana1;
    private TextView InteriorVentana2;
    private TextView Interiorpuerta;
    private TextView InteriorGarage;
    private TextView InteriorAlarma;
    private TextView txt_temperaturaCocina;
    private Button btnConfigurar;
    Handler mHandler6 = new Handler() ;

    private String LM1="";
    private String P1="";
    private String P2="";
    private String V1="";
    private String V2="";
    private String PI="";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        //________________________________________________________________
        // INICIALIZACION DE VARIABLES ___________________________________

        View view;
        view= inflater.inflate(R.layout.fragment_exterior, container, false);

        seekInterior_temperaturaCocina= (SeekBar) view.findViewById(R.id.seekInterior_temperaturaCocina);
        txtInteriorMovimiento= (TextView) view.findViewById(R.id.txtInteriorMovimiento);
        InteriorVentana1= (TextView) view.findViewById(R.id.InteriorVentana1);
        InteriorVentana2= (TextView) view.findViewById(R.id.InteriorVentana2);
        Interiorpuerta= (TextView) view.findViewById(R.id.Interiorpuerta);
        InteriorGarage= (TextView) view.findViewById(R.id.InteriorGarage);
        txt_temperaturaCocina= (TextView) view.findViewById(R.id.txt_temperaturaCocina);
        InteriorAlarma= (TextView) view.findViewById(R.id.InteriorAlarma);

        btnConfigurar= (Button) view.findViewById(R.id.btnConfigurar);



        seekInterior_temperaturaCocina.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });

        txt_temperaturaCocina.setText(String.valueOf(seekInterior_temperaturaCocina.getProgress()) + "º");

        //________________________________________________________________
        // EVENTOS CLICK  ___________________________________

        // LECTURA
        mHandler6.post(runnable6);
        //-------------------------------------------------------------------------

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


    Runnable runnable6 = new Runnable() {
        @Override
        public void run()
        {
            mHandler6.postDelayed(runnable6,3000);
            LM1=onColorChangeListener.LM1();
            P1=onColorChangeListener.P1();
            P2=onColorChangeListener.P2();
            V1=onColorChangeListener.V1();
            V2=onColorChangeListener.V2();
            PI=onColorChangeListener.PI();

            seekInterior_temperaturaCocina.setProgress(Integer.parseInt(LM1));
        }
    };


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


    public interface OnColorChangeListener{
        public String LM1();
        public String LM2();
        public String LM3();
        public String LM4();
        public String P1();
        public String P2();
        public String V1();
        public String V2();
        public String PI();
    }




}

