package com.models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AnimeDao implements GenericDao<Anime>{

    public Connection getConnection() {
        Connection con = null;
        String URL = "jdbc:mariadb://localhost:3306/myanimes";
        String USER = "root";
        String PASSWORD = "";

        try {
            Class.forName("org.mariadb.jdbc.Driver");
            con = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Deu certo ^_^ conectado!");
            return con;
        } catch(SQLException | ClassNotFoundException e){
            System.out.println("Não deu certo :/");
        }
        return con;
    }

    @Override
    public boolean insert(Anime anime) {
        String sql = "INSERT INTO animes(titulo, ano, descricao, avaliacao, status) VALUE(?, ?, ?, ?, ?)";
        try{
            Connection con = getConnection();
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, anime.getTitulo());
            pstm.setInt(2, anime.getAno());
            pstm.setString(3, anime.getDescricao());
            pstm.setString(4, anime.getAvaliacao());
            pstm.setBoolean(5, anime.isStatus());
            pstm.executeUpdate();
            return true;

        } catch (Exception e) {
            System.out.println("Erro na inserção :/");
        }
        return false;
    }

    @Override
    public Anime select(int id) {
        String sql = "SELECT * FROM animes WHERE id = ?";
        Anime anime = null;
        try (Connection con = getConnection()){
            if(con!=null){
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                List<Anime> animes = resultSetToList(rs);
                anime = animes.isEmpty() ? null : animes.get(0);
            }
        } catch (SQLException e) {
            System.out.println("Anime não encontrado!");
        }
        return anime;
    }

    @Override
    public List<Anime> select() {
        String sql = "SELECT * FROM animes";
        try(Connection con = getConnection()){
            if(con != null){
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                List<Anime> animes = resultSetToList(rs);
                return animes;
            }
        } catch (SQLException e) {
            System.out.println("Não foi possível encontrar os Animes.");
        }
        return null;
    }

    @Override
    public List<Anime> select(String titulo) {
        String sql = "SELECT * FROM animes WHERE titulo = ?";
        try(Connection con = getConnection()){
            if(con != null){
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, titulo);
                ResultSet rs = ps.executeQuery();
                return resultSetToList(rs);
            }
        } catch (Exception e) {
            System.out.println("Não foi possível encontrar os Animes com o titulo "+titulo+" ");
        }
        return null;
    }

    @Override
    public boolean update(Anime anime) {
        try(Connection con = getConnection()){
            if(con != null){
                String sql = "UPDATE animes SET titulo = ?, ano = ?, descricao = ?, avaliacao = ?, status = ? WHERE id = ?";
                PreparedStatement psm = con.prepareStatement(sql);
                psm.setString(1, anime.getTitulo());
                psm.setInt(2, anime.getAno());
                psm.setString(3, anime.getDescricao());
                psm.setString(4, anime.getAvaliacao());
                psm.setBoolean(5, anime.isStatus());
                psm.setInt(6, anime.getId());
                psm.executeUpdate();
                return true;
            }
        } catch(SQLException e) {
            System.out.println("Erro ao tentar efetuar a atualização.");
        }
        return false;
    }

    @Override
    public boolean delete(Anime anime) {
        String sql = "DELETE FROM animes WHERE id = ?";
        try (Connection con = getConnection()){
            if(con!=null){
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, anime.getId());
                ps.executeUpdate();
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Não foi possível efetuar a deleção.");
        }
        return false;
    }

    private List<Anime> resultSetToList(ResultSet rs) throws SQLException{
        List<Anime> animes = new ArrayList<Anime>();
        while(rs.next()){
            Anime anime = new Anime();
            anime.setId(rs.getInt("id"));
            anime.setAno(rs.getInt("ano"));
            anime.setTitulo(rs.getString("titulo"));
            anime.setDescricao(rs.getString("descricao"));
            anime.setStatus(rs.getBoolean("status"));
            anime.setAvaliacao(rs.getString("avaliacao"));
            animes.add(anime);
        }
        return animes;
    }
}
