package performance.latency.throughput.http.server;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class WorldCountHandler implements HttpHandler {

    private final String book;

    public WorldCountHandler(String book) {
        this.book = book;
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String queryParameter = httpExchange.getRequestURI().getQuery();
        String[] keyValue = queryParameter.split("=");
        String action = keyValue[0];
        String searchWord = keyValue[1];

        if(!action.equals("word")){
            httpExchange.sendResponseHeaders(400,0);
            return;
        }

        long count = cout(searchWord);
        byte[] response = Long.toString(count).getBytes(StandardCharsets.UTF_8);
        httpExchange.sendResponseHeaders(200, response.length);
        OutputStream responseBody = httpExchange.getResponseBody();
        responseBody.write(response);
        responseBody.close();
    }

    private long cout(String searchWord) {
            long count = 0;
            int index = 0;

            while(index >= 0){
               index =  book.indexOf(searchWord, index);
               if(index >= 0){
                    count++;
                    index++;
               }
            }
            return count;
    }
}
