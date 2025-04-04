import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Knotenpunkt {
    private int dauer;
    private int knotenNummer;
    private String knotenName;
    private int faz;

    public Knotenpunkt(int knotenNummer,String knotenName, int dauer, List<Knotenpunkt>vorgaengerListe){
        this.knotenNummer = knotenNummer;
        this.knotenName = knotenName;
        this.dauer = dauer;
        this.vorgaengerListe = vorgaengerListe;
        for(Knotenpunkt vorgaenger : vorgaengerListe) {
            if (vorgaenger != null) {
                vorgaenger.nachfolgerListe.add(this);
            }
        }
    }
    @Override
    public String toString() {
        return "Knotennummer: " + knotenNummer + "\n" +
                "  Knotenname: " + knotenName + "\n" +
                "  Dauer: " + dauer + " Stunden\n" +
                "  Frühester Anfangszeitpunkt: " + faz + "\n" +
                "  Spätester Anfangszeitpunkt: " + sazBerechnung() + "\n" +
                "  Früherster Endzeitunkt: " + fezBerechnung() + "\n" +
                "  Spätester Endzeitpunkt: " + sezBerechnung() + "\n" +
                "  Freier Puffer: " + fpBerechnung() + "\n" +
                "  Gesamtpuffer: " + gpBerechnung() + "\n";
    }

    List<Knotenpunkt> vorgaengerListe = new ArrayList<>();

//    public List<Knotenpunkt> getVorgaengerListe() {
//        return vorgaengerListe;
//    }
//    public void addVorgaengerListe(List<Knotenpunkt> vorgaengerListe) {
//        this.vorgaengerListe = vorgaengerListe;
//    }

    List<Knotenpunkt> nachfolgerListe = new ArrayList<>();

//    public List<Knotenpunkt> getNachfolgerListe() {
//        return nachfolgerListe;
//    }
//    public void addNachfolgerListe(List<Knotenpunkt> nachfolgerListe) {
//        this.nachfolgerListe = nachfolgerListe;
//    }

//    public int getDauer() {
//        return dauer;
//    }
    public void setDauer(int dauer) {
        this.dauer = dauer;
    }

    public int getKnotenNummer() {
        return knotenNummer;
    }
    public void setKnotenNummer(int knotenNummer) {
        this.knotenNummer = knotenNummer;
    }

//    public String getKnotenName() {
//        return knotenName;
//    }
    public void setKnotenName(String knotenName) {
        this.knotenName = knotenName;
    }

//    public int getFaz() {
//        return faz;
//    }

    public int fezBerechnung(){
        int fez = (dauer + faz);
        return fez;
    }
    public void fazBerechnenUndSetzen(){
        int fazAktuell = 0;
        for(Knotenpunkt vorgaenger : vorgaengerListe) {
            if (vorgaenger.fezBerechnung() > fazAktuell) {
                fazAktuell = vorgaenger.fezBerechnung();
            }
        }
        this.faz = fazAktuell;
    }

    public int sezBerechnung(){
        int sez = 0;
        if(nachfolgerListe.isEmpty()){
            sez = fezBerechnung();
        }else {
            List<Integer> saz = new ArrayList<>();
            for (Knotenpunkt knotenpunkt : nachfolgerListe) {
                saz.add(knotenpunkt.sazBerechnung());
                    sez = Collections.min(saz);
            }
        }
        return sez;
    }
    public int sazBerechnung(){
        return (sezBerechnung() - dauer);
    }
    public int gpBerechnung(){
        int saz = sazBerechnung();
            return (saz - faz);
    }
    public int fpBerechnung(){
        int fez = fezBerechnung();
        int fazNachfolger;
        if(nachfolgerListe.isEmpty()){
            fazNachfolger = fezBerechnung();
        } else {
            fazNachfolger = nachfolgerListe.getFirst().faz;
            for(Knotenpunkt nachfolger : nachfolgerListe){
                if(nachfolger.faz < fazNachfolger){
                    fazNachfolger = nachfolger.faz;
                }
            }
        }
        return (fazNachfolger - fez);
    }

}

