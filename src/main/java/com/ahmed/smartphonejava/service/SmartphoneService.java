package com.ahmed.smartphonejava.service;

import com.ahmed.smartphonejava.beans.Category;
import com.ahmed.smartphonejava.beans.Smartphone;
import com.ahmed.smartphonejava.connexion.Connexion;
import com.ahmed.smartphonejava.dao.ISmartphoneDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SmartphoneService implements ISmartphoneDao<Smartphone> {
    @Override
    public List<Smartphone> getAllSmartphones() {
        List<Smartphone> list=new ArrayList<>();
        String query="Select smartphone.id,brand,model,nom,price,smartphone.created_at,description,category.created_at,category.id as idC from gestion_smartphone.category,gestion_smartphone.smartphone where smartphone.catId=category.id";
        try {
            PreparedStatement ps= Connexion.connecter().prepareStatement(query);
            ResultSet rs =ps.executeQuery();
            while (rs.next()){
                Smartphone smartphone=new Smartphone();
                smartphone.setId(rs.getInt("smartphone.id"));
                smartphone.setBrand(rs.getString("brand"));
                smartphone.setModel(rs.getString("model"));
                smartphone.setPrice(rs.getDouble("price"));
                Category c=new Category();
                smartphone.setCategory(c);
                smartphone.getCategory().setId(rs.getInt("idC"));
                smartphone.getCategory().setNom(rs.getString("nom"));
                smartphone.getCategory().setDescription(rs.getString("description"));
                smartphone.getCategory().setCreate_at(rs.getDate("category.created_at"));
                smartphone.setCreated_at(rs.getDate("smartphone.created_at"));
                list.add(smartphone);
            }

        }catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Smartphone getSmartphonesById(int id) {
        return null;
    }

    @Override
    public List<Smartphone> getSmartphonesByCat(int catId) {
        List<Smartphone> list=new ArrayList<>();
        String query="select smartphone.id,brand,model,nom,price,smartphone.created_at,description,category.created_at,category.id as idC FROM  gestion_smartphone.category,gestion_smartphone.smartphone where smartphone.catId=category.id and category.id=?";
        try {
            PreparedStatement ps=Connexion.connecter().prepareStatement(query);
            ps.setInt(1,catId);
            ResultSet rs=ps.executeQuery();
            while (rs.next()){
                Smartphone smartphone=new Smartphone();
                smartphone.setId(rs.getInt("smartphone.id"));
                smartphone.setBrand(rs.getString("brand"));
                smartphone.setModel(rs.getString("model"));
                smartphone.setPrice(rs.getDouble("price"));
                Category c=new Category();
                smartphone.setCategory(c);
                smartphone.getCategory().setId(rs.getInt("idC"));
                smartphone.getCategory().setNom(rs.getString("nom"));
                smartphone.getCategory().setDescription(rs.getString("description"));
                smartphone.getCategory().setCreate_at(rs.getDate("category.created_at"));
                smartphone.setCreated_at(rs.getDate("smartphone.created_at"));
                list.add(smartphone);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Smartphone> getSmartphonesByBrand(String mc) {
        String query = "SELECT s.id,s.brand, s.model, s.price, s.created_at AS smartphone_created_at, " +
                "c.id AS idC, c.nom, c.description, c.created_at AS category_created_at " +
                "FROM gestion_smartphone.smartphone s " +
                "JOIN gestion_smartphone.category c ON s.catId = c.id " +
                "WHERE s.brand LIKE ?";

        List<Smartphone> list = new ArrayList<>();

        try (PreparedStatement ps = Connexion.connecter().prepareStatement(query)) {
            ps.setString(1, "%" + mc + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Smartphone smartphone = new Smartphone();
                smartphone.setId(rs.getInt("id"));
                smartphone.setBrand(rs.getString("brand"));
                smartphone.setModel(rs.getString("model"));
                smartphone.setPrice(rs.getDouble("price"));
                smartphone.setCreated_at(rs.getDate("smartphone_created_at"));

                Category category = new Category();
                category.setId(rs.getInt("idC"));
                category.setNom(rs.getString("nom"));
                category.setDescription(rs.getString("description"));
                category.setCreate_at(rs.getDate("category_created_at"));

                smartphone.setCategory(category);
                list.add(smartphone);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }


    @Override
    public boolean addSmartphone(Smartphone smartphone) {
        String q="insert into gestion_smartphone.smartphone (brand,model,price,catId) values (?,?,?,?)";
        try{
            PreparedStatement ps= Connexion.connecter().prepareStatement(q);
            ps.setString(1,smartphone.getBrand());
            ps.setString(2,smartphone.getModel());
            ps.setDouble(3,smartphone.getPrice());
            ps.setInt(4,smartphone.getCategory().getId());
            ps.executeUpdate();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateSmartphone(Smartphone smartphone) {
        String query="UPDATE gestion_smartphone.smartphone set brand=?,model=?,price=?,catId=? where id=? ";
        try {
            PreparedStatement ps=Connexion.connecter().prepareStatement(query);
            ps.setString(1,smartphone.getBrand());
            ps.setString(2,smartphone.getModel());
            ps.setDouble(3,smartphone.getPrice());
            ps.setInt(4,smartphone.getCategory().getId());
            ps.setInt(5,smartphone.getId());
            int res=ps.executeUpdate();
            if(res>0){
                return true;
            }else{
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteSmartphone(Smartphone smartphone) {
        String query="delete from gestion_smartphone.smartphone where id=?";
        try{
            PreparedStatement ps=Connexion.connecter().prepareStatement(query);
            ps.setInt(1,smartphone.getId());
            int res=ps.executeUpdate();
            System.out.println(res);
            System.out.println(smartphone);
            if(res>0){
                return true;
            }else{
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
