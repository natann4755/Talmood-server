package com.example.myapp.repository;

import android.content.Context;

import com.example.model.Learning;
import com.example.model.StudyPlan;
import com.example.myapp.dataBase.AppDataBase;

import java.util.ArrayList;

public class Repository {
    private Context mContext;

    public Repository(Context context) {
        this.mContext = context;
    }

    public void initDataFromDBOrServer(RepositoryListener mListener){

        ArrayList<StudyPlan> mStudyPlans =  (ArrayList<StudyPlan>) AppDataBase.getInstance(mContext).daoLearning().getAllStudyPlans();

        if (mStudyPlans != null && mStudyPlans.size()>0){
            mListener.onSuccessGetData(false);
        }else {
            mListener.onSuccessGetData(true);
        }

        //        if (mAllLearning != null)
//        init from server

    }








    public interface RepositoryListener {

        void onSuccessGetData(boolean firstTime);
    }
}
