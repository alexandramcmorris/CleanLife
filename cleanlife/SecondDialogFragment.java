package com.example.cleanlife;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toolbar;

import com.example.cleanlife.db.AppDatabase;
import com.example.cleanlife.db.Lineage;
import com.example.cleanlife.db.ListStorage;
import com.example.cleanlife.db.Storage;
import com.example.cleanlife.db.stoj;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;


public class SecondDialogFragment extends DialogFragment {

    View root;
    Button startButton;
    private int userDifficulty;
    ArrayList<Lineage> rooms;
    private List<Lineage> testList;
    private Storage storageItem;

    private TextInputEditText tietBedroom, tietBathroom, tietLivingRoom, tietDiningRoom, tietKitchen,
        tietGarage, tietPantry;

    public SecondDialogFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //final Toolbar toolbar = root.findViewById(R.id.toolbar);
        //final FloatingActionButton fab = root.findViewById(R.id.fab);

        root = inflater.inflate(R.layout.fragment_second_dialog, container, false);

        startButton = (Button) root.findViewById(R.id.startbtn);
        tietBathroom = (TextInputEditText) root.findViewById(R.id.txtBathroom);
        tietBedroom = (TextInputEditText) root.findViewById(R.id.txtBedroom);
        tietLivingRoom = (TextInputEditText) root.findViewById(R.id.txtLivingRoom);
        tietDiningRoom = (TextInputEditText) root.findViewById(R.id.txtDiningRoom);
        tietKitchen = (TextInputEditText) root.findViewById(R.id.txtKitchen);
        tietGarage = (TextInputEditText) root.findViewById(R.id.txtGarage);
        tietPantry = (TextInputEditText) root.findViewById(R.id.txtPantry);


        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createRooms();
                //fab.setVisibility(View.VISIBLE);
                //toolbar.setVisibility(View.VISIBLE);
                ActionBar actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();

                if (actionBar != null)
                {
                    actionBar.show();
                }
                setHasOptionsMenu(true);
                dismiss();
            }
        });

        return root;
    }



    public void getUsersPreference(int userchoice) {
        Log.d("seconddialog", userchoice + " ");
        userDifficulty = userchoice;
    }



    private void createRooms()
    {
        int bedroom = Integer.parseInt(tietBedroom.getText().toString());
        int bathroom = Integer.parseInt(tietBathroom.getText().toString());
        int livingroom = Integer.parseInt(tietLivingRoom.getText().toString());
        int diningroom = Integer.parseInt(tietDiningRoom.getText().toString());
        int kitchen = Integer.parseInt(tietKitchen.getText().toString());
        int garage = Integer.parseInt(tietGarage.getText().toString());
        int pantry = Integer.parseInt(tietPantry.getText().toString());

        int count = 0; //this is used to loop through and insert the rooms
        final int total = bedroom + bathroom + livingroom + diningroom + kitchen + pantry + garage;

        rooms = new ArrayList<Lineage>(total); //holds the rooms to be stored

        if (bedroom != 0) {
            Lineage lines = new Lineage(
                    Integer.toString(userDifficulty),
                    "1",
                    Integer.toString(bedroom));

            rooms.add(lines);
        }

        if (bathroom != 0) {
            Lineage lines = new Lineage(
                    Integer.toString(userDifficulty),
                    "2",
                    Integer.toString(bathroom)
            );
            rooms.add(lines);
        }

        if (livingroom != 0) {
            Lineage lines = new Lineage(
                    Integer.toString(userDifficulty),
                    "3",
                    Integer.toString(livingroom)
            );
            rooms.add(lines);
        }

        if (diningroom != 0) {
            Lineage lines = new Lineage(
                    Integer.toString(userDifficulty),
                    "4",
                    Integer.toString(diningroom)
            );
            rooms.add(lines);
        }

        if (kitchen != 0) {
            Lineage lines = new Lineage(
                    Integer.toString(userDifficulty),
                    "5",
                    Integer.toString(kitchen)
            );
            rooms.add(lines);
        }

        if (garage != 0)
        {
            Lineage lines = new Lineage(
                Integer.toString(userDifficulty),
                "6",
                Integer.toString(garage)
            );
            rooms.add(lines);
        }

        if (pantry != 0) {
            Lineage lines = new Lineage(
                    Integer.toString(userDifficulty),
                    "7",
                    Integer.toString(pantry)
            );
            rooms.add(lines);
        }



            new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < rooms.size(); i++)
                {
                    Log.d("seconddialog","adding lineage " + i);
                    AppDatabase.getInstance(getContext())
                            .lineageDAO()
                            .insert(rooms.get(i));
                }

                testList = AppDatabase.getInstance(getContext())
                        .lineageDAO()
                        .getAllLineage();

                for (int j = 0; j < testList.size(); j++)
                {
                    Log.d("testList", " " + testList.get(j));
                    Lineage temp = testList.get(j);
                    createStorage(temp);
                }

            }
            }).start();
    }


    private void createStorage(Lineage lineage) {
        int quantity = Integer.parseInt(lineage.getLineQuantity());
        int lid = lineage.getL_id();
        String rid = lineage.getLineroomID();

        Log.d("second", "in storage");
        String nameTest = AppDatabase.getInstance(getContext())
                .RoomDAO()
                .getRoomName(rid);

        Storage temp = new Storage(lid,
                nameTest,
                false
                );

        for (int i = 0; i < quantity; i++) {
            AppDatabase.getInstance(getContext())
                    .StorageDAO()
                    .insert(temp);
            Log.d("second", "in for loop of storage");
        }

        List<Storage> storageList = AppDatabase.getInstance(getContext())
                .StorageDAO()
                .testinglistRooms();

        for (int i = 0; i < storageList.size(); i++)
        {
            Log.d("second", "Storage List " + storageList.get(i));
            setListItems(storageList.get(i), rid);
        }
    }

    private void setListItems(Storage currentStorage, String roomID)
    {

        int storageId = currentStorage.getSid();
        String templist = AppDatabase.getInstance(getContext())
                .RoomDAO()
                .getRoom(roomID, Integer.toString(userDifficulty));

        List<String> entering;

        stoj changestring = new stoj(templist);
        try {
                entering = changestring.returnStringArray(1);
                int listNum = entering.size();

                for (int i = 0; i < listNum; i++)
                {
                    Log.d("lists", "change string: " + entering.get(i));

                    ListStorage ls = new ListStorage(storageId,
                            i,
                            entering.get(i),
                            false);
                    AppDatabase.getInstance(getContext())
                            .StorageDAO()
                            .insertList(ls);
                }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }




}