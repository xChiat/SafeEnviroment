package com.example.safeenviroment;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import java.util.List;

public class CheckableAdapter extends ArrayAdapter<String> {
    private List<String> items;
    private boolean[] checkedStates;

    public CheckableAdapter(Context context, List<String> items) {
        super(context, R.layout.list_item_checkable, items);
        this.items = items;
        this.checkedStates = new boolean[items.size()];
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_checkable, parent, false);
        }

        CheckBox checkBox = convertView.findViewById(R.id.checkBox);
        checkBox.setText(items.get(position));
        checkBox.setChecked(checkedStates[position]);

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                checkedStates[position] = isChecked;
            }
        });

        return convertView;
    }
}