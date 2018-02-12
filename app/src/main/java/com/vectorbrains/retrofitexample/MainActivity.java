package com.vectorbrains.retrofitexample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.vectorbrains.retrofitexample.adapter.OwnerRecyclerAdapter;
import com.vectorbrains.retrofitexample.appconstant.AppConstant;
import com.vectorbrains.retrofitexample.model.Owner;
import com.vectorbrains.retrofitexample.network.OwnerRequestListener;
import com.vectorbrains.retrofitexample.network.RetrofitAdapterForGithub;

import java.util.List;

public class MainActivity extends AppCompatActivity implements OwnerRequestListener, OwnerRecyclerAdapter.OnItemClickListener {
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private LinearLayout llErrorLayout;
    private TextView tvError;
    private List<Owner> ownerList;
    private RetrofitAdapterForGithub githubAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        llErrorLayout = (LinearLayout) findViewById(R.id.ll_error_layout);
        tvError = (TextView) findViewById(R.id.textview_error);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false));
        loadUserList();
    }

    public void loadUserList() {
        llErrorLayout.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
        githubAdapter = new RetrofitAdapterForGithub();
        githubAdapter.runRequestToFetchUsers(this);
    }

    @Override
    public void onSuccess(List<Owner> ownerList) {
        llErrorLayout.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
        this.ownerList = ownerList;
        OwnerRecyclerAdapter adapter = new OwnerRecyclerAdapter(MainActivity.this, ownerList);
        adapter.setOnItemClickListener(this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onError(String error) {
        recyclerView.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);
        llErrorLayout.setVisibility(View.VISIBLE);
        tvError.setText(error);
    }

    public void tryAgainClick(View view) {
        loadUserList();
    }

    @Override
    public void onItemClicked(int position) {
        if (position >= 0 && position < ownerList.size()) {
            Intent in = new Intent(MainActivity.this, OwnerProfileActivity.class);
            in.putExtra(AppConstant.OWNER_KEY, ownerList.get(position));
            startActivity(in);
        }
    }
}
