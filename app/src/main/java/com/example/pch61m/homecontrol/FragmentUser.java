package com.example.pch61m.homecontrol;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pch61m.homecontrol.home.db.Inventory;
import com.example.pch61m.homecontrol.home.db.Users;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentUser.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentUser#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentUser extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    FragmentExterior.OnColorChangeListener onColorChangeListener;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public FragmentUser() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentUser.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentUser newInstance(String param1, String param2) {
        FragmentUser fragment = new FragmentUser();
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

    private EditText name;
    private EditText lastname;
    private EditText email;
    private EditText new_pass;
    private EditText new_nip;
    private LinearLayout linearLayout_pass;
    private TextView text_modificar_pass;
    private  int Acceso=0;
    private int request_code=0;
    private int id;
    Inventory inventory;
    private Users user;
    private Button button_ok;
    private Button button_cancel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment




        View view;
        view = inflater.inflate(R.layout.fragment__user, container, false);
        name=(EditText)view.findViewById(R.id.txt_name_update);
        lastname=(EditText)view.findViewById(R.id.txt_lastname_update);
        email=(EditText)view.findViewById(R.id.txt_email_update);
        new_pass=(EditText)view.findViewById(R.id.txt_new_pass);
        new_nip=(EditText)view.findViewById(R.id.txt_new_NIP);
        linearLayout_pass= (LinearLayout)view.findViewById(R.id.layout_pass);
        text_modificar_pass= (TextView)view.findViewById(R.id.txt_modificar);

        button_cancel= (Button)view.findViewById(R.id.btn_cancel_confirmation1);
        button_ok=(Button)view.findViewById(R.id.btn_delete_confirmation1) ;

        inventory= new Inventory(getContext());


        Bundle bundle = this.getArguments();
        if (bundle != null) {
            id = bundle.getInt(MainActivity.KEY_ID, 0);
        }


        user=inventory.getUserFromID(id);
        name.setText(user.getName());
        lastname.setText(user.getLastname());
        email.setText(user.getEmail());


      //  Toast.makeText(getContext(), String.valueOf(id), Toast.LENGTH_SHORT).show();


        text_modificar_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(Acceso==0){

                    Intent i = new Intent(getContext(), Modificar_Contrasena.class);
                    i.putExtra( Modificar_Contrasena.KEY_ID, id);
                    startActivityForResult(i, request_code);

                }else{

                    linearLayout_pass.setVisibility(View.VISIBLE);


                }



            }
        });

        button_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(Acceso==1) {


                    if (name.getText().toString().equals("") || lastname.getText().toString().equals("") || email.getText().toString().equals("") || new_pass.getText().toString().equals("") || new_nip.getText().toString().equals("")) {
                        Toast.makeText(getContext(), "Campo Vacío", Toast.LENGTH_SHORT).show();

                    } else {

                        if (new_pass.getText().toString().length() <= 4) {
                            Toast.makeText(getContext(), "La contraseña debe contener mínimo 5 Carácteres", Toast.LENGTH_SHORT).show();

                        } else if (new_nip.getText().toString().length() < 4) {
                            Toast.makeText(getContext(), "El NIP debe ser de 4 números", Toast.LENGTH_SHORT).show();

                        } else {

                            String new_name = name.getText().toString();
                            String new_lastname = lastname.getText().toString();
                            String new_email = email.getText().toString();
                            String new_password = new_pass.getText().toString();
                            int new_nipaz = Integer.valueOf(new_nip.getText().toString());

                            inventory.updateUser(String.valueOf(id), new_name, new_lastname, new_email, new_password);
                            inventory.updateNIP(String.valueOf(id), new_nipaz);
                            Toast.makeText(getContext(), "Datos Modificados ", Toast.LENGTH_SHORT).show();


                        }


                      }
                }else {

                    if (name.getText().toString().equals("") || lastname.getText().toString().equals("") || email.getText().toString().equals("")) {
                        Toast.makeText(getContext(), "Campo Vacío", Toast.LENGTH_SHORT).show();

                    } else {


                        String new_name = name.getText().toString();
                        String new_lastname = lastname.getText().toString();
                        String new_email = email.getText().toString();
                        String new_password = user.getPass();
                        inventory.updateUser(String.valueOf(id), new_name, new_lastname, new_email, new_password);
                        Toast.makeText(getContext(), "Datos Modificados ", Toast.LENGTH_SHORT).show();


                    }

                }


            }
        });

        button_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(Acceso==1){
                    linearLayout_pass.setVisibility(View.GONE);
                    Acceso=0;
                    Toast.makeText(getContext(), "Cancelado ", Toast.LENGTH_SHORT).show();

                }

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
            onColorChangeListener = (FragmentExterior.OnColorChangeListener) context;
        } catch (Exception e) {

        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public interface OnColorChangeListener {
        public void L1(String value);

        public void L2(String value);

        public void S1(String value);

        public void S2(String value);

        public void UpdateA1(String value);

        public String LM4();

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if ((requestCode == request_code && resultCode == RESULT_OK)) {
            Acceso=1;
           linearLayout_pass.setVisibility(View.VISIBLE);

        }



    }
}
