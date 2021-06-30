package com.example.autenticationfireb.Adaptadores;

import com.example.autenticationfireb.R;

import java.util.ArrayList;
import java.util.List;

public class ItemFavorito {
    private static final List<itemFav> ITEMS = new ArrayList<itemFav>();

    //contenido del arreglo
    static {
        addItem(new ItemFavorito .itemFav ("1","New Sensation", R.drawable.vid1,R.drawable.estrella,"https://www.youtube.com/watch?v=wbV1nfWzMg4"));
        addItem(new ItemFavorito .itemFav("2","WADU WADU",R.drawable.vid2,R.drawable.estrella,"https://www.youtube.com/watch?v=qvgBAX4-Buo"));
        addItem(new ItemFavorito .itemFav("3","Llamame si me necesitas", R.drawable.vid3,R.drawable.estrella,"https://www.youtube.com/watch?v=TsZsB0r7mms"));
        addItem(new ItemFavorito .itemFav("4","Lay all your Love on Me", R.drawable.vid4,R.drawable.estrella,"https://www.youtube.com/watch?v=YgXrd7eE6ME"));
        addItem(new ItemFavorito .itemFav("5","Bring you up", R.drawable.vid5,R.drawable.estrella,"https://www.youtube.com/watch?v=Uv_4O35xWpE"));
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
        private String link;
        public itemFav(String id,String titulo,Integer imagen,Integer estado,String link){
            this.id = id;
            this.titulo = titulo;
            this.imagen = imagen;
            this.estado = estado;
            this.link=link;
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

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }


    }
}
