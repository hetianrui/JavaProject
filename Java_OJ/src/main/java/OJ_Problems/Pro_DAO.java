package OJ_Problems;

import common.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//数据访问层,和数据库交互的方法层
public class Pro_DAO {
    //获取所有题目信息
    public List<Problem> selectAll(){
        Connection connection=null;
        String sql="select * from oj_table";
        List<Problem> list= null;
        PreparedStatement statement=null;
        ResultSet resultSet=null;
        try {
            connection=DBUtil.getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            list = new ArrayList<>();
            while (resultSet.next()){
                Problem problem=new Problem();
                problem.setId(resultSet.getInt("id"));
                //problem.setTestCode(resultSet.getString("testCode"));
                problem.setDescription(resultSet.getString("description"));
                problem.setLevel(resultSet.getString("level"));
                problem.setTitle(resultSet.getString("title"));
                //problem.setTemplateCode(resultSet.getString("templateCode"));
                list.add(problem);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection,statement,resultSet);
        }
        return list;
    }
    public Problem selectById(int id){
        Connection connection=null;
        String sql="select * from oj_table where id=?";
        PreparedStatement statement=null;
        ResultSet resultSet=null;
        Problem problem=new Problem();
        try {
            connection=DBUtil.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1,id);
            resultSet = statement.executeQuery();
            if(resultSet.next()){
                problem.setId(resultSet.getInt("id"));
                problem.setTestCode(resultSet.getString("testCode"));
                problem.setDescription(resultSet.getString("description"));
                problem.setLevel(resultSet.getString("level"));
                problem.setTitle(resultSet.getString("title"));
                problem.setTemplateCode(resultSet.getString("templateCode"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection,statement,resultSet);
        }
        return problem;
    }
    public static void insert(Problem problem){
        Connection connection = DBUtil.getConnection();
        String sql="insert into oj_table values(null,?,?,?,?,?)";
        PreparedStatement statement=null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1,problem.getTitle());
            statement.setString(2,problem.getLevel());
            statement.setString(3,problem.getDescription());
            statement.setString(4,problem.getTemplateCode());
            statement.setString(5,problem.getTestCode());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(connection,statement,null);
        }

    }
    public void delete(int id){
        Connection connection = DBUtil.getConnection();
        String sql="delete from oj_table where id=?";
        PreparedStatement statement=null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1,id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(connection,statement,null);
        }
    }
    public void update(int id){
        Connection connection = DBUtil.getConnection();
        String sql="update table oj_table set ";
        PreparedStatement statement=null;
        try {
//            statement = connection.prepareStatement(sql);
//            statement.setString(1,problem.getTitle());
//            statement.setString(2,problem.getLevel());
//            statement.setString(3,problem.getDescription());
//            statement.setString(4,problem.getTemplateCode());
//            statement.setString(5,problem.getTestCode());
//            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(connection,statement,null);
        }
    }
}
