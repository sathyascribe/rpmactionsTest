package com.inventica.rpmapp.ui.fragment;

import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.buffer.BarBuffer;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.Transformer;
import com.inventica.rpmapp.R;
import com.inventica.rpmapp.ui.utils.CustomBarChartRender;

import java.util.ArrayList;
import java.util.List;

public class ActivityFragment extends Fragment {

    private ActivityFragment galleryViewModel;
    BarChart barChart;

    // variable for our bar data.
    BarData barData;

    // variable for our bar data set.
    BarDataSet barDataSet;

    // array list for storing entries.
    ArrayList barEntriesArrayList;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
      /*  galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);*/
        View root = inflater.inflate(R.layout.fragment_activity, container, false);
     //   barChart = root.findViewById(R.id.idBarChart);
       /* CustomBarChartRender barChartRender = new CustomBarChartRender(barChart,barChart.getAnimator(), barChart.getViewPortHandler());
        barChartRender.setRadius(20);
        barChart.setRenderer(barChartRender);*/
       barChart = root.findViewById(R.id.idBarChart);

        // calling method to get bar entries.
        getBarEntries();

        // creating a new bar data set.
        barDataSet = new BarDataSet(barEntriesArrayList, "Geeks for Geeks");

        // creating a new bar data and
        // passing our bar data set.
        barData = new BarData(barDataSet);

        // below line is to set data
        // to our bar chart.
        barChart.setData(barData);

        // adding color to our bar data set.
        barDataSet.setColors(R.color.barchart_orange);

        // setting text color.
        barDataSet.setValueTextColor(Color.WHITE);

        // setting text size
        barDataSet.setValueTextSize(16f);
        barChart.getDescription().setEnabled(false);

    /*    BarChart chart = (BarChart) root.findViewById(R.id.idBarChart);

        // replace
        ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry (1, 5));
        entries.add(new BarEntry (3, 7));
        entries.add(new BarEntry (5,3));
        entries.add(new BarEntry (7,4));
        entries.add(new BarEntry (9,1));
        BarDataSet dataset = new BarDataSet(entries, "First");
        dataset.setColors(new int[] {R.color.orange, R.color.orange, R.color.orange, R.color.orange,R.color.orange});
        BarData data = new BarData(dataset);
        chart.setData(data);*/
        // replace



        // below is simply styling decisions on code that I have)
   /*     YAxis left = chart.getAxisLeft();
        left.setAxisMaxValue(10);//dataset.getYMax()+2);
        left.setAxisMinValue(0);
        chart.getAxisRight().setEnabled(false);
        XAxis bottomAxis = chart.getXAxis();
        bottomAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        bottomAxis.setAxisMinValue(0);

        bottomAxis.setLabelCount(10);
        bottomAxis.setAxisMaxValue(10);
        bottomAxis.setDrawGridLines(false);
        chart.setDrawValueAboveBar(false);
       // chart.setDescription();
        // legend
        Legend legend = chart.getLegend();
        legend.setYOffset(40);
        //legend.setPosition(Legend.LegendPosition.BELOW_CHART_CENTER);
        legend.setTextSize(200);*/
        return root;
    }
    private void getBarEntries() {
        // creating a new array list
        barEntriesArrayList = new ArrayList<>();

        // adding new entry to our array list with bar
        // entry and passing x and y axis value to it.
        barEntriesArrayList.add(new BarEntry(1f, 4));
        barEntriesArrayList.add(new BarEntry(2f, 6));
        barEntriesArrayList.add(new BarEntry(3f, 8));
        barEntriesArrayList.add(new BarEntry(4f, 2));
        barEntriesArrayList.add(new BarEntry(5f, 4));
        barEntriesArrayList.add(new BarEntry(6f, 1));
    }

}