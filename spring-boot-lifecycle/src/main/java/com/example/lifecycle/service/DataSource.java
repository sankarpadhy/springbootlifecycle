package com.example.lifecycle.service;

public interface DataSource {
    void connect();
    void disconnect();
    boolean isConnected();
}
