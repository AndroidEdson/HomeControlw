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
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

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
    private String Z1="";
    private String p1="";
    private String p2="";
    private String v1="";
    private String v2="";
    private String pi="";
    int request_code=1;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        //________________________________________________________________
        // INICIALIZACION DE VARIABLES ___________________________________

        View view;
        view= inflater.inflate(R.layout.fragment_interior, container, false);

        seekInterior_temperaturaCocina= (SeekBar) view.findViewById(R.id.seekInterior_temperaturaCocina);
        txtInteriorMovimiento= (TextView) view.findViewById(R.id.txtInteriorMovimiento);
        InteriorVentana1= (TextView) view.findViewById(R.id.InteriorVentana1);
        InteriorVentana2 = (TextView) view.findViewById(R.id.InteriorVentana2);
        Interiorpuerta = (TextView) view.findViewById(R.id.Interiorpuerta);
        InteriorGarage = (TextView) view.findViewById(R.id.InteriorGarage);
        txt_temperaturaCocina = (TextView) view.findViewById(R.id.txt_temperaturaCocina);
        InteriorAlarma = (TextView) view.findViewById(R.id.InteriorAlarma);

        btnConfigurar= (Button) view.findViewById(R.id.btnConfigurar);



        seekInterior_temperaturaCocina.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });

        //txt_temperaturaCocina.setText(String.valueOf(seekInterior_temperaturaCocina.getProgress()) + "º");

        //________________________________________________________________
        // EVENTOS CLICK  ___________________________________


        // LECTURA
        mHandler6.post(runnable6);
        //-------------------------------------------------------------------------
        btnConfigurar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AlarmPopup.class);
                intent.putExtra(AlarmPopup.EXTRA_P1, Integer.valueOf(onColorChangeListener.p1().substring(2)));
                intent.putExtra(AlarmPopup.EXTRA_P2, Integer.valueOf(onColorChangeListener.p2().substring(2)));
                intent.putExtra(AlarmPopup.EXTRA_V1, Integer.valueOf(onColorChangeListener.v1().substring(2)));
                intent.putExtra(AlarmPopup.EXTRA_V2, Integer.valueOf(onColorChangeListener.v2().substring(2)));
                intent.putExtra(AlarmPopup.EXTRA_PI, Integer.valueOf(onColorChangeListener.pi().substring(2)));
                startActivityForResult(intent, request_code);
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
    //hh

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

            //P1=onColorChangeListener.P1();
            //P2=onColorChangeListener.P2();
            //V1=onColorChangeListener.V1();
            //V2=onColorChangeListener.V2();
            //PI=onColorChangeListener.PI();

            if(LM1!= null) {
                LM1=onColorChangeListener.LM1().substring(3);
                int val= Integer.valueOf(LM1);
                seekInterior_temperaturaCocina.setProgress(Integer.valueOf(LM1));
                txt_temperaturaCocina.setText(String.valueOf(seekInterior_temperaturaCocina.getProgress()) + "º");
            }
            if(PI!= null) {
                PI=onColorChangeListener.PI().substring(2);
                int val= Integer.valueOf(PI);

                if (val == 0)
                {txtInteriorMovimiento.setText("Sin movimiento");}
                else if (val == 1) {
                    txtInteriorMovimiento.setText("Movimiento");}
            }
            if(P1!= null) {
                P1=onColorChangeListener.P1().substring(2);
                int val= Integer.valueOf(P1);
                if (val == 0)
                {Interiorpuerta.setText("Cerrado");}
                else if (val == 1){Interiorpuerta.setText("Abierto");}
            }
            if(P2!= null) {
                P2=onColorChangeListener.P2().substring(2);
                int val= Integer.valueOf(P2);
                if (val == 0)
                {InteriorGarage.setText("Cerrado");}
                else if (val == 1) {InteriorGarage.setText("Abierto");}
            }
            if(V1!= null) {
                V1=onColorChangeListener.V1().substring(2);
                int val= Integer.valueOf(V1);
                if (val == 0)
                {InteriorVentana1.setText("Cerrado");}
                else if (val == 1) {InteriorVentana1.setText("Abierto");}
            }
            if(V2!= null) {
                V2=onColorChangeListener.V2().substring(2);
                int val= Integer.valueOf(V2);
                if (val == 0)
                {InteriorVentana2.setText("Cerrado");}
                else if (val == 1) {InteriorVentana2.setText("Abierto");}
            }
            if(Z1!= null) {
                Z1=onColorChangeListener.Z1().substring(2);
                int val= Integer.valueOf(Z1);
                if (val == 0)
                {InteriorAlarma.setText("Desactivada");}
                else if (val == 1) {InteriorVentana2.setText("Activada");}
            }
            if(pi!= null) {
                pi=onColorChangeListener.pi().substring(2);
            }
            if(p1!= null) {
                P1=onColorChangeListener.p1().substring(2);
            }
            if(p2!= null) {
                p2=onColorChangeListener.p2().substring(2);
            }
            if(v1!= null) {
                v1=onColorChangeListener.v1().substring(2);
            }
            if(v2!= null) {
                v2=onColorChangeListener.v2().substring(2);
            }

        }
    };


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


    public interface OnColorChangeListener{

        public void buzzerconfig(String value);

        public void SEND_Z1(String value);
        public void SEND_P1(String value);
        public void SEND_P2(String value);
        public void SEND_V1(String value);
        public void SEND_V2(String value);
        public void SEND_PI(String value);



        public String LM1();
        public String LM3();
        public String LM4();
        public String P1();
        public String P2();
        public String V1();
        public String V2();
        public String PI();
        public String Z1();
        public String p1();
        public String p2();
        public String v1();
        public String v2();
        public String pi();
    }
   //actualiza
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == AlarmPopup.RESULT_OK){
                onColorChangeListener.buzzerconfig("P1"+data.getStringExtra("p1")+"P2"+data.getStringExtra("p2")+"V1"+data.getStringExtra("v1")
                +"V2"+data.getStringExtra("v2")+"PI"+data.getStringExtra("pi"));

            //    onColorChangeListener.SEND_P1("P1"+ data.getStringExtra("p1"));

             //   onColorChangeListener.SEND_P2("P2"+ data.getStringExtra("p2"));

               // onColorChangeListener.SEND_V1("V1"+ data.getStringExtra("v1"));

               // onColorChangeListener.SEND_V2("V2"+ data.getStringExtra("v2"));

              //  onColorChangeListener.SEND_PI("PI"+ data.getStringExtra("pi"));





            }
        }

    }

}

