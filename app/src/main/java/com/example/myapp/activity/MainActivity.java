package com.example.myapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.model.Daf;
import com.example.model.DafLearned;
import com.example.model.Learning;
import com.example.model.StudyPlan;
import com.example.model.shas_masechtot.Masechet;
import com.example.model.shas_masechtot.Seder;
import com.example.model.shas_masechtot.Shas;
import com.example.myapp.R;
import com.example.myapp.dataBase.AppDataBase;
import com.example.myapp.databinding.ActivityMainBinding;
import com.example.myapp.fragment.DeleteStudyFragment;
import com.example.myapp.fragment.ShewStudyRvFragment;
import com.example.myapp.fragment.ShowDafFragment;
import com.example.myapp.utils.ConvertIntToPage;
import com.example.myapp.utils.InitAllShasFromGson;
import com.example.myapp.utils.ToastAndDialog;
import com.example.myapp.utils.UtilsCalender;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;

import static com.example.myapp.utils.StaticVariables.Assets_Name;
import static com.example.myapp.utils.StaticVariables.index1;
import static com.example.myapp.utils.StaticVariables.index2;
import static com.example.myapp.utils.StaticVariables.index3;
import static com.example.myapp.utils.StaticVariables.stringDafHayomi;


public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private ArrayList<Learning> mLearning;


    private Shas mAllShas;
    public final static String KEY_STUDY_1 = "KEY_STUDY_1";
    public final static String KEY_STUDY_2 = "KEY_STUDY_2";
    public final static String KEY_STUDY_3 = "KEY_STUDY_3";

    private ArrayList<Daf> myStudy1;
    private ArrayList<Daf> myStudy2;
    private ArrayList<Daf> myStudy3;
    private ShewStudyRvFragment mShewStudyRvFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        mAllShas = InitAllShasFromGson.getInstance(this);
        initData();
        openFragment(mShewStudyRvFragment = ShewStudyRvFragment.newInstance(myStudy1, myStudy2, myStudy3), ShewStudyRvFragment.TAG);
        initViews();
        initListeners();
    }

    private void initData() {
        mLearning = (ArrayList<Learning>) AppDataBase.getInstance(this).daoLearning().getStudyPlanWithLearning();
        initAllDafs(mLearning);

       if (mLearning.size() == 1){
           myStudy1 = mLearning.get(0).getAllDafs();
       }
        if (mLearning.size() == 2){
            myStudy1 = mLearning.get(0).getAllDafs();
            myStudy2 = mLearning.get(1).getAllDafs();
        }
        if (mLearning.size() == 3){
            myStudy1 = mLearning.get(0).getAllDafs();
            myStudy2 = mLearning.get(1).getAllDafs();
            myStudy3 = mLearning.get(2).getAllDafs();
        }
    }

    private void initAllDafs(ArrayList<Learning> mLearning) {
        for (Learning learning : mLearning) {
            if (learning.getStudyPlan().getTypeOfStudy().equals(stringDafHayomi)) {
                learning.setAllDafs(createListDafHayomi(learning.getStudyPlan()));
            } else {
                learning.setAllDafs(createListMasechet(learning.getStudyPlan()));
            }
            updateDafsLearned(learning);
        }
    }

    private void updateDafsLearned(Learning learning) {
        for (DafLearned dafLearned:learning.getDafLearned()) {
            if (dafLearned.getStudyPlanID() == learning.getStudyPlan().getId()){
                learning.getAllDafs().get(dafLearned.getIndexInListDafs()).setLearning(true);
                learning.getAllDafs().get(dafLearned.getIndexInListDafs()).setChazara(dafLearned.getChazara());
            }
        }
    }



    private ArrayList<Daf> createListDafHayomi(StudyPlan mStudyPlan) {
        ArrayList <Daf> allDafs = new ArrayList<>();
        Calendar dateDafHayomi = UtilsCalender.findDateOfStartDafHayomiEnglishDate();
        for (int i = 0; i < mAllShas.getSeder().size(); i++) {
            for (int j = 0; j < mAllShas.getSeder().get(i).getMasechtot().size(); j++) {
                for (int k = 2; k < (mAllShas.getSeder().get(i).getMasechtot().get(j).getPages() + 2); k++) {
                    int masechetPage = ConvertIntToPage.fixKinimTamidMidot(k, mAllShas.getSeder().get(i).getMasechtot().get(j).getName());
                    Daf mPage = new Daf(mAllShas.getSeder().get(i).getMasechtot().get(j).getName(), masechetPage, stringDafHayomi, mStudyPlan.getId());
                    mPage.setPageDate(UtilsCalender.dateStringFormat(dateDafHayomi));
                    allDafs.add(mPage);
                    dateDafHayomi.add(Calendar.DATE, 1);
                }
            }
        }
        return allDafs;
    }

    private ArrayList<Daf> createListMasechet(StudyPlan mStudyPlan) {
        int pages = findPageSum(mStudyPlan.getTypeOfStudy());
        ArrayList <Daf> allDafs = new ArrayList<>();
        for (int i = 2; i < (pages + 2); i++) {
            allDafs.add(new Daf(mStudyPlan.getTypeOfStudy(), ConvertIntToPage.fixKinimTamidMidot(i, mStudyPlan.getTypeOfStudy()),
                    mStudyPlan.getTypeOfStudy(), mStudyPlan.getId()));
        }
        return allDafs;
    }


    private int findPageSum(String typeOfStudy) {
        for (Seder mSeder :mAllShas.getSeder()) {
            for (Masechet masechet:mSeder.getMasechtot()) {
                if (masechet.getName().equals(typeOfStudy)){
                    return masechet.getPages();
                }
            }
        }
        return -1;
    }

    public void openFragment(Fragment myFragment, String tag) {
        getSupportFragmentManager().beginTransaction().replace(R.id.MA_frameLayout, myFragment)
                .addToBackStack(tag)
                .commit();
    }

    private void initViews() {
        initToolbar();
        initButtonsTypesStudy();
    }


    private void initToolbar() {
        Toolbar toolbar = binding.toolbarMainActivityTB;
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_type_of_study_MENU_I:
                if (checkIfCanOpenTypeOfStudy()) {
                    startActivity(new Intent(this, ProfileActivity.class));
                } else {
                    ToastAndDialog.toast(this, getString(R.string.more_than_thre_types_of_study));
                }
                return true;
            case R.id.delete_type_of_study_MENU_I:
                openFragment(DeleteStudyFragment.newInstance(myStudy1, myStudy2, myStudy3), DeleteStudyFragment.TAG);
                return true;
            case R.id.edit_profile_MENU_I:
                // TODO: edit profile
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private void initButtonsTypesStudy() {
        if (myStudy1 != null && myStudy1.size() > 0) {
            binding.typeOfStudy1BU.setText(myStudy1.get(0).getTypeOfStudy());
        }
        if (myStudy2 != null && myStudy2.size() > 0) {
            binding.typeOfStudy2BU.setVisibility(View.VISIBLE);
            binding.typeOfStudy2BU.setText(myStudy2.get(0).getTypeOfStudy());
        } else {
            binding.typeOfStudy2BU.setVisibility(View.GONE);
        }
        if (myStudy3 != null && myStudy3.size() > 0) {
            binding.typeOfStudy3BU.setVisibility(View.VISIBLE);
            binding.typeOfStudy3BU.setText(myStudy3.get(0).getTypeOfStudy());
        } else {
            binding.typeOfStudy3BU.setVisibility(View.GONE);
        }
    }

    private void initListeners() {
        binding.typeOfStudy1BU.setOnClickListener(v -> mShewStudyRvFragment.changeLearning(index1));
        binding.typeOfStudy2BU.setOnClickListener(v -> mShewStudyRvFragment.changeLearning(index2));
        binding.typeOfStudy3BU.setOnClickListener(v -> mShewStudyRvFragment.changeLearning(index3));
    }


    private boolean checkIfCanOpenTypeOfStudy() {
        ArrayList<Integer> mLeaning = (ArrayList<Integer>) AppDataBase.getInstance(this).daoLearning().getAllIndexTypeOfLeaning();
        return !mLeaning.contains(index1) || !mLeaning.contains(index2) || !mLeaning.contains(index3);
    }

    @Override
    public void onBackPressed() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment currentFragment = fragmentManager.findFragmentById(R.id.MA_frameLayout);
        if (currentFragment instanceof ShewStudyRvFragment) {
            finish();
        }
        if (currentFragment instanceof DeleteStudyFragment) {
            fragmentManager.popBackStack();
        }
        if (currentFragment instanceof ShowDafFragment) {
            fragmentManager.popBackStack();
        }
    }

    @Subscribe()
    public void showDaf(Daf dafToShow) {
        openFragment(ShowDafFragment.newInstance(dafToShow), ShowDafFragment.TAG);
    }

    @Override
    protected void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
    }

}