package com.drmmx.devmax.todoapp.ui.todo;

import android.support.annotation.NonNull;
import android.util.Log;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.DataQueryBuilder;

import java.util.List;
import java.util.Map;

public class TodoPresenter implements TodoContract.Presenter {

    public static final String TAG = "TodoPresenter";

    private final TodoContract.View mView;

    public TodoPresenter(@NonNull TodoContract.View view) {
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
        Backendless.Data.of("Todo").find(queryBuilder, new AsyncCallback<List<Map>>() {
            @Override
            public void handleResponse(final List<Map> response) {
                Log.d(TAG, "handleResponse: TodoList created");
                mView.setData(response);
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                Log.d(TAG, fault.getMessage());
            }
        });
    }

    @Override
    public void checkTodo(Map map) {
        map.put("checked", "true");
        Backendless.Persistence.of("Todo").save(map, new AsyncCallback<Map>() {
            @Override
            public void handleResponse(Map response) {
                Log.d(TAG, "handleResponse: Todo completed");
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                Log.d(TAG, fault.getMessage());
            }
        });
    }
}
