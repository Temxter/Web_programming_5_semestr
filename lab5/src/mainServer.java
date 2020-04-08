import server.Server;

public class mainServer {
    public static void main(String[] args) {
        Server server = new Server(10001);
        server.startServer();
        server.join();
    }
}
