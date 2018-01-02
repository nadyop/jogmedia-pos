package com.blibli.Config;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class AppConfig{
    public static DriverManagerDataSource dataSource() {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName("org.postgresql.Driver");
        driverManagerDataSource.setUrl("jdbc:postgresql://ec2-54-235-148-19.compute-1.amazonaws.com:5432/dc30q5j3noe3mj");
        driverManagerDataSource.setUsername("asmerpqvzuwdmg");
        driverManagerDataSource.setPassword("018e4696ce272a12ec7dcf4422a44b9b772204f58afaa1ce9d2c2e21781689fb");
        return driverManagerDataSource;
    }
}
