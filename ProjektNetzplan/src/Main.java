
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.Collections;

public class Main {
    public static void main(String[] args) {

        List<Knotenpunkt> knotenpunkteListe = new ArrayList<>();
        erstelleKnoten(knotenpunkteListe);
        ausgabeKnotenpunkt(knotenpunkteListe);
        ausgabeProjektdauer(knotenpunkteListe);
        knotenAendern(knotenpunkteListe);
        knotenlisteKritischerPfad(knotenpunkteListe);

    }

    public static void erstelleKnoten(List<Knotenpunkt> knotenpunkteListe) {
        Scanner scanner = new Scanner(System.in);
        int anzahlKnotenpunkte = 0; // muss außerhalb der While Schleife da sonst die fori im nächsten Schritt nicht darauf zugreifen kann
        while (anzahlKnotenpunkte == 0) { // so kann man verhindern, dass es in eine Endlosschleife kommt anstatt true
            try {
                System.out.println("Geben Sie die Anzahl der Knotenpunkte ein: ");
                anzahlKnotenpunkte = scanner.nextInt(); // hier wird es überschrieben
            } catch (InputMismatchException e) {
                System.out.println("Das war eine falsche Eingabe versuchen Sie es erneut: ");
                scanner.nextLine();
            }
        }
        scanner.nextLine();
        for (int i = 0; i < anzahlKnotenpunkte; i++) {
            int knotenNummer = i + 1; // Knotennummer wird so gleich festgelegt mit jedem Durchlauf
            String knotenNamen; // nur Deklarieren da die Prüfung unten mit einem Leeren String gemacht wird
            int dauer;         // und unten in der TryCatch wird sie initialisiert
            while (true) {
                System.out.println("Geben Sie den Namen des " + (knotenNummer) + ". Knotenpunktes ein: ");
                knotenNamen = scanner.nextLine();// hier keinen Cleaner weil nach einem String keine \n kommt
                if (knotenNamen == "") { // Prüfen auf ist String Leer eine andere Prüfung braucht man nicht da ein String auch eine Zahl annehmen kann
                    System.out.println("Das war eine falsche Eingabe, bitte versuchen Sie es erneut!");
                    continue;
                }
                break; // wenn String eingegeben wurde Schleife stoppen
            }
            while (true) {
                try {
                    System.out.println("Geben Sie die Dauer des " + (knotenNummer) + ". Knotenpunktes ein: ");
                    dauer = scanner.nextInt();
                    scanner.nextLine();
                    break;
                } catch (Exception e) {
                    System.out.println("Das war eine falsche Eingabe, bitte versuchen Sie es erneut!");
                    scanner.nextLine();
                }
            }

            List<Integer> vorgaengerNummerListe = new ArrayList<>();//neue Liste, welche die Knotennummern der Vorgänger enthält
            while (true) {
                try {
                    System.out.println("Geben Sie die Anzahl der Vorgänger ein: ");
                    int anzahlVorgaenger = scanner.nextInt();
                    while (true) {
                        try {
                            if (anzahlVorgaenger > 0) {
                                System.out.println("Bitte geben Sie die Nummer(n) des/der Vorgänger(s) des " + (i + 1) + ". Knotenpunktes ein: ");
                                for (int j = 0; j < anzahlVorgaenger; j++) {
                                    vorgaengerNummerListe.add(scanner.nextInt()); //alle Vorgänger werden in die Liste anhand ihrer Knotennummer eingefügt
                                }
                            } else {
                                vorgaengerNummerListe.add(0);
                            }
                            scanner.nextLine();
                            break;
                        } catch (Exception e) {
                            System.out.println("Das war eine Falsche eingabe bitte versuchen Sie es erneut!");
                            scanner.nextLine();
                        }
                    }
                    break;
                } catch (Exception e) {
                    System.out.println("Das war eine falsche Eingabe, bitte versuchen Sie es erneut!");
                    scanner.nextLine();
                }
            }

            List<Knotenpunkt> knotenVorgaengerListe = new ArrayList<>(); //Hier wird eine Liste erstellt, die später in dieser Klasse alle Vorgängerknoten enthält
            for (Integer vorgaengerNummer : vorgaengerNummerListe) {     //Die Knotennummern der Vorgänger, die durch den Anwender eingegeben wurden, werden abgerufen
                if (vorgaengerNummer != null) {
                    for (Knotenpunkt knotenpunkt : knotenpunkteListe) {      //Es werden alle Knoten der Liste knotenpunktListe aufgerufen
                        if (knotenpunkt.getKnotenNummer() == vorgaengerNummer) {  //Hier wird überprüft, ob die Knotennummer des aufgerufenen Knotens mit der Vorgängerknotennummer übereinstimmt
                            knotenVorgaengerListe.add(knotenpunkt);               //Wenn die Nummern übereinstimmen wird der besagte Knoten in die Knotenvorgängerliste eingetragen
                        }
                    }
                }
            }

            Knotenpunkt knotenpunkt = new Knotenpunkt(knotenNummer, knotenNamen, dauer, knotenVorgaengerListe);
            knotenpunkt.setKnotenNummer(knotenNummer);
            knotenpunkt.setKnotenName(knotenNamen);
            knotenpunkt.setDauer(dauer);
            knotenpunkt.fazBerechnenUndSetzen();
            knotenpunkt.fezBerechnung();
            knotenpunkt.sazBerechnung();
            knotenpunkt.sezBerechnung();
            knotenpunkt.gpBerechnung();
            knotenpunkt.fpBerechnung();
            knotenpunkteListe.add(knotenpunkt);
        }
    }

