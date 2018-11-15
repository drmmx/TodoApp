package com.drmmx.devmax.todoapp.ui.addtodo;

import android.os.Bundle;
import android.support.design.button.MaterialButton;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.drmmx.devmax.todoapp.R;
import com.drmmx.devmax.todoapp.model.Todo;

public class AddTodoActivity extends AppCompatActivity implements AddTodoContract.View {

    public static final String EDIT_TODO_ID = "EDIT_TODO_ID";

    private TextInputEditText mNameTodo;
    private TextInputEditText mDescriptionTodo;

    private Todo mTodo;

    AddTodoContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_todo);

        final String taskId = getIntent().getStringExtra(EDIT_TODO_ID);

        if (taskId == null) {
            setTitle(R.string.add_title);
        } else {
            setTitle(R.string.edit_title);
        }

        mNameTodo = findViewById(R.id.nameEditText);
        mDescriptionTodo = findViewById(R.id.descriptionEditText);
        MaterialButton saveButton = findViewById(R.id.saveButton);

        mPresenter = new AddTodoPresenter(taskId, this);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTodo.setName(String.valueOf(mNameTodo.getText()));
                mTodo.setDescription(String.valueOf(mDescriptionTodo.getText()));
                if (taskId == null) {
                    mPresenter.saveTodo(mTodo);
                } else {
                    mPresenter.updateTodo(mTodo);
                }
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void enterValues(Todo todo) {
        if (mTodo == null) {
            mTodo = todo;
        }
        mNameTodo.setText(mTodo.getName());
        mDescriptionTodo.setText(mTodo.getDescription());
    }
}
