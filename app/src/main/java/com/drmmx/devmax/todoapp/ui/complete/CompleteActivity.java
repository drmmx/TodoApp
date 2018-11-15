package com.drmmx.devmax.todoapp.ui.complete;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.drmmx.devmax.todoapp.R;
import com.drmmx.devmax.todoapp.model.Todo;

import java.util.List;

public class CompleteActivity extends AppCompatActivity implements CompleteContract.View {

    private RecyclerView mRecyclerView;

    private CompletePresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete);

        mRecyclerView = findViewById(R.id.recyclerView);

        mPresenter = new CompletePresenter(this);
        mPresenter.start();
    }

    @Override
    public void setData(List<Todo> completeList) {
        CompleteAdapter adapter = new CompleteAdapter(this, completeList, mPresenter);
        adapter.notifyDataSetChanged();
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(adapter);
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
}
