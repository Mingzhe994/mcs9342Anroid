package com.example.stuart.magic8ball;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.content.Intent;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.*;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.Random;


public class Magic8Ball extends AppCompatActivity {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    TextView answer;
    Button someButton;
    ImageView pic;
    EditText question;
    String[] extraR = {"Godfather","StonyBrook"};
    int counter;

    final Animation out = new AlphaAnimation(1.0f, 0.0f);
    MagicEightBallModel newModel = new MagicEightBallModel(extraR);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        RelativeLayout layout = new RelativeLayout(this);
        layout.setBackgroundResource(R.drawable.rsz_background);

        question = new EditText(this);
        question.setHint("Ask a question...");
        //question.setId(1);
        RelativeLayout.LayoutParams qParams = new
                RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.FILL_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        qParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        // aParams.addRule(RelativeLayout.CENTER_VERTICAL);
        question.setSingleLine(true);
        question.setLayoutParams(qParams);
        layout.addView(question);

        someButton = new Button(this);
        someButton.setText("Shake");
        someButton.setBackgroundResource(R.drawable.rsz_shakebutton);
        someButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeMessage(v);
            }
        });

        RelativeLayout.LayoutParams bParams = new
                RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.FILL_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        bParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        someButton.setLayoutParams(bParams);

        layout.addView(someButton);

        pic = new ImageView(this);
        pic.setImageResource(R.drawable.rsz_circle1);

        RelativeLayout.LayoutParams pParams = new
                RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        pParams.addRule(RelativeLayout.CENTER_IN_PARENT);
        pParams.addRule(RelativeLayout.BELOW,1);
        pParams.addRule(RelativeLayout.ABOVE,2);

        pic.setLayoutParams(pParams);

        layout.addView(pic);


        answer = new TextView(this);
        answer.setText("Test");
        answer.setTextSize(20);
        answer.setTextColor(Color.parseColor("#FFFFFF"));
        RelativeLayout.LayoutParams aParams = new
                RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        aParams.addRule(RelativeLayout.CENTER_HORIZONTAL,RelativeLayout.TRUE);
        aParams.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);

        answer.setLayoutParams(aParams);
        layout.addView(answer);



        Log.i("Name","Mingzhe");

        final double age = 28;
        final String name = "Mingzhe";

        Log.i("Age:", Double.toString(age));
        Log.i("Name", name);

        Log.i("Question: ","Who gives you money?");
        Log.i("Answer",newModel.reresponseArray[0]);

        Log.i("Question: ","Who feeds you?");
        Log.i("Answer",newModel.reresponseArray[1]);

        Log.i("Question: ","Who protects you from the Mafia?");
        Log.i("Answer",newModel.reresponseArray[2]);




        final String[] questions = {"hey, Who gives you money?","hey, Who feeds you?","hey, Who protects you from the Mafia?"};

        for(int i = 0 ;i < questions.length;i++) {
            Log.i("Question: ",questions[i]);
            Log.i("Answer", newModel.reresponseArray[i]);
        }

        setContentView(layout);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        /*someButton = (Button) findViewById(R.id.button);
        pic = (ImageView) findViewById(R.id.imageView);
        answer = (TextView) findViewById(R.id.textView);
        question = (EditText) findViewById(R.id.searchbox);*/
        question.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    changeMessage(question);
                    InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                    return true; // consume.
                }
                return false; // pass on to other listeners.
            }
        });


        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Magic8Ball Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.stuart.magic8ball/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Magic8Ball Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.stuart.magic8ball/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }

    public void changeMessage(View view) {
        //if(counter != 0)
                counter++;
        int pos = counter % newModel.reresponseArray.length;

        String[] imageCircle = {"rsz_circle1","rsz_circle2","rsz_circle3","rsz_circle4","rsz_circle5","rsz_circle6"};
        Random rand = new Random();
        int n = rand.nextInt(imageCircle.length);
        Resources res = getResources();
        int id = res.getIdentifier(imageCircle[n], "drawable", getPackageName());
        pic.setImageResource(id);
        answer.setText(newModel.reresponseArray[pos]);

        out.setDuration(3000);
        answer.startAnimation(out);
    }
}
