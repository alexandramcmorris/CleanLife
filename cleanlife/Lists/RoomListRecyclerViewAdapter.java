package com.example.cleanlife.Lists;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cleanlife.R;
import com.example.cleanlife.db.ListStorage;


import java.util.List;

public class RoomListRecyclerViewAdapter extends RecyclerView.Adapter<RoomListRecyclerViewAdapter.ViewHolder> {
    public final List<ListStorage> rooms;

        public RoomListRecyclerViewAdapter(List<ListStorage> rooms)
        {
            this.rooms = rooms;
        }

    public void addRooms(List<ListStorage> rooms){
        this.rooms.clear();
        this.rooms.addAll(rooms);
        notifyDataSetChanged();
        }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public View root;
        public ListStorage item;
        public TextView txtcheckbox;

        public ViewHolder(View itemView) {
            super(itemView);
            root = itemView;
            txtcheckbox = (TextView) root.findViewById(R.id.checkboxlistitem);
        }
    }

    @NonNull
    @Override
    public RoomListRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.roomlistcheckoff, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RoomListRecyclerViewAdapter.ViewHolder holder, int position) {
        final ListStorage item = rooms.get(position);
        if (item != null){
            holder.item = item;
            holder.txtcheckbox.setText(item.getListItem());
            Log.d("roomlistrva",item.getListItem());
            /*holder.root.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putInt("room_pk", room.getRoomId());

                    CourseDetailsDialogFragment detailsDialogFragment = new CourseDetailsDialogFragment();
                    detailsDialogFragment.setArguments(bundle);

                    AppCompatActivity activity = (AppCompatActivity)v.getContext();
                    activity.getSupportFragmentManager()
                            .beginTransaction()
                            .add(android.R.id.content, detailsDialogFragment)
                            .addToBackStack(null)
                            .commit();
                }
            });*/
        }
    }

    @Override
    public int getItemCount() {

            Log.d("rlrva","getItemCount: " + rooms.size());
        return rooms.size();
    }
}
