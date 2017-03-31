package com.udacity.stockhawk.ui;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.udacity.stockhawk.R;
import com.udacity.stockhawk.data.Contract;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChartActivity extends AppCompatActivity {

    @BindView(R.id.chart)
    LineChart lineChart;

    @BindView(R.id.tv_yaxis_label)
    TextView yAxisLabel;

    String symbol;
    String history;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);
        ButterKnife.bind(this);

        // get symbol from parent intent
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("symbol")) {
            symbol = intent.getStringExtra("symbol");
            Cursor c = getContentResolver().query(Contract.Quote.makeUriForStock(symbol), null, null, null, null);

            // initialize cursor
            if (c != null) {
                c.moveToFirst();
                history = c.getString(c.getColumnIndex(Contract.Quote.COLUMN_HISTORY));
                c.close();
            }

            if (!history.isEmpty()) {
//                RotateAnimation rotate = (RotateAnimation) AnimationUtils.loadAnimation(this, R.anim.rotate);
//                rotate.setFillAfter(true); //For the textview to remain at the same place after the rotation
//                yAxisLabel.setAnimation(rotate);

                List<Entry> entries = new ArrayList<>();
                String hist[] = history.split("\n");
                ArrayList<String> xLabels = new ArrayList<>();

                // extract values and labels
                for (int i = 0; i < hist.length; i++) {
                    String point[] = hist[i].split(",");
                    xLabels.add(convertEpochToDate(Long.parseLong(point[0])));
                    entries.add(new Entry(i, Float.parseFloat(point[1])));
                }

                // customize dataset
                LineDataSet dataSet = new LineDataSet(entries, "Stock Value");
                dataSet.setColor(Color.YELLOW);
                dataSet.setValueTextColor(Color.CYAN);
                LineData lineData = new LineData(dataSet);

                // set x-axis data
                XAxis xAxis = lineChart.getXAxis();
                xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
                xAxis.setLabelCount(3);
                xAxis.setGranularity(1f);
                // add custom x-axis labels
                xAxis.setValueFormatter(new MyXAxisValueFormatter(xLabels));

                // customize line chart
                lineChart.getAxisLeft().setTextColor(Color.WHITE);
                lineChart.getAxisRight().setTextColor(Color.WHITE);
                lineChart.getXAxis().setTextColor(Color.WHITE);
                lineChart.getLegend().setTextColor(Color.WHITE);
                lineChart.getDescription().setTextColor(Color.WHITE);
                lineChart.getDescription().setText(symbol);

                // show data
                lineChart.setData(lineData);
                lineChart.invalidate();
            }
        }

    }

    /**
     * Convert time in epoch format to a human readable format
     *
     * @param epoch in milliseconds
     * @return String date of an epoch time
     */
    private String convertEpochToDate(long epoch) {
        return new java.text.SimpleDateFormat("MM/dd/yyyy").format(new java.util.Date(epoch));
    }
}

class MyXAxisValueFormatter implements IAxisValueFormatter {

    private ArrayList<String> xLabels;

    public MyXAxisValueFormatter(ArrayList<String> xLables) {
        this.xLabels = xLables;
    }

    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        return xLabels.get((int) value);
    }
}
