package chau.com.cupz.main.view;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import chau.com.cupz.R;
import chau.com.cupz.main.bean.Battery;

/**
 * Created by HoVanLy on 5/30/2016.
 */
public class BatteryFragment extends Fragment {
    private TextView health;
    private TextView level;
    private TextView power;
    private TextView status;
    private TextView technology;
    private TextView temperature;
    private TextView voltage;
    private TextView capacity;
    private Battery battery;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.battery, container, false);
        health = (TextView) view.findViewById(R.id.health);
        level = (TextView) view.findViewById(R.id.level);
        status = (TextView) view.findViewById(R.id.status);
        power = (TextView) view.findViewById(R.id.power);
        voltage = (TextView) view.findViewById(R.id.voltage);
        technology = (TextView) view.findViewById(R.id.technology);
        temperature = (TextView) view.findViewById(R.id.temperature);
        capacity = (TextView) view.findViewById(R.id.capacity);
        loadAboutDevice();
        getActivity().registerReceiver(this.battery_receiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        setView();
        return view;
    }

    private void setView() {
        health.setText(battery.getHealth());
        level.setText(battery.getLevel() + " %");
        status.setText(battery.getStatus());
        power.setText(battery.getPower());
        voltage.setText(String.valueOf(battery.getVoltage()) + " mV");
        capacity.setText(String.valueOf(battery.getCapacity()) + " mAh");
        technology.setText(battery.getTechnology());
        temperature.setText(String.valueOf(battery.getTemperature()));

    }

    private void loadAboutDevice() {
        battery = new Battery();

    }

    private BroadcastReceiver battery_receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            boolean isPresent = intent.getBooleanExtra("present", false);
            String technology = intent.getStringExtra("technology");
            int plugged = intent.getIntExtra("plugged", -1);
            int scale = intent.getIntExtra("scale", -1);
            int health = intent.getIntExtra("health", 0);
            int status = intent.getIntExtra("status", 0);
            int rawlevel = intent.getIntExtra("level", -1);
            int voltage = intent.getIntExtra("voltage", 0);
            int temperature = intent.getIntExtra("temperature", 0);
            int capacity = intent.getIntExtra("capacity", 0);
            int level = 0;
            if (isPresent) {
                if (rawlevel >= 0 && scale > 0) {
                    level = (rawlevel * 100) / scale;
                }
                battery.setLevel(level);
                battery.setTechnology(technology);
                battery.setPower(getPlugTypeString(plugged));
                battery.setHealth(getHealthString(health));
                battery.setStatus(getStatusString(status));
                battery.setVoltage(voltage);
                battery.setTemperature(temperature);
                battery.setCapacity(capacity);

                setView();
            } else {

            }
        }
    };

    @Override
    public void onResume() {
        super.onResume();
        getActivity().registerReceiver(this.battery_receiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
    }

    private String getPlugTypeString(int plugged) {
        String plugType = "Unknown";
        switch (plugged) {
            case BatteryManager.BATTERY_PLUGGED_AC:
                plugType = "AC";
                break;
            case BatteryManager.BATTERY_PLUGGED_USB:
                plugType = "USB";
                break;
        }
        return plugType;
    }

    private String getHealthString(int health) {
        String healthString = "Unknown";

        switch (health) {
            case BatteryManager.BATTERY_HEALTH_DEAD:
                healthString = "Dead";
                break;
            case BatteryManager.BATTERY_HEALTH_GOOD:
                healthString = "Good";
                break;
            case BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE:
                healthString = "Over Voltage";
                break;
            case BatteryManager.BATTERY_HEALTH_OVERHEAT:
                healthString = "Over Heat";
                break;
            case BatteryManager.BATTERY_HEALTH_UNSPECIFIED_FAILURE:
                healthString = "Failure";
                break;
        }

        return healthString;
    }

    private String getStatusString(int status) {
        String statusString = "Unknown";

        switch (status) {
            case BatteryManager.BATTERY_STATUS_CHARGING:
                statusString = "Charging";
                break;
            case BatteryManager.BATTERY_STATUS_DISCHARGING:
                statusString = "Discharging";
                break;
            case BatteryManager.BATTERY_STATUS_FULL:
                statusString = "Full";
                break;
            case BatteryManager.BATTERY_STATUS_NOT_CHARGING:
                statusString = "Not Charging";
                break;
        }
        return statusString;
    }
}
