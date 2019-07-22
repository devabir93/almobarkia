package net.selsela.almobarakeya.ui.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.GravityEnum;
import com.afollestad.materialdialogs.MaterialDialog;
import net.selsela.almobarakeya.R;
import net.selsela.almobarakeya.SelselaApplication;
import net.selsela.almobarakeya.data.local.PreferencesHelper;
import net.selsela.almobarakeya.data.local.UserSession;
import net.selsela.almobarakeya.data.model.BaseResponse;
import net.selsela.almobarakeya.data.model.config.ConfigData;
import net.selsela.almobarakeya.data.model.send_order.ProductOrderBody;
import net.selsela.almobarakeya.injection.component.ActivityComponent;
import net.selsela.almobarakeya.injection.component.DaggerActivityComponent;
import net.selsela.almobarakeya.injection.module.ActivityModule;
import net.selsela.almobarakeya.util.DialogFactory;
import net.selsela.almobarakeya.util.ErrorResponseAdapter;
import net.selsela.almobarakeya.util.ViewUtil;
import net.selsela.almobarakeya.util.language.LanguageUtils;

import javax.inject.Inject;

import butterknife.BindView;
import timber.log.Timber;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;


public class BaseFragment extends Fragment implements MvpView {

    @Inject
    public UserSession mUserSession;
    @Inject
    public LanguageUtils languageUtils;
    @Inject
    public PreferencesHelper preferencesHelper;
    public MaterialDialog progress;
    private ActivityComponent mActivityComponent;
    @Nullable
    @BindView(R.id.progress)
    ProgressBar progressBar;
    @Nullable
    @BindView(R.id.pullToRefresh)
    public SwipeRefreshLayout pullToRefresh;
    public String activityTitle;
    @Nullable
    @BindView(R.id.my_toolbar)
    public Toolbar toolbar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivityComponent().inject(this);
        languageUtils.initLocal();
        if (pullToRefresh != null)
            pullToRefresh.setColorSchemeResources(R.color.colorprimary, R.color.accent);

    }

    public boolean hasInternetConnection() {
        return BaseActivity.hasInternetConnection();
    }

    public void goToMain() {
//        startActivity(new Intent(getContext(), MainActivity.class)
//                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
//                        Intent.FLAG_ACTIVITY_CLEAR_TASK |
//                        Intent.FLAG_ACTIVITY_NEW_TASK)
//        );
    }


    protected void logout() {
        mUserSession.destroySession();
    }

    public ActivityComponent getActivityComponent() {
        if (mActivityComponent == null) {
            mActivityComponent = DaggerActivityComponent.builder()
                    .activityModule(new ActivityModule(getActivity()))
                    .applicationComponent(SelselaApplication.get(getActivity()).getComponent())
                    .build();
        }
        return mActivityComponent;
    }

    public void hasActiveInternetConnection(boolean b) {
        if (!b) {
            Timber.d("no connection");
            if (getActivity() != null)
                ViewUtil.createSnackbar(getActivity().getWindow().getDecorView().getRootView(), getResources().getString(R.string.no_connection)).show();
        }
    }

    public boolean isArabic() {
        return languageUtils.isArabic();
    }


    public boolean isUserLogged() {
        return mUserSession.hasActiveSession();
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(CalligraphyContextWrapper.wrap(context));
    }

    @Override
    public void showMessageDialog(String s) {
        if (s != null && !s.isEmpty()) {
            MaterialDialog dialog = new MaterialDialog.Builder(getContext())
                    .content(s)
                    .contentGravity(GravityEnum.START)
                    .positiveText(R.string.dialog_action_ok)
                    .onPositive(new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                            dialog.dismiss();
                        }
                    })
                    .build();
            dialog.show();
        }
    }

    @Override
    public void showProgressView(boolean show) {
        if (show) {
            progressBar.setVisibility(View.VISIBLE);
        } else
            progressBar.setVisibility(View.GONE);

    }

    @Override
    public void onRequestEnd() {
        stopRefreshing();

    }

    @Override
    public void showEmpty() {

    }

    @Override
    public void isExceedWeight(Boolean aBoolean) {

    }

    @Override
    public void showCartBadge(Integer integer) {

    }

    @Override
    public void showSavedProductOrder(ProductOrderBody productOrder) {

    }

    @Override
    public void showCartPrice(Double price) {

    }

    @Override
    public void isSuccess(Boolean status) {

    }

    @Override
    public void onRequestStart() {
        startRefreshing();
        //emptyViewHolder.showMessage(getString(R.string.fetching_data));
    }

    public void stopRefreshing() {
        if (pullToRefresh != null) {
            pullToRefresh.postDelayed(new Runnable() {
                @Override
                public void run() {

                    pullToRefresh.setRefreshing(false);
                }
            }, 100);
        }
    }

    public void startRefreshing() {
        if (pullToRefresh != null) {
            pullToRefresh.postDelayed(new Runnable() {
                @Override
                public void run() {
                    pullToRefresh.setRefreshing(true);
                }
            }, 100);
        }
    }

    public String getCurrency() {

        ConfigData configData = preferencesHelper.getCurrentUserSetting();
        String cur = configData.getConfig().getCurrency();
        String cur_en = configData.getConfig().getCurrencyEn();
        return preferencesHelper.getCountry() == null ? (isArabic() ? cur : cur_en) :
                preferencesHelper.getCountry().getCurreny();
    }


    @Override
    public void showMessageDialog(BaseResponse response) {
        MaterialDialog dialog = new MaterialDialog.Builder(getContext())
                .customView(R.layout.error_layout, false)
                .contentGravity(GravityEnum.START)
                .title(response.getResponseMessage())
                .positiveText(R.string.dialog_action_ok)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        dialog.dismiss();
                    }
                })
                .build();
        if (response.getErrors() != null && response.getErrors().size() > 0) {
            View view = dialog.getCustomView();
            RecyclerView recyclerView = view.findViewById(R.id.recyclerview);
            ErrorResponseAdapter errorResponseAdapter = new ErrorResponseAdapter(response.getErrors());
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
            recyclerView.setAdapter(errorResponseAdapter);
        }
        dialog.show();

    }

    @Override
    public void showAlertDialog(String message) {
        DialogFactory.showAlertDialog(getActivity(), message);

    }

    @Override
    public void showSuccessDialog(String message) {
        DialogFactory.showSuccsesDialog(getActivity(), message);

    }
}
