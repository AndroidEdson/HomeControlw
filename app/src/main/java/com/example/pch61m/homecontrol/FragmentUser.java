package com.example.pch61m.homecontrol;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;


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




        text_modificar_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                linearLayout_pass.setVisibility(View.VISIBLE);

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


}
