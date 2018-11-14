package com.drmmx.devmax.todoapp.ui.todo;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.drmmx.devmax.todoapp.R;

import java.util.List;
import java.util.Map;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.ViewHolder> {

    private TodoContract.View mView;
    private List<Map> mTodoList;
    private TodoContract.Presenter mPresenter;

    public TodoAdapter(TodoContract.View view,
                       List<Map> todoList,
                       TodoContract.Presenter presenter) {
        mView = view;
        mTodoList = todoList;
        mPresenter = presenter;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_todo, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.nameTodoText.setText(String.valueOf(mTodoList.get(position).get("name")));
        holder.descriptionTodoText.setText(String.valueOf(mTodoList.get(position).get("description")));

        if (!holder.checkBox.isChecked()) {
            mPresenter.checkTodo(mTodoList.get(position));
        }


        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mView.startAddTodo(String.valueOf(mTodoList.get(holder.getAdapterPosition()).get("objectId")));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mTodoList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        View view;

        TextView nameTodoText;
        TextView descriptionTodoText;
        CheckBox checkBox;

        ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            nameTodoText = itemView.findViewById(R.id.nameTextView);
            checkBox = itemView.findViewById(R.id.checkBox);
            descriptionTodoText = itemView.findViewById(R.id.descriptionTextView);
        }
    }
}