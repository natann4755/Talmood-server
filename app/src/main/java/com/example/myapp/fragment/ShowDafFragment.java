package com.example.myapp.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

import com.example.model.Daf;
import com.example.myapp.R;
import com.example.myapp.databinding.FragmentShowDafBinding;
import com.example.myapp.utils.ConvertNamesToEnglish;
import com.example.myapp.utils.ToastAndDialog;

import java.util.Objects;


public class ShowDafFragment extends Fragment {

    public static final String TAG = ShowDafFragment.class.getSimpleName();
    private static final String KEY_DAF_TO_SHOW = "KEY_DAF_TO_SHOW";
    private FragmentShowDafBinding binding;
    private Daf mDafToShow;
    private LinearLayout linearLayoutButtons;
    private static final String BASIC_PATH = "https://www.sefaria.org.il/";

    public static Fragment newInstance(Daf dafToShow) {
        ShowDafFragment fragment = new ShowDafFragment();
        Bundle args = new Bundle();
        args.putParcelable(KEY_DAF_TO_SHOW, dafToShow);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mDafToShow = getArguments().getParcelable(KEY_DAF_TO_SHOW);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentShowDafBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews();
        ShowDaf(mDafToShow);
    }


    @SuppressLint("SetJavaScriptEnabled")
    private void ShowDaf(Daf mDafToShow) {
        String path = createPath(mDafToShow);
        binding.FSDWebViewWV.getSettings().setJavaScriptEnabled(true);
        binding.FSDWebViewWV.loadUrl(path);

//        binding.FSDWebViewWV.loadUrl("http://daf-yomi.com/Data/UploadedFiles/DY_Page/735.pdf");


        binding.FSDWebViewWV.setWebViewClient(new WebViewClient() {

            public void onPageFinished(WebView view, String url) {
                binding.FSDWebViewWV.setVisibility(View.VISIBLE);
                binding.FSDProgressBarPB.setVisibility(View.GONE);

            }
        });
    }

    private String createPath(Daf mDafToShow) {
        String pathMasechet = ConvertNamesToEnglish.convertMasechetNamesToEnglish(mDafToShow.getMasechet());
        if (pathMasechet.equals("Shekalim") || pathMasechet.equals("Kinnim") || pathMasechet.equals("Middot")) {
            return pathForShekalimKinnimMiddot(pathMasechet);
        } else {
            return BASIC_PATH +
                    pathMasechet
                    + "."
                    + mDafToShow.getPageNumber()
                    + "a"
                    + "?lang=he";
        }
    }



    private String pathForShekalimKinnimMiddot(String pathMasechet) {
        switch (pathMasechet) {
            case "Shekalim":
                ToastAndDialog.dialog(getActivity(), getString(R.string.alert_text_for_skalim));
                return BASIC_PATH
                        + "Jerusalem_Talmud_Shekalim.1a"
                        + "?lang=he";

            case "Kinnim":
            case "Middot":
                ToastAndDialog.dialog(getActivity(), getString(R.string.alert_text_for_kinim_midot));
                return BASIC_PATH
                        + "Mishnah_" + pathMasechet+ ".1"
                        + "?lang=he";

            default:
                return "";
        }
    }


    private void initViews() {
        linearLayoutButtons = Objects.requireNonNull(getActivity()).findViewById(R.id.typeOfStudy_buttons_LL);
        linearLayoutButtons.setVisibility(View.GONE);
    }

    @Override
    public void onPause() {
        super.onPause();
        linearLayoutButtons.setVisibility(View.VISIBLE);
    }
}