import java.sql.*;

public class User_DAO {
    private Connection conn;
    static Statement st;

    public User_DAO() throws SQLException {
        // super();
        this.conn = DB_Connection.conn;
        st = conn.createStatement();
    }

    void addRequest(int user_id, int[] job_ids) throws SQLException {
        String q = "insert into job_request values (?,?,'waiting');";
        PreparedStatement pst = conn.prepareStatement(q);
        int flag = 0;
        for (int i : job_ids) {
            pst.setInt(1, i);
            pst.setInt(2, user_id);
            if (pst.executeUpdate() != 1) {
                System.out.println("Insertion failed for user ID :" + user_id + " and job ID :" + i);
                flag++;
            }
        }
        if (flag == job_ids.length) {
            System.out.println("your request has been Succesfully inserted into database");
        }
    }

    boolean addUser(User u) {
        boolean b = false;
        try {
            CallableStatement cst = conn.prepareCall("{call AddUser(?,?,?,?,?,?,?,?)}");
            cst.setInt(1, u.getId());
            cst.setString(2, u.getName());
            cst.setString(3, u.getEmail());
            cst.setString(4, u.getEducation());
            cst.setString(5, u.getPreferences());
            cst.setString(6, u.getExperience());
            cst.setString(7, u.getContactNo());
            cst.setString(8, u.getPassword());
            cst.execute();
            b = true;
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Error While Adding User Into Database");
        }
        return b;
    }

    public boolean addUser2(User u) {

        boolean f = false;
        try {

            String sql = "insert into user values (?,?,?,?,?,?,?,?)";
            java.sql.PreparedStatement rs = conn.prepareStatement(sql);
            rs.setInt(1, u.getId());
            rs.setString(2, u.getName());
            rs.setString(3, u.getEmail());
            rs.setString(4, u.getEducation());
            rs.setString(5, u.getPreferences());
            rs.setString(6, u.getExperience());
            rs.setString(7, u.getContactNo());
            rs.setString(8, u.getPassword());

            int i = rs.executeUpdate();
            if (i == 1) {
                f = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return f;
    }

    public boolean updateUser(User u) {
        boolean f = false;
        try {

            String sql = "update user set name=?, qualification=?, email=?, password=? where id=?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, u.getName());
            ps.setString(2, u.getEducation());
            ps.setString(3, u.getEmail());
            ps.setString(4, u.getPassword());
            ps.setInt(5, u.getId());

            int i = ps.executeUpdate();
            if (i == 1) {
                f = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return f;
    }

    boolean deleteUser(int id) throws SQLException {
        String q = "delete from user where id=" + id + ";";
        if (st.executeUpdate(q) == 1) {
            return true;
        } else {
            return false;
        }

    }

}