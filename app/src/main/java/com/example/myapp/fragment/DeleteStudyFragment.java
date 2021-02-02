package com.example.myapp.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.example.model.Daf;
import com.example.myapp.R;
import com.example.myapp.activity.SplashActivity;
import com.example.myapp.dataBase.AppDataBase;
import com.example.myapp.databinding.FragmentDeleteStudyBinding;
import java.util.ArrayList;
import java.util.Objects;

import static com.example.myapp.activity.MainActivity.KEY_STUDY_1;
import static com.example.myapp.activity.MainActivity.KEY_STUDY_2;
import static com.example.myapp.activity.MainActivity.KEY_STUDY_3;


public class DeleteStudyFragment extends Fragment {
    public static final String TAG = DeleteStudyFragment.class.getSimpleName();
    private FragmentDeleteStudyBinding binding;
    private LinearLayout linearLayoutButtons;
    private static Bundle args;
    private ArrayList<Daf> myListLearning1 = new ArrayList<>();
    private ArrayList<Daf> myListLearning2 = new ArrayList<>();
    private ArrayList<Daf> myListLearning3 = new ArrayList<>();


    public DeleteStudyFragment() {
        // Required empty public constructor
    }


    public static DeleteStudyFragment newInstance(ArrayList<Daf> myList1, ArrayList<Daf>myList2 , ArrayList<Daf>myList3) {
        DeleteStudyFragment fragment = new DeleteStudyFragment();
        args = new Bundle();
        args.putParcelableArrayList(KEY_STUDY_1,myList1);
        args.putParcelableArrayList(KEY_STUDY_2,myList2);
        args.putParcelableArrayList(KEY_STUDY_3,myList3);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            myListLearning1 = getArguments().getParcelableArrayList(KEY_STUDY_1);
            myListLearning2 = getArguments().getParcelableArrayList(KEY_STUDY_2);
            myListLearning3 = getArguments().getParcelableArrayList(KEY_STUDY_3);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDeleteStudyBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        args.clear();
        initViews();
        initListeners();
    }

    @Override
    public void onPause() {
        super.onPause();
        linearLayoutButtons.setVisibility(View.VISIBLE);
    }

    private void initListeners() {
        binding.deleteStudyBU.setOnClickListener(v -> {
            int selectedId = binding.deleteTypeOfStudyRG.getCheckedRadioButtonId();
            switch (selectedId) {
                case R.id.typeOfStudy1_RB:
//                    AppDataBase.getInstance(getContext()).daoLearning().deleteStudyPlan(int StudyPlanID);


                    deleteStudy(1);
                    myListLearning1.clear();
                    AppDataBase.getInstance(getContext()).daoLearning().updateIndexTypeOfStudy(2);
                    AppDataBase.getInstance(getContext()).daoLearning().updateIndexTypeOfStudy(3);
                    break;



                case R.id.typeOfStudy2_RB:
                    deleteStudy(2);
                    myListLearning2.clear();
                    AppDataBase.getInstance(getContext()).daoLearning().updateIndexTypeOfStudy(3);
                    break;
                case R.id.typeOfStudy3_RB:


                    deleteStudy(3);
                    myListLearning3.clear();
                    break;
            }
            startActivity(new Intent(getActivity(), SplashActivity.class));
            Objects.requireNonNull(getActivity()).finish();
        });
    }

    private void deleteStudy(int indexStudy) {
        AppDataBase.getInstance(getContext()).daoLearning().deleteTypeOfLeaning(indexStudy);
        AppDataBase.getInstance(getContext()).daoLearning().updateDeletedLeaning(indexStudy);
    }

    private void initViews() {
        linearLayoutButtons = Objects.requireNonNull(getActivity()).findViewById(R.id.typeOfStudy_buttons_LL);
        linearLayoutButtons.setVisibility(View.GONE);
        if (myListLearning1 != null && myListLearning1.size()>0){
            binding.typeOfStudy1RB.setText(myListLearning1.get(0).getTypeOfStudy());
        }else {
            binding.typeOfStudy1RB.setVisibility(View.GONE);
        }
        if (myListLearning2 != null && myListLearning2.size()>0){
            binding.typeOfStudy2RB.setText(myListLearning2.get(0).getTypeOfStudy());
        }else {
            binding.typeOfStudy2RB.setVisibility(View.GONE);
        }
        if (myListLearning3 != null && myListLearning3.size()>0){
            binding.typeOfStudy3RB.setText(myListLearning3.get(0).getTypeOfStudy());
        }else {
            binding.typeOfStudy3RB.setVisibility(View.GONE);
        }
    }
}