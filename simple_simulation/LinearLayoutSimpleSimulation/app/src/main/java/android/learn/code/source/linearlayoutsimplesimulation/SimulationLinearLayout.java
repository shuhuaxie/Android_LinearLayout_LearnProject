package android.learn.code.source.linearlayoutsimplesimulation;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

public class SimulationLinearLayout extends ViewGroup {


  public SimulationLinearLayout(Context context) {
    this(context, null);
    Log.e("simL", " 1 ");
  }

  public SimulationLinearLayout(Context context, @Nullable AttributeSet attrs) {
    this(context, attrs, R.attr.attr_style);
    Log.e("simL", " 2 "); // layout
  }

  public SimulationLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    this(context, attrs, defStyleAttr, 0);
    Log.e("simL", " 3 ");
  }

  public SimulationLinearLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
    super(context, attrs, defStyleAttr);
    final TypedArray a = context.obtainStyledAttributes(
        attrs, R.styleable.SimulationLinearLayout, defStyleAttr, defStyleRes);
    int index = a.getInt(R.styleable.SimulationLinearLayout_orient, -1);
    Log.e("simL", "index:" + index);
    a.recycle();
  }

  @Override
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    for (int i = 0; i < getChildCount(); i++) {
      getChildAt(i).measure(widthMeasureSpec, heightMeasureSpec); //必须手动调用
    }
  }

  @Override
  protected void onLayout(boolean changed, int l, int t, int r, int b) {
    int childTop = 0;
    for (int i = 0; i < getChildCount(); i++) {
      View child = getChildAt(i);
      final int childWidth = child.getMeasuredWidth();
      final int childHeight = child.getMeasuredHeight();
      child.layout(0, childTop, childWidth, childTop + childHeight);
      childTop += childHeight;
    }
  }
}
