package com.selsela.example.data;

import android.content.Context;

import com.orm.SugarDb;
import com.selsela.example.data.local.PreferencesHelper;
import com.selsela.example.data.model.BaseResponse;
import com.selsela.example.data.model.boxes.Box;
import com.selsela.example.data.model.boxes.BoxsData;
import com.selsela.example.data.model.coupon.CheckCoponData;
import com.selsela.example.data.model.home.Color;
import com.selsela.example.data.model.home.Product;
import com.selsela.example.data.model.home.Size;
import com.selsela.example.data.model.send_order.AddressBody;
import com.selsela.example.data.model.send_order.ProductOrderBody;
import com.selsela.example.data.model.user.UserData;
import com.selsela.example.data.remote.SelselaService;
import com.selsela.example.injection.ApplicationContext;
import com.selsela.example.util.Const;
import com.selsela.example.util.language.LanguageUtils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import timber.log.Timber;


@Singleton
public class CartManager {

    private final SelselaService mBazarlakService;
    private final PreferencesHelper mPreferencesHelper;
    private final LanguageUtils languageUtils;
    protected SugarDb sugarDb;
    Context mContext;
    String token;

    @Inject
    public CartManager(SelselaService selselaService, PreferencesHelper preferencesHelper,
                       LanguageUtils languageUtils, @ApplicationContext Context context) {
        mBazarlakService = selselaService;
        mPreferencesHelper = preferencesHelper;
        this.languageUtils = languageUtils;
        mContext = context;
    }

    public PreferencesHelper getPreferencesHelper() {
        return mPreferencesHelper;
    }

    public LanguageUtils getLanguageUtils() {
        return languageUtils;
    }

    private void closeDatabase() {
        sugarDb.close();
    }

    private void openDatabase() {
        sugarDb = new SugarDb(mContext);
        if (!sugarDb.getDB().isOpen())
            sugarDb.getReadableDatabase();
    }

    @android.support.annotation.NonNull
    protected String getCurrentToken() {
        UserData currentUser = getPreferencesHelper().getCurrentUser();
        return currentUser.getToken();

    }


    public UserData getUserSession() {
        if (getPreferencesHelper() != null && getPreferencesHelper().getCurrentUser() != null) {
        }
        // Timber.d("getPreferencesHelper().getCurrentUserSetting() %s", getPreferencesHelper().getCurrentUser());
        return getPreferencesHelper().getCurrentUser();
    }


    int getCountryID() {
        return getPreferencesHelper().getCountry().getId();
    }

    int getUserId() {
        return getUserSession() != null ? getUserSession().getId() : 0;
    }
    /////////Auth

    public Observable<BaseResponse<CheckCoponData>> checkCopon(String code) {
        return mBazarlakService.check_copone(getUserId(), getCurrentToken(), code)
                .concatMap(new Function<BaseResponse<CheckCoponData>, ObservableSource<? extends BaseResponse<CheckCoponData>>>() {
                    @Override
                    public ObservableSource<? extends BaseResponse<CheckCoponData>> apply(final BaseResponse<CheckCoponData> response) {
                        return Observable.create(new ObservableOnSubscribe<BaseResponse<CheckCoponData>>() {
                            @Override
                            public void subscribe(ObservableEmitter<BaseResponse<CheckCoponData>> e) {
                                try {
                                    e.onNext(response);
                                } catch (Exception e1) {
                                    e.onError(e1);
                                }
                                e.onComplete();

                            }
                        });
                    }
                });
    }

