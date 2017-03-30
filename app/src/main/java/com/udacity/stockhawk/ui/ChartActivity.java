package com.udacity.stockhawk.ui;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.udacity.stockhawk.R;
import com.udacity.stockhawk.data.Contract;

import java.util.ArrayList;
import java.util.List;

public class ChartActivity extends AppCompatActivity {

    String symbol;
    String history;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);
        BarChart lineChart = (BarChart) findViewById(R.id.chart);
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("symbol")) {
            symbol = intent.getStringExtra("symbol");
            Cursor c = getContentResolver().query(Contract.Quote.makeUriForStock(symbol), null, null, null, null);

            if (c != null) {
                c.moveToFirst();
                history = c.getString(c.getColumnIndex(Contract.Quote.COLUMN_HISTORY));
            }

            if (!history.isEmpty()) {
                List<BarEntry> entries = new ArrayList<BarEntry>();
                String hist[] = history.split("\n");
                ArrayList<String> xLabels = new ArrayList<>();

                for (int i = 0; i < hist.length; i++) {
                    String point[] = hist[i].split(",");
                    xLabels.add(convertEpochToDate(Long.parseLong(point[0])));
                    entries.add(new BarEntry(i, Float.parseFloat(point[1])));
                }

                BarDataSet dataSet = new BarDataSet(entries, "Date");
                dataSet.setColor(Color.YELLOW);
                dataSet.setValueTextColor(Color.CYAN);
                BarData lineData = new BarData(dataSet);

                XAxis xAxis = lineChart.getXAxis();
                xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
                xAxis.setLabelCount(3);
                xAxis.setGranularity(1f);
                xAxis.setValueFormatter(new MyXAxisValueFormatter(xLabels));

                lineChart.setData(lineData);
                lineChart.invalidate();
            }
        }

    }

    private String convertEpochToDate(long epoch) {
        return new java.text.SimpleDateFormat("MM/dd/yyyy").format(new java.util.Date(epoch));
    }
}

class MyXAxisValueFormatter implements IAxisValueFormatter {

    ArrayList<String> xLabels;

    public MyXAxisValueFormatter(ArrayList<String> xLables) {
        this.xLabels = xLables;
    }

    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        return xLabels.get((int) value);
    }
}
