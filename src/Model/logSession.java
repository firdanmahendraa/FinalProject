package Model;

public class logSession {
    private static String username;
    private static String password;
    private static String nama_user;

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        logSession.username = username;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        logSession.password = password;
    }

    public static String getNama_user() {
        return nama_user;
    }

    public static void setNama_user(String name_user) {
        logSession.nama_user = nama_user;
    }
    
    
}
