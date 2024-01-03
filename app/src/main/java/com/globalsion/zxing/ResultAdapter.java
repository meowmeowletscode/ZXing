package com.globalsion.zxing;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.mlkit.vision.barcode.common.Barcode;

import java.util.ArrayList;
import java.util.List;

public class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.ResultViewHolder> {

    private List<Barcode> barcodes;

    public ResultAdapter() {
        this.barcodes = new ArrayList<>();
    }

    public void setBarcodes(List<Barcode> barcodes) {
        this.barcodes = barcodes;
        notifyDataSetChanged();
    }

    public void addBarcode(Barcode barcode) {
        barcodes.add(barcode);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ResultViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        return new ResultViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ResultViewHolder holder, int position) {
        holder.bind(barcodes.get(position));
    }

    @Override
    public int getItemCount() {
        return barcodes.size();
    }

    static class ResultViewHolder extends RecyclerView.ViewHolder {
        private final TextView resultTextView;

        public ResultViewHolder(@NonNull View itemView) {
            super(itemView);
            resultTextView = itemView.findViewById(R.id.resultTv);
        }

        public void bind(Barcode barcode) {
            // Extract relevant information from barcode and display it in the TextView
            String resultText = extractBarcodeInfoAsString(barcode);
            resultTextView.setText(resultText);
        }

        // Add a method to extract barcode information as a string
        private String extractBarcodeInfoAsString(Barcode barcode) {
            return "Barcode Info: \n" + barcode.getRawValue();
        }
    }

    public void clearResults() {
        barcodes.clear();
        notifyDataSetChanged();
    }

}

