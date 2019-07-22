package net.selsela.almobarakeya.ui.specialorder;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TextInputEditText;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.esafirm.imagepicker.features.ImagePicker;
import com.esafirm.imagepicker.features.ReturnMode;
import com.esafirm.imagepicker.model.Image;
import net.selsela.almobarakeya.R;
import net.selsela.almobarakeya.data.model.order.Order;
import net.selsela.almobarakeya.data.model.user.UserBody;
import net.selsela.almobarakeya.ui.base.BaseFragment;
import net.selsela.almobarakeya.ui.orders.OrdersPresenter;
import net.selsela.almobarakeya.ui.orders.OrdresMvpView;
import net.selsela.almobarakeya.util.Permissions;
import net.selsela.almobarakeya.util.ViewUtil;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import timber.log.Timber;

import static android.app.Activity.RESULT_OK;
import static net.selsela.almobarakeya.util.Permissions.REQUEST_EXTERNAL_STORAGE;
import static net.selsela.almobarakeya.util.ViewUtil.getResizedBitmap;
import static net.selsela.almobarakeya.util.ViewUtil.modifyOrientation;


public class SpecialOrderFragment extends BaseFragment implements OrdresMvpView {

    private static final int REQUEST_CODE_PICKER = 10;
    @BindView(R.id.add_imageView)
    ImageView addImageView;
    @BindView(R.id.Innerlayout)
    ConstraintLayout Innerlayout;
    @BindView(R.id.textInputEditText)
    TextInputEditText textInputEditText;
    @BindView(R.id.line)
    View line;
    @BindView(R.id.user_data)
    TextView userData;
    @BindView(R.id.phone_number_editText)
    TextInputEditText phoneNumberEditText;
    @BindView(R.id.line3)
    View line3;
    @BindView(R.id.phone_num)
    TextView phoneNum;
    @BindView(R.id.product_name_editText)
    TextInputEditText productNameEditText;
    @BindView(R.id.line4)
    View line4;
    @BindView(R.id.topic)
    TextView topic;
    @BindView(R.id.productdetails_editText)
    TextInputEditText productdetailsEditText;
    @BindView(R.id.line5)
    View line5;
    @BindView(R.id.details_label)
    TextView detailsLabel;
    @BindView(R.id.send_order)
    TextView sendOrder;
    Unbinder unbinder;
    @Inject
    OrdersPresenter ordersPresenter;
    @BindView(R.id.imageView)
    ImageView imageView;
    private UserBody userBody;
    private String resizedPath;

    public SpecialOrderFragment() {
        // Required empty public constructor
    }

    public static SpecialOrderFragment newInstance() {


        SpecialOrderFragment fragment = new SpecialOrderFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivityComponent().inject(this);
        ordersPresenter.attachView(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_special_order, container, false);
        unbinder = ButterKnife.bind(this, view);
        userBody = new UserBody();

        return view;
    }


    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void showOrders(List<Order> orders) {

    }


    @Override
    public void isSuccess(Boolean status) {
        super.isSuccess(status);
        Timber.d("isSuccess");
        textInputEditText.setText("");
        phoneNumberEditText.setText("");
        productNameEditText.setText("");
        productdetailsEditText.setText("");
        imageView.setVisibility(View.GONE);
        resizedPath = "";
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public void openGallery() {
        ImagePicker.create(this)
                .returnMode(ReturnMode.ALL) // set whether pick and / or camera action should return immediate result or not.
                .folderMode(true) // folder mode (false by default)
                .toolbarFolderTitle("Folder") // folder selection title
                .toolbarImageTitle("Tap to select") // image selection title
                .single() // single mode
                .limit(1) // max images can be selected (99 by default)
                .showCamera(true) // show camera or not (true by default)
                .imageDirectory("Camera") // directory name for captured image  ("Camera" folder by default)
                //.origin(images) // original selected images, used in multi mode
                .start(REQUEST_CODE_PICKER); // start image picker activity with request code
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d("onActivityResult", "fragment");
        if (requestCode == REQUEST_CODE_PICKER && resultCode == RESULT_OK && data != null) {
            Image selectedImage = ImagePicker.getFirstImageOrNull(data);
            Timber.d("selectedImage %s", selectedImage.getPath());
            imageView.setVisibility(View.VISIBLE);
            //addImageLayout.setVisibility(View.GONE);
            resizedPath = ViewUtil.resizeAndCompressImageBeforeSend(getContext(), selectedImage.getPath(), selectedImage.getName());
            Bitmap selectedImageTobeshown = BitmapFactory.decodeFile(resizedPath);

            selectedImageTobeshown = getResizedBitmap(selectedImageTobeshown, 400);// 400 is for example, replace with desired size
            try {
                imageView.setImageBitmap(modifyOrientation(selectedImageTobeshown, resizedPath));
            } catch (IOException e) {
                e.printStackTrace();
            }
            //image.setImageBitmap(selectedImageTobeshown);
            //image.setImageBitmap(BitmapFactory.decodeFile(resizedPath));
            userBody.setUploaded_file(resizedPath);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_EXTERNAL_STORAGE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length <= 0
                        || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    showMessageDialog("Cannot Open gallery because you deny the permission");
                } else {
                    openGallery();
                }
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    public void onAddImage() {

        if (!Permissions.checkWriteExternalPermission(getActivity()))
            Permissions.verifyStoragePermissions(getActivity());
        else openGallery();
    }

    public void sendOrder() {

        if (hasInternetConnection()) {

            if (textInputEditText.getText().length() < 1) {
                textInputEditText.setError(this.getString(R.string.name_erro));
            }
            if (phoneNumberEditText.getText().length() < 1) {
                phoneNumberEditText.setError(this.getString(R.string.phone_empty_label));
            } else if (productNameEditText.getText().length() < 1) {
                productNameEditText.setError(this.getString(R.string.product_erro));
            } else if
            (productdetailsEditText.getText().length() < 1) {
                productdetailsEditText.setError(this.getString(R.string.productde_erro));
            } else {
                MultipartBody.Builder builder = new MultipartBody.Builder();
                builder.setType(MultipartBody.FORM);
                String user_name = textInputEditText.getText().toString();
                String phone_num = phoneNumberEditText.getText().toString();
                String product_name = productNameEditText.getText().toString();
                String product_details = productdetailsEditText.getText().toString();

                builder.addFormDataPart("mobile", phone_num);
                builder.addFormDataPart("name", user_name);
                builder.addFormDataPart("product_name", product_name);
                builder.addFormDataPart("product_details", product_details);
                builder.addFormDataPart("country_id", String.valueOf(preferencesHelper.getCountry().getId()));
                // Single Image
                if (resizedPath != null && !resizedPath.isEmpty()) {
                    File file = new File(resizedPath);
                    builder.addFormDataPart("uploaded_file", file.getName(), RequestBody.create(MediaType.parse("image/*"), file));
                }

                MultipartBody multipartBody = builder.build();
//                userBody.setName(user_name);
//                userBody.setMobile(phone_num);
//                userBody.setProductDetails(product_details);
//                userBody.setProductName(product_name);
//                userBody.setCountryId(preferencesHelper.getCountry().getId());
                ordersPresenter.specialOrder(getContext(), multipartBody);
            }

        } else

            hasActiveInternetConnection(false);
    }

    @OnClick({R.id.Innerlayout, R.id.send_order})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.Innerlayout:
                onAddImage();
                break;
            case R.id.send_order:
                sendOrder();
                break;
        }
    }

}


