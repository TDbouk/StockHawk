// Generated code from Butter Knife. Do not modify!
package com.udacity.stockhawk.ui;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.udacity.stockhawk.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class StockAdapter$StockViewHolder_ViewBinding<T extends StockAdapter.StockViewHolder> implements Unbinder {
  protected T target;

  @UiThread
  public StockAdapter$StockViewHolder_ViewBinding(T target, View source) {
    this.target = target;

    target.symbol = Utils.findRequiredViewAsType(source, R.id.symbol, "field 'symbol'", TextView.class);
    target.price = Utils.findRequiredViewAsType(source, R.id.price, "field 'price'", TextView.class);
    target.change = Utils.findRequiredViewAsType(source, R.id.change, "field 'change'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.symbol = null;
    target.price = null;
    target.change = null;

    this.target = null;
  }
}
