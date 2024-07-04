package net.selsela.almobarakeya.ui.categories;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.afollestad.materialdialogs.GravityEnum;
import com.afollestad.materialdialogs.MaterialDialog;

import net.selsela.almobarakeya.R;
import net.selsela.almobarakeya.data.model.home.Color;
import net.selsela.almobarakeya.data.model.home.MainCategory;
import net.selsela.almobarakeya.data.model.home.Product;
import net.selsela.almobarakeya.data.model.home.Size;
import net.selsela.almobarakeya.ui.base.BaseFragment;
import net.selsela.almobarakeya.ui.favorites.FavProduct;
import net.selsela.almobarakeya.ui.home.ProductRecyclerViewAdapter;
import net.selsela.almobarakeya.ui.main.UpdateCategories;
import net.selsela.almobarakeya.ui.productdeatials.ProductDetailsActivity;
import net.selsela.almobarakeya.ui.productlist.ColorRecyclerViewAdapter;
import net.selsela.almobarakeya.ui.productlist.ProductListActivity;
import net.selsela.almobarakeya.ui.productlist.SizeRecyclerViewAdapter;
import net.selsela.almobarakeya.util.Const;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.apptik.widget.MultiSlider;
import timber.log.Timber;

/**
 * A placeholder fragment containing a simple view.
 */
public class ProductListFragment extends BaseFragment implements CategoryMvpView, SubCategoryMenuAdapter.UpdateDataClickListener, MainCategoryMenuAdapter.UpdateDataClickListener {

    private static final String ARG_SECTION_NUMBER = "section_number";
    @BindView(R.id.sub_category_list)
    RecyclerView subCategoryRecyclerview;
    @BindView(R.id.product_list)
    RecyclerView productRecyclerview;
    Unbinder unbinder;
    @BindView(R.id.label)
    TextView label;
    @BindView(R.id.line)
    View line;
    @BindView(R.id.empty_view)
    TextView emptyView;
    @BindView(R.id.main_category_list)
    RecyclerView mainCategoryList;


    private RecyclerView colorRecyclerView;
    private RecyclerView sizeRecyclerView;
    private List<Color> colors;
    private MaterialDialog dialog;
    private Size selectedSize;
    private Color selectedColor;
    private int fromPrice, toPrice;
    private SizeRecyclerViewAdapter sizeAdapter;
    private ColorRecyclerViewAdapter colorAdapter;
    public static int GRID = 1;
    public static int LINEAR = 0;
    List<MainCategory> subCategories;
    private SubCategoryMenuAdapter subCategoryAdapter;

    @Inject
    CategoryPresenter categoryPresenter;
    private ProductRecyclerViewAdapter productAdapter;
    private List<Product> productlist;
    private boolean hasFilteredData;
    private List<MainCategory> originalSubCategories;
    private MainCategory category;
    boolean needRequest;
    private List<MainCategory> mainCategories;
    int selectedCategoryPos = 0;
    private MainCategoryMenuAdapter mainCategoryAdapter;
    private boolean found;
    private Product favedProduct;

    public void setSelectedCategoryPos(int selectedCategoryPos) {
        this.selectedCategoryPos = selectedCategoryPos;
    }

    public void setNeedRequest(boolean needRequest) {
        this.needRequest = needRequest;
    }

    public void setCategory(MainCategory subCategories) {
        this.category = subCategories;
    }

