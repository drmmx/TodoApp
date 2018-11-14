package com.drmmx.devmax.todoapp.ui.todo;

import com.drmmx.devmax.todoapp.base.AppBasePresenter;
import com.drmmx.devmax.todoapp.base.AppBaseView;

import java.util.List;
import java.util.Map;

public interface TodoContract {

    interface View extends AppBaseView<Presenter> {

        void setData(List<Map> todoList);

        void startAddTodo(String id);

    }

    interface Presenter extends AppBasePresenter {

        void getAllTodoList();

        void checkTodo(Map response);
    }
}
