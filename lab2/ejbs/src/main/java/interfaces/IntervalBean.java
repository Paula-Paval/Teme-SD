package interfaces;

public interface IntervalBean {
    void setA(Integer a);
    void setB(Integer b);
    Integer getA();
    Integer getB();
    boolean isInInterval(Integer x);
}
