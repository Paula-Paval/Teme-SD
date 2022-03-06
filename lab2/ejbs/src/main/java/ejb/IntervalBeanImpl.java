package ejb;

import interfaces.IntervalBean;

public class IntervalBeanImpl implements IntervalBean {
    private Integer a;
    private Integer b;

    public void setA(Integer a) {
       this.a=a;
    }

    public void setB(Integer b) {
        this.b=b;
    }

    public Integer getA() {
        return a;
    }

    public Integer getB() {
        return b;
    }

    public boolean isInInterval(Integer x) {
        return (a<=x&&b>=x);
    }
}
