import java.io.*;
import java.net.*;
import java.util.*;
import java.util.regex.*;
//класс реализует веб-сканер
//сокет-это ресурс, предоставл операц системой, который предоставляет
// нам обмениваться данными с другими компами по сети. можем использовать 
// сокет для установки соединения с веб-сервером
// порт - число и каждая из программ которая исполняется на опред компе, может выбраться уник число порта.
class Crawler implements Runnable{

    private HashMap<String, URLDepthPair> links = new HashMap<>();
    private LinkedList<URLDepthPair> pool = new LinkedList<>();
    // 1 хранит пройден ссылки, 2 нет
    private int depth = 0;
 // конструктор для ввода глубины и ссылки   
    public Crawler(String url, int depth_) {
        depth = depth_;
        pool.add(new URLDepthPair(url, 0));
    }
// вырезает из пула ссылку и запускает поток, который сканирует страницу    
    public void run() 
    {
        while (pool.size() > 0 || Thread.activeCount() > 1) 
        {
            if (pool.size() > 0) 
            {
                URLDepthPair link = pool.pop();
                CrawlerThread task = new CrawlerThread(link);
                task.start();
           }
        }
        
        for (URLDepthPair link : links.values())
            System.out.println(link);
            
        System.out.println();
        System.out.printf("Found %d URLS\n", links.size());
    }
// регулярное выражение которое хранит макет ссылки    
    public static Pattern LINK_REGEX = Pattern.compile(
        "<a\\s+(?:[^>]*?\\s+)?href=([\"'])(.*?)\\1"
    );
    
    private class CrawlerThread extends Thread 
    {
        private URLDepthPair link;

        public CrawlerThread(URLDepthPair link_) 
        {
            link = link_;
        }

// сканирует страницу и добавляет ссылку в pool
        public void run() 
        {
            if (links.containsKey(link.getURL())) {
                URLDepthPair knownLink = links.get(link.getURL());
                knownLink.incrementVisited();
                return;
            }
            
            links.put(link.getURL(), link);
            // проверка глубины
            if (link.getDepth() >= depth)
                return;
  // поиск ссылок на странице и добавление в pool                 
            try {
                URL url = new URL(link.getURL());
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");
                
                Scanner s = new Scanner(con.getInputStream());
                while (s.findWithinHorizon(LINK_REGEX, 0) != null) {
                    String newURL = s.match().group(2);
                    if (newURL.startsWith("/"))
                        newURL = link.getURL() + newURL;
                    else if (!newURL.startsWith("http"))
                        continue;
                    URLDepthPair newLink = new URLDepthPair(newURL, link.getDepth() + 1);
                    pool.add(newLink);
                }
            } catch (Exception e) {}
        }
    }
// если польз ввел одно из двух, то будет реализован данный метод 
    public static void showHelp() {
        System.out.println("usage: java Crawler <URL> <depth>");
        System.exit(1);
    }

    public static void main(String[] args) {
        if (args.length != 2) showHelp();
        
        String url = args[0];
        int depth = 0;
        
        try {
            depth = Integer.parseInt(args[1]);
        } catch (Exception e) {
            showHelp();
        }
        
        Crawler crawler = new Crawler(url, depth);
        crawler.run();
    }
}