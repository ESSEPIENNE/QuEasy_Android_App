package com.essepienne.mallin;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.SingleValueDataSet;
import com.anychart.charts.LinearGauge;
import com.anychart.enums.Anchor;
import com.anychart.enums.Layout;
import com.anychart.enums.MarkerType;
import com.anychart.enums.Orientation;
import com.anychart.enums.Position;
import com.anychart.scales.OrdinalColor;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class NegozioAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Negozio> Negozi;

    public NegozioAdapter(Context context, ArrayList Negozi) {
        this.context = context;
        this.Negozi = Negozi;
    }

    @Override
    public int getCount() {
        return Negozi.size();
    }

    @Override
    public Negozio getItem(int position) {
        return Negozi.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).
                    inflate(R.layout.negozio, parent, false);
        }
        Negozio currentNegozio = getItem(position);
        TextView nome = convertView.findViewById(R.id.nameShop);
//        ProgressBar pb = convertView.findViewById(R.id.progressBar);
//        pb.setMax(currentNegozio.getMax_in_store() + currentNegozio.getMax_queue());
//        pb.setProgress(currentNegozio.getCurrent_in_store());
//        pb.setSecondaryProgress(currentNegozio.getCurrent_queue() + currentNegozio.getCurrent_in_store());

        AnyChartView anyChartView = convertView.findViewById(R.id.barChart);
//        anyChartView.setProgressBar(convertView.findViewById(R.id.progressBar));

        LinearGauge linearGauge = AnyChart.linear();

        linearGauge.data(new SingleValueDataSet(new Double[] { 5.3D }));

        linearGauge.layout(Layout.HORIZONTAL);

        OrdinalColor scaleBarColorScale = OrdinalColor.instantiate();
        scaleBarColorScale.ranges(new String[]{
                "{ from: 0, to: 7, color: ['green 0.5'] }",
                "{ from: 7, to: 8, color: ['yellow 0.5'] }",
                "{ from: 8, to: 10, color: ['red 0.5'] }"
        });

        linearGauge.scaleBar(0)
                .width("5%")
                .colorScale(scaleBarColorScale);

        linearGauge.marker(0)
                .type(MarkerType.TRIANGLE_DOWN)
                .color("red")
                .offset("-3.5%")
                .zIndex(10);

        linearGauge.scale()
                .minimum(0)
                .maximum(10);
       linearGauge.scale();

        linearGauge.axis(0)
                .minorTicks(false)
                .width("1%");
        linearGauge.axis(0)
                .offset("-1.5%")
                .orientation(Orientation.TOP)
                .labels("top");

        linearGauge.padding(0, 30, 0, 30);

        anyChartView.setChart(linearGauge);

        ImageView logo = convertView.findViewById(R.id.ImmagineNegozio);
        Picasso.get()
                .load(currentNegozio.getImmagine())
                .resize(100, 100)
                .centerCrop()
                .into(logo);


        nome.setText(currentNegozio.getNome());

        return convertView;
    }
}
