package com.example.insyte;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class GraphDiet extends AppCompatActivity implements OnChartGestureListener, OnChartValueSelectedListener {

    private static final String TAG = "GraphDiet";

    private LineChart dietChart;

    private ArrayList<Answers> userAnswers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph_diet);
        dietChart = (LineChart) findViewById(R.id.dietChart);

        SharedPreferences sharedPreferences = getSharedPreferences("User Answers", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("User Answers", null);
        Type type = new TypeToken<ArrayList<Answers>>() {}.getType();
        userAnswers = gson.fromJson(json, type);

        dietChart.setOnChartGestureListener(GraphDiet.this);
        dietChart.setOnChartValueSelectedListener(GraphDiet.this);

        dietChart.setDragEnabled(true);
        dietChart.setScaleEnabled(false);

        ArrayList<Entry> yValues = new ArrayList<>();

        String[] xValues = new String[7];
        Float[] yScores = new Float[7];

        while(userAnswers.size() > 7){
            userAnswers.remove(0);
        }

        for(int i = 0; i < userAnswers.size(); i++){
            xValues[i] = userAnswers.get(i).getDate();
            yScores[i] = Float.valueOf(userAnswers.get(i).getA5());
        }

        for(int i = 0; i < xValues.length; i++){
            if(xValues[i] == null){
                xValues[i] = "No Data";
            }
            if(yScores[i] == null){
                yScores[i] = 0f;
            }
        }

        for(int i = 0; i < xValues.length; i++){
            yValues.add(new Entry(i+1, yScores[i]));
        }

        LineDataSet set1 = new LineDataSet(yValues, "Healthiness Level");
        set1.setFillAlpha(110);

        set1.setColor(R.color.blue_2);
        set1.setLineWidth(2f);
        set1.setCircleRadius(6f);
        set1.setCircleColor(R.color.blue_3);
        set1.setCircleHoleRadius(5f);
        set1.setCircleColorHole(R.color.blue_3);
        set1.setDrawValues(false);

        dietChart.getAxisRight().setEnabled(false);
        dietChart.getLowestVisibleX();
        dietChart.setBorderColor(R.color.blue_2);
        dietChart.setBorderWidth(2f);

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(set1);

        LineData data = new LineData(dataSets);
        dietChart.setData(data);

        XAxis xAxis = dietChart.getXAxis();
        xAxis.setValueFormatter(new GraphDiet.XAxisValues(xValues));
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

        YAxis yAxis = dietChart.getAxisLeft();
        yAxis.setAxisMinimum(0);
        yAxis.setAxisMaximum(5);
        yAxis.setDrawZeroLine(true);
        yAxis.setLabelCount(6);

        Description des = new Description();
        des.setText("Diet");
        des.setTextColor(R.color.blue_2);
        des.setTextSize(20);
        dietChart.setDescription(des);
    }

    @Override
    public void onChartGestureStart(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {
        Log.i(TAG, "onChartGestureStart: X: " + me.getX() + "Y: " + me.getY());
    }

    @Override
    public void onChartGestureEnd(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {
        Log.i(TAG, "onChartGestureEnd: " + lastPerformedGesture);
    }

    @Override
    public void onChartLongPressed(MotionEvent me) {
        Log.i(TAG, "onChartLongPressed");
    }

    @Override
    public void onChartDoubleTapped(MotionEvent me) {
        Log.i(TAG, "onChartDoubleTapped");
    }

    @Override
    public void onChartSingleTapped(MotionEvent me) {
        Log.i(TAG, "onChartSingleTapped");
    }

    @Override
    public void onChartFling(MotionEvent me1, MotionEvent me2, float velocityX, float velocityY) {
        Log.i(TAG, "onChartFling: veloX: "+ velocityX + "veloY: " + velocityY);
    }

    @Override
    public void onChartScale(MotionEvent me, float scaleX, float scaleY) {
        Log.i(TAG, "onChartScale: ScaleX: " + scaleX + "scaleY" + scaleY);
    }

    @Override
    public void onChartTranslate(MotionEvent me, float dX, float dY) {
        Log.i(TAG, "onChartTranslate: dX: " + dX + "dY: " + dY);
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {
        Log.i(TAG, "onValueSelected: " + e.toString());
    }

    @Override
    public void onNothingSelected() {
        Log.i(TAG, "onNothingSelected: ");
    }

    public class XAxisValues implements IAxisValueFormatter {
        private String[] mValues;
        public XAxisValues(String[] values){
            this.mValues = values;
        }

        @Override
        public String getFormattedValue(float value, AxisBase axis) {
            axis.setLabelCount(7, true);
            return mValues[(int) value-1];
        }
    }
}