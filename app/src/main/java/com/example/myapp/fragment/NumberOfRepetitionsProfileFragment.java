package com.example.myapp.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapp.R;
import com.example.myapp.databinding.FragmentNumberOfRepetitionsProfileBinding;


public class NumberOfRepetitionsProfileFragment extends Fragment {
    public static final String TAG = NumberOfRepetitionsProfileFragment.class.getSimpleName();
    private FragmentNumberOfRepetitionsProfileBinding binding;
    private ListenerNumberOfRepetitionsProfile mListener;


    public static NumberOfRepetitionsProfileFragment newInstance() {
        NumberOfRepetitionsProfileFragment fragment = new NumberOfRepetitionsProfileFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentNumberOfRepetitionsProfileBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViewListeners();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof ListenerNumberOfRepetitionsProfile) {
            mListener = (ListenerNumberOfRepetitionsProfile) context;
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

    private void initViewListeners() {
        binding.NumberOfRepOkBU.setOnClickListener(v -> onRadioNumberOfReps());
    }

    public void onRadioNumberOfReps() {
        int selectedId = binding.ProfileNumberOfRepsRG.getCheckedRadioButtonId();
        switch (selectedId) {
            case R.id.radio_No_thanks:
                mListener.numberOfRepOk(0);
                break;
            case R.id.radio_Once:
                mListener.numberOfRepOk(1);
                break;
            case R.id.radio_Twice:
                mListener.numberOfRepOk(2);
                break;
            case R.id.radio_3_times:
                mListener.numberOfRepOk(3);
                break;
        }
    }


    public interface ListenerNumberOfRepetitionsProfile {
        void numberOfRepOk(int numberOfReps);
    }
}