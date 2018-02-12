package com.vectorbrains.retrofitexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.vectorbrains.retrofitexample.appconstant.AppConstant;
import com.vectorbrains.retrofitexample.model.Owner;

public class OwnerProfileActivity extends AppCompatActivity {

    private ImageView ivOwnerAvatar;
    private TextView tvLogin, tvType, tvId, tvRepo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_profile);
        tvLogin = (TextView) findViewById(R.id.login_value);
        tvId = (TextView) findViewById(R.id.id_value);
        tvType = (TextView) findViewById(R.id.type_value);
        tvRepo = (TextView) findViewById(R.id.repo_value);
        ivOwnerAvatar = (ImageView) findViewById(R.id.iv_owner_avatar);
        Owner owner = (Owner) getIntent().getParcelableExtra(AppConstant.OWNER_KEY);
        if (owner != null) {
            Glide.with(this).load(owner.getAvatarUrl()).placeholder(R.drawable.ic_placeholder).crossFade().into(ivOwnerAvatar);
            tvLogin.setText(owner.getLogin());
            tvId.setText(String.valueOf(owner.getId()));
            tvType.setText(owner.getType());
            tvRepo.setText(owner.getReposUrl());
        }
    }
}
