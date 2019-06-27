package com.selsela.example.data.local;


import com.selsela.example.data.model.order.OrderBody;

public enum DataHolder {
    INSTANCE;

    private OrderBody orderBody;

    public static boolean hasOrder() {
        return INSTANCE.orderBody != null;
    }

    public static void setOrder(final OrderBody objectList) {
        INSTANCE.orderBody = objectList;
    }

    public static OrderBody getOrder() {
        return INSTANCE.orderBody;
    }

    public static void clearOrder() {
        INSTANCE.orderBody = null;
    }


    @Override
    public String toString() {
        return "DataHolder{" +
                "orderBody=" + orderBody +
                '}';
    }


}

