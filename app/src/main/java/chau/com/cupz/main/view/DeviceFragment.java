package chau.com.cupz.main.view;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import chau.com.cupz.R;
import chau.com.cupz.main.bean.Device;

/**
 * Created by HoVanLy on 5/30/2016.
 */
public class DeviceFragment extends Fragment {
    private Device device;
    private TextView model;
    private TextView brand;
    private TextView board;
    private TextView screen_size;
    private TextView screen_resolution;
    private TextView screen_density;
    private TextView total_ram;
    private TextView available_ram;
    private TextView internal_storage;
    private TextView available_storage;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.device, container, false);
        model = (TextView) view.findViewById(R.id.model);
        brand = (TextView) view.findViewById(R.id.brand);
        board = (TextView) view.findViewById(R.id.board);
        screen_size = (TextView) view.findViewById(R.id.screen_size);
        screen_resolution = (TextView) view.findViewById(R.id.screen_resolution);
        screen_density = (TextView) view.findViewById(R.id.screen_density);
        total_ram = (TextView) view.findViewById(R.id.total_ram);
        available_ram = (TextView) view.findViewById(R.id.available_ram);
        internal_storage = (TextView) view.findViewById(R.id.internal_storage);
        available_storage = (TextView) view.findViewById(R.id.available_storage);
        loadAboutDevice();
        setView();
        return view;
    }

    public void loadAboutDevice() {
        device = new Device();
        // set model
        device.setModel(Build.MODEL);
        // set brand
        device.setBrand(Build.BRAND);
        // set board
        device.setBoard(Build.BOARD);
        // set Screen size
        DisplayMetrics displayMetrics = getActivity().getResources().getDisplayMetrics();
        float yInches = displayMetrics.heightPixels / displayMetrics.ydpi;
        float xInches = displayMetrics.widthPixels / displayMetrics.xdpi;
        double diagonalInches = Math.sqrt(xInches * xInches + yInches * yInches);
        device.setScreen_size(diagonalInches);
        // set screen resolution
        DisplayMetrics metrics1 = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics1);
        device.setScreen_resolution(metrics1.heightPixels + " x " + metrics1.widthPixels + " pixels");
        // set density
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        int densityDpi = (int) (metrics.density * 160f);
        device.setScreen_density(densityDpi);
        // set available ram
        ActivityManager.MemoryInfo mi = new ActivityManager.MemoryInfo();
        ActivityManager activityManager = (ActivityManager) getActivity().getSystemService(Context.ACTIVITY_SERVICE);
        activityManager.getMemoryInfo(mi);
        float availableMegs = mi.availMem / 1048576L;
        device.setAvailable_ram(availableMegs);
        // set total ram
        float total_ram_ = mi.totalMem / 1048576L;
        device.setTotal_ram(total_ram_);
        // set internal_storage
       // StatFs statFs = new StatFs(Environment.getRootDirectory().getAbsolutePath());
        StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getAbsolutePath());
        float blockSize = statFs.getBlockSize();
        float totalSize = statFs.getBlockCount() * blockSize / 1073741824;
        device.setInternal_storage(totalSize);
        float availableSize = statFs.getAvailableBlocks() * blockSize / 1073741824;
        device.setAvailable_storage(availableSize);
        float freeSize = statFs.getFreeBlocks() * blockSize;

    }

    public void setView() {
        model.setText(device.getModel());
        brand.setText(device.getBrand());
        board.setText(device.getBoard());
        screen_size.setText(device.getScreen_size() + " inches");
        screen_resolution.setText(device.getScreen_resolution());
        screen_density.setText(device.getScreen_density() + " dpi");
        total_ram.setText(device.getTotal_ram() + " MB");
        available_ram.setText(device.getAvailable_ram() + " MB (" + device.getAvailable_ram() / device.getTotal_ram() * 100 + " %)");
        internal_storage.setText(device.getInternal_storage() + " GB");
        available_storage.setText(device.getAvailable_storage() + " GB (" + device.getAvailable_storage() / device.getInternal_storage() * 100 + " %)");
    }




    @Override
    public void onResume() {
        super.onResume();
        ActivityManager.MemoryInfo mi = new ActivityManager.MemoryInfo();
        ActivityManager activityManager = (ActivityManager) getActivity().getSystemService(Context.ACTIVITY_SERVICE);
        activityManager.getMemoryInfo(mi);
        float availableMegs = mi.availMem / 1048576L;
        device.setAvailable_ram(availableMegs);
        available_ram.setText(device.getAvailable_ram() + " MB (" + device.getAvailable_ram() / device.getTotal_ram() * 100 + " %)");
        // StatFs statFs = new StatFs(Environment.getRootDirectory().getAbsolutePath());
        StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getAbsolutePath());
        float blockSize = statFs.getBlockSize();
        float totalSize = statFs.getBlockCount() * blockSize / 1073741824;
        device.setInternal_storage(totalSize);
        float availableSize = statFs.getAvailableBlocks() * blockSize / 1073741824;
        device.setAvailable_storage(availableSize);
        float freeSize = statFs.getFreeBlocks() * blockSize;
        available_storage.setText(device.getAvailable_storage() + " GB (" + device.getAvailable_storage() / device.getInternal_storage() * 100 + " %)");
    }
}
