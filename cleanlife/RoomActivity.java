package com.example.cleanlife;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;

import com.example.cleanlife.db.AppDatabase;
import com.example.cleanlife.db.ListStorage;

import java.util.List;

public class RoomActivity extends Fragment implements ListStorageAdapter.ItemClickListener {

    ListStorageAdapter adapter;
    View root;
    private RecyclerView recyclerView;
    //private int columnCount = 1;
    private int storage_pk;
    List<ListStorage> listStorage;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_room, container, false);

        Bundle bundle = this.getArguments();

        final Context context = getContext();

            storage_pk = bundle.getInt("storage_pk");

        recyclerView = (RecyclerView)root.findViewById(R.id.recyclerViewCheck);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Log.d("roomactivity", "getting list");
                    listStorage = AppDatabase.getInstance(getContext())
                            .StorageDAO()
                            .getAllItemsRoom(storage_pk);



                    adapter = new ListStorageAdapter(context, listStorage);

                    recyclerView.setAdapter(adapter);
                }
            }).start();







        return root;
    }


    @Override
    public void onResume() {
        super.onResume();
        Log.d("roomactivity", "onresume");
    }

    @Override
    public void onItemClick(View view, int position) {

    }
}