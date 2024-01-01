package com.example.todo_liste.adapter;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Adapter_task_decortion extends RecyclerView.ItemDecoration {
    public Adapter_task_decortion(int space) {
        this.space = space;
    }

    private int space;

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.top=space;
        outRect.bottom=space;
    }
}
