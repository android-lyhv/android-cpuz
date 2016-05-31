package chau.com.cupz.main.bean;

/**
 * Created by HoVanLy on 5/30/2016.
 */
public class Device {
    private String model;
    private String brand;
    private String board;
    private double screen_size;
    private String screen_resolution;
    private int screen_density;
    private String dimensions;
    private float total_ram;
    private float available_ram;
    private float internal_storage;
    private float available_storage;

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getBoard() {
        return board;
    }

    public void setBoard(String board) {
        this.board = board;
    }

    public double getScreen_size() {
        return screen_size;
    }

    public void setScreen_size(double screen_size) {
        this.screen_size = screen_size;
    }

    public String getScreen_resolution() {
        return screen_resolution;
    }

    public void setScreen_resolution(String screen_resolution) {
        this.screen_resolution = screen_resolution;
    }

    public int getScreen_density() {
        return screen_density;
    }

    public void setScreen_density(int screen_density) {
        this.screen_density = screen_density;
    }

    public String getDimensions() {
        return dimensions;
    }

    public void setDimensions(String dimensions) {
        this.dimensions = dimensions;
    }

    public float getTotal_ram() {
        return total_ram;
    }

    public void setTotal_ram(float total_ram) {
        this.total_ram = total_ram;
    }

    public float getAvailable_ram() {
        return available_ram;
    }

    public void setAvailable_ram(float available_ram) {
        this.available_ram = available_ram;
    }

    public float getInternal_storage() {
        return internal_storage;
    }

    public void setInternal_storage(float internal_storage) {
        this.internal_storage = internal_storage;
    }

    public float getAvailable_storage() {
        return available_storage;
    }

    public void setAvailable_storage(float available_storage) {
        this.available_storage = available_storage;
    }
}
