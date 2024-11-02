package com.mrahmed.itsquiztime.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.bumptech.glide.Glide;
import com.mrahmed.itsquiztime.Adapter.LeaderAdapter;
import com.mrahmed.itsquiztime.Domain.UserModel;
import com.mrahmed.itsquiztime.R;
import com.mrahmed.itsquiztime.databinding.ActivityLeaderBinding;

import java.util.ArrayList;
import java.util.List;

public class LeaderActivity extends AppCompatActivity {
    private ActivityLeaderBinding binding;
    private LeaderAdapter leaderAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLeaderBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        leaderAdapter = new LeaderAdapter();

        Window window = this.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.gray));

        binding.scoreTop1Txt.setText(String.valueOf(loadData().get(0).getScore()));
        binding.scoreTop2Txt.setText(String.valueOf(loadData().get(1).getScore()));
        binding.scoreTop3Txt.setText(String.valueOf(loadData().get(2).getScore()));

        binding.titleTop1Txt.setText(loadData().get(0).getName());
        binding.titleTop2Txt.setText(loadData().get(1).getName());
        binding.titleTop3Txt.setText(loadData().get(2).getName());

        int drawableResourceId1 = getResources().getIdentifier(
                loadData().get(0).getPic(), "drawable", getPackageName());
        Glide.with(this)
                .load(drawableResourceId1)
                .into(binding.pic1);

        int drawableResourceId2 = getResources().getIdentifier(
                loadData().get(1).getPic(), "drawable", getPackageName());
        Glide.with(this)
                .load(drawableResourceId2)
                .into(binding.pic2);

        int drawableResourceId3 = getResources().getIdentifier(
                loadData().get(2).getPic(), "drawable", getPackageName());
        Glide.with(this)
                .load(drawableResourceId3)
                .into(binding.pic3);

        binding.bottomMenu.setItemSelected(R.id.board, true);
        binding.bottomMenu.setOnItemSelectedListener(item -> {
            if (item == R.id.home) {
                startActivity(new Intent(LeaderActivity.this, MainActivity.class));
            }
        });

        List<UserModel> list = loadData();
        list.remove(0);
        list.remove(0);
        list.remove(0);

        leaderAdapter.submitList(list);
        binding.leaderView.setLayoutManager(new LinearLayoutManager(this));
        binding.leaderView.setAdapter(leaderAdapter);
    }

    private List<UserModel> loadData() {
        List<UserModel> users = new ArrayList<>();
        users.add(new UserModel(1, "Raju", "person3", 4522));
        users.add(new UserModel(2, "Ahmed", "person6", 4504));
        users.add(new UserModel(3, "Rezvi", "person2", 4426));
        users.add(new UserModel(4, "Nusrat", "person8", 4408));
        users.add(new UserModel(5, "Towhid", "person4", 4382));
        users.add(new UserModel(6, "Shabab", "person6", 4367));
        users.add(new UserModel(7, "Shamima", "person7", 4349));
        users.add(new UserModel(8, "Neyamul", "person2", 4325));
        users.add(new UserModel(9, "Rafiqul", "person4", 4303));
        users.add(new UserModel(10, "Sanaullah", "person3", 4287));
        users.add(new UserModel(11, "Nirjash", "person2", 4269));
        users.add(new UserModel(12, "Najmul", "person9", 4245));
        users.add(new UserModel(13, "Kutub", "person4", 4227));
        return users;
    }
}
