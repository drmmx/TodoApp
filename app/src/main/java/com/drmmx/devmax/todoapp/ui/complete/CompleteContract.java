package com.drmmx.devmax.todoapp.ui.complete;

import com.drmmx.devmax.todoapp.base.AppBasePresenter;
import com.drmmx.devmax.todoapp.base.AppBaseView;
import com.drmmx.devmax.todoapp.model.Todo;

import java.util.List;

public interface CompleteContract {

    interface View extends AppBaseView<Presenter> {

        void setData(List<Todo> todoList);

        void setToast(String message);

    }

    interface Presenter extends AppBasePresenter {

        void getAllCompleteList();

        void remove(Todo todo);
    }
}