    public Observable<List<ProductOrderBody>> deleteOrder(
            final ProductOrderBody productOrder) {
        return Observable.create(new ObservableOnSubscribe<List<ProductOrderBody>>() {
            @Override
            public void subscribe(ObservableEmitter<List<ProductOrderBody>> e) {
                openDatabase();
                //  Timber.d("productOrder %s", productOrder);
                int row = 0;
//                Product author = Product.findById(Product.class, productOrder.getProduct().getId());
//                author.delete();
                if (productOrder.getColor() != null && productOrder.getSize() != null) {

                    row = ProductOrderBody.deleteAll(ProductOrderBody.class,
                            "order_id = ? and size_id = ? and color_id = ? and user_id = ?",
                            String.valueOf(productOrder.getOrderId()),
                            String.valueOf(productOrder.getSizeId()), String.valueOf(productOrder.getColorId()),
                            String.valueOf(getUserId()));
                } else if (productOrder.getColor() == null && productOrder.getSize() != null) {

                    row = ProductOrderBody.deleteAll(ProductOrderBody.class,
                            "order_id = ? and size_id = ? and user_id = ?",
                            String.valueOf(productOrder.getOrderId()),
                            String.valueOf(productOrder.getSizeId()),
                            String.valueOf(getUserId()));
                } else if (productOrder.getSize() == null && productOrder.getColor() != null) {

                    row = ProductOrderBody.deleteAll(ProductOrderBody.class,
                            "order_id = ? and color_id = ? and user_id = ?",
                            String.valueOf(productOrder.getOrderId()),
                            String.valueOf(productOrder.getColorId()),
                            String.valueOf(getUserId()));
                } else if (productOrder.getSize() == null && productOrder.getColor() == null) {
                    row = ProductOrderBody.deleteAll(ProductOrderBody.class,
                            "order_id = ?  and user_id = ?",
                            String.valueOf(productOrder.getOrderId()),
                            String.valueOf(getUserId()));
                }

                if (row > 0)
                    Timber.d("delete %s", row);

                List<ProductOrderBody> productOrderList = ProductOrderBody.find(ProductOrderBody.class,
                        "user_id=?", String.valueOf(getUserId()));
                closeDatabase();
                // Timber.d("productOrderList %s", productOrderList);
                try {
                    e.onNext(productOrderList);
                    e.onComplete();
                } catch (Exception ex) {
                    e.onError(ex);
                }
            }
        });
    }


    public boolean deleteOrders(final List<ProductOrderBody> productOrders) {
        boolean idDeleted = false;
        try {

            openDatabase();
            // Timber.d("productOrder %s", productOrders);
            int row = 0;
            for (ProductOrderBody productOrder :
                    productOrders) {

                if (productOrder.getColor() != null && productOrder.getSize() != null) {

                    row = ProductOrderBody.deleteAll(ProductOrderBody.class,
                            "order_id = ? and size_id = ? and color_id = ? and user_id = ?",
                            String.valueOf(productOrder.getOrderId()),
                            String.valueOf(productOrder.getSizeId()), String.valueOf(productOrder.getColorId()),
                            String.valueOf(getUserId()));
                } else if (productOrder.getColor() == null && productOrder.getSize() != null) {

                    row = ProductOrderBody.deleteAll(ProductOrderBody.class,
                            "order_id = ? and size_id = ? and user_id = ?",
                            String.valueOf(productOrder.getOrderId()),
                            String.valueOf(productOrder.getSizeId()),
                            String.valueOf(getUserId()));
                } else if (productOrder.getSize() == null && productOrder.getColor() != null) {

                    row = ProductOrderBody.deleteAll(ProductOrderBody.class,
                            "order_id = ? and color_id = ? and user_id = ?",
                            String.valueOf(productOrder.getOrderId()),
                            String.valueOf(productOrder.getColorId()),
                            String.valueOf(getUserId()));
                } else if (productOrder.getSize() == null && productOrder.getColor() == null) {
                    row = ProductOrderBody.deleteAll(ProductOrderBody.class,
                            "order_id = ?  and user_id = ?",
                            String.valueOf(productOrder.getOrderId()),
                            String.valueOf(getUserId()));
                }
                //Product.delete(productOrder.getProduct().getProductId());

                if (row > 0) {
                    idDeleted = true;
                    Timber.d("delete %s", row);
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeDatabase();
        }
        return idDeleted;
    }

    private void checkAndUpdateCart() {
        List<ProductOrderBody> productOrderList;
        productOrderList = ProductOrderBody.find(ProductOrderBody.class, "user_id= ? ",
                String.valueOf(0));
        if (productOrderList.size() > 0) {
            Timber.d("saved productOrder");
            for (ProductOrderBody serviceOrderBody :
                    productOrderList) {
                serviceOrderBody.setUserId(getUserId());
                serviceOrderBody.save();
            }
        }
    }

    public Observable<Boolean> clearCart(final List<ProductOrderBody> productOrders) {

        return Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(ObservableEmitter<Boolean> e) {
                try {
                    if (productOrders == null) {
                        openDatabase();
                        List<ProductOrderBody> productOrderList = ProductOrderBody.find(ProductOrderBody.class,
                                "user_id=?", String.valueOf(getUserId()));
                        //  Timber.d("productOrderBodyList %s", productOrderList);
                        if (productOrderList != null && productOrderList.size() > 0) {
                            deleteOrders(productOrderList);
                        }
                        Product.deleteAll(Product.class);

                        closeDatabase();
                    } else
                        deleteOrders(productOrders);
                    e.onNext(true);
                } catch (Exception e1) {
                    e.onError(e1);
                }
                e.onComplete();
            }
        });

    }

