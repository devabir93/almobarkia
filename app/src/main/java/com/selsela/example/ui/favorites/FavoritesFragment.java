package com.selsela.example.ui.favorites;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.selsela.example.R;
import com.selsela.example.data.model.home.Product;
import com.selsela.example.ui.base.BaseFragment;
import com.selsela.example.ui.productlist.ProductListActivity;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class FavoritesFragment extends BaseFragment implements FavMvpView {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    @Inject
    FavouritesPresenter favouritesPresenter;
    @BindView(R.id.favoritelist)
    RecyclerView favoritelist;
    Unbinder unbinder;
    @BindView(R.id.empty_fav)
    LinearLayout emptyFav;

    FavoriteRecyclerViewAdapter favAdapter;
    private int selectedPos;

    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;
    private boolean isEmpty;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public FavoritesFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static FavoritesFragment newInstance(int columnCount) {
        FavoritesFragment fragment = new FavoritesFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivityComponent().inject(this);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.favorite_item_list, container, false);
        unbinder = ButterKnife.bind(this, view);
        favouritesPresenter.attachView(this);
        getFav();
        pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getFav();
            }
        });
        return view;
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void showFav(List<Product> favorites) {

        favAdapter = new FavoriteRecyclerViewAdapter(favorites, getContext(), new FavoriteRecyclerViewAdapter.UpdateDataClickListener() {
            @Override
            public void onproductSelected(Product product, int position) {

            }
        });
        favAdapter.setLayout(ProductListActivity.GRID);
        favoritelist.setLayoutManager(new GridLayoutManager(getContext(), 2));
        favoritelist.setAdapter(favAdapter);
        //favoritelist.setVisibility(View.VISIBLE);
        emptyFav.setVisibility(View.GONE);

    }

    @Override
    public void isSuccessAll(Boolean isSucess) {
        if (isSucess) {
            showEmpty();
        } else
            getFav();
    }

    @Override
    public void isSuccess(Boolean status) {
        // if (status) {
        //   favAdapter.removeAt(selectedPos);
        //  checkAdapterIsEmpty();
        //   }

    }

    private void checkAdapterIsEmpty() {
        if (favAdapter.getItemCount() == 0) {
            emptyFav.setVisibility(View.VISIBLE);
            favoritelist.setVisibility(View.GONE);
        } else {
            emptyFav.setVisibility(View.GONE);
        }
    }

    private void getFav() {
        if (hasInternetConnection()) {
            favouritesPresenter.get_user_favorites();
        } else {
            stopRefreshing();
            showEmpty();
            hasActiveInternetConnection(false);
        }
    }

    public interface OnListFragmentInteractionListener {

    }

    @Override
    public void showEmpty() {
        stopRefreshing();
        isEmpty = true;
        favoritelist.setVisibility(View.GONE);
        emptyFav.setVisibility(View.VISIBLE);
    }
}
