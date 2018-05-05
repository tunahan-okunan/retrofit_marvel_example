package com.okunan.tunahan.retrofidmarvelornek;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class HeroActivity extends SingleFragmentActivity {


    @Override
    protected Fragment createFragment() {
        return HeroFragment.newInstance();
    }
}