    public static void ausgabeKnotenpunkt(List<Knotenpunkt> knotenpunkteListe) {
        System.out.println("Möchten Sie einen Knotenpunkt auslesen? (J/N)");
        Scanner scanner = new Scanner(System.in);
        String eingabe = scanner.nextLine();
        String yes = "J";
        while (eingabe.equalsIgnoreCase(yes)) {
            System.out.println("Geben Sie die Nummer des Knotenpunktes ein, den Sie auslesen möchten: ");
            while (scanner.hasNextInt()) {
                try {
                    int eingabeKnotenpunkt = scanner.nextInt() - 1; // hier eins abziehen da Liste bei 0 beginnt
                    if (eingabeKnotenpunkt >= 0) {
                        System.out.println(knotenpunkteListe.get(eingabeKnotenpunkt));
                        break;
                    }
                } catch (Exception e) {
                    System.out.println("Das war eine falsche Eingabe versuchen Sie es erneute!");
                    scanner.nextLine();
                }
            }
            System.out.println("Möchten Sie einen weiteren Knotenpunkt auslesen?");
            scanner.nextLine();
            eingabe = scanner.nextLine();
        }
    }

    public static void ausgabeProjektdauer(List<Knotenpunkt> knotenpunkteListe) {
        Scanner scanner = new Scanner(System.in);
        boolean valid = false;
        while (!valid) {
            System.out.println("Möchten Sie die Projektdauer abrufen? (j/n)");
            String eingabe = scanner.nextLine().trim().toLowerCase();
            if (eingabe.equals("j")) {
                valid = true;
                System.out.println("Die Projektdauer beträgt: ");
                System.out.println((knotenpunkteListe.getLast().sezBerechnung()) + " Stunden");
            } else if (eingabe.equals("n")) {
                valid = true;
                break;
            } else {
                System.out.println("Ungültige Eingabe. Bitte geben Sie 'j' für Ja oder 'n' für Nein ein.");
            }
        }
    }

    public static void knotenlisteKritischerPfad(List<Knotenpunkt> knotenpunkteListe) {
        System.out.println("Möchten Sie den kritischen Pfad ausgeben? (j/n)");
        Scanner scanner = new Scanner(System.in);
        String yes = "j";
        String no = "n";
        boolean valid = false;
        while(!valid) {
            String kritischerPfad = scanner.nextLine();
            if (kritischerPfad.equalsIgnoreCase(yes)) {
                valid = true;
                List<Knotenpunkt> knotenlisteKritischerPfad = new ArrayList<>();
                for (Knotenpunkt knoten : knotenpunkteListe) {
                    if (knoten.gpBerechnung() == 0) {
                        knotenlisteKritischerPfad.add(knoten);
                    }
                }
                Collections.sort(knotenlisteKritischerPfad, (o1, o2) -> {
                    if (o1.sezBerechnung() < o2.sezBerechnung()) {
                        return -1;
                    } else if (o1.sezBerechnung() > o2.sezBerechnung()) {
                        return 1;
                    }
                    return 0;
                });
                System.out.println("Kritischer Pfad: ");
                for (Knotenpunkt knoten : knotenlisteKritischerPfad) {
                    if (knoten != knotenlisteKritischerPfad.getLast()) {
                        System.out.print(knoten.getKnotenNummer() + " --> ");
                    } else {
                        System.out.println(knoten.getKnotenNummer());
                    }
                }
                for (Knotenpunkt knoten : knotenlisteKritischerPfad) {
                    System.out.println(knoten);
                }
            } else if (kritischerPfad.equalsIgnoreCase(no)) {
                valid = true;
                break;
            }else {
                System.out.println("Ungültige Eingabe. Bitte geben Sie 'j' für Ja oder 'n' für Nein ein.");
                valid = false;
            }
        }
    }

