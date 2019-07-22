package net.selsela.almobarakeya.ui.categories;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import net.selsela.almobarakeya.data.model.home.MainCategory;

import java.util.List;


/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    private final Context mContext;
    private final List<MainCategory> categoriesList;

    public SectionsPagerAdapter(Context context, FragmentManager fm, List<MainCategory> categoriesList) {
        super(fm);
        mContext = context;
        this.categoriesList = categoriesList;
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a ProductListFragment (defined as a static inner class below).
//        if (mainCategory != null)
//            return ProductListFragment.newInstance(mainCategory,true);
        //return ProductListFragment.newInstance(categoriesList.get(position));
        return null;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return categoriesList != null ? categoriesList.get(position).getName() : "";
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return categoriesList != null ? categoriesList.size() : 0;
    }
}