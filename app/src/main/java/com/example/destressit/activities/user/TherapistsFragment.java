package com.example.destressit.activities.user;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.destressit.R;
import com.example.destressit.TherapistClass;
import com.example.destressit.TherapistsViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TherapistsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TherapistsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TherapistsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private RecyclerView recyclerView;

    private DatabaseReference databaseReference,ref;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private View root;

    public TherapistsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TherapistsFragment.
     */
    // TODO: Rename and change types and number of parameters
    private static TherapistsFragment newInstance(String param1, String param2) {
        TherapistsFragment fragment = new TherapistsFragment();
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

        databaseReference= FirebaseDatabase.getInstance().getReference().child("therapists");
        databaseReference.keepSynced(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_therapists, container, false);
        recyclerView=(RecyclerView)root.findViewById(R.id.myrecyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return root;
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

    @Override
    public void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<TherapistClass> options = new FirebaseRecyclerOptions.Builder<TherapistClass>()
                .setQuery(databaseReference, TherapistClass.class)
                .build();

        FirebaseRecyclerAdapter<TherapistClass, TherapistsViewHolder> adapter = new FirebaseRecyclerAdapter<TherapistClass, TherapistsViewHolder>(options) {

            @NonNull
            @Override
            public TherapistsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                Toast.makeText(getContext(),"Here1",Toast.LENGTH_SHORT).show();

                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.therapist_display_row, parent, false);
                TherapistsViewHolder viewHolder = new TherapistsViewHolder(view);
                return viewHolder;
            }

            @Override
            protected void onBindViewHolder(@NonNull TherapistsViewHolder therapistsViewHolder, int i, @NonNull final TherapistClass therapist) {

                Toast.makeText(getContext(),"Here",Toast.LENGTH_SHORT).show();

                therapistsViewHolder.nameText.setText(therapist.getTname());
                therapistsViewHolder.emailText.setText(therapist.getTemail());
                therapistsViewHolder.phoneText.setText(therapist.getTphone());

                therapistsViewHolder.requestApp.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getContext(),"Clicked",Toast.LENGTH_SHORT).show();
                    }
                });
            }

        };
        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }
}
