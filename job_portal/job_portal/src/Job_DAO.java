import java.sql.*;

public class Job_DAO {
    private Connection conn;
    static Statement st;

    public Job_DAO(Connection connection) throws SQLException {
        this.conn = connection;
        st = conn.createStatement();
    }

    Connection getConnection() {
        return conn;
    }

    boolean addJobs(Job j) {
        boolean b = false;
        try {
            CallableStatement ps = conn.prepareCall("call AddJob(?,?,?,?,?,?,?,?)");
            ps.setInt(1, j.getId());
            ps.setString(2, j.getcompany_name());
            ps.setString(3, j.getRole());
            ps.setString(4, j.getSalary());
            ps.setString(5, j.getLocation());
            ps.setInt(6, j.getVacancy());
            ps.setString(7, j.getStatus());
            ps.setString(8, j.getPassword());
            ps.execute();
            b = true;
        } catch (Exception e) {
        }
        return b;
    }

    public boolean addJobs2(Job j) {
        boolean f = false;
        try {
            String sql = "insert into jobs(id,compony_name, Role, salary_range, status, location,vacancy,password) values(?,?,?,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, j.getId());
            ps.setString(2, j.getcompany_name());
            ps.setString(3, j.getRole());
            ps.setString(4, j.getSalary());
            ps.setString(5, j.getStatus());
            ps.setString(6, j.getLocation());
            ps.setInt(7, j.getVacancy());
            ps.setString(8, j.getPassword());

            int i = ps.executeUpdate();

            if (i == 1) {
                f = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return f;
    }

    public boolean updateJob(Job j) {
        boolean f = false;
        try {
            String sql = "update jobs set compony_name=?, Role=?, salary_range=?, status=?, location=? where id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, j.getcompany_name());
            ps.setString(2, j.getRole());
            ps.setString(3, j.getSalary());
            ps.setString(3, j.getStatus());
            ps.setString(4, j.getLocation());
            ps.setInt(5, j.getId());

            int i = ps.executeUpdate();

            if (i == 1) {
                f = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return f;
    }

    public boolean deleteJob(int id) {
        boolean f = false;
        try {
            String sql = "delete from jobs where id=?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, id);

            int i = ps.executeUpdate();

            if (i == 1) {
                f = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return f;
    }

}
