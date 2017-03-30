// Generated code from Butter Knife. Do not modify!
package com.udacity.stockhawk.ui;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.udacity.stockhawk.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MainActivity_ViewBinding<T extends MainActivity> implements Unbinder {
  protected T target;

  @UiThread
  public MainActivity_ViewBinding(T target, View source) {
    this.target = target;

    target.stockRecyclerView = Utils.findRequiredViewAsType(source, R.id.recycler_view, "field 'stockRecyclerView'", RecyclerView.class);
    target.swipeRefreshLayout = Utils.findRequiredViewAsType(source, R.id.swipe_refresh, "field 'swipeRefreshLayout'", SwipeRefreshLayout.class);
    target.error = Utils.findRequiredViewAsType(source, R.id.error, "field 'error'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.stockRecyclerView = null;
    target.swipeRefreshLayout = null;
    target.error = null;

    this.target = null;
  }
}
