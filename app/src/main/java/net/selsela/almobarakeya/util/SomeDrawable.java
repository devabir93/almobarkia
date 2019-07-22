package net.selsela.almobarakeya.util;

import android.graphics.drawable.GradientDrawable;

public class SomeDrawable extends GradientDrawable {

    public SomeDrawable(int pStartColor, int pStrokeColor, int pStartwidth, int shape, int cornerRadius, int size) {
        super(Orientation.BOTTOM_TOP, new int[]{pStartColor, pStartColor, pStartColor});
        setStroke(pStartwidth, pStrokeColor);
        setShape(shape);
        setCornerRadius(cornerRadius);
        setSize(size, size);
        setUseLevel(true);
    }

    public SomeDrawable(int pStartColor, int pStrokeColor, int pStartwidth, int shape, int cornerRadius) {
        super(Orientation.BOTTOM_TOP, new int[]{pStartColor, pStartColor, pStartColor});
        setStroke(pStartwidth, pStrokeColor);
        setShape(shape);
        setCornerRadius(cornerRadius);
        setUseLevel(true);
    }
    public SomeDrawable(int pStartColor, int pStrokeColor, int pStartwidth, int shape) {
        super(Orientation.BOTTOM_TOP, new int[]{pStartColor, pStartColor, pStartColor});
        setStroke(pStartwidth, pStrokeColor);
        setShape(shape);
        setUseLevel(true);
    }

    public SomeDrawable(int pStartColor, int shape) {
        super(Orientation.BOTTOM_TOP, new int[]{pStartColor, pStartColor, pStartColor});
        setShape(shape);
        setUseLevel(true);
    }
/*    public void setStroke(int pStartwidth, int pStrokeColor) {
        setStroke(pStartwidth, pStrokeColor);
    }

    public void setShape(int shape) {
        setShape(shape);

    }

    public void setCornerRadius(int cornerRadius) {
        setCornerRadius(cornerRadius);

    }

    public void setSize(int size) {
        setSize(size, size);

    }*/
}