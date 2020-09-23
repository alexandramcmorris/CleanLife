package com.example.cleanlife.db;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.cleanlife.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@Database(entities = {Lineage.class, RoomTable.class, Storage.class, ListStorage.class}, version=1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase{

    private static AppDatabase instance;

    private static Context mcontext;

    static RoomDatabase.Callback mcallback = new RoomDatabase.Callback(){
        public void onCreate(SupportSQLiteDatabase db){
            Log.d("dbgroup", "on create");
            loadRooms();
        }

        public void onOpen(SupportSQLiteDatabase db) {

            Log.d("dbgroup", "on open");
        }
    };

    public static AppDatabase getInstance(Context context){
        if (instance != null) return instance;

        mcontext = context;

        instance = Room.databaseBuilder(context, AppDatabase.class, "lineage-database")
                .addCallback(mcallback)
                .build();

        return instance;
    }

    public static void loadRooms()
    {
        new Thread(new Runnable() {
            public void run() {
                try {
                    loadRoom();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }

    private static void loadRoom() throws IOException {

        InputStream inputStream = mcontext.getResources().openRawResource(R.raw.rooms);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        try {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] strings = TextUtils.split(line, "-");
                if (strings.length < 4) continue;
                Log.d("app", strings[0] + " "+ strings[1] +" "+ strings[2] +" "+strings[3]);
                addRoom(strings[0].trim(), strings[1].trim(), strings[2].trim(), strings[3].trim());
                //if (id < 0) {
                // Log.e("adding rooms", "unable to add word: " + strings[0].trim());
                Log.d("app", strings[0]);
                //}
            }
        } finally {
            reader.close();
        }
    }

    private static void addRoom(final String roomID, final String roomName, final String difficulty, final String lists) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d("newb", roomID);
                RoomTable roomTable = new RoomTable(roomID, roomName, difficulty, lists);

                AppDatabase.getInstance(mcontext)
                        .RoomDAO()
                        .insert(roomTable);

            }
        }).start();


    }

    public abstract StorageDAO StorageDAO();
    public abstract LineageDAO lineageDAO();
    public abstract RoomDAO RoomDAO();

}
