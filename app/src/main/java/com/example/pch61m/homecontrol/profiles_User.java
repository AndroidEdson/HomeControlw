package com.example.pch61m.homecontrol;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pch61m.homecontrol.home.db.Inventory;
import com.example.pch61m.homecontrol.home.db.User_to_profile;
import com.example.pch61m.homecontrol.home.db.Users;
import com.flask.colorpicker.ColorPickerView;
import com.flask.colorpicker.OnColorSelectedListener;
import com.flask.colorpicker.builder.ColorPickerClickListener;
import com.flask.colorpicker.builder.ColorPickerDialogBuilder;

import java.util.List;

import static android.app.Activity.RESULT_OK;
import static android.widget.AdapterView.*;


public class profiles_User extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public profiles_User() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static profiles_User newInstance(String param1, String param2) {
        profiles_User fragment = new profiles_User();
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

    private Inventory inventory;
    private Spinner spinner;
    private  int id;
    private Users user;
    private ImageButton btn_add_profile;
    private  int request_code=0;
    private  int Posicion_spinner=0;
    private String SetDefault;

    String L1, L2, B1, T1, B2, T2, R1, R2, B1_aux, B2_aux;


    private Switch L1_switch_terraza;
    private Switch L2_switch_patio;
    private SeekBar seekBar_terraza;
    private SeekBar seekBar_patio;

    private Switch switchvent_room1;
    private Switch switchventauto_room1;
    private Switch switch_rgbroom1;
    private Button colorrom1;
    private TextView textView_temp1;

    private Switch switchvent_room2;
    private Switch switchventauto_room2;
    private Switch switch_rgbroom2;
    private Button colorrom2;
    private TextView textView_temp2;

    private int color;
    int value=0xffffff00;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view;
        view = inflater.inflate(R.layout.fragment_profiles__user, container, false);

        spinner= (Spinner)view.findViewById(R.id.spinner_profile);
        btn_add_profile=(ImageButton)view.findViewById(R.id.btn_add_profile);

        L1_switch_terraza = (Switch) view.findViewById(R.id.switch_terraza);
        L2_switch_patio = (Switch) view.findViewById(R.id.switch_patio);
        seekBar_terraza = (SeekBar) view.findViewById(R.id.seek_terraza);
        seekBar_patio = (SeekBar) view.findViewById(R.id.seek_patio);

        switchvent_room1 = (Switch) view.findViewById(R.id.switchvent_room1);
        switchventauto_room1  = (Switch) view.findViewById(R.id.switchventauto_room1);
        switch_rgbroom1 = (Switch) view.findViewById(R.id.switch_rgbroom1);
        colorrom1 = (Button) view.findViewById(R.id.colorrom1);
        textView_temp1= (TextView)view.findViewById(R.id.txt_temp1);

        switchvent_room2 = (Switch) view.findViewById(R.id.switchvent_room2);
        switchventauto_room2 = (Switch) view.findViewById(R.id.switchventauto_room2);
        switch_rgbroom2 = (Switch) view.findViewById(R.id.switch_rgbroom2);
        colorrom2 = (Button) view.findViewById(R.id.colorrom2);
        textView_temp2= (TextView)view.findViewById(R.id.txt_temp2);

        inventory= new Inventory(getContext());

      Bundle bundle = this.getArguments();
      if (bundle != null) {
          id = bundle.getInt(MainActivity.KEY_ID, 0);
      }

        Toast.makeText(getContext(), String.valueOf(id), Toast.LENGTH_SHORT).show();

        user=inventory.getUserFromID(id);

        List<User_to_profile> user_to_profiles= inventory.getProfiles(String.valueOf(user.getId()));

        SetDefault=user_to_profiles.get(Posicion_spinner).getActuators();

        // _____________________________________________SETEAR LOS VALORES ACTUALES_________________________


