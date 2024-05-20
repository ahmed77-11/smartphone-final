package com.ahmed.smartphonejava.dao;

import java.util.List;

public interface ISmartphoneDao <T>{
    public List<T> getAllSmartphones();
    public T getSmartphonesById(int id);

    public List<T> getSmartphonesByCat(int catId);
    public List<T> getSmartphonesByBrand(String mc);
    public boolean addSmartphone(T smartphone);
    public boolean updateSmartphone(T smartphone);
    public boolean deleteSmartphone(T smartphone);
}
