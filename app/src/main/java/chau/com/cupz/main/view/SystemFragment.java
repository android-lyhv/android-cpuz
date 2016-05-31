package chau.com.cupz.main.view;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ConfigurationInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import chau.com.cupz.R;
import chau.com.cupz.main.bean.*;
import chau.com.cupz.main.bean.System;

/**
 * Created by HoVanLy on 5/30/2016.
 */
public class SystemFragment extends Fragment {
    private TextView android_version;
    private TextView api_level;
    private TextView bootloader;
    private TextView build_id;
    private TextView java_vm;
    private TextView openGl_es;
    private TextView kernel_architecture;
    private TextView kernel_version;
    private TextView root_access;
    private System system;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.system, container, false);
        android_version = (TextView) view.findViewById(R.id.android_version);
        api_level = (TextView) view.findViewById(R.id.api_level);
        bootloader = (TextView) view.findViewById(R.id.bootloader);
        build_id = (TextView) view.findViewById(R.id.build_id);
        java_vm = (TextView) view.findViewById(R.id.java_vm);
        openGl_es = (TextView) view.findViewById(R.id.openGl_es);
        kernel_architecture = (TextView) view.findViewById(R.id.kernel_architecture);
        kernel_version = (TextView) view.findViewById(R.id.kernel_version);
        root_access = (TextView) view.findViewById(R.id.root_access);
        loadAboutSystem();
        setView();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void loadAboutSystem() {
        system = new System();
        // set android version
        system.setAndroid_version(Build.VERSION.RELEASE);
        // set api_level
        system.setApi_level(String.valueOf(Build.VERSION.SDK_INT));
        // set bootloader
        system.setBootloader(Build.BOOTLOADER);
        // set build_id
        system.setBuild_id(Build.ID);
        // set java_vm
        String vmVersion = java.lang.System.getProperty("java.vm.version");
        system.setJava_vm(vmVersion);
        // set openGl_es
        final ActivityManager activityManager =
                (ActivityManager) getActivity().getSystemService(Context.ACTIVITY_SERVICE);
        final ConfigurationInfo configurationInfo =
                activityManager.getDeviceConfigurationInfo();
        String supportsEs = String.valueOf(configurationInfo.getGlEsVersion());
        system.setOpenGl_es(supportsEs);
        // set kernel_architecture

        FileReader fr = null;
        try {
            fr = new FileReader("/proc/cpuinfo");
            BufferedReader br = new BufferedReader(fr);
            String text = br.readLine();
            br.close();
            String[] array = text.split(":\\s+", 2);
            if (array.length >= 2) {
               system.setKernel_architecture(array[1]);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //
        String version = java.lang.System.getProperty("os.version");
        system.setKernel_version(version);
    }

    private void setView() {

        android_version.setText(system.getAndroid_version());
        api_level.setText(system.getApi_level());
        bootloader.setText(system.getBootloader());
        build_id.setText(system.getBuild_id());
        java_vm.setText(system.getJava_vm());
        openGl_es.setText(system.getOpenGl_es());
        kernel_architecture.setText(system.getKernel_architecture());
        kernel_version.setText(system.getKernel_version());
        root_access.setText(system.getRoot_access());
    }


}
