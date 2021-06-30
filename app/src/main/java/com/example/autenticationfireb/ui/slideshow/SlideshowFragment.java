package com.example.autenticationfireb.ui.slideshow;

import android.app.Activity;
import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;


import com.example.autenticationfireb.Adaptadores.AdapterGrabacion;

import com.example.autenticationfireb.Adaptadores.ItemGrabacion;
import com.example.autenticationfireb.MenuDrawerActivity;
import com.example.autenticationfireb.R;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;



public class SlideshowFragment extends Fragment {

    private SlideshowViewModel slideshowViewModel;
    AdapterGrabacion adapter;
    ListView lista;
    String outputFile=null;
    MediaPlayer mediaPlayer = new MediaPlayer();
    private final int PICKER=1;
    private int state =1;
    private final int Playing=1;
    private final int Pausing=2;
    ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        // There are no request codes
                        Intent data = result.getData();
                        String FilePath =data.getData().getPath();
                        try{
                            mediaPlayer. setDataSource (FilePath) ;
                            mediaPlayer.prepare();
                        }catch (IOException e){
                            e.printStackTrace();
                        }
                    }
                }
            });

    private void PickFile (){
    Intent intent= new Intent (Intent.ACTION_GET_CONTENT);
intent.setType ("Eile/*");
        intent.addCategory (Intent.CATEGORY_OPENABLE);
            try{
                someActivityResultLauncher.launch(Intent.createChooser(intent, "Instale un adninistradorde archivos."));
            }catch (android.content.ActivityNotFoundException ex){}}

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                new ViewModelProvider(this).get(SlideshowViewModel.class);
        View root = inflater.inflate(R.layout.fragment_slideshow, container, false);
        lista = root.findViewById(R.id.listEquipos);

        adapter = new AdapterGrabacion(getContext(),getDatos()) {
        };
        lista.setAdapter(adapter);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // La posición donde se hace clic en el elemento de lista se obtiene de la
                // la posición de parámetro de la vista de lista de Android
                ItemGrabacion.itemGab item = (ItemGrabacion.itemGab) parent.getItemAtPosition(position);
               Intent intent = new Intent(getActivity().getBaseContext(), MenuDrawerActivity.class);
                intent.putExtra("id", item.getId());
                PickFile();
                mediaPlayer.start();

            //startActivity(intent);

            }
        });
        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView adapterView, View view, int i, long l) {
                Toast.makeText(getContext(), "click Largo " + i, Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        return root;
    }
    //metodo para retornar o recibir metodos

    private ArrayList<ItemGrabacion.itemGab> getDatos() {
        return ItemGrabacion.ArregloLista();
    }

    // evento se repite n veces , se usa varias vecxes
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menubuscardestino, menu);
        MenuItem item = menu.findItem(R.id.menuSearch);
        SearchView searchView = (SearchView) item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);

                return false;
            }
        });
        super.onCreateOptionsMenu(menu,inflater);
    }
}