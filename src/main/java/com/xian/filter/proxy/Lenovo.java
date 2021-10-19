package com.xian.filter.proxy;

public class Lenovo implements SaleComputer{
    @Override
    public String sale(Double money) {
        return "买电脑花了"+money+"元！";
    }

    @Override
    public String fix() {
        return "Your computer has been fixed...";
    }
}
