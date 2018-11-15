package com.drmmx.devmax.todoapp.ui.complete;

import android.support.annotation.NonNull;
import android.util.Log;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.DataQueryBuilder;
import com.drmmx.devmax.todoapp.model.Todo;

import java.util.List;

public class CompletePresenter implements CompleteContract.Presenter {

    private static final String TAG = "CompletePresenter";

    private final CompleteContract.View mView;

    CompletePresenter(@NonNull CompleteContract.View view) {
        mView = view;
    }

    @Override
    public void start() {
        getAllCompleteList();
    }

    @Override
    public void getAllCompleteList() {
        DataQueryBuilder queryBuilder = DataQueryBuilder.create();
        queryBuilder.setWhereClause("checked = true");
        Backendless.Persistence.of(Todo.class).find(queryBuilder, new AsyncCallback<List<Todo>>() {
            @Override
            public void handleResponse(List<Todo> response) {
                mView.setData(response);
                start();
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                Log.d(TAG, fault.getMessage());
            }
        });
    }

    @Override
    public void remove(Todo todo) {
        Backendless.Persistence.of(Todo.class).save(todo, new AsyncCallback<Todo>() {
            @Override
            public void handleResponse(Todo response) {
                Backendless.Persistence.of(Todo.class).remove(response, new AsyncCallback<Long>() {
                    @Override
                    public void handleResponse(Long response) {
                        start();
                    }

                    @Override
                    public void handleFault(BackendlessFault fault) {
                        Log.d(TAG, fault.getMessage());
                    }
                });
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                Log.d(TAG, fault.getMessage());
            }
        });
        mView.setToast("Task Removed");
    }
}
