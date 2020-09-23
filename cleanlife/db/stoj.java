package com.example.cleanlife.db;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;


import java.util.ArrayList;

import java.util.List;



//string to JSON for the room lists
public class stoj {

    private String jsonString;
    private ArrayList<String> returnDesctiption;
    private JSONArray jsonArr;


    private int returnwhichlist;

    public stoj(String jsonString)
    {
        this.jsonString = jsonString;
    }


    public List<String> returnStringArray(int returns) throws JSONException {

        returnwhichlist = returns;
        String holding;
        jsonArr = new JSONArray(jsonString);
        int arraylength = jsonArr.length();
        returnDesctiption = new ArrayList<String>(arraylength);

        for (int i = 0; i < arraylength; i++)
        {
            JSONObject jsonObject = jsonArr.getJSONObject(i);
            String jsonText = jsonObject.toString();
            //inputStream = new ByteArrayInputStream(jsonText.getBytes());
            holding = decypherjson(jsonText);

            returnDesctiption.add(holding);
        }

        return returnDesctiption;
    }


   private String decypherjson(String jsonString) throws JSONException {
       JSONObject object = (JSONObject) new JSONTokener(jsonString).nextValue();

       String returning;

       if (returnwhichlist == 1)        //getting the description
       {
           returning = object.getString("desc");
       }
       else
       {
           returning = object.getString("listnum");
       }


       return returning;


   }




}
