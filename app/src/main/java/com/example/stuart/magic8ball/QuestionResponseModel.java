package com.example.stuart.magic8ball;

import java.io.Serializable;

/**
 * Created by tuzuk on 23/04/2016.
 */
public class QuestionResponseModel implements Serializable{
    private static final long serialVersionUID = 1L;
    String question;
    String answer;

    public QuestionResponseModel(String question, String answer){
        this.question = question;
        this.answer = answer;
    }

    public String getQuestion(){
        return question;
    }

    public String getAnswer(){
        return answer;
    }
}
