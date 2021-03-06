package com.drmmx.devmax.todoapp.ui.todo;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.backendless.Backendless;
import com.drmmx.devmax.todoapp.R;
import com.drmmx.devmax.todoapp.model.Todo;
import com.drmmx.devmax.todoapp.ui.addtodo.AddTodoActivity;
import com.drmmx.devmax.todoapp.ui.complete.CompleteActivity;
import com.drmmx.devmax.todoapp.util.Settings;

import java.util.List;

public class TodoActivity extends AppCompatActivity implements TodoContract.View {

    private FloatingActionButton mFab;
    private RecyclerView mRecyclerView;

    private TodoContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);

        mFab = findViewById(R.id.fab);
        mRecyclerView = findViewById(R.id.recyclerView);

        Backendless.setUrl(Settings.SERVER_URL);
        Backendless.initApp(getApplicationContext(), Settings.APPLICATION_ID, Settings.ANDROID_API_KEY);

        mPresenter = new TodoPresenter(this);
        mPresenter.start();

        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), AddTodoActivity.class));
            }
        });
    }

    @Override
    public void setData(List<Todo> todoList) {
        TodoAdapter adapter = new TodoAdapter(this, todoList, mPresenter);
        adapter.notifyDataSetChanged();
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void startAddTodo(String id) {
        Intent intent = new Intent(this, AddTodoActivity.class);
        intent.putExtra(AddTodoActivity.EDIT_TODO_ID, String.valueOf(id));
        startActivity(intent);
    }

    @Override
    public void setToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter = null;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuCompleted:
                startActivity(new Intent(this, CompleteActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
