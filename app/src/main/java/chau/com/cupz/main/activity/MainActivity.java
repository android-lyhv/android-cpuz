package chau.com.cupz.main.activity;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import android.widget.Toast;

import chau.com.cupz.R;
import chau.com.cupz.main.view.BatteryFragment;
import chau.com.cupz.main.view.CpuFragment;
import chau.com.cupz.main.view.DeviceFragment;
import chau.com.cupz.main.view.SystemFragment;
import chau.com.cupz.main.view.ViewPagerAdapter;

public class MainActivity extends AppCompatActivity {
private BatteryFragment batteryFragment;
    private CpuFragment cpuFragment;
    private SystemFragment systemFragment;
    private DeviceFragment deviceFragment;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityManager.MemoryInfo mi = new ActivityManager.MemoryInfo();
        ActivityManager activityManager = (ActivityManager) this.getSystemService(Context.ACTIVITY_SERVICE);
        activityManager.getMemoryInfo(mi);
        long availableMegs = mi.availMem / 1048576L;
        String  details = "VERSION.RELEASE : " + Build.VERSION.RELEASE
                +"\nVERSION.INCREMENTAL : "+Build.VERSION.INCREMENTAL
                + "\nVERSION.SDK.NUMBER : "+Build.VERSION.SDK_INT
                +"\nBOARD : "+Build.BOARD
                +"\nBOOTLOADER : "+Build.BOOTLOADER
                +"\nBRAND : "+Build.BRAND
                +"\nCPU_ABI : "+Build.CPU_ABI
                +"\nCPU_ABI2 : "+Build.CPU_ABI2
                +"\nDISPLAY : "+Build.DISPLAY
                +"\nFINGERPRINT : "+Build.FINGERPRINT
                +"\nHARDWARE : "+Build.HARDWARE
                +"\nHOST : "+Build.HOST
                +"\nID : "+Build.ID
                +"\nMANUFACTURER : "+Build.MANUFACTURER
                +"\nMODEL : "+Build.MODEL
                +"\nPRODUCT : "+Build.PRODUCT
                +"\nSERIAL : "+Build.SERIAL
                +"\nTAGS : "+Build.TAGS
                +"\nTIME : "+Build.TIME
                +"\nTYPE : "+Build.TYPE
                +"\nRam : "+availableMegs
                +"\nProcess : "+Runtime.getRuntime().availableProcessors()
                +"\nUSER : "+Build.USER;


        TextView textView = new TextView(this);
        textView.setText(details);
        setContentView(R.layout.activity_main);
        //
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        batteryFragment = new BatteryFragment();
        cpuFragment = new CpuFragment();
        systemFragment = new SystemFragment();
        deviceFragment = new DeviceFragment();
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupNormalViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
    }
    private void setupNormalViewPager(ViewPager viewPager) {
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(cpuFragment, "CPU");
        adapter.addFragment(deviceFragment, "Device");
        adapter.addFragment(systemFragment, "System");
        adapter.addFragment(batteryFragment, "Battery");
        viewPager.setAdapter(adapter);
    }

}
