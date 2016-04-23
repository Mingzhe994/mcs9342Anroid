package com.example.stuart.magic8ball;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.content.Intent;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.*;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Random;


public class Magic8Ball extends AppCompatActivity {
    // Storage Permissions
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }
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
    ArrayList<QuestionResponseModel> questionArray = new ArrayList<QuestionResponseModel>();

    final Animation out = new AlphaAnimation(1.0f, 0.0f);
    MagicEightBallModel newModel = new MagicEightBallModel(extraR);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        verifyStoragePermissions(this);
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

        setContentView(R.layout.activity_magic8_ball);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        someButton = (Button) findViewById(R.id.button);
        pic = (ImageView) findViewById(R.id.imageView);
        answer = (TextView) findViewById(R.id.textView);
        question = (EditText) findViewById(R.id.searchbox);
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
        setSerializableMethod( question.getText().toString(),newModel.reresponseArray[pos]);

        out.setDuration(3000);
        answer.startAnimation(out);
    }

    // q == question, a == answer
    public void setSerializableMethod(String q, String a){
        QuestionResponseModel tmp = new QuestionResponseModel(q,a);
        questionArray.add(tmp);

        try
        {
            FileOutputStream fileOut = new FileOutputStream("/mnt/sdcard/questionRecord.bin");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(questionArray);
            out.close();
            fileOut.close();
            System.out.printf("Serialized data is saved in a.out");
        }catch(IOException i)
        {
            i.printStackTrace();
        }
    }

    public ArrayList<QuestionResponseModel> deserializing(){
        ArrayList<QuestionResponseModel> tmp = new ArrayList<QuestionResponseModel>();

        try
        {
            FileInputStream fileIn = new FileInputStream("/mnt/sdcard/questionRecord.bin");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            tmp = (ArrayList<QuestionResponseModel>) in.readObject();
            in.close();
            fileIn.close();
        }catch(IOException i){ i.printStackTrace();
        }catch(ClassNotFoundException c){
            System.out.println("Qeustion class not found");
            c.printStackTrace();
        }

        return tmp;
    }

    public void toHistoryActivity(View view){
        Intent intent = new Intent();
        intent.setClass(Magic8Ball.this, HistoryActivity.class);
        Bundle bundle = new Bundle();
        Log.i("Number of question: ",Integer.toString(questionArray.size()));
        bundle.putSerializable("questionList", deserializing());
        intent.putExtras(bundle);
        startActivity(intent);
    }

}
