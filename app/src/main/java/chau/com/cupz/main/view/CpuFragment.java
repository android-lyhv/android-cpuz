package chau.com.cupz.main.view;

import android.content.Context;
import android.content.Intent;
import android.opengl.GLSurfaceView;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import chau.com.cupz.R;
import chau.com.cupz.main.bean.Cpu;

/**
 * Created by HoVanLy on 5/30/2016.
 */
public class CpuFragment extends Fragment {
    private TextView model;
    private TextView cores;
    private TextView architecture;
    private TextView revision;
    private TextView process;
    private TextView clock_speed;
    private TextView cpu_load;
    private TextView gpu_vendor;
    private TextView gpu_renderer;
    private TextView gpu_clock_speed;
    private Cpu cpu;
    private ArrayList<String> array = new ArrayList<>();
    private GLSurfaceView mGLView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.cpu, container, false);
        model = (TextView) view.findViewById(R.id.model);
        cores = (TextView) view.findViewById(R.id.cores);
        architecture = (TextView) view.findViewById(R.id.architecture);
        revision = (TextView) view.findViewById(R.id.revision);
        process = (TextView) view.findViewById(R.id.process);
        clock_speed = (TextView) view.findViewById(R.id.clock_speed);
        cpu_load = (TextView) view.findViewById(R.id.cpu_load);
        gpu_renderer = (TextView) view.findViewById(R.id.gpu_renderer);
        gpu_vendor = (TextView) view.findViewById(R.id.gpu_vendor);
        gpu_clock_speed = (TextView) view.findViewById(R.id.gpu_clock_speed);
        loadAboutDevice();
        mGLView = new GLSurfaceView(getContext());
        mGLView.setRenderer(mGlRenderer);
        setView();
        return view;
    }

    private void setView() {
        model.setText(cpu.getModel());
        cores.setText(cpu.getCores());
        architecture.setText(cpu.getArchitecture());
        revision.setText(cpu.getRevision());
        process.setText(cpu.getProcess());
        clock_speed.setText(cpu.getClock_speed());
        cpu_load.setText(cpu.getCpu_load());
        gpu_renderer.setText(cpu.getGpu_renderer());
        gpu_vendor.setText(cpu.getGpu_vendor());
        gpu_clock_speed.setText(cpu.getGpu_clock_speed());

    }

    private void loadAboutDevice() {
        cpu = new Cpu();
        int cores = Runtime.getRuntime().availableProcessors();
        cpu.setModel(Build.MANUFACTURER);
        cpu.setCores(String.valueOf(cores));
        cpu.setClock_speed(String.valueOf(getClockMinSpeed() / 1000) + " Mhz - " + String.valueOf(getClockMaxSpeed() / 1000000) + " GHz");
        cpu.setGpu_vendor(Build.HARDWARE);


    }

    private float getClockMaxSpeed() {
        String[] args = {"/system/bin/cat", "/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq"};
        ProcessBuilder builder = new ProcessBuilder(args);
        // set working directory
        if ("/system/bin/" != null)
            builder.directory(new File("/system/bin/"));
        builder.redirectErrorStream(true);
        Process process = null;
        try {
            process = builder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        InputStream in = process.getInputStream();
        String temp = "";
        byte[] re = new byte[1024];
        try {
            while (in.read(re) != -1) {
                temp = temp + new String(re) + "\n";
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Float.parseFloat(temp);

    }

    private float getClockMinSpeed() {
        String[] args = {"/system/bin/cat", "/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_min_freq"};
        ProcessBuilder builder = new ProcessBuilder(args);
        // set working directory
        if ("/system/bin/" != null)
            builder.directory(new File("/system/bin/"));
        builder.redirectErrorStream(true);
        Process process = null;
        try {
            process = builder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        InputStream in = process.getInputStream();
        String temp = "";
        byte[] re = new byte[1024];
        try {
            while (in.read(re) != -1) {
                temp = temp + new String(re) + "\n";
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Float.parseFloat(temp);

    }

    @Override
    public void onResume() {
        super.onResume();
    }
    private GLSurfaceView.Renderer mGlRenderer = new GLSurfaceView.Renderer() {

        @Override
        public void onSurfaceCreated(GL10 gl, EGLConfig config) {// TODO Auto-generated method stub
           cpu.setGpu_renderer(gl.glGetString(GL10.GL_RENDERER));
            Toast.makeText(getContext(), "ok", Toast.LENGTH_LONG).show();
           }

        @Override
        public void onSurfaceChanged(GL10 gl, int width, int height) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onDrawFrame(GL10 gl) {
            // TODO Auto-generated method stub

        }
    };

}
