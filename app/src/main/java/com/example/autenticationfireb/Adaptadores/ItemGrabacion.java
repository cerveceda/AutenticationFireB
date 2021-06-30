package com.example.autenticationfireb.Adaptadores;

import com.example.autenticationfireb.R;

import java.util.ArrayList;
import java.util.List;

public class ItemGrabacion {
    private static final List<ItemGrabacion.itemGab> ITEMS = new ArrayList<ItemGrabacion.itemGab>();

    //contenido del arreglo
    static {
        addItem(new ItemGrabacion.itemGab ("1","Minnesota Rokkr", R.drawable.common_google_signin_btn_icon_dark,""));
        addItem(new ItemGrabacion.itemGab("2","Dallas Empire",R.drawable.common_google_signin_btn_icon_dark,""));
        addItem(new ItemGrabacion.itemGab("3","New York Subliners", R.drawable.common_google_signin_btn_icon_dark,""));
        addItem(new ItemGrabacion.itemGab("4","Atlanta Faze", R.drawable.common_google_signin_btn_icon_dark,""));
        addItem(new ItemGrabacion.itemGab("5","Optic Chicago", R.drawable.common_google_signin_btn_icon_dark,""));
    }
    //metodo que contruye el arreglo , metodo principal
    static void addItem(ItemGrabacion.itemGab item){
        ITEMS.add(item);
    }
    // metodo para agregar objetos
    public static ArrayList<ItemGrabacion.itemGab> ArregloLista (){
        ArrayList<ItemGrabacion.itemGab> d = new ArrayList<ItemGrabacion.itemGab>();
        for(ItemGrabacion.itemGab obj:ITEMS){
            d.add(obj);
        }
        return d;
    }
    // metodo para traer datos
    public static ItemGrabacion.itemGab getCodItem(String id){
        for(ItemGrabacion.itemGab obj:ITEMS){
            if (obj.id.equals(id)){
                return obj;
            }
        }
        return ITEMS.get(1); // busqueda título
    }
    // crear arreglo
    public static class itemGab {

        private String id;
        private String titulo;
        private Integer imagen;
        private String estado;

        public itemGab(String id,String titulo,Integer imagen,String estado){
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

        public String getestado() {
            return estado;
        }

        public void setestado(String estado) {
            this.estado = estado;
        }
    }
}
