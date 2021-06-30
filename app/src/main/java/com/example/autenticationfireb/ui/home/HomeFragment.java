package com.example.autenticationfireb.ui.home;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.autenticationfireb.R;

import java.io.IOException;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private LinearLayout btnPlay, btnStop, btnRecord;
    private WebView webView;
    MediaRecorder miGrabacion;
    String outputFile=null;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
       homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.RECORD_AUDIO)!= PackageManager.PERMISSION_GRANTED){ ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest .permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO}, 1000);}
        this.btnPlay = root.findViewById(R.id.btnPlay);
        this.btnStop = root.findViewById(R.id.btnStop);
        this.btnRecord = root.findViewById(R.id.btnRecord);

        this.webView = root.findViewById(R.id.wvWeb);

        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://www.youtube.com");
        btnPlay.setOnClickListener(e ->reproduccir());
        btnRecord.setOnClickListener(e->grabar());
        btnStop.setOnClickListener(e->detener());
        return root;
    }
    public void grabar(){
       outputFile= Environment.getExternalStorageDirectory().getAbsolutePath()+"/Grabacion.3gp";
        miGrabacion = new MediaRecorder();
        miGrabacion.setAudioSource(MediaRecorder.AudioSource.MIC);
        miGrabacion.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        miGrabacion.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
       miGrabacion.setOutputFile(outputFile) ;

        try{
            miGrabacion.prepare();
            miGrabacion.start();
        }catch (IllegalStateException e){

        }catch (IOException e){
            e.printStackTrace();}
        Toast.makeText(getContext(),"La grabacion comenzo",Toast.LENGTH_LONG).show();
    }
    public void detener(){
        if (miGrabacion != null){
            miGrabacion.stop();
            miGrabacion.release();
            miGrabacion = null;};

    }
    public void reproduccir(){
        MediaPlayer m = new MediaPlayer();
        try{
           m.setDataSource(outputFile) ;
        }catch (IOException e){
            e.printStackTrace();
        }try{
            m.prepare ();
        }catch (IOException e){
            e.printStackTrace();}
        m.start();
        Toast.makeText(getContext(),"Reproduciendo audio",Toast.LENGTH_LONG).show();



    }

}