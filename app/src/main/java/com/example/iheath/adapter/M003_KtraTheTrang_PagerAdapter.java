package com.example.iheath.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.iheath.ktrathetrang_003.Frg003_BMI;
import com.example.iheath.ktrathetrang_003.Frg003_GiamCan;
import com.example.iheath.ktrathetrang_003.Frg003_Kcal;

public class M003_KtraTheTrang_PagerAdapter extends FragmentStatePagerAdapter {

    public M003_KtraTheTrang_PagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }
    @Override
    public Fragment getItem(int position) {
        Fragment frag=null;
        switch (position){
            case 0:
                frag = new Frg003_BMI();
                break;
            case 1:
                frag = new Frg003_Kcal();
                break;
            case 2:
                frag = new Frg003_GiamCan();
                break;
        }
        return frag;
    }

    @Override
    public int getCount() {
        return 3;
    }
    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position){
            case 0:
                title = "Chỉ số BMI";
                break;
            case 1:
                title = "Chỉ số Kcal";
                break;
            case 2:
                title = "Giảm cân";
                break;
        }
        return title;
    }
}
