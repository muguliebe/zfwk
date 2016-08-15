package zany.model.system;

public class TestModel {

    int a = 3;
    String s = "abc";
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
    @Override
    public String toString() {
        return "TestModel [a=" + a + ", s=" + s + "]";
    }
}
