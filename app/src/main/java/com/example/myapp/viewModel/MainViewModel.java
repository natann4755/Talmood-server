package com.example.myapp.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.model.Learning;

import java.util.ArrayList;

public class MainViewModel extends AndroidViewModel {
    private MutableLiveData<ArrayList<Learning>> currentName;

    public MainViewModel(@NonNull Application application) {
        super(application);
    }

}
