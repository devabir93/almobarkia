package com.selsela.example.data.remote;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.selsela.example.data.local.PreferencesHelper;
import com.selsela.example.data.model.BaseResponse;
import com.selsela.example.data.model.about.AboutData;
import com.selsela.example.data.model.boxes.BoxsData;
import com.selsela.example.data.model.category.CategoriesData;
import com.selsela.example.data.model.config.ConfigData;
import com.selsela.example.data.model.country.CountryData;
import com.selsela.example.data.model.coupon.CheckCoponData;
import com.selsela.example.data.model.filter.Filterdata;
import com.selsela.example.data.model.home.HomeData;
import com.selsela.example.data.model.home.MainCategory;
import com.selsela.example.data.model.notifications.Notificationsdata;
import com.selsela.example.data.model.order.OrderData;
import com.selsela.example.data.model.send_order.AddressBody;
import com.selsela.example.data.model.user.LoginData;
import com.selsela.example.data.model.user.UserBody;
import com.selsela.example.data.model.user_fav.favData;
import com.selsela.example.data.model.user_fav.favProduct;
import com.selsela.example.util.RxErrorHandlingCallAdapterFactory;
import com.selsela.example.util.language.LanguageUtils;

import java.io.IOException;

import io.reactivex.Observable;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface SelselaService {

    String ENDPOINT = "http://selsela.info/mobarakia/public/api/";
    String IMAGE_URL = "http://selsela.info/mobarakia/public/uploads/";

    @POST("user/login")
    Observable<BaseResponse<LoginData>> login(@Body UserBody userBody);

    @POST("user/register")
    Observable<BaseResponse<LoginData>> register(@Body UserBody userBody);
    @POST("contact_us")
    Observable<BaseResponse> contact_us(@Body UserBody userBody);
    @POST("user/update_profile")
    Observable<BaseResponse> update_profile(@Body UserBody userBody);
    @POST("user/change_password")
    Observable<BaseResponse> change_password(@Body UserBody userBody);
    @POST("user/add_rate")
    Observable<BaseResponse> add_rate(@Body UserBody userBody);
    @POST("specialOrder")
    Observable<BaseResponse> specialOrder(@Body UserBody userBody);
    @GET("get_about_page")
    Observable<BaseResponse<AboutData>> get_about_page();
    @GET("user/get_notifications")
    Observable<BaseResponse<Notificationsdata>> get_notifications(@Query("user_id") int user_id,
                                                                  @Query("token") String tocken_id);
    @GET("get_rules_page")
    Observable<BaseResponse<AboutData>> get_rules_page();

    @GET("get_filter_const")
    Observable<BaseResponse<Filterdata>> get_filter_const();


    @GET("get_safty_page")
    Observable<BaseResponse<AboutData>> get_safty_page();

    @GET("get_policies_page")
    Observable<BaseResponse<AboutData>> get_policies_page();

    @GET("get_config")
    Observable<BaseResponse<ConfigData>> get_config();

    @GET("user/get_orders")
    Observable<BaseResponse<OrderData>> get_orders(@Query("user_id") int user_id,
                                                   @Query("token") String tocken_id);

    @GET("get_categories")
    Observable<BaseResponse<CategoriesData>> get_categories(@Query("country_id") int country_id, @Query("user_id") int userId);

    @GET("get_countries")
    Observable<BaseResponse<CountryData>> get_countries();

    @GET("filter")
    Observable<BaseResponse<MainCategory>> filter(@Query("country_id") int country_id, @Query("category_id") int categoryId, @Query("color_id")int colorId , @Query("size_id")int sizeId,@Query("price_from") int priceForm,@Query("price_to") int priceTo );

    @GET("get_home")
    Observable<BaseResponse<HomeData>> get_home(@Query("country_id") int country_id,
                                                @Query("user_id") int userId);

    @GET("user/get_user_favorites")
    Observable<BaseResponse<favData>> get_user_favorites(@Query("user_id") int user_id,
                                                         @Query("token") String tocken_id);

    @GET("get_shopping_boxes")
    Observable<BaseResponse<BoxsData>> get_shopping_boxes();

    ///Post

    @POST("add_order")
    Observable<BaseResponse<OrderData>> add_order(@Body AddressBody addressBody);

    @POST("user/check_copone")
    Observable<BaseResponse<CheckCoponData>> check_copone(@Query("user_id") int user_id,
                                                          @Query("token") String tocken_id, @Query("code") String code);


    /******** Helper class that sets up a new services *******/
    class Creator {
        public static SelselaService newRibotsService(Context context) {
            final PreferencesHelper sharedPreferences = new PreferencesHelper(context);
            final LanguageUtils mLanguageUtils = new LanguageUtils(context, sharedPreferences);
            Gson gson = new GsonBuilder()
                    //.registerTypeAdapterFactory(MyGsonTypeAdapterFactory.create())
                    .excludeFieldsWithoutExposeAnnotation()
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                    .create();

            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request newRequest = chain.request().newBuilder()
                            .addHeader("Accept", "application/json")
                            .addHeader("Content-Type", "application/x-www-form-urlencoded")
                            .addHeader("X-Requested-With", "XMLHttpRequest")
                            .addHeader("x-localization", mLanguageUtils.getCurrentLang())
                            .build();
                    return chain.proceed(newRequest);
                }
            }).build();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(SelselaService.ENDPOINT)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    //.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addCallAdapterFactory(RxErrorHandlingCallAdapterFactory.create())
                    .client(client)
                    .build();
            return retrofit.create(SelselaService.class);
        }
    }
}
