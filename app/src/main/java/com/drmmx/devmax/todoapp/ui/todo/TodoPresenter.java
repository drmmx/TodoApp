package com.drmmx.devmax.todoapp.ui.todo;

import android.support.annotation.NonNull;
import android.util.Log;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.DataQueryBuilder;
import com.drmmx.devmax.todoapp.model.Todo;

import java.util.List;

public class TodoPresenter implements TodoContract.Presenter {

    private static final String TAG = "TodoPresenter";

    private final TodoContract.View mView;

    TodoPresenter(@NonNull TodoContract.View view) {
        mView = view;
    }

    @Override
    public void start() {
        getAllTodoList();
    }

    @Override
    public void getAllTodoList() {
        DataQueryBuilder queryBuilder = DataQueryBuilder.create();
        queryBuilder.setWhereClause("checked = 'false'");
        Backendless.Persistence.of(Todo.class).find(queryBuilder, new AsyncCallback<List<Todo>>() {
            @Override
            public void handleResponse(List<Todo> response) {
                mView.setData(response);
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                Log.d(TAG, fault.getMessage());
            }
        });
    }

    @Override
    public void checkTodo(Todo todo, boolean isChecked) {
        if (isChecked) {
            todo.setChecked(true);
            Backendless.Persistence.of(Todo.class).save(todo, new AsyncCallback<Todo>() {
                @Override
                public void handleResponse(Todo response) {
                    start();
                }
                @Override
                public void handleFault(BackendlessFault fault) {
                    Log.d(TAG, fault.getMessage());
                }
            });
            mView.setToast("Completed");
        }
    }
}
