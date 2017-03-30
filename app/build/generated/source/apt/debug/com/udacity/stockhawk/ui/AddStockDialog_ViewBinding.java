// Generated code from Butter Knife. Do not modify!
package com.udacity.stockhawk.ui;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.udacity.stockhawk.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class AddStockDialog_ViewBinding<T extends AddStockDialog> implements Unbinder {
  protected T target;

  @UiThread
  public AddStockDialog_ViewBinding(T target, View source) {
    this.target = target;

    target.stock = Utils.findRequiredViewAsType(source, R.id.dialog_stock, "field 'stock'", EditText.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.stock = null;

    this.target = null;
  }
}
