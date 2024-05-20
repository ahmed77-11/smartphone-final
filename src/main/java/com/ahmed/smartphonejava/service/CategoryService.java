package com.ahmed.smartphonejava.service;

import com.ahmed.smartphonejava.beans.Category;
import com.ahmed.smartphonejava.beans.Smartphone;
import com.ahmed.smartphonejava.connexion.Connexion;
import com.ahmed.smartphonejava.dao.ICategoryDao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryService implements ICategoryDao<Category> {
    @Override
    public List<Category> getAllCategories() {
        List<Category> list=new ArrayList<>();
        String query="Select * from category";
        try{
            PreparedStatement ps= Connexion.connecter().prepareStatement(query);
            ResultSet rs=ps.executeQuery();
            while (rs.next()){
                Category category=new Category();
                category.setId(rs.getInt("id"));
                category.setNom(rs.getString("nom"));
                category.setDescription(rs.getString("description"));
                category.setCreate_at(rs.getDate("created_at"));
                list.add(category);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Category getCategoryById(int id) {
        String query="Select * from category where id=?";
        Category category=new Category();
        try{
            PreparedStatement ps= Connexion.connecter().prepareStatement(query);
            ps.setInt(1,id);
            ResultSet rs=ps.executeQuery();
            while (rs.next()){
                category.setId(rs.getInt("id"));
                category.setNom(rs.getString("nom"));
                category.setDescription(rs.getString("description"));
                category.setCreate_at(rs.getDate("created_at"));
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return category;
    }
    
    @Override
    public boolean addCategory(Category category) {
        String query = "INSERT INTO category ( nom, description) VALUES (?,  ?)";
        try {
            PreparedStatement ps = Connexion.connecter().prepareStatement(query);
            ps.setString(1, category.getNom());
            ps.setString(2, category.getDescription());
            int rowsInserted = ps.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

	public List<Category> searchCategories(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
    public boolean deleteCategory(Category category) {
        String query="delete from gestion_smartphone.category where id=?";
        try{
            PreparedStatement ps=Connexion.connecter().prepareStatement(query);
            ps.setInt(1,category.getId());
            int res=ps.executeUpdate();
            System.out.println(res);
            System.out.println(category);
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
    public boolean updateCategory(Category category) {
        String query="UPDATE gestion_smartphone.category set nom=?,description=? where id=? ";
        try {
            PreparedStatement ps=Connexion.connecter().prepareStatement(query);
            ps.setString(1,category.getNom());
            ps.setString(2,category.getDescription());
            ps.setInt(3,category.getId());
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
	
}