      //  ( 0,'Día Lluvioso', 'JAL1250L2250B11T150B21T250R1FFFFFFR2FFFFFF');
      //  ( 1,'Día Soleado', 'JML1250L2250B101B201R1FFFFFFR2FFFFFF');
      //  ( 2,'Noche', 'JRL1250L2250B11T150B201R1FFFFFFR2FFFFFF');
      //   (3,'Atardecer', 'JKL1250L2250B101B21T250R1FFFFFFR2FFFFFF');

        if(SetDefault.startsWith("JA")){  // PARA CICLO AUTOMATICO


            L1= SetDefault.substring(4,7);
            L2= SetDefault.substring(9,12);
            B1= SetDefault.substring(13,14); //MODO MANUAL
            B1_aux= SetDefault.substring(14,15); // B ENCENDIDO O APAGADO
            T1=SetDefault.substring(17,19);
            B2= SetDefault.substring(22,23); //MODO MANUAL
            B2_aux= SetDefault.substring(21,22); // B ENCENDIDO O APAGADO
            T2=SetDefault.substring(24,26);
            R1=SetDefault.substring(28,34);
            R2=SetDefault.substring(36,42);

             //Toast.makeText(getContext(), "L1"+L1+ " L2"+ L2+ " B1"+B1_aux+ " T1"+T1 + " B2"+ B2_aux+" T2"+ T2+" R1"+R1+ " R2"+ R2 , Toast.LENGTH_LONG).show();

            if(Integer.valueOf(L1)==0){
                L1_switch_terraza.setChecked(false);
            }else{
                L1_switch_terraza.setChecked(true);
                seekBar_terraza.setProgress(Integer.valueOf(L1));
            }

            if(Integer.valueOf(L2)==0){
                L2_switch_patio.setChecked(false);
            }else{
                L2_switch_patio.setChecked(true);
                seekBar_patio.setProgress(Integer.valueOf(L2));

            }
            if(B1_aux.equals("1")){
                switchventauto_room1.setChecked(true);
                textView_temp1.setText(T1);
            }else{
                switchventauto_room1.setChecked(true);
                textView_temp1.setText("");
            }

            if(B2_aux.equals("1")){
                switchventauto_room2.setChecked(true);
                textView_temp2.setText(T2);
            }else{
                switchventauto_room2.setChecked(true);
                textView_temp2.setText("");
            }

            if(R1.equals("000000"))
            {
                switch_rgbroom1.setChecked(false);
            }else{

                R1="#" +R1;

                colorrom1.setBackgroundColor(Color.parseColor(R1));
                switch_rgbroom1.setChecked(true);

            }

            if(R2.equals("000000"))
            {
                switch_rgbroom2.setChecked(false);
            }else{

                R2="#" +R2;

                colorrom2.setBackgroundColor(Color.parseColor(R2));
                switch_rgbroom2.setChecked(true);

            }

        }else if(SetDefault.startsWith("JM")){ // PARA CICLO MANUAL

            L1= SetDefault.substring(4,7);
            L2= SetDefault.substring(9,12);
            B1= SetDefault.substring(13,14); //MODO MANUAL
            B1_aux= SetDefault.substring(14,15); // B ENCENDIDO O APAGADO
            B2= SetDefault.substring(18,19); //MODO MANUAL
            B2_aux= SetDefault.substring(19,20); // B ENCENDIDO O APAGADO
            R1=SetDefault.substring(22,28);
            R2=SetDefault.substring(30,36);

           // Toast.makeText(getContext(), "L1"+L1+ " L2"+ L2+ " B1"+ B1+B1_aux+ " B2"+B2+ B2_aux+ " R1"+R1+ " R2"+ R2 , Toast.LENGTH_LONG).show();

            if(Integer.valueOf(L1)==0){
                L1_switch_terraza.setChecked(false);
            }else{
                L1_switch_terraza.setChecked(true);
                seekBar_terraza.setProgress(Integer.valueOf(L1));
            }

            if(Integer.valueOf(L2)==0){
                L2_switch_patio.setChecked(false);
            }else{
                L2_switch_patio.setChecked(true);
                seekBar_patio.setProgress(Integer.valueOf(L2));

            }

            //__________________MODO MANUAL__________________________________
            if(Integer.valueOf(B1)==0){
                textView_temp1.setEnabled(false);

                if(Integer.valueOf(B1_aux)==0) {
                    switchvent_room1.setChecked(false);
                }else{
                    switchvent_room1.setChecked(true);
                }


            }


            //__________________MODO MANUAL__________________________________
            if(Integer.valueOf(B2)==0){
                textView_temp2.setEnabled(false);

                if(Integer.valueOf(B2_aux)==0) {
                    switchvent_room2.setChecked(false);
                }else{
                    switchvent_room2.setChecked(true);
                }
            }

            if(R1.equals("000000"))
            {
                switch_rgbroom1.setChecked(false);
            }else{

                R1="#" +R1;

                colorrom1.setBackgroundColor(Color.parseColor(R1));
                switch_rgbroom1.setChecked(true);

            }

            if(R2.equals("000000"))
            {
                switch_rgbroom2.setChecked(false);
            }else{

                R2="#" +R2;

                colorrom2.setBackgroundColor(Color.parseColor(R2));
                switch_rgbroom2.setChecked(true);

            }


        }else if(SetDefault.startsWith("JR")){ // B1 AUTOMATICO B2 MANUAL

            L1= SetDefault.substring(4,7);
            L2= SetDefault.substring(9,12);
            B1= SetDefault.substring(13,14); //MODO MANUAL
            T1=SetDefault.substring(17,19);
            B2= SetDefault.substring(21,22); //MODO MANUAL
            B2_aux= SetDefault.substring(22,23); // B ENCENDIDO O APAGADO
            R1=SetDefault.substring(25,31);
            R2=SetDefault.substring(33,39);

            Toast.makeText(getContext(), "L1"+L1+ " L2"+ L2+ " B1"+B1+ " T1"+T1 + " B2"+B2+ B2_aux+" R1"+R1+ " R2"+ R2 , Toast.LENGTH_LONG).show();

            if(Integer.valueOf(L1)==0){
                L1_switch_terraza.setChecked(false);
            }else{
                L1_switch_terraza.setChecked(true);
                seekBar_terraza.setProgress(Integer.valueOf(L1));
            }

            if(Integer.valueOf(L2)==0){
                L2_switch_patio.setChecked(false);
            }else{
                L2_switch_patio.setChecked(true);
                seekBar_patio.setProgress(Integer.valueOf(L2));

            }

            //__________________MODO MANUAL__________________________________


            if(B1.equals("1")){
                switchventauto_room1.setChecked(true);
                textView_temp1.setText(T1);
            }else{
                switchventauto_room1.setChecked(true);
                textView_temp1.setText("");
            }



            if(Integer.valueOf(B2)==0){
                textView_temp2.setEnabled(false);

                if(Integer.valueOf(B2_aux)==0) {
                    switchvent_room2.setChecked(false);
                }else{
                    switchvent_room2.setChecked(true);
                }
            }



            if(R1.equals("000000"))
            {
                switch_rgbroom1.setChecked(false);
            }else{

                R1="#" +R1;

                colorrom1.setBackgroundColor(Color.parseColor(R1));
                switch_rgbroom1.setChecked(true);

            }


            if(R2.equals("000000"))
            {
                switch_rgbroom2.setChecked(false);
            }else{

                R2="#" +R2;

                colorrom2.setBackgroundColor(Color.parseColor(R2));
                switch_rgbroom2.setChecked(true);

            }


        }else if(SetDefault.startsWith("JK")){

            L1= SetDefault.substring(4,7);
            L2= SetDefault.substring(9,12);
            B1= SetDefault.substring(14,15); //MODO MANUAL
            B1_aux= SetDefault.substring(15,16);
            B2= SetDefault.substring(18,19); //MODO MANUAL
            T2=SetDefault.substring(21,23);
            R1=SetDefault.substring(25,31);
            R2=SetDefault.substring(33,39);


            if(Integer.valueOf(L1)==0){
                L1_switch_terraza.setChecked(false);
            }else{
                L1_switch_terraza.setChecked(true);
                seekBar_terraza.setProgress(Integer.valueOf(L1));
            }

            if(Integer.valueOf(L2)==0){
                L2_switch_patio.setChecked(false);
            }else{
                L2_switch_patio.setChecked(true);
                seekBar_patio.setProgress(Integer.valueOf(L2));

            }

            //__________________MODO MANUAL__________________________________
            if(Integer.valueOf(B1)==0){
                textView_temp1.setEnabled(false);

                if(Integer.valueOf(B1_aux)==0) {
                    switchvent_room1.setChecked(false);
                }else{
                    switchvent_room1.setChecked(true);
                }


            }

            if(B2_aux.equals("1")){
                switchventauto_room2.setChecked(true);
                textView_temp2.setText(T2);
            }else{
                switchventauto_room2.setChecked(true);
                textView_temp2.setText("");
            }

            if(R1.equals("000000"))
            {
                switch_rgbroom1.setChecked(false);
            }else{

                R1="#" +R1;

                colorrom1.setBackgroundColor(Color.parseColor(R1));
                switch_rgbroom1.setChecked(true);

            }

            if(R2.equals("000000"))
            {
                switch_rgbroom2.setChecked(false);
            }else{

                R2="#" +R2;

                colorrom2.setBackgroundColor(Color.parseColor(R2));
                switch_rgbroom2.setChecked(true);

            }



            Toast.makeText(getContext(), "L1"+L1+ " L2"+ L2+ " B1"+B1+B1_aux+" B2"+ B2+" T2"+ T2  +" R1"+R1+ " R2"+ R2 , Toast.LENGTH_LONG).show();

        }else{

            Toast.makeText(getContext(),"Comando no reconocido", Toast.LENGTH_SHORT).show();
        }

        //_____________________________________________INICIALIZACION______________________________________











        // _____________________________________________SETEAR LOS VALORES ACTUALES_________________________




        final ArrayAdapter<String> spinner_adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item);

        for (User_to_profile User_to_profile : user_to_profiles) {
            spinner_adapter.add(User_to_profile.getDescription());
        }

        spinner.setAdapter(spinner_adapter);

        btn_add_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getContext(), new_profile.class);
                i.putExtra(new_profile.KEY_ID, id);
                startActivityForResult(i, request_code);
            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Posicion_spinner  = position;



                Toast.makeText(getContext(), String.valueOf(Posicion_spinner), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
//
            }
        });



      //  colorrom1.setBackgroundColor(0xffffff00);
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
                                //if(switch_rgbroom1.isChecked()){onColorChangeListener.R1("R1" + Integer.toHexString(color).substring(2) );}
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




  //      colorrom2.setBackgroundColor(0xffffff00);
        colorrom2.setOnClickListener(new View.OnClickListener() {
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
                                colorrom2.setBackgroundColor(selectedColor);
                                color = selectedColor;
                                //if(switch_rgbroom2.isChecked()){onColorChangeListener.R2("R2" + Integer.toHexString(color).substring(2) );}
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



        return view;
    }// END ON CREATE VIEW

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }





    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if ((requestCode == request_code && resultCode == RESULT_OK)) {

            List<User_to_profile> user_to_profiles = inventory.getProfiles(String.valueOf(user.getId()));


            final ArrayAdapter<String> spinner_adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(null);
            for (User_to_profile User_to_profile : user_to_profiles) {
                spinner_adapter.add(User_to_profile.getDescription());
            }

            spinner.setAdapter(spinner_adapter);




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
}
