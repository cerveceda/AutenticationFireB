package com.example.autenticationfireb;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class MapsFragment extends Fragment {
    private GoogleMap mapa;
    private OnMapReadyCallback callback = new OnMapReadyCallback() {

        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        @Override
        public void onMapReady(GoogleMap googleMap) {

            LatLng sydney = new LatLng(-13.518121379124077, -71.97867329157455);
            googleMap.addMarker(new MarkerOptions().position(sydney).title("Karaoke"));
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
            mapa = googleMap;
            googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                @Override
                public void onMapClick(@NonNull LatLng latLng) {
                    // Cuando se hace clic al mapa
                    MarkerOptions marker = new MarkerOptions();
                    // Poner posiciones
                    marker.position(latLng);
                    // Titulo
                    marker.title(latLng.latitude + ", " + latLng.longitude);
                    // Remover marcadores
                    googleMap.clear();
                    // Animaciones
                    googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));
                    // Añadir marcadores
                    googleMap.addMarker(marker);
                }
            });
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_maps, container, false);
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map); // Inicializar
        SearchView buscador = root.findViewById(R.id.search);

        buscador.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                String ubicacion = buscador.getQuery().toString();
                List<Address> listaDirecciones = null;
                if (ubicacion != null || !ubicacion.equals("")) {
                    Geocoder geo = new Geocoder(getContext());
                    try {
                        listaDirecciones = geo.getFromLocationName(ubicacion, 3);
                    } catch (IOException io) {
                        io.printStackTrace();
                    }
                    Address direccion = listaDirecciones.get(0);
                    LatLng latlng = new LatLng(direccion.getLatitude(), direccion.getLongitude());
                    mapa.addMarker(new MarkerOptions().position(latlng).title(ubicacion));
                    mapa.animateCamera(CameraUpdateFactory.newLatLngZoom(latlng, 10));
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }



        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
    }
}