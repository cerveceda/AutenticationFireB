package com.example.autenticationfireb.Adaptadores;

import com.example.autenticationfireb.R;

import java.util.ArrayList;
import java.util.List;

public class ItemGrabacion {
    private static final List<ItemGrabacion.itemGab> ITEMS = new ArrayList<ItemGrabacion.itemGab>();

    //contenido del arreglo
    static {
        addItem(new ItemGrabacion.itemGab ("1","Grabacion", R.drawable.musica,"Grabacion0.m4a"));
        addItem(new ItemGrabacion.itemGab("2","Grabacion1",R.drawable.musica,"/storage/emulated/Grabacion1.m4a"));
        addItem(new ItemGrabacion.itemGab("3","Grabacion2", R.drawable.musica,"/storage/emulated/Grabacion2.m4a"));
        addItem(new ItemGrabacion.itemGab("4","Grabacion3", R.drawable.musica,"/storage/emulated/Grabacion3.m4a"));
        addItem(new ItemGrabacion.itemGab("5","Grabacion4", R.drawable.musica,"/storage/emulated/Grabacion4.m4a"));
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
