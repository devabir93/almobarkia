package net.selsela.almobarakeya.util;

import io.reactivex.disposables.Disposable;

public class RxUtil {

    public static void dispose(Disposable disposable) {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }

}
