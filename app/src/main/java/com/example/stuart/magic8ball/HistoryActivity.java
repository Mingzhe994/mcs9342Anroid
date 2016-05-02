package com.example.stuart.magic8ball;

import android.app.ListActivity;
import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HistoryActivity extends ListActivity{

    ListView listView;
    MediaPlayer mp;
    HashMap<String,Integer> map;
    ArrayList<QuestionResponseModel> showQuestionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        map = new HashMap<String, Integer>();
        mp = new MediaPlayer();
        showQuestionList = (ArrayList<QuestionResponseModel>) getIntent().getSerializableExtra("questionList");

        //listView = (ListView) findViewById(R.id.list);
        listView = getListView();

        myClassAdapter adapter;
        adapter = new myClassAdapter(this, android.R.layout.simple_list_item_2, showQuestionList);


       listView.setAdapter(adapter);

        map.put("battlec",R.raw.battlec);
        map.put("dropship",R.raw.dropship);
        map.put("frigate",R.raw.frigate);
        map.put("marine",R.raw.marine);
        map.put("tank",R.raw.tank);


    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
       //String o = this.getListAdapter().getItem(position).toString();
        //String pen = o.toString();

         String selection = showQuestionList.get(position).getAnswer();

        Toast.makeText(this, selection, Toast.LENGTH_LONG).show();
       // Log.i("music",playlist.get(position).toString());

        mp =  MediaPlayer.create(this, map.get(selection));
        mp.start();
    }
}

class myClassAdapter extends ArrayAdapter<QuestionResponseModel> {

    private class ViewHolder {
        TextView questionView;
        TextView answerView;
    }

    private int resource;
    private Context mcontext;
    ViewHolder viewHolder;

    public myClassAdapter(Context context, int textViewResourceId, ArrayList<QuestionResponseModel> items) {
        super(context, textViewResourceId, items);
        resource = textViewResourceId;
        mcontext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {

            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(mcontext).inflate(R.layout.list_items, parent, false);

            viewHolder.questionView = (TextView) convertView.findViewById(R.id.questionView);
            viewHolder.answerView = (TextView) convertView.findViewById(R.id.answerView);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.questionView.setText(getItem(position).getQuestion());
        viewHolder.answerView.setText(getItem(position).getAnswer());

        return convertView;
    }
}