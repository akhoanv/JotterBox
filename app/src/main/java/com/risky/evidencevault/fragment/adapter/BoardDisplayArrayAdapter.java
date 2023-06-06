package com.risky.evidencevault.fragment.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.risky.evidencevault.MainActivity;
import com.risky.evidencevault.R;
import com.risky.evidencevault.dao.Board;
import com.risky.evidencevault.data.ObjectBoxBoardManager;
import com.risky.evidencevault.utils.BoardSelectCallback;
import com.risky.evidencevault.utils.NumberUtil;

import java.util.List;

public class BoardDisplayArrayAdapter extends ArrayAdapter {
    // Database manager
    private ObjectBoxBoardManager boardManager;

    private BoardSelectCallback callback;

    public BoardDisplayArrayAdapter(@NonNull Context context, int resource, @NonNull List<Board> objects, BoardSelectCallback callback) {
        super(context, resource, objects);

        this.boardManager = ObjectBoxBoardManager.get();
        this.callback = callback;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;

        if (view == null) {
            // getting reference to the main layout and initializing
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.fragment_edit_note_connection_add_item, null);
        }

        // Find element
        ImageView iconView = view.findViewById(R.id.note_edit_connection_icon);
        TextView titleView = view.findViewById(R.id.note_edit_connection_title);
        TextView idView = view.findViewById(R.id.note_edit_connection_id);

        // Assign data
        iconView.setImageResource(((Board) getItem(position)).color.getRoundId());
        titleView.setText(((Board) getItem(position)).name);
        idView.setText("#" + NumberUtil.convertToDisplayId(((Board) getItem(position)).id));

        view.setOnClickListener(view1 -> {
            callback.run(((Board) getItem(position)));
        });

        return view;
    }

    public void update() {
        clear();
        addAll(boardManager.getAll());
        notifyDataSetChanged();
    }
}
