package com.example.cleanlife.Rooms;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.cleanlife.R;
import com.example.cleanlife.Rooms.RoomRecyclerViewAdapter;
import com.example.cleanlife.Rooms.allRoomsViewModel;
import com.example.cleanlife.db.Storage;

import java.util.ArrayList;
import java.util.List;

public class HomeMainActivity extends Fragment implements onItemClick{


        View root;
        private RecyclerView recyclerView;
        private RoomRecyclerViewAdapter roomRecyclerViewAdapter;
        private int columnCount = 1;
        Toolbar toolbar;


        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                                 @Nullable Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            root = inflater.inflate(R.layout.fragment_home_main, container, false);
            toolbar = (Toolbar)root.findViewById(R.id.toolbar);
            recyclerView = (RecyclerView)root.findViewById(R.id.recyclerView);

            Bundle bundle = this.getArguments();
            ActionBar actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();

            //Log.d("mainmenu", bundle.getBoolean("isSelected"));

            if (bundle != null)
            {
                Log.d("mainactivity","bundle is loaded");

            }
            else
            {
                if (actionBar != null) {
                    actionBar.setDisplayHomeAsUpEnabled(true);
                    actionBar.setHomeButtonEnabled(true);
                    actionBar.setHomeAsUpIndicator(android.R.drawable.ic_menu_close_clear_cancel);
                }

                setHasOptionsMenu(true);
                ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);


            }




            return root;
        }

        @Override
        public void onResume() {
            super.onResume();



            Context context = getContext();
            Bundle bundle = this.getArguments();


                roomRecyclerViewAdapter = new RoomRecyclerViewAdapter(new ArrayList<Storage>());

                if (columnCount <= 1) {
                    recyclerView.setLayoutManager(new LinearLayoutManager(context));
                }
                else
                {
                    recyclerView.setLayoutManager(new GridLayoutManager(context, columnCount));
                }

                recyclerView.setAdapter(roomRecyclerViewAdapter);
                recyclerView.setHasFixedSize(false);

                ViewModelProviders.of(this)
                        .get(allRoomsViewModel.class)
                        .getRoomList(context)
                        .observe(this, new Observer<List<Storage>>() {
                            @Override
                            public void onChanged(List<Storage> rooms) {
                                if (rooms != null)
                                {
                                    roomRecyclerViewAdapter.addRooms(rooms);
                                }
                            }
                        });










        }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        menu.clear();
        getActivity().getMenuInflater().inflate(R.menu.menu_main, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.homedelete:
                // get new course info and save it to the db
               /* new Thread(new Runnable() {
                    @Override
                    public void run() {
                        if (isNew){
                            Log.d("newb", "is new");
                            Course course = new Course(txtCourseNumber.getText().toString(),
                                    txtCourseName.getText().toString(),
                                    txtCourseCode.getText().toString(),
                                    txtStart.getText().toString(),
                                    txtEnd.getText().toString());
                            AppDatabase.getInstance(getContext())
                                    .courseDAO()
                                    .insert(course);
                        }
                        else
                        {
                            Log.d("newb",  course.toString());
                            course.setCourse_number(txtCourseNumber.getText().toString());
                            course.setCourseName(txtCourseName.getText().toString());
                            course.setCourse_code(txtCourseCode.getText().toString());
                            course.setStart_at(txtStart.getText().toString());
                            course.setEnd_at(txtEnd.getText().toString());
                            AppDatabase.getInstance(getContext())
                                    .courseDAO()
                                    .update(course);

                        }
                    }
                }).start();*/


                return true;

            case R.id.homeedit:

                break;

            case R.id.checkListmenuoption:



            default:
                return super.onOptionsItemSelected(item);

        }


        return super.onOptionsItemSelected(item);
    }


    public void stuffandthings(){
            Log.d("stuffandthings", "you are here");
    }



}