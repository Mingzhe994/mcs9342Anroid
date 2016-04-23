package com.example.stuart.magic8ball;

import android.content.Context;
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

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<QuestionResponseModel> showQuestionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        showQuestionList = (ArrayList<QuestionResponseModel>) getIntent().getSerializableExtra("questionList");

        listView = (ListView) findViewById(R.id.list);

        myClassAdapter adapter;
        adapter = new myClassAdapter(this, android.R.layout.simple_list_item_2, showQuestionList);

        listView.setAdapter(adapter);
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