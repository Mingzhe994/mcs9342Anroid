package com.example.stuart.magic8ball;

/**
 * Created by Stuart on 14/03/2016.
 */
public class MagicEightBallModel extends Object{

    final static String[] initailRespenseArray = {"Dad","And","Mom"};
    String[] reresponseArray;

    public MagicEightBallModel(){
        String[] reresponseArray = initailRespenseArray;
    }

    public MagicEightBallModel(String[] extraR){

        reresponseArray = new String[initailRespenseArray.length+extraR.length];
        System.arraycopy(initailRespenseArray,0,reresponseArray,0,initailRespenseArray.length);
        System.arraycopy(extraR,0,reresponseArray,initailRespenseArray.length,extraR.length);

    }

    public String description(){
        String outPut = "";
        for (String tmp: reresponseArray){
            outPut += tmp;
        }


        return outPut;
    }

    public String debugDescription(){
        String outPut = "Debug";
        for (String tmp: reresponseArray){
            outPut += tmp;
        }
        return outPut;
    }
}