    public Observable<Integer> getCartCount() {
        return Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) {
                openDatabase();
                List<ProductOrderBody> productOrderList = ProductOrderBody.find(ProductOrderBody.class,
                        "user_id=?", String.valueOf(getUserId()));
//
                // Timber.d("productOrderList %s", productOrderList);
                try {
                    e.onNext(productOrderList.size());
                    e.onComplete();
                } catch (Exception ex) {
                    e.onError(ex);
                } finally {
                    closeDatabase();
                }
            }
        });
    }

    public Observable<Integer> addTobag(final ProductOrderBody productOrder, final boolean isFromDetails) {
        // Timber.d("productOrder %s", productOrder);
        return Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> subscriber) {
                try {
                    int isSavedInDatabase = saveOrderWithoutRX(productOrder, isFromDetails);
                    Timber.d("isSavedInDatabase %s", isSavedInDatabase);

                    subscriber.onNext(isSavedInDatabase);
                    subscriber.onComplete();
                } catch (Exception e) {
                    subscriber.onNext(-1);
                    subscriber.onError(e);
                }
            }
        });

    }

    public int saveOrderWithoutRX(ProductOrderBody productOrder, boolean isFromDetails) {
        int isSavedInDatabase;
        Timber.d("saveOrderWithoutRX %s", productOrder);
        List<ProductOrderBody> productOrderList = new ArrayList<>();
        if (productOrder.getColor() != null && productOrder.getSize() != null) {
            productOrderList = ProductOrderBody.find(ProductOrderBody.class, "user_id=? and order_id=? and size_id=? and color_id=?",
                    String.valueOf(getUserId()), String.valueOf(productOrder.getOrderId()),
                    String.valueOf(productOrder.getSizeId()), String.valueOf(productOrder.getColorId()));
        } else if (productOrder.getColor() == null && productOrder.getSize() != null) {
            productOrderList = ProductOrderBody.find(ProductOrderBody.class, "user_id=? and order_id=? and size_id=?",
                    String.valueOf(getUserId()), String.valueOf(productOrder.getOrderId()),
                    String.valueOf(productOrder.getSizeId()));
        } else if (productOrder.getSize() == null && productOrder.getColor() != null) {
            productOrderList = ProductOrderBody.find(ProductOrderBody.class, "user_id=? and order_id=? and color_id=?",
                    String.valueOf(getUserId()), String.valueOf(productOrder.getOrderId()),
                    String.valueOf(productOrder.getColorId()));
        } else if (productOrder.getColor() == null && productOrder.getSize() == null) {
            productOrderList = ProductOrderBody.find(ProductOrderBody.class, "user_id=? and order_id=? and size_id=? and color_id=?",
                    String.valueOf(getUserId()),
                    String.valueOf(productOrder.getOrderId()),
                    String.valueOf(0),
                    String.valueOf(0));
        }
        if (productOrderList.size() > 0) {
            Timber.d("saved productOrder");
            productOrderList.get(0).setPrice(productOrder.getPrice());
            productOrderList.get(0).setWeight(productOrder.getWeight());
            productOrderList.get(0).setAmount(productOrder.getAmount());
            productOrderList.get(0).setQuantity(productOrder.getQuantity());
            productOrderList.get(0).setGiftName(productOrder.getGiftName());
            productOrderList.get(0).setGiftMessage(productOrder.getGiftMessage());
            productOrderList.get(0).setGift(productOrder.getIsGift());
            productOrderList.get(0).save();
        } else {
            Timber.d("else");
            if (productOrder.getProduct() != null) {
                productOrder.setOrderId(productOrder.getOrderId());
                //productOrder.setId(Long.valueOf(productOrder.getOrderId()));
                productOrder.setPrice(productOrder.getPrice());
                if (productOrder.getColor() != null) {
                    Color color = productOrder.getColor();
                    color.setId(Long.valueOf(color.getColorId()));
                    color.save();
                }
                productOrder.setColor(productOrder.getColor());
                if (productOrder.getSize() != null) {
                    Size size = productOrder.getSize();
                    size.setId(Long.valueOf(size.getSizeId()));
                    size.save();
                }
                productOrder.setSize(productOrder.getSize());
                productOrder.setProduct(productOrder.getProduct());
                productOrder.setQuantity(productOrder.getQuantity());
                productOrder.setAmount(productOrder.getAmount());
                productOrder.setGiftName(productOrder.getGiftName());
                productOrder.setGiftMessage(productOrder.getGiftMessage());
                productOrder.setGift(productOrder.getIsGift());
                productOrder.setPriceForSingleItem(productOrder.getPriceForSingleItem());
                Product product = productOrder.getProduct();
                product.save();
                productOrder.save();
                if (productOrder.save() > 0) {
                    Timber.d("save productOrder %s", productOrder);
                }
            }
        }
        productOrderList = ProductOrderBody.find(ProductOrderBody.class,
                "user_id=?", String.valueOf(getUserId()));
        isSavedInDatabase = productOrderList.size();
        return isSavedInDatabase;
    }

    public Observable<List<ProductOrderBody>> getSavedOrders() {

        return Observable.create(new ObservableOnSubscribe<List<ProductOrderBody>>() {
            @Override
            public void subscribe(ObservableEmitter<List<ProductOrderBody>> e) {
                try {
                    openDatabase();
                    List<ProductOrderBody> productOrderList = ProductOrderBody.find(ProductOrderBody.class,
                            "user_id=?", String.valueOf(getUserId()));
                    //List<ProductOrderBody> productOrderList = Lists.newArrayList(productOrderIterator);
                    // Timber.d("productOrderList %s", productOrderList);
                    e.onNext(productOrderList);
                    e.onComplete();
                } catch (Exception ex) {
                    e.onError(ex);
                } finally {
                    closeDatabase();
                }
            }
        });
    }

    public Observable<BaseResponse> sendOrder(final AddressBody productOrderBody) {

        return mBazarlakService.add_order(productOrderBody)
                .concatMap(new Function<BaseResponse, ObservableSource<? extends BaseResponse>>() {
                    @Override
                    public ObservableSource<? extends BaseResponse> apply(final BaseResponse response) {
                        return Observable.create(new ObservableOnSubscribe<BaseResponse>() {
                            @Override
                            public void subscribe(ObservableEmitter<BaseResponse> e) {
                                //Timber.d("response %s", response);
                                try {
                                    if (response.getStatus()) {
                                        //getPreferencesHelper().addUserSession(response.getOrdersData().getUserData());
                                        openDatabase();
                                        List<ProductOrderBody> productOrderList = ProductOrderBody.find(ProductOrderBody.class,
                                                "user_id=?", String.valueOf(getUserId()));
                                        //   Timber.d("productOrderBodyList %s", productOrderList);
                                        if (productOrderList != null && productOrderList.size() > 0) {
                                            deleteOrders(productOrderList);
                                        }
                                    }
                                    e.onNext(response);
                                } catch (Exception e1) {
                                    e.onError(e1);
                                } finally {
                                    closeDatabase();
                                }
                                e.onComplete();

                            }
                        });
                    }
                });
    }

    public Observable<Boolean> checkExceedWight(final ProductOrderBody productOrder) {
        return Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(ObservableEmitter<Boolean> e) {
                try {
                    openDatabase();
                    boolean isExceeds = false;
                    List<ProductOrderBody> productOrderList = ProductOrderBody.find(ProductOrderBody.class,
                            "user_id=?", String.valueOf(getUserId()));
                    //  Timber.d("productOrderList %s", productOrderList);
                    double totalWeight = 0;
                    for (ProductOrderBody productOrderBody : productOrderList
                    ) {
                        if (productOrderBody.getOrderId().equals(productOrder.getOrderId()))
                            productOrderBody.setQuantity(productOrder.getQuantity());
                        totalWeight += productOrderBody.getProduct().getWeight() * productOrderBody.getQuantity();
                    }
                    if (totalWeight >= 5000)
                        isExceeds = true;
                    e.onNext(isExceeds);
                    e.onComplete();
                } catch (Exception ex) {
                    e.onError(ex);
                } finally {
                    closeDatabase();
                }
            }
        });
    }

    public Observable<List<Box>> getShippingBoxes() {
        return mBazarlakService.get_shopping_boxes()
                .concatMap(new Function<BaseResponse<BoxsData>, ObservableSource<? extends List<Box>>>() {
                    @Override
                    public ObservableSource<? extends List<Box>> apply(final BaseResponse<BoxsData> response) {
                        return Observable.create(new ObservableOnSubscribe<List<Box>>() {
                            @Override
                            public void subscribe(ObservableEmitter<List<Box>> e) {

                                try {
                                    if (response.getStatus()) {
                                        if (getPreferencesHelper().getCountry() != null &&
                                                !getPreferencesHelper().getCountry().getPrefix().equals(Const.KUWAIT))
                                            getPreferencesHelper().setShippingBoxes(response.getData());
                                        //Timber.d("getPreferencesHelper() %s",getPreferencesHelper().getShippingBoxes().getBoxs());
                                        e.onNext(response.getData().getBoxs());
                                    } else
                                        e.onNext(null);
                                } catch (Exception e1) {
                                    e.onError(e1);
                                }
                                e.onComplete();
                            }
                        });
                    }
                })
                ;
    }

