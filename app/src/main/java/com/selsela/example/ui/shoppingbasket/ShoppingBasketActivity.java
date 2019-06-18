package com.selsela.example.ui.shoppingbasket;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import com.selsela.example.R;
import com.selsela.example.ui.address1.Address1Activity;
import com.selsela.example.ui.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShoppingBasketActivity extends BaseActivity {

    @BindView(R.id.my_toolbar)
    Toolbar myToolbar;
    @BindView(R.id.shoppingbasket_list)
    RecyclerView shoppingbasketList;
    @BindView(R.id.total_amount)
    TextView totalAmount;
    @BindView(R.id.contiue_buying_action)
    TextView contiueBuyingAction;
    @BindView(R.id.total_amount_label)
    TextView totalAmountLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_basket);
        getActivityComponent().inject(this);

        ButterKnife.bind(this);
       shoppingbasketList .setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
       shoppingbasketList.setAdapter(new CartRecyclerViewAdapter());
    }

    @OnClick(R.id.contiue_buying_action)
    public void onViewClicked() {
        Intent intent = new Intent(this, Address1Activity.class);
        startActivity(intent);
    }
}
