package com.example.myapp.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.model.StudyPlan;
import com.example.model.shas_masechtot.Shas;
import com.example.model.shas_masechtot.Seder;
import com.example.myapp.R;
import com.example.myapp.dataBase.AppDataBase;
import com.example.myapp.databinding.ActivityProfileBinding;
import com.example.myapp.fragment.NumberOfRepetitionsProfileFragment;
import com.example.myapp.fragment.TypeStudyProfileFragment;
import com.example.myapp.utils.InitAllShasFromGson;
import com.example.myapp.utils.ToastAndDialog;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Locale;


import static com.example.myapp.utils.StaticVariables.Assets_Name;


public class ProfileActivity extends AppCompatActivity implements TypeStudyProfileFragment.ListenerFragmentTypeStudyProfile, NumberOfRepetitionsProfileFragment.ListenerNumberOfRepetitionsProfile {

    private ActivityProfileBinding binding;
//    private ArrayList<Daf> mListLearning = new ArrayList<>();
    private Shas mAllShas;
    private String mTypeOfStudy;
    private StudyPlan mStudyPlan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProfileBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        mAllShas = InitAllShasFromGson.getInstance(this);
        openFragment(TypeStudyProfileFragment.newInstance((ArrayList<Seder>) mAllShas.getSeder()), TypeStudyProfileFragment.TAG);
    }



    public void openFragment(Fragment myFragment, String tag) {
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout_profile_FL, myFragment)
                .addToBackStack(tag)
                .commit();
    }

//    public void createListTypeOfStudy(String typeOfStudy) {
//        if (mStringTypeOfStudy.equals(stringDafHayomi)) {
////            createListAllShas();
//        } else {
////            createListMasechet(typeOfStudy, pages);
//        }
//    }
//
//    private void createListAllShas() {
//        mListLearning.clear();
//        Calendar dateDafHayomi = UtilsCalender.findDateOfStartDafHayomiEnglishDate();
//        int id = checkHighestId();
//        int correctIndexTypeStudy = findCorrectIndexTypeStudy();
//        for (int i = 0; i < mAllShas.getSeder().size(); i++) {
//            for (int j = 0; j < mAllShas.getSeder().get(i).getMasechtot().size(); j++) {
//                for (int k = 2; k < (mAllShas.getSeder().get(i).getMasechtot().get(j).getPages() + 2); k++) {
//                    int masechetPage = ConvertIntToPage.fixKinimTamidMidot(k, mAllShas.getSeder().get(i).getMasechtot().get(j).getName());
//                    Daf mPage = new Daf(mAllShas.getSeder().get(i).getMasechtot().get(j).getName(), masechetPage, stringDafHayomi, correctIndexTypeStudy, id);
//                    mPage.setPageDate(UtilsCalender.dateStringFormat(dateDafHayomi));
//                    mListLearning.add(mPage);
//                    dateDafHayomi.add(Calendar.DATE, 1);
//                    id++;
//                }
//            }
//        }
//    }
//
//    private int checkHighestId() {
//        int id = AppDataBase.getInstance(this).daoLearning().getHighestId();
//        if (id > 0) {
//            return id + 1;
//        } else {
//            return 1;
//        }
//    }
//
//    private int findCorrectIndexTypeStudy() {
//        ArrayList<Integer> mLeaning = (ArrayList<Integer>) AppDataBase.getInstance(this).daoLearning().getAllIndexTypeOfLeaning();
//        if (!mLeaning.contains(index1)) {
//            return index1;
//        } else if (!mLeaning.contains(index2)) {
//            return index2;
//        } else if (!mLeaning.contains(index3)) {
//            return index3;
//        }
//        return 0;
//    }
//
//
//    private void createListMasechet(String masechetName, int pages) {
//        mListLearning.clear();
//        int id = checkHighestId();
//        int correctIndexTypeStudy = findCorrectIndexTypeStudy();
//        for (int i = 2; i < (pages + 2); i++) {
//            mListLearning.add(new Daf(masechetName, ConvertIntToPage.fixKinimTamidMidot(i, masechetName), masechetName, correctIndexTypeStudy, id));
//            id++;
//        }
//    }


    private void alertDialogAreYouSure() {
        DialogInterface.OnClickListener dialogClickListener = (dialog, which) -> {
            switch (which) {
                case DialogInterface.BUTTON_POSITIVE:
                    AppDataBase.getInstance(getBaseContext()).daoLearning().insertStudyPlan(mStudyPlan);
                    startActivity(new Intent(ProfileActivity.this, MainActivity.class));
                    finish();
                    break;
                case DialogInterface.BUTTON_NEGATIVE:
                    dialog.dismiss();
                    openFragment(TypeStudyProfileFragment.newInstance((ArrayList<Seder>) mAllShas.getSeder()), TypeStudyProfileFragment.TAG);
                    break;
            }
        };
        String typeOfStudy = String.format(Locale.getDefault(),"%s %s", getString(R.string.type), mTypeOfStudy);
        String numberOfReps = String.format(Locale.getDefault(),"%s %d", getString(R.string.chazara), mStudyPlan.getWantChazara());
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.you_want_create_daily_study);
        builder.setMessage(typeOfStudy + getString(R.string.break_line) + numberOfReps);
        builder.setPositiveButton(R.string.yes, dialogClickListener)
                .setNegativeButton(R.string.no, dialogClickListener).show();
    }




//    @Override
//    public void updateActivityTypeStudy(String typeOfStudy) {
//
////        createListTypeOfStudy(typeOfStudy);
//    }

    @Override
    public void typeStudyOk(String studyPlan) {
        if (studyPlan == null || studyPlan.isEmpty()) {
            ToastAndDialog.toast(this, getString(R.string.you_must_choose_type_of_study));
        } else {
            mTypeOfStudy = studyPlan;
            openFragment(NumberOfRepetitionsProfileFragment.newInstance(), NumberOfRepetitionsProfileFragment.TAG);
        }
    }

    @Override
    public void numberOfRepOk(int numberOfRep) {
        initStudyPlan(mTypeOfStudy,numberOfRep);
//        updateListWithNumberOfRep(numberOfRep);
        alertDialogAreYouSure();
    }

    private void initStudyPlan(String mTypeOfStudy, int numberOfRep) {
        mStudyPlan = new StudyPlan(getHighestStudyPlanID(),mTypeOfStudy,numberOfRep);
    }

    private int getHighestStudyPlanID() {
        return AppDataBase.getInstance(this).daoLearning().getHighestStudyPlanId() +1;
    }

    //    private void updateListWithNumberOfRep(int numberOfRep) {
//        for (Daf daf : mListLearning) {
//            daf.setWantChazara(numberOfRep);
//        }
//    }
    @Override
    public void onBackPressed() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment currentFragment = fragmentManager.findFragmentById(R.id.frameLayout_profile_FL);
        if (currentFragment instanceof TypeStudyProfileFragment){
           finish();
        }
        if (currentFragment instanceof NumberOfRepetitionsProfileFragment){
            fragmentManager.popBackStack(TypeStudyProfileFragment.TAG,0);
        }
    }
}

