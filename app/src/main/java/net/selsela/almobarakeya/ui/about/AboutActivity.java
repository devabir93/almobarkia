package net.selsela.almobarakeya.ui.about;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import net.selsela.almobarakeya.R;
import net.selsela.almobarakeya.data.model.about.AboutData;
import net.selsela.almobarakeya.ui.base.BaseActivity;
import net.selsela.almobarakeya.util.Const;

import org.sufficientlysecure.htmltextview.HtmlTextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AboutActivity extends BaseActivity implements AboutMvpView {
    @Inject
    AboutPresenter aboutPresenter;
    @BindView(R.id.logo)
    ImageView logo;
    @BindView(R.id.aboutus_label)
    TextView aboutusLabel;
    @BindView(R.id.about_content_tv)
    HtmlTextView aboutContentTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        getActivityComponent().inject(this);
        ButterKnife.bind(this);

        aboutPresenter.attachView(this);
        String type = getIntent().getStringExtra(Const.Type);
        aboutPresenter.getAbout(type);
        //  initToolbar();

    }

    @Override
    public void showData(AboutData data) {
        aboutusLabel.setText(data.getPage().getTitle());
        aboutContentTv.setHtml(data.getPage().getText());
        activityTitle = data.getPage().getTitle();
        initToolbar();
    }

    @Override
    public void onRequestStart() {

    }

    @Override
    public void onRequestEnd() {

    }
}
