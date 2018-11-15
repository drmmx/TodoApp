package com.drmmx.devmax.todoapp.ui.todo;

import com.drmmx.devmax.todoapp.base.AppBasePresenter;
import com.drmmx.devmax.todoapp.base.AppBaseView;
import com.drmmx.devmax.todoapp.model.Todo;

import java.util.List;

public interface TodoContract {

    interface View extends AppBaseView<Presenter> {

        void setData(List<Todo> todoList);

        void startAddTodo(String id);

        void setToast(String message);

    }

    interface Presenter extends AppBasePresenter {

        void getAllTodoList();

        void checkTodo(Todo todo, boolean isChecked);
    }
}
