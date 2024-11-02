package com.mrahmed.quiztime.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.bumptech.glide.Glide;
import com.mrahmed.quiztime.Adapter.LeaderAdapter;
import com.mrahmed.quiztime.Domain.UserModel;
import com.mrahmed.quiztime.R;
import com.mrahmed.quiztime.databinding.ActivityLeaderBinding;
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

        binding.bottomMenu.setItemSelected(R.id.board);
        binding.bottomMenu.setOnItemSelectedListener(item -> {
            if (item == R.id.home) {
                startActivity(new Intent(LeaderActivity.this, MainActivity.class));
            }
            return true;
        });

        List<UserModel> list = loadData();
        list.remove(0);
        list.remove(0); // removing second item since index shifts after first removal
        list.remove(0); // removing third item after first two removals

        leaderAdapter.submitList(list);
        binding.leaderView.setLayoutManager(new LinearLayoutManager(this));
        binding.leaderView.setAdapter(leaderAdapter);
    }

    // Example data loading function (replace this with your own API call or data source)
    private List<UserModel> loadData() {
        List<UserModel> users = new ArrayList<>();
        users.add(new UserModel(1, "Raju", "person1", 4521));
        users.add(new UserModel(2, "Ahmed", "person2", 4521));
        users.add(new UserModel(3, "Rezvi", "person3", 4521));
        users.add(new UserModel(4, "Nusrat", "person4", 4521));
        users.add(new UserModel(5, "Towhid", "person5", 4521));
        users.add(new UserModel(6, "Shabab", "person6", 4521));
        users.add(new UserModel(7, "Shamima", "person7", 4521));
        users.add(new UserModel(8, "Neyamul", "person8", 4521));
        users.add(new UserModel(9, "Rafiqul", "person9", 4521));
        users.add(new UserModel(10, "Sanaullah", "person1", 4521));
        users.add(new UserModel(11, "Nirjash", "person2", 4521));
        users.add(new UserModel(12, "Najmul", "person3", 4521));
        users.add(new UserModel(13, "Kutub", "person4", 4521));
        return users;
    }
}
