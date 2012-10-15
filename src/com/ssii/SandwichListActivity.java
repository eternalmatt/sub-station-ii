package com.ssii;

import android.R;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class SandwichListActivity extends FragmentActivity
        implements SandwichListFragment.Callbacks {

    private boolean mTwoPane;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sandwich_list);

        if (findViewById(R.id.sandwich_detail_container) != null) {
            mTwoPane = true;
            ((SandwichListFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.sandwich_list))
                    .setActivateOnItemClick(true);
        }
    }

    @Override
    public void onItemSelected(String id) {
        if (mTwoPane) {
            Bundle arguments = new Bundle();
            arguments.putString(SandwichDetailFragment.ARG_ITEM_ID, id);
            SandwichDetailFragment fragment = new SandwichDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.sandwich_detail_container, fragment)
                    .commit();

        } else {
            Intent detailIntent = new Intent(this, SandwichDetailActivity.class);
            detailIntent.putExtra(SandwichDetailFragment.ARG_ITEM_ID, id);
            startActivity(detailIntent);
        }
    }
}
