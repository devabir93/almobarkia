package net.selsela.almobarakeya.data.remote;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.orm.SugarRecord;

import net.selsela.almobarakeya.data.local.PreferencesHelper;
import net.selsela.almobarakeya.data.model.BaseResponse;
import net.selsela.almobarakeya.data.model.about.AboutData;
import net.selsela.almobarakeya.data.model.address.AddressData;
import net.selsela.almobarakeya.data.model.boxes.BoxsData;
import net.selsela.almobarakeya.data.model.category.CategoriesData;
import net.selsela.almobarakeya.data.model.config.ConfigData;
import net.selsela.almobarakeya.data.model.country.CountryData;
import net.selsela.almobarakeya.data.model.country.GovsData;
import net.selsela.almobarakeya.data.model.coupon.CheckCoponData;
import net.selsela.almobarakeya.data.model.filter.FilterData;
import net.selsela.almobarakeya.data.model.home.HomeData;
import net.selsela.almobarakeya.data.model.home.MainCategory;
import net.selsela.almobarakeya.data.model.notifications.Notificationsdata;
import net.selsela.almobarakeya.data.model.order.OrderBody;
import net.selsela.almobarakeya.data.model.order.OrderData;
import net.selsela.almobarakeya.data.model.user.LoginData;
import net.selsela.almobarakeya.data.model.user.UserBody;
import net.selsela.almobarakeya.data.model.user_fav.favData;
import net.selsela.almobarakeya.util.RxErrorHandlingCallAdapterFactory;
import net.selsela.almobarakeya.util.SugarExclusionStrategy;
import net.selsela.almobarakeya.util.language.LanguageUtils;

import java.io.IOException;

import io.reactivex.Observable;
import okhttp3.Interceptor;
import okhttp3.MultipartBody;
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

    @POST("user/update_device_key")
    Observable<BaseResponse> update_device_key(@Query("user_id") int user_id,
                                               @Query("token") String tocken_id, @Query("key") String code);

    @POST("user/login")
    Observable<BaseResponse<LoginData>> login(@Body UserBody userBody);

    @POST("user/register")
    Observable<BaseResponse<LoginData>> register(@Body UserBody userBody);

    @POST("contact_us")
    Observable<BaseResponse> contact_us(@Body UserBody userBody);

    @POST("user/guest_log_reg")
    Observable<BaseResponse<LoginData>> guest_log_reg(@Body UserBody userBody);

    @POST("user/update_profile")
    Observable<BaseResponse<LoginData>> update_profile(@Body UserBody userBody);

    @POST("user/change_password")
    Observable<BaseResponse> change_password(@Body UserBody userBody);

    @POST("user/create_guest_password")
    Observable<BaseResponse> create_guest_password(@Body UserBody userBody);


    @POST("user/forget_password")
    Observable<BaseResponse> forget_password(@Body UserBody userBody);

    @POST("user/add_rate")
    Observable<BaseResponse> add_rate(@Body UserBody userBody);

    @POST("specialOrder")
    Observable<BaseResponse> specialOrder(@Body MultipartBody userBody);


    @POST("user/check_copone")
    Observable<BaseResponse<CheckCoponData>> checkCopon(@Query("user_id") int user_id,
                                                        @Query("token") String tocken_id, @Query("code") String code);


    @GET("get_about_page")
    Observable<BaseResponse<AboutData>> get_about_page();

    @GET("user/get_notifications")
    Observable<BaseResponse<Notificationsdata>> get_notifications(@Query("user_id") int user_id,
                                                                  @Query("token") String tocken_id);

    @GET("get_rules_page")
    Observable<BaseResponse<AboutData>> get_rules_page();

    @GET("get_filter_const")
    Observable<BaseResponse<FilterData>> get_filter_const();


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

    @GET("get_category_products")
    Observable<BaseResponse<MainCategory>> get_category_products(@Query("category_id") int category_id, @Query("page") int page,
                                                                   @Query("country_id") int country_id, @Query("user_id") int userId);

    @GET("get_countries")
    Observable<BaseResponse<CountryData>> get_countries();

    @GET("search")
    Observable<BaseResponse<MainCategory>> search(@Query("field_text") String key, @Query("country_id") int countryID);

    @GET("filter")
    Observable<BaseResponse<MainCategory>> filter(@Query("country_id") int country_id, @Query("color_id") int colorId, @Query("size_id") int sizeId,
                                                  @Query("category_id") int categoryId,
                                                  @Query("price_from") int priceForm, @Query("price_to") int priceTo);

    @GET("get_home")
    Observable<BaseResponse<HomeData>> get_home(@Query("country_id") int country_id,
                                                @Query("user_id") int userId);

    @GET("user/get_user_favorites")
    Observable<BaseResponse<favData>> get_user_favorites(@Query("user_id") int user_id,
                                                         @Query("token") String tocken_id);

    @GET("get_shopping_boxes")
    Observable<BaseResponse<BoxsData>> get_shopping_boxes(@Query("country_id") int country_id);

    @GET("user/get_address")
    Observable<BaseResponse<AddressData>> get_address(@Query("user_id") int user_id,
                                                      @Query("token") String tocken_id,
                                                      @Query("country_id") int country_id);

    @GET("get_govs")
    Observable<BaseResponse<GovsData>> get_govs(@Query("country_id") int countryId);

    ///Post

    @POST("user/add_order")
    Observable<BaseResponse<OrderData>> add_order(@Body OrderBody addressBody);

    @POST("user/check_copone")
    Observable<BaseResponse<CheckCoponData>> check_copone(@Query("user_id") int user_id,
                                                          @Query("token") String tocken_id, @Query("code") String code);

    @POST("user/add_favorite")
    Observable<BaseResponse> add_favorite(@Query("user_id") int user_id,
                                          @Query("token") String tocken_id, @Query("product_id") int productID);


    /******** Helper class that sets up a new services *******/
    class Creator {
        public static SelselaService newRibotsService(Context context) {
            final PreferencesHelper sharedPreferences = new PreferencesHelper(context);
            final LanguageUtils mLanguageUtils = new LanguageUtils(context, sharedPreferences);
            final SugarExclusionStrategy strategy = new SugarExclusionStrategy(SugarRecord.class);
            Gson gson = new GsonBuilder()
                    //.registerTypeAdapterFactory(MyGsonTypeAdapterFactory.create())
                    .excludeFieldsWithoutExposeAnnotation()
                    .setLenient()
                    .addDeserializationExclusionStrategy(strategy)
                    .addSerializationExclusionStrategy(strategy)
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
