package com.example.autenticationfireb.ui.gallery;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.autenticationfireb.Adaptadores.AdapterFavorito;
import com.example.autenticationfireb.Adaptadores.ItemFavorito;
import com.example.autenticationfireb.R;
import com.example.autenticationfireb.ui.home.HomeFragment;

import java.util.ArrayList;

public class GalleryFragment extends  Fragment {
    AdapterFavorito adapter;
    ListView lista;
    private GalleryViewModel galleryViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        lista = root.findViewById(R.id.listEquipos);

        adapter = new AdapterFavorito(getContext(),getDatos()) {
        };
        lista.setAdapter(adapter);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // La posición donde se hace clic en el elemento de lista se obtiene de la
                // la posición de parámetro de la vista de lista de Android
                ItemFavorito.itemFav item = (ItemFavorito .itemFav) parent.getItemAtPosition(position);
                Intent intent = new Intent(getActivity().getBaseContext(), MenuDrawerActivity.class);
                intent.putExtra("id", item.getId());
                startActivity(intent);

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

    private ArrayList<ItemFavorito .itemFav> getDatos() {
        return ItemFavorito.ArregloLista();
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