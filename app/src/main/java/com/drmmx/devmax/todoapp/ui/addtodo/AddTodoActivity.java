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
    private MaterialButton mSaveButton;

    private Todo mTodo;

    AddTodoContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_todo);

        String taskId = getIntent().getStringExtra(EDIT_TODO_ID);

        mNameTodo = findViewById(R.id.nameEditText);
        mDescriptionTodo = findViewById(R.id.descriptionEditText);
        mSaveButton = findViewById(R.id.saveButton);

        mPresenter = new AddTodoPresenter(taskId, this);

        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Todo todo = new Todo(String.valueOf(mNameTodo.getText()),
                        String.valueOf(mDescriptionTodo.getText()), false);
                mPresenter.saveTodo(todo);
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
