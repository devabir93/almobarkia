package net.selsela.almobarakeya.ui.address.new_address;

import android.app.Fragment;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TextInputEditText;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import net.selsela.almobarakeya.R;
import net.selsela.almobarakeya.data.local.DataHolder;
import net.selsela.almobarakeya.data.model.order.OrderBody;
import net.selsela.almobarakeya.ui.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import timber.log.Timber;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link NewAddressMoreFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewAddressMoreFragment extends BaseFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final int BUILDING = 1;
    private static final int HOUSE = 2;

    Unbinder unbinder;
    @BindView(R.id.building)
    TextView building;
    @BindView(R.id.switch1)
    Switch switch1;
    @BindView(R.id.house)
    TextView house;
    @BindView(R.id.switch_layout)
    ConstraintLayout switchLayout;
    @BindView(R.id.building_nmuber_EditText)
    TextInputEditText buildingNmuberEditText;
    @BindView(R.id.line4)
    View line4;
    @BindView(R.id.buildingnumber)
    TextView buildingnumber;
    @BindView(R.id.floor_numberEditText)
    TextInputEditText floorNumberEditText;
    @BindView(R.id.line5)
    View line5;
    @BindView(R.id.floornumber)
    TextView floornumber;
    @BindView(R.id.flat_numberEditText)
    TextInputEditText flatNumberEditText;
    @BindView(R.id.line6)
    View line6;
    @BindView(R.id.productnumber)
    TextView productnumber;
    @BindView(R.id.checkBox)
    CheckBox checkBox;
    @BindView(R.id.lay2)
    ConstraintLayout lay2;
    @BindView(R.id.building_lay)
    ConstraintLayout buildingLay;


    private OrderBody orderBody;

    public NewAddressMoreFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static NewAddressMoreFragment newInstance() {
        NewAddressMoreFragment fragment = new NewAddressMoreFragment();
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_second_address, container, false);
        getActivityComponent().inject(this);

        unbinder = ButterKnife.bind(this, view);
        orderBody = DataHolder.getOrder();
        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    showHouse();
                } else showBuilding();
            }
        });
        return view;

    }

    private void showHouse() {
        buildingNmuberEditText.setHint(getActivity().getString(R.string.house_input));
        buildingnumber.setText(getString(R.string.housenumer_label));
        buildingLay.setVisibility(View.GONE);
    }

    private void showBuilding() {
        buildingNmuberEditText.setHint(getActivity().getString(R.string.building_input));
        buildingnumber.setText(getString(R.string.buildingnumer_label));
        buildingLay.setVisibility(View.VISIBLE);
    }

    public OrderBody getMoreNewAddress() {

        if (!switch1.isChecked()) {
            if (buildingNmuberEditText.getEditableText().length() < 1) {
                buildingNmuberEditText.setError(getContext().getString(R.string.topic_label));
                return null;
            } else
                orderBody.setBuilding_number(buildingNmuberEditText.getEditableText().toString());
            if (flatNumberEditText.length() < 1) {
                flatNumberEditText.setError(getContext().getString(R.string.topic_label));
                return null;
            } else
                orderBody.setFlat(flatNumberEditText.toString());
            if (floorNumberEditText.getEditableText().length() < 1) {
                floorNumberEditText.setError(getContext().getString(R.string.topic_label));
                return null;
            } else
                orderBody.setFloor(floorNumberEditText.getEditableText().toString());
            orderBody.setBuilding_or_house(BUILDING);
        } else {

            if (buildingNmuberEditText.getEditableText().length() < 1) {
                buildingNmuberEditText.setError(getContext().getString(R.string.topic_label));
                return null;
            } else {
                orderBody.setBuilding_number(buildingNmuberEditText.getEditableText().toString());
                orderBody.setBuilding_or_house(HOUSE);
            }

        }
        Timber.d("orderBody.getFull_address() %s", orderBody.getFull_address());
        orderBody.setSave_address(switch1.isChecked() ? 1 : 0);

        String moreAddress = orderBody.getFull_address();
        if (orderBody.getBuilding_or_house() == HOUSE) {
            moreAddress = moreAddress + ", " + this.getString(R.string.housenumer_label) + " " + buildingNmuberEditText.getEditableText().toString();
        } else if (orderBody.getBuilding_or_house() == 1) {
            if (buildingNmuberEditText.getEditableText().length() > 0) {
                moreAddress = moreAddress + ", " + this.getString(R.string.buildingnumer_label) + " " + buildingNmuberEditText.getEditableText().toString();
            }
            if (floorNumberEditText.getEditableText().length() > 0) {
                moreAddress = moreAddress + ", " + this.getString(R.string.floornumber_label) + " " + floorNumberEditText.getEditableText().toString();
            }

            if (flatNumberEditText.getEditableText().length() > 0) {
                moreAddress = moreAddress + ", " + this.getString(R.string.flatnumber_label) + " " + flatNumberEditText.getEditableText().toString();
            }
        }
        Timber.d("moreAddress %s", moreAddress);
        orderBody.setFull_address(moreAddress);

        return orderBody;
    }


}