/*
    public Observable<Product> saveProductSizeAndColor(final Product product) {
        return Observable.create(new ObservableOnSubscribe<Product>() {
            @Override
            public void subscribe(ObservableEmitter<Product> e) {
                try {
                    openDatabase();
                    // List<Product> producDb = Product.find(Product.class, "product_id = ?", String.valueOf(productN.getProductId()));
                    // if (producDb.size() == 0) {
                    Pivot pivot;
                    List<Color> colors = product.getColors();
                    for (Color color :
                            colors) {
                        pivot = color.getPivot();
                        color.setId(Long.valueOf(color.getColorId()));
                        color.save();
                        pivot.setId(Long.valueOf((pivot.getProductId() + "00" + pivot.getColorId() + "00" + pivot.getSizeId())));
                        pivot.save();

                    }
                    //  }
//                    else
//                        productN=producDb.get(0);

                    for (Size size :
                            product.getSizes()) {
                        String qurey = "SELECT DISTINCT * FROM COLOR AS c INNER JOIN PIVOT AS p on c.color_id = p.color_id WHERE p.size_id = ? and p.product_id = ?";
                        List<Color> colorSizeList = Color.findWithQuery(Color.class, qurey, String.valueOf(size.getSizeId()), String.valueOf(product.getProductId()));
                        Timber.d("colorSizeList %s", colorSizeList);
                        size.setColors(colorSizeList);
                        size.setId(Long.valueOf(size.getSizeId()));
                        size.save();
                        Timber.d("size updated %s", size);
                    }
                    product.setId(Long.valueOf(product.getProductId()));
                    //productN.save();
                    e.onNext(product);
                } catch (Exception ex) {
                    e.onError(ex);
                } finally {
                    e.onComplete();
                    closeDatabase();
                }
            }
        });
    }
*/

}
