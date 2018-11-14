package com.drmmx.devmax.todoapp.ui.addtodo;

import android.support.annotation.NonNull;
import android.util.Log;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.drmmx.devmax.todoapp.model.Todo;

import java.util.Map;

public class AddTodoPresenter implements AddTodoContract.Presenter {

    public static final String TAG = "AddTodoPresenter";

    private final AddTodoContract.View mView;

    private String mTodoId;

    public AddTodoPresenter(String todoId, @NonNull AddTodoContract.View view) {
        mTodoId = todoId;
        mView = view;
    }

    @Override
    public void saveTodo(final Map map) {
        if (mTodoId != null) {
            Backendless.Data.of("Todo").findById(mTodoId, new AsyncCallback<Map>() {
                @Override
                public void handleResponse(Map response) {
                    response.put("name", String.valueOf(map.get("name")));
                    response.put("description", String.valueOf(map.get("description")));
                    Backendless.Persistence.save(response, new AsyncCallback<Map>() {
                        @Override
                        public void handleResponse(Map response) {
                            Log.d(TAG, "handleResponse: Todo updated");
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
        } else {
            Backendless.Data.of("Todo").save(map, new AsyncCallback<Map>() {
                @Override
                public void handleResponse(Map response) {
                    Log.d(TAG, "handleResponse: New Todo saved");
                }
                @Override
                public void handleFault(BackendlessFault fault) {
                    Log.d(TAG, fault.getMessage());
                }
            });
        }
    }

    @Override
    public void start() {
        if (mTodoId != null) {
            Backendless.Data.of("Todo").findById(mTodoId, new AsyncCallback<Map>() {
                @Override
                public void handleResponse(Map response) {
                    Todo todo = new Todo();
                    todo.setName(String.valueOf(response.get("name")));
                    todo.setDescription(String.valueOf(response.get("description")));
                    mView.enterValues(todo);
                }

                @Override
                public void handleFault(BackendlessFault fault) {
                    Log.d(TAG, fault.getMessage());
                }
            });
        } else {
            Todo todo = new Todo("", "", false);
            mView.enterValues(todo);
        }
    }
}
