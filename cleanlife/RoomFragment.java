package com.example.cleanlife;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cleanlife.Lists.RoomListRecyclerViewAdapter;
import com.example.cleanlife.db.AppDatabase;
import com.example.cleanlife.db.ListStorage;

import java.util.List;


public class RoomFragment extends DialogFragment {

    View root;
    private RecyclerView recyclerView;
    ListStorageAdapter adapter;
    private RoomListRecyclerViewAdapter roomListRecyclerViewAdapter;

    private int columnCount = 1;
    Bundle bundle;
    Toolbar toolbar;
    TextView txtcheckRoomName;

    private int storage_pk;
    List<ListStorage> listStorage;
    String roomName;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        root = inflater.inflate(R.layout.fragment_room, container, false);


        toolbar = (Toolbar)root.findViewById(R.id.toolbar);
        txtcheckRoomName = (TextView)root.findViewById(R.id.checkRoomName);



        recyclerView = (RecyclerView) root.findViewById(R.id.recyclerViewCheck);
/*
        bundle = this.getArguments();

        if (bundle != null)
        {

            storage_pk = bundle.getInt("storage_pk");
            new Thread(new Runnable() {
                @Override
                public void run() {
                    listStorage = AppDatabase.getInstance(getContext())
                            .StorageDAO()
                            .getAllItemsRoom(storage_pk);

                    roomName = AppDatabase.getInstance(getContext())
                            .StorageDAO()
                            .returnName(storage_pk);

                    txtcheckRoomName.append(roomName);


                }
            }).start();
        }


        if (columnCount <= 1) {
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(getContext(), columnCount));
        }


        roomListRecyclerViewAdapter = new RoomListRecyclerViewAdapter(listStorage);

        recyclerView.setAdapter(roomListRecyclerViewAdapter);
*/
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);

        ActionBar actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
            actionBar.setHomeAsUpIndicator(android.R.drawable.ic_menu_close_clear_cancel);
        }

        setHasOptionsMenu(true);


        return root;
    }

    @Override
    public void onResume() {
        super.onResume();

        bundle = this.getArguments();

        if (bundle != null)
        {

            storage_pk = bundle.getInt("storage_pk");
            new Thread(new Runnable() {
                @Override
                public void run() {
                    listStorage = AppDatabase.getInstance(getContext())
                            .StorageDAO()
                            .getAllItemsRoom(storage_pk);

                    roomName = AppDatabase.getInstance(getContext())
                            .StorageDAO()
                            .returnName(storage_pk);

                    txtcheckRoomName.append(roomName);


                }
            }).start();
        }



        roomListRecyclerViewAdapter = new RoomListRecyclerViewAdapter(listStorage);
        Context context = getContext();
        if (columnCount <= 1) {
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(context, columnCount));
        }

        recyclerView.setAdapter(roomListRecyclerViewAdapter);
        recyclerView.setHasFixedSize(false);


        //roomListRecyclerViewAdapter.addRooms(listStorage);


        /*
        ViewModelProviders.of(this)
                .get(allListViewModel.class)
                .getLists(context)
                .observe(this, new Observer<List<ListStorage>>() {
                    @Override
                    public void onChanged(List<ListStorage> rooms) {
                        if (rooms != null) {
                            roomListRecyclerViewAdapter.addRooms(rooms);
                        }
                    }
                });*/


    }
}