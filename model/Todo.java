package zany.model;

public class Todo {

    int id;
    String name;
    String contents;
    
    public Todo(int id, String name) {
        this.id = id;
        this.name = name;
    }
    
    public Todo(int id, String name, String contents) {
        this.id = id;
        this.name = name;
        this.contents = contents;
    }
    
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getContents() {
        return contents;
    }
    public void setContents(String contents) {
        this.contents = contents;
    }
    
}
