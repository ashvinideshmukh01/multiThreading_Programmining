package performance.latency.throughput.http.server;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HttpServerStart {

    private static final String BOOK_PATH = "/war_and_peace.txt";
    private  static  final int NUMBER_OF_WORKERS = 10;

    public static void main(String[] args) throws URISyntaxException, IOException {
        URI uri = HttpServerStart.class.getResource(BOOK_PATH).toURI();
        String bookString = new String(Files.readAllBytes(Paths.get(uri)));
        startServer(bookString);
    }

    private static void startServer(String bookString) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/search",new WorldCountHandler(bookString));
        Executor executor = Executors.newFixedThreadPool(NUMBER_OF_WORKERS);
        server.setExecutor(executor);
        server.start();
        System.out.println("server started om port 8080");



    }
}