    public static void knotenAendern(List<Knotenpunkt> knotenpunkteListe) {
        System.out.println("Möchten Sie einen Knotenpunkt ändern? (j/n): ");
        Scanner scanner = new Scanner(System.in);
        String yes = "J";
        String no = "N";
        boolean gueltigeAbfrageJaNein = false;
        while (!gueltigeAbfrageJaNein) {
            try {
                String knotenAendernAbfrage = scanner.nextLine();
                boolean gueltigeNummerKnotenpunkt = false;
                if (knotenAendernAbfrage.equalsIgnoreCase(yes)) {
                    gueltigeAbfrageJaNein = true;
                    while (!gueltigeNummerKnotenpunkt) {
                        try {
                            System.out.println("Geben Sie die Nummer des Knotenpunktes ein den Sie ändern möchten: ");
                            int eingabeKnotenpunkt = scanner.nextInt();
                            scanner.nextLine();
                            for (int i = 0; i < knotenpunkteListe.size(); i++) {
                                if (eingabeKnotenpunkt - 1 == i && eingabeKnotenpunkt < knotenpunkteListe.size()) {
                                    gueltigeNummerKnotenpunkt = true;
                                    System.out.println(knotenpunkteListe.get(i));
                                    Knotenpunkt zuAendernderKnoten = knotenpunkteListe.get(i);
                                    boolean schleifeWasAendern = false;
                                    while(!schleifeWasAendern) {
                                        System.out.println("Möchten Sie [Knotenname] oder [Dauer] ändern?");
                                        String wasAendern = scanner.nextLine();
                                        switch (wasAendern) {
                                            case "Knotenname":
                                                schleifeWasAendern = true;
                                                System.out.println("Bitte geben Sie den neuen Namen ein:");
                                                String wieAendern1 = scanner.nextLine();
                                                zuAendernderKnoten.setKnotenName(wieAendern1);
                                                break;
                                            case "Dauer":
                                                schleifeWasAendern = true;
                                                System.out.println("Bitte geben Sie die neue Dauer an:");
                                                int wieAendern2 = scanner.nextInt();
                                                zuAendernderKnoten.setDauer(wieAendern2);
                                                neuberechnungKnoten(knotenpunkteListe);
                                                break;
                                            default:
                                                System.out.println("Ungültige Eingabe!");
                                                schleifeWasAendern = false;
                                                continue;
                                        }
                                        System.out.println(knotenpunkteListe.get(i));
                                        gueltigeAbfrageJaNein = true;
                                    }
                                }
                            }
                        } catch (Exception e) {
                            System.out.println("Das war eine falsche Eingabe, bitte versuchen Sie es erneut!");
                            scanner.nextLine();
                        }
                    }
                } else if (knotenAendernAbfrage.equalsIgnoreCase(no)) {
                    gueltigeAbfrageJaNein = true;
//                    System.out.println("Programm beendet!");
                } else {
                    throw new RuntimeException();
                }
            } catch (Exception e) {
                System.out.println("Das ware eine Falsche Eingabe bitte versuchen Sie es erneut! Geben Sie J oder N ein");
                gueltigeAbfrageJaNein = false;
            }
        }
    }

//    public static void knotenAendern(List<Knotenpunkt> knotenpunkteListe) {
//        System.out.println("Möchten Sie einen Knotenpunkt ändern? (J/N)");
//        Scanner scanner = new Scanner(System.in);
//        String yes = "J";
//        String no = "N";
//        boolean gueltigeAbfrageJaNein = false;
//        while (!gueltigeAbfrageJaNein) {
//            try {
//                String knotenAendernAbfrage = scanner.nextLine();
//                boolean gueltigeNummerKnotenpunkt = false;
//                    if (knotenAendernAbfrage.equalsIgnoreCase(yes)) {
//                        gueltigeAbfrageJaNein = true;
//                        while (!gueltigeNummerKnotenpunkt) {
//                            try {
//                                System.out.println("Geben Sie die Nummer des Knotenpunktes ein den Sie ändern möchten: ");
//                                int eingabeKnotenpunkt = scanner.nextInt();
//                                scanner.nextLine();
//                                for (int i = 0; i < knotenpunkteListe.size(); i++) {
//                                    if (eingabeKnotenpunkt - 1 == i && eingabeKnotenpunkt < knotenpunkteListe.size()) {
//                                        gueltigeNummerKnotenpunkt = true;
//                                        System.out.println(knotenpunkteListe.get(i));
//                                        Knotenpunkt zuAendernderKnoten = knotenpunkteListe.get(i);
//                                        boolean schleifeWasAendern = false;
//                                        while(!schleifeWasAendern) {
//                                            System.out.println("Welchen Wert möchten Sie ändern? Und in was möchten Sie diesen umändern?");
//                                            String wasAendern = scanner.nextLine();
//                                            switch (wasAendern) {
//                                                case "Knotenname":
//                                                    String wieAendern1 = scanner.nextLine();
//                                                    zuAendernderKnoten.setKnotenName(wieAendern1);
//                                                    break;
//                                                case "Dauer":
//                                                    schleifeWasAendern = true;
//                                                    int wieAendern2 = scanner.nextInt();
//                                                    zuAendernderKnoten.setDauer(wieAendern2);
//                                                    neuberechnungKnoten(knotenpunkteListe);
//                                                    break;
//                                                default:
//                                                    System.out.println("Ungültige Eingabe!");
//                                                    schleifeWasAendern = false;
//                                                    continue;
//                                            }
//                                            System.out.println(knotenpunkteListe.get(i));
//                                            gueltigeAbfrageJaNein = true;
//                                        }
//                                    }
//                                }
//                            } catch (Exception e) {
//                                System.out.println("Das war eine falsche Eingabe, bitte versuchen Sie es erneut!");
//                                scanner.nextLine();
//                            }
//                        }
//                    } else if (knotenAendernAbfrage.equals(no)) {
//                        gueltigeAbfrageJaNein = true;
//                        System.out.println("Programm beendet!");
//                    } else {
//                        throw new RuntimeException();
//                    }
//            } catch (Exception e) {
//                System.out.println("Das ware eine Falsche Eingabe bitte versuchen Sie es erneut! Geben Sie J oder N ein");
//                gueltigeAbfrageJaNein = false;
//            }
//        }
//
//    }

    public static void neuberechnungKnoten(List<Knotenpunkt>knotenpunkteList) {
        for (int i = 0; i < knotenpunkteList.size(); i++) {
            knotenpunkteList.get(i).fazBerechnenUndSetzen();
            knotenpunkteList.get(i).fezBerechnung();
            knotenpunkteList.get(i).sazBerechnung();
            knotenpunkteList.get(i).sezBerechnung();
            knotenpunkteList.get(i).gpBerechnung();
            knotenpunkteList.get(i).fpBerechnung();
        }
    }




//    public static void dateiSpeichern(knotenpunkteListe){
//
//        try {
//            File file = new File("Datei.txt");
//            Reader reader = new FileReader(erstelleKnoten(file));
//            Writer writer = new FileWriter();
//
//
//
//            BufferedReader bufferedReader = new BufferedReader();
//            BufferedWriter bufferedWriter = new BufferedWriter();
//
//            reader.close();
//            writer.close();
//
//
//        } catch (Exception e) {
//
//        }
//

//    }

}

