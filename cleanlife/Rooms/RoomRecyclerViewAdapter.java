package com.example.cleanlife.Rooms;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;


import com.example.cleanlife.R;
import com.example.cleanlife.RoomActivity;
import com.example.cleanlife.RoomFragment;
import com.example.cleanlife.db.Storage;


import java.util.List;

public class RoomRecyclerViewAdapter extends RecyclerView.Adapter<RoomRecyclerViewAdapter.ViewHolder> {

    public final List<Storage> rooms;

    private onItemClick mCallBack;





    RoomRecyclerViewAdapter(List<Storage> rooms)
    {
        this.rooms = rooms;
    }

    public void addRooms(List<Storage> rooms){
        this.rooms.clear();
        this.rooms.addAll(rooms);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public View root;
        public Storage room;
        public TextView txtRoomName;
        public Button btnViewList;
        public Bundle bundle;

        public ViewHolder(View itemView) {
            super (itemView);
            root = itemView;
            txtRoomName = (TextView)root.findViewById(R.id.txtRoomName);
            btnViewList = (Button)root.findViewById(R.id.openListViewButton);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyler_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final Storage room = rooms.get(position);

        if (room != null){
            holder.room = room;
            holder.txtRoomName.setText(room.getSroomname());
            final Bundle bundle = new Bundle();
           holder.root.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    bundle.putInt("storage_pk", room.getSid());
                    bundle.putBoolean("isSelected", true);
                    holder.btnViewList.setVisibility(View.VISIBLE);
                    if (bundle == null)
                    {

                    }
                    else
                    {

                    }


                    Fragment currentFrag;
                    //homeMainActivity.setArguments(bundle);
                    AppCompatActivity activity = (AppCompatActivity)v.getContext();
                    currentFrag =  activity.getSupportFragmentManager()
                            .findFragmentById(R.id.content);
                    //((HomeMainActivity)currentFrag).stuffandthings();

                            //.add(android.R.id.content, homeMainActivity)
                            //.addToBackStack(null)                            .commit();
                    Log.d("rrva", bundle.toString());


                    holder.btnViewList.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            RoomActivity roomActivity = new RoomActivity();
                            roomActivity.setArguments(bundle);

                            AppCompatActivity activity = (AppCompatActivity)v.getContext();
                            activity.getSupportFragmentManager()
                                    .beginTransaction()
                                    .replace(android.R.id.content, roomActivity)
                                    .addToBackStack(null)
                                    .commit();
                        }
                    });



                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return rooms.size();
    }


}
