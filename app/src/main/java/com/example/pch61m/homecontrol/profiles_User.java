package com.example.pch61m.homecontrol;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
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
import android.widget.Spinner;
import android.widget.Toast;

import com.example.pch61m.homecontrol.home.db.Inventory;
import com.example.pch61m.homecontrol.home.db.User_to_profile;
import com.example.pch61m.homecontrol.home.db.Users;

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view;
        view = inflater.inflate(R.layout.fragment_profiles__user, container, false);

        spinner= (Spinner)view.findViewById(R.id.spinner_profile);
        btn_add_profile=(ImageButton)view.findViewById(R.id.btn_add_profile);
        inventory= new Inventory(getContext());


      Bundle bundle = this.getArguments();
      if (bundle != null) {
          id = bundle.getInt(MainActivity.KEY_ID, 0);
      }

        Toast.makeText(getContext(), String.valueOf(id), Toast.LENGTH_SHORT).show();

        user=inventory.getUserFromID(id);

        List<User_to_profile> user_to_profiles= inventory.getProfiles(String.valueOf(user.getId()));

        SetDefault=user_to_profiles.get(Posicion_spinner).getActuators();

        if(SetDefault.startsWith("JA")){

        }else if(SetDefault.startsWith("JM")){

        }else if(SetDefault.startsWith("JR")){

        }else if(SetDefault.startsWith("JK")){

        }else{

            Toast.makeText(getContext(),"Comando no reconocido", Toast.LENGTH_SHORT).show();
        }


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
