package com.drmmx.devmax.todoapp.ui.complete;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.drmmx.devmax.todoapp.R;
import com.drmmx.devmax.todoapp.model.Todo;

import java.util.List;

public class CompleteAdapter extends RecyclerView.Adapter<CompleteAdapter.ViewHolder> {

    private CompleteContract.View mView;
    private List<Todo> mCompleteList;
    private CompleteContract.Presenter mPresenter;

    public CompleteAdapter(CompleteContract.View view,
                           List<Todo> completeList,
                           CompleteContract.Presenter presenter) {
        mView = view;
        mCompleteList = completeList;
        mPresenter = presenter;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_complete, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.nameTodoText.setText(String.valueOf(mCompleteList.get(position).getName()));
        holder.descriptionTodoText.setText(String.valueOf(mCompleteList.get(position).getDescription()));

        holder.imageDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.remove(mCompleteList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mCompleteList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        View view;

        TextView nameTodoText;
        TextView descriptionTodoText;
        ImageView imageDelete;

        ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            nameTodoText = itemView.findViewById(R.id.nameTextView);
            imageDelete = itemView.findViewById(R.id.imageDelete);
            descriptionTodoText = itemView.findViewById(R.id.descriptionTextView);
        }
    }
}