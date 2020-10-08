package com.apress.springrecipes.shop;

public class Battery extends Product {
    private Boolean rechargeable;

    public Battery() {
        super();
    }

    public Battery(String name, Double price) {
        super(name, price);
    }

    public Boolean getRechargeable() {
        return rechargeable;
    }

    public void setRechargeable(Boolean rechargeable) {
        this.rechargeable = rechargeable;
    }

}
