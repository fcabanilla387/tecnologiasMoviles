package com.tecnologiasmoviles.iua.myapplication;

import android.util.JsonReader;
import android.util.JsonToken;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fcabanilla on 22/09/2017.
 */

class ItemParser {

    public List<Item> getJsonStream (InputStream in) throws IOException{

        JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
        try {
            return getItemArray(reader);
        }
        finally {
            reader.close();
        }

    }

    private List<Item> getItemArray(JsonReader reader) throws IOException {

        /*
        * El metodo este lo que hace es recorrer un item del arreglo, para poder leer cada uno de los Json
        * */
        List<Item> songs = new ArrayList<Item>();

        reader.beginArray();
        while (reader.hasNext()){
            songs.add(getItemData(reader));
        }
        reader.endArray();
        return songs;
    }

    private Item getItemData(JsonReader reader) throws IOException {

        /*
        * Recorro cada uno de las claves del Json, practicamente aca devuelvo un tema de musica entero
        * */

        int id = -1;
        String title = null;
        String subtitle = null;
        String main_image = null;
        List<String> listImages = null;

        reader.beginObject();
        while (reader.hasNext()){
            String name = reader.nextName();
            if (name.equals("id")){
                id = reader.nextInt();
            }else if (name.equals("title")){
                title = reader.nextString();
            }else if (name.equals("subtitle")){
                subtitle = reader.nextString();
            }else if (name.equals("main_image")){
                main_image = reader.nextString();
            }else if (name.equals("images")){
                listImages = new ArrayList<String>();
                reader.beginArray();
                while (reader.hasNext()){
                    if (reader.peek() != JsonToken.NULL){
                        listImages.add(reader.nextString());
                    }else {
                        reader.skipValue();
                    }
                }reader.endArray();
            }else
                reader.skipValue();
        }reader.endObject();

        return new Item(id, title, subtitle, main_image, listImages);
    }
}