    public static ProductListFragment newInstance(int selectedCategoryPos) {
        ProductListFragment fragment = new ProductListFragment();
        fragment.setSelectedCategoryPos(selectedCategoryPos);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivityComponent().inject(this);
        setHasOptionsMenu(true);
        if (!EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().register(this);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_category_product, container, false);
        categoryPresenter.attachView(this);
        unbinder = ButterKnife.bind(this, root);
        Timber.d("onCreateView");
        categoryPresenter.getCategories();
        pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                categoryPresenter.getCategories();
            }
        });
        return root;
    }

    @Subscribe()
    public void UpdateCategories(UpdateCategories updateCategories) {
        Timber.d("UpdateCategories %s", updateCategories.getSelectedPos());
        selectedCategoryPos = updateCategories.getSelectedPos();
        categoryPresenter.getCategories();
    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void updateProduct(FavProduct favProduct) {
        if (productlist.contains(favProduct.getFav())) {
            Timber.d("category index");
            int itemIndex = productlist.indexOf(favProduct.getFav());
            if (itemIndex != -1) {
                productlist.set(itemIndex, favProduct.getFav());
                productAdapter.notifyDataSetChanged();
            }
        }
        for (MainCategory mainCategory :
                mainCategories) {
            for (MainCategory category :
                    mainCategory.getSubCategories()) {
                if (category.getProducts() != null && category.getProducts().size() > 0) {
                    if (category.getProducts().contains(favProduct.getFav())) {
                        int itemIndex = category.getProducts().indexOf(favProduct.getFav());
                        if (itemIndex != -1) {
                            Timber.d("category index");
                            category.getProducts().set(itemIndex, favProduct.getFav());
                            found = true;
                            break;
                        }
                        if (found) break;
                    }
                }
                if (found) break;
            }
        }
    }

    @Override
    public void isSuccess(Boolean status) {
        super.isSuccess(status);
//        if (status) {
//            updateProduct(new FavProduct(favedProduct));
//        }
    }

    private void initMainCategoryRecyclerview() {
        if (mainCategories != null && mainCategories.size() > 0) {
            mainCategoryAdapter = new MainCategoryMenuAdapter(getContext(), mainCategories, this);
            mainCategoryList.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
            mainCategoryList.setAdapter(mainCategoryAdapter);
            onMainCategorySelect(selectedCategoryPos, mainCategories.get(selectedCategoryPos).getSubCategories());
        } else
            showEmpty();
    }

    private void initSubCategoryRecyclerview() {
        if (subCategories != null && subCategories.size() > 0) {
            subCategoryAdapter = new SubCategoryMenuAdapter(getContext(), subCategories, this);
            subCategoryRecyclerview.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
            subCategoryRecyclerview.setAdapter(subCategoryAdapter);
            onItemClick(0, subCategories.get(0).getProducts());
        } else
            showEmpty();
    }

    @Override
    public void onItemClick(int position, List<Product> mProducts) {
        Timber.d("onItemClick %s %s", position, mProducts);
        category = mainCategories.get(position);
        subCategoryAdapter.selected(position);
        showProducts(mProducts);
    }


    private void initProductRecyclerview() {
        Timber.d("initProductRecyclerview");
        productAdapter = new ProductRecyclerViewAdapter(productlist, getContext(), new ProductRecyclerViewAdapter.UpdateDataClickListener() {
            @Override
            public void onproductSelected(Product product, int position) {
                Intent intent = new Intent(getContext(), ProductDetailsActivity.class);
                intent.putExtra(Const.Details, product);
                startActivity(intent);
            }

            @Override
            public void onFavProduct(Product product, int pos) {
                favedProduct = product;
                categoryPresenter.addToFav(product, getContext());
            }

        });
        productRecyclerview.setLayoutManager(new GridLayoutManager(getContext(), 2));
        productRecyclerview.setAdapter(productAdapter);
        productAdapter.setCurrency(getCurrency());
    }


    private void showChangeDialog() {
        categoryPresenter.get_filter_const();

        dialog = new MaterialDialog.Builder(getContext())
                .customView(R.layout.dialog_filter, false)
                .contentGravity(GravityEnum.START)
                .build();
        dialog.show();

        View view2 = dialog.getCustomView();

        sizeRecyclerView = view2.findViewById(R.id.size_list);
        colorRecyclerView = view2.findViewById(R.id.color_list);
        TextView verifyButton = view2.findViewById(R.id.filter_action);
        MultiSlider multiSlider = view2.findViewById(R.id.range_slider5);
        multiSlider.setMin(1);
        multiSlider.setMax(5000);
        final TextView from_price = view2.findViewById(R.id.start_price);
        final TextView to_price = view2.findViewById(R.id.endprice_label_box);
        multiSlider.setOnThumbValueChangeListener(new MultiSlider.OnThumbValueChangeListener() {
            @Override
            public void onValueChanged(MultiSlider multiSlider, MultiSlider.Thumb thumb, int thumbIndex, int value) {
                if (thumbIndex == 0) {
                    fromPrice = value;
                    from_price.setText(fromPrice + "");
                } else
                    toPrice = value;
                to_price.setText(toPrice + "");
            }
        });
        verifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedColor != null && selectedSize != null) {
                    categoryPresenter.filter(selectedColor.getcId(), selectedSize.getSizeId(), category.getId(), fromPrice, toPrice);
                }
            }
        });

    }


    @Override
    public void showSize(List<Size> sizeList) {
        if (sizeList != null && sizeList.size() > 0) {
            sizeAdapter = new SizeRecyclerViewAdapter(sizeList, getContext(), new SizeRecyclerViewAdapter.UpdateDataClickListener() {
                @Override
                public void onSizeSelected(Size size, int position) {
                    selectedSize = size;
                    sizeAdapter.selected(position);


                }
            });
            selectedSize = sizeList.get(0);
            sizeRecyclerView.setAdapter(sizeAdapter);
        }

    }

    @Override
    public void showColor(List<Color> colorList) {
        this.colors = colorList;
        if (colorList != null && colorList.size() > 0) {
            colorRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
            colorAdapter = new ColorRecyclerViewAdapter(colorList, getContext(), new ColorRecyclerViewAdapter.UpdateDataClickListener() {
                @Override
                public void oncolorSelected(Color color, int position) {
                    selectedColor = color;
                    colorAdapter.selected(position);
                }
            });
            selectedColor = colorList.get(0);
            colorRecyclerView.setAdapter(colorAdapter);
        }

    }

    @Override
    public void showProducts(List<Product> products) {
        Timber.d("showProducts %s", products);
        if (products != null && products.size() > 0) {
            Timber.d("products != null && products.size() > 0");
            emptyView.setVisibility(View.GONE);
            productRecyclerview.setVisibility(View.VISIBLE);
            productlist = products;

            if (productAdapter != null) {
                productAdapter.setProducts(products);

            } else
                initProductRecyclerview();

        } else {
            Timber.d("else");
            if (productAdapter != null)
                productAdapter.setProducts(null);

            showEmpty();
        }
    }


    @Override
    public void showEmptyFilterResult() {
        dialog.dismiss();
        showMessageDialog(getString(R.string.empty_filter_result));
    }

    @Override
    public void showCategories(List<MainCategory> categoryList) {
        mainCategories = categoryList;
        initMainCategoryRecyclerview();

    }

    @Override
    public void onMainCategorySelect(int position, List<MainCategory> subCategories) {
        originalSubCategories = subCategories;
        mainCategoryAdapter.selected(position);
        showSubCategories(subCategories);

    }

    public void showSubCategories(List<MainCategory> categoryList) {

        subCategories = categoryList;
        initSubCategoryRecyclerview();

    }


    @Override
    public void showEmpty() {
        super.showEmpty();
        Timber.d("showEmpty");
        emptyView.setVisibility(View.VISIBLE);
        productRecyclerview.setVisibility(View.INVISIBLE);

    }

    @Override
    public void showSearchResult(List<Product> products) {
        Intent intent = new Intent(getContext(), ProductListActivity.class);
        intent.putExtra(Const.Details, (ArrayList) products);
        intent.putExtra(Const.Name, getString(R.string.search_result));
        startActivity(intent);
    }

    @Override
    public void showFilterCategory(MainCategory category) {
        this.category = category;
        dialog.dismiss();
        subCategoryAdapter.setData(category.getSubCategories());
        if (category.getSubCategories() != null && category.getSubCategories().size() > 0)
            onItemClick(0, category.getSubCategories().get(0).getProducts());
        hasFilteredData = true;
        getActivity().invalidateOptionsMenu();

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_categories, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        MenuItem myActionMenuItem = menu.findItem(R.id.action_search);
        MenuItem clearAction = menu.findItem(R.id.action_clear);
        MenuItem filterAction = menu.findItem(R.id.action_filter);
        clearAction.setVisible(hasFilteredData);
        filterAction.setVisible(!hasFilteredData);
        super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                startActivity(new Intent(getContext(), ProductListActivity.class).putExtra("search", "search"));
                return true;
            case R.id.action_filter:
                showChangeDialog();
                return true;

            case R.id.action_clear:
                subCategories = originalSubCategories;
                subCategoryAdapter.setData(originalSubCategories);
                if (originalSubCategories != null && originalSubCategories.size() > 0)
                    onItemClick(0, originalSubCategories.get(0).getProducts());
                hasFilteredData = false;
                getActivity().invalidateOptionsMenu();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}