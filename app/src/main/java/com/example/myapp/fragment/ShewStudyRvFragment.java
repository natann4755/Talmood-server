package com.example.myapp.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.model.Daf;
import com.example.model.enums.FILTERS;
import com.example.myapp.R;
import com.example.myapp.adapters.AllMasechtotAdapter;
import com.example.myapp.adapters.DafAdapter;
import com.example.myapp.databinding.FragmentShewStudyRvBinding;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Objects;

import static com.example.myapp.activity.MainActivity.KEY_STUDY_1;
import static com.example.myapp.activity.MainActivity.KEY_STUDY_2;
import static com.example.myapp.activity.MainActivity.KEY_STUDY_3;
import static com.example.myapp.utils.StaticVariables.index0;
import static com.example.myapp.utils.StaticVariables.index1;
import static com.example.myapp.utils.StaticVariables.index2;

public class ShewStudyRvFragment extends Fragment implements AllMasechtotAdapter.ListenerNameMasechet, DafAdapter.listenerOneDafAdapter {

    public static final String TAG = ShewStudyRvFragment.class.getSimpleName();
    private static Bundle args;
    private FragmentShewStudyRvBinding binding;
    private TabLayout tabLayout;
    private ArrayList<Daf> myListLearning1 = new ArrayList<>();
    private ArrayList<Daf> myListLearning2 = new ArrayList<>();
    private ArrayList<Daf> myListLearning3 = new ArrayList<>();
    private RecyclerView recyclerViewPage;
    private DafAdapter myAdapter;





    public static ShewStudyRvFragment newInstance(ArrayList<Daf> myList1, ArrayList<Daf> myList2, ArrayList<Daf> myList3) {
        ShewStudyRvFragment fragment = new ShewStudyRvFragment();
        args = new Bundle();
        args.putParcelableArrayList(KEY_STUDY_1, myList1);
        args.putParcelableArrayList(KEY_STUDY_2, myList2);
        args.putParcelableArrayList(KEY_STUDY_3, myList3);
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
        binding = FragmentShewStudyRvBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        args.clear();
        initRVMasechtot();
        checkIfVisibleRVMasechtot(index1);
        initRVPage();
        initTabLayout();
    }


    private void initRVMasechtot() {
        binding.showStudyRVMasechtot.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        ArrayList<Daf> listDafHayomi = null;
        if (myListLearning1.size() > 2000) {
            listDafHayomi = myListLearning1;
        } else if (myListLearning2.size() > 2000) {
            listDafHayomi = myListLearning2;
        } else if (myListLearning3.size() > 2000) {
            listDafHayomi = myListLearning3;
        }
        if (listDafHayomi != null) {
            ArrayList<String> allMasechtotName = getNameAllMasechtot(listDafHayomi);
            AllMasechtotAdapter allMasechtotAdapter = new AllMasechtotAdapter(allMasechtotName, this);
            binding.showStudyRVMasechtot.setAdapter(allMasechtotAdapter);
        }
    }


    private ArrayList<String> getNameAllMasechtot(ArrayList<Daf> listDafHayomi) {
        ArrayList<String> allMasechtot = new ArrayList<>();
        for (int i = 1; i < listDafHayomi.size(); i++) {
            if (allMasechtot.size() == 0) {
                allMasechtot.add(listDafHayomi.get(i).getMasechet());
            } else if (!listDafHayomi.get(i).getMasechet().equals(allMasechtot.get(allMasechtot.size() - 1))) {
                allMasechtot.add(listDafHayomi.get(i).getMasechet());
            }
        }
        return allMasechtot;
    }


    private void checkIfVisibleRVMasechtot(int typeOfStudy) {
        switch (typeOfStudy) {
            case 1:
                if (myListLearning1 != null && myListLearning1.size() > 0)
                    visibleRVMasechtot(myListLearning1.size());
                break;
            case 2:
                if (myListLearning2 != null && myListLearning2.size() > 0)
                    visibleRVMasechtot(myListLearning2.size());
                break;
            case 3:
                if (myListLearning3 != null && myListLearning3.size() > 0)
                    visibleRVMasechtot(myListLearning3.size());
                break;
        }
    }

    private void visibleRVMasechtot(int listSize) {
        if (listSize > 2000) {
            binding.showStudyRVMasechtot.setVisibility(View.VISIBLE);
        } else {
            binding.showStudyRVMasechtot.setVisibility(View.GONE);
        }
    }

    private void initRVPage() {
        recyclerViewPage = binding.showStudyRVDapim;
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerViewPage.setLayoutManager(mLayoutManager);
        myAdapter = new DafAdapter(getActivity(), mLayoutManager, myListLearning1, myListLearning2, myListLearning3, this);
        recyclerViewPage.setAdapter(myAdapter);
    }

    private void initTabLayout() {
        tabLayout = binding.tabLayoutTypeList;
        createTab(R.string.all);
        createTab(R.string.learned);
        createTab(R.string.skipped);
        setListenerOfTabLayout(tabLayout);
    }

    private void createTab(int tabString) {
        TabLayout.Tab tab = tabLayout.newTab();
        tab.setText(tabString);
        tabLayout.addTab(tab);
    }

    private void setListenerOfTabLayout(TabLayout tabLayout) {
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case index0:
                        myAdapter.filter(FILTERS.allLearning);
                        break;
                    case index1:
                        binding.showStudyRVMasechtot.setVisibility(View.GONE);
                        myAdapter.filter(FILTERS.learned);
                        recyclerViewPage.scrollToPosition(0);
                        break;
                    case index2:
                        binding.showStudyRVMasechtot.setVisibility(View.GONE);
                        myAdapter.filter(FILTERS.skipped);
                        recyclerViewPage.scrollToPosition(0);
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0) {
                        myAdapter.filter(FILTERS.allLearning);
                }
            }
        });
    }

    public void changeLearning(int typeOfStudy) {
        checkIfVisibleRVMasechtot(typeOfStudy);
        myAdapter.changeTypeStudy(typeOfStudy);
        TabLayout.Tab newTab = tabLayout.getTabAt(0);
        Objects.requireNonNull(newTab).select();
    }

    public void initSummaryLearning(String nameLearning, String pageLearned, String totalLearning) {
        binding.showStudySummaryTypeOfLearningTV.setText(nameLearning);
        binding.showStudySummaryLearnedTV.setText(pageLearned);
        binding.showStudySummaryTotalTV.setText(totalLearning);
    }

    @Override
    public void nameMasechet(String nameMasechet) {
        myAdapter.filterOneMasechet(nameMasechet);
        recyclerViewPage.scrollToPosition(0);
    }
}