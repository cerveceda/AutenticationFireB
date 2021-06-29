package com.example.autenticationfireb.Adaptadores;

import com.example.autenticationfireb.R;

import java.util.ArrayList;
import java.util.List;

public class ItemFavorito {
    private static final List<itemFav> ITEMS = new ArrayList<itemFav>();

    //contenido del arreglo
    static {
        addItem(new ItemFavorito .itemFav ("1","Minnesota Rokkr", R.drawable.common_google_signin_btn_icon_dark,R.drawable.estrella));
        addItem(new ItemFavorito .itemFav("2","Dallas Empire",R.drawable.common_google_signin_btn_icon_dark,R.drawable.estrella));
        addItem(new ItemFavorito .itemFav("3","New York Subliners", R.drawable.common_google_signin_btn_icon_dark,R.drawable.estrella));
        addItem(new ItemFavorito .itemFav("4","Atlanta Faze", R.drawable.common_google_signin_btn_icon_dark,R.drawable.estrella));
        addItem(new ItemFavorito .itemFav("5","Optic Chicago", R.drawable.common_google_signin_btn_icon_dark,R.drawable.estrella));
    }
    //metodo que contruye el arreglo , metodo principal
    static void addItem(ItemFavorito .itemFav item){
        ITEMS.add(item);
    }
    // metodo para agregar objetos
    public static ArrayList<ItemFavorito .itemFav> ArregloLista (){
        ArrayList<ItemFavorito .itemFav> d = new ArrayList<itemFav>();
        for(ItemFavorito .itemFav obj:ITEMS){
            d.add(obj);
        }
        return d;
    }
    // metodo para traer datos
    public static ItemFavorito .itemFav getCodItem(String id){
        for(ItemFavorito .itemFav obj:ITEMS){
            if (obj.id.equals(id)){
                return obj;
            }
        }
        return ITEMS.get(1); // busqueda título
    }
    // crear arreglo
    public static class itemFav {

        private String id;
        private String titulo;
        private Integer imagen;
        private Integer estado;

        public itemFav(String id,String titulo,Integer imagen,Integer estado){
            this.id = id;
            this.titulo = titulo;
            this.imagen = imagen;
            this.estado = estado;
        }
        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitulo() {
            return titulo;
        }

        public void setTitulo(String titulo) {
            this.titulo = titulo;
        }

        public Integer getImagen() {
            return imagen;
        }

        public void setImagen(Integer imagen) {
            this.imagen = imagen;
        }

        public Integer getestado() {
            return estado;
        }

        public void setestado(Integer estado) {
            this.estado = estado;
        }
    }
}
