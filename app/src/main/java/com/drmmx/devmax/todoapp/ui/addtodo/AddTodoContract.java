package com.drmmx.devmax.todoapp.ui.addtodo;

import com.drmmx.devmax.todoapp.base.AppBasePresenter;
import com.drmmx.devmax.todoapp.base.AppBaseView;
import com.drmmx.devmax.todoapp.model.Todo;

public interface AddTodoContract {

    interface View extends AppBaseView<Presenter> {

        void enterValues(Todo todo);
    }

    interface Presenter extends AppBasePresenter {

        void saveTodo(Todo todo);

        void updateTodo(Todo todo);

    }
}
