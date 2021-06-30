package com.example.autenticationfireb.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.autenticationfireb.R;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private LinearLayout btnPlay, btnStop, btnRecord;
    private WebView webView;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        this.btnPlay = root.findViewById(R.id.btnPlay);
        this.btnStop = root.findViewById(R.id.btnStop);
        this.btnRecord = root.findViewById(R.id.btnRecord);

        this.webView = root.findViewById(R.id.wvWeb);

        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://www.youtube.com");

        return root;
    }
}