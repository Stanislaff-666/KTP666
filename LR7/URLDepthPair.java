// данный класс хранит в себе юрл, глубину и кол-во посещений
class URLDepthPair {
    private String url;
    private int depth;
    private int visited;
// конструктор    
    public URLDepthPair(String url_, int depth_) {
        url = url_;
        depth = depth_;
        visited = 1;
    }
    public String getURL() {
        return url;
    }
    public int getDepth() {
        return depth;
    }
// кол-во посещений данной ссылки    
    public void incrementVisited() {
        visited++;
    }
 // Возврат всего!   
    public String toString() {
        return "<URL href=\"" + url + "\" visited=\"" + visited + "\" depth=\"" + depth + "\" \\>";
    }
}
