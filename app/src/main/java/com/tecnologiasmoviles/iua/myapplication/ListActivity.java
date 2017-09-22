package com.tecnologiasmoviles.iua.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        ListView songList = (ListView) findViewById(R.id.listItem);
        ItemListAdapter songListAdapter = new ItemListAdapter();
        songList.setAdapter(songListAdapter);
    }
    private class ItemListAdapter extends BaseAdapter{

        private List<Item> dataSource;

        public ItemListAdapter (){
            ItemParser parser = new ItemParser();
            try {
                dataSource = parser.getJsonStream(getResources().openRawResource(R.raw.item_json));

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        @Override
        public int getCount() {
            return dataSource.size();
        }

        @Override
        public Object getItem(int i) {
            return dataSource.get(i);
        }

        @Override
        public long getItemId(int i) {
            return dataSource.get(i).getId();
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            if(view == null){
                view = getLayoutInflater().inflate(R.layout.layout_item, viewGroup, false);
            }

            ImageView coverImage = (ImageView) view.findViewById(R.id.cover);
            coverImage.setBackgroundResource(getResources().getIdentifier(dataSource.get(i).getMain_image(), "drawable", getPackageName()));

            TextView itemTitle = (TextView) view.findViewById(R.id.title);
            itemTitle.setText(String.valueOf(dataSource.get(i).getTitle()));

            TextView itemSubtitle = (TextView) view.findViewById(R.id.subtitle);
            itemSubtitle.setText(String.valueOf(dataSource.get(i).getSubtitle()));

            for(int j=0; j<dataSource.get(i).getListItem().size(); j++){
                String tag = "imageView" + (j+1);
                ImageView imageView = (ImageView) view.findViewWithTag(tag);
                imageView.setBackgroundResource(getResources().getIdentifier(dataSource.get(i).getListItem().get(j), "drawable", getPackageName()));
            }

            return view;
        }
    }


}
