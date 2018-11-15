package com.drmmx.devmax.todoapp.ui.todo;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.drmmx.devmax.todoapp.R;
import com.drmmx.devmax.todoapp.model.Todo;

import java.util.List;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.ViewHolder> {

    private TodoContract.View mView;
    private List<Todo> mTodoList;
    private TodoContract.Presenter mPresenter;

    public TodoAdapter(TodoContract.View view,
                       List<Todo> todoList,
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
        holder.nameTodoText.setText(String.valueOf(mTodoList.get(position).getName()));
        holder.descriptionTodoText.setText(String.valueOf(mTodoList.get(position).getDescription()));

        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mPresenter.checkTodo(mTodoList.get(position), isChecked);
            }
        });


        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mView.startAddTodo(String.valueOf(mTodoList.get(holder.getAdapterPosition()).getObjectId()));
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