package zany.model.system;

public class TestEntity {

    int a = 3;
    String s = "abc";
    
    TestModel tt = new TestModel();
    
    public int getA() {
        return a;
    }
    public void setA(int a) {
        this.a = a;
    }
    public String getS() {
        return s;
    }
    public void setS(String s) {
        this.s = s;
    }
    public TestModel getTt() {
        return tt;
    }
    public void setTt(TestModel tt) {
        this.tt = tt;
    }
    @Override
    public String toString() {
        return "TestEntity [a=" + a + ", s=" + s + ", tt=" + tt + "]";
    }
    
    
}
