package com.example.iheath.ktrathetrang_003;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import com.example.iheath.R;
import com.example.iheath.adapter.M003_KtraTheTrang_PagerAdapter;
import com.example.iheath.ktrathetrang_003.history_003_1.M003_1_HistoryCalo;
import com.example.iheath.ktrathetrang_003.history_003_1.M003_1_History_BMI;
import com.example.iheath.ktrathetrang_003.history_003_1.M003_1_History_CaloCanNap;
import com.google.android.material.tabs.TabLayout;

public class M003_KtraTheTrang extends AppCompatActivity {
    private ViewPager pager;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m003_ktrathetrang);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Kiểm tra thể trạng");
        actionBar.setDisplayHomeAsUpEnabled(true);

//        LayoutInflater inflator = (LayoutInflater) this .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        View v = inflator.inflate(R.layout.search, null);

        addControl();
    }

    private void addControl() {
        pager = (ViewPager) findViewById(R.id.view_pager);
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        FragmentManager manager = getSupportFragmentManager();
        M003_KtraTheTrang_PagerAdapter adapter = new M003_KtraTheTrang_PagerAdapter(manager);
        pager.setAdapter(adapter);
        tabLayout.setupWithViewPager(pager);
        pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setTabsFromPagerAdapter(adapter);//deprecated
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(pager));
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            case  R.id.menu_bmi:
                Intent intent= new Intent();
                intent.setClass(M003_KtraTheTrang.this, M003_1_History_BMI.class);
                startActivity(intent);
                return true;
            case  R.id.menu_calo:
                Intent intent2= new Intent();
                intent2.setClass(M003_KtraTheTrang.this, M003_1_HistoryCalo.class);
                startActivity(intent2);
                return true;
            case  R.id.menu_giamcan:
                Intent intent3= new Intent();
                intent3.setClass(M003_KtraTheTrang.this, M003_1_History_CaloCanNap.class);
                startActivity(intent3);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_ktrathetrang_m003,menu);
        return super.onCreateOptionsMenu(menu);
    }


}
