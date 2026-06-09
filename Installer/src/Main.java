public class Main {
    public static void main(String[] args) {
        String gamesPath = "D:\\Games";
        GameInstaller installer = new GameInstaller(gamesPath);
        installer.install();
    }
}
