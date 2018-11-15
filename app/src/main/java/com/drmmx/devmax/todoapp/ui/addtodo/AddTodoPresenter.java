package com.drmmx.devmax.todoapp.ui.addtodo;

import android.support.annotation.NonNull;
import android.util.Log;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.drmmx.devmax.todoapp.model.Todo;

public class AddTodoPresenter implements AddTodoContract.Presenter {

    public static final String TAG = "AddTodoPresenter";

    private final AddTodoContract.View mView;

    private String mTodoId;

    public AddTodoPresenter(String todoId, @NonNull AddTodoContract.View view) {
        mTodoId = todoId;
        mView = view;
    }

    @Override
    public void saveTodo(final Todo todo) {
        Backendless.Persistence.of(Todo.class).save(todo, new AsyncCallback<Todo>() {
            @Override
            public void handleResponse(Todo response) {
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                Log.d(TAG, fault.getMessage());
            }
        });
    }

    @Override
    public void updateTodo(final Todo todo) {
        Backendless.Persistence.of(Todo.class).save(todo, new AsyncCallback<Todo>() {
            @Override
            public void handleResponse(Todo response) {
                response.setName(todo.getName());
                response.setDescription(todo.getDescription());
                response.setObjectId(todo.getObjectId());
                Backendless.Persistence.save(response, new AsyncCallback<Todo>() {
                    @Override
                    public void handleResponse(Todo response) {
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
    }

    @Override
    public void start() {
        if (mTodoId != null) {
            Backendless.Persistence.of(Todo.class).findById(mTodoId, new AsyncCallback<Todo>() {
                @Override
                public void handleResponse(Todo response) {
                    response.setName(String.valueOf(response.getName()));
                    response.setDescription(String.valueOf(response.getDescription()));
                    mView.enterValues(response);
                }

                @Override
                public void handleFault(BackendlessFault fault) {
                    Log.d(TAG, fault.getMessage());
                }
            });
        } else {
            Todo todo = new Todo();
            mView.enterValues(todo);
        }
    }
}
