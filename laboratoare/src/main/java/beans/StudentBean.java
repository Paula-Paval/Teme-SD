package beans;

public class StudentBean implements java.io.Serializable {
    private int Id=0;
    private String nume = null;
    private String prenume = null;
    private int varsta = 0;
    private String specializare=null;

    public StudentBean() {
    }
    public String getNume() {
        return nume;
    }
    public void setNume(String nume) {
        this.nume = nume;
    }
    public String getPrenume() {
        return prenume;
    }
    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }
    public int getVarsta() {
        return varsta;
    }
    public void setVarsta(int varsta) {
        this.varsta = varsta;
    }

    public String getSpecializare() {
        return specializare;
    }

    public void setSpecializare(String specializare) {
        this.specializare = specializare;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    @Override
    public String toString() {
        return "{" +
                "\"Id\":" + Id +","+
                "\"nume\":\"" + nume + "\"," +
                "\"prenume\":\"" + prenume + "\"," +
                "\"varsta\":" + varsta +","+
                " \"specializare\":\"" + specializare + "\"" +
                "}";
    }
}