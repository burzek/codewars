package sk.mysko.codewars;

public class Translator {

    private String[] ones = {"zero", "jeden", "dwa", "trzy", "cztery", "piec", "szesc" , "siedem", "osiem", "dziewiec"};
    private String[] tens = {"dziesiec", "jedenascie", "dwanascie", "trzynascie", "czternascie", "pietnascie", "szesnascie", "siedemnascie", "osiemnascie", "dziewietnascie"};
    private String[] tens2 = {"dziesiec", "dwadziescia", "trzydziesci", "czterdziesci", "piecdziesiat", "szescdziesiat", "siedemdziesiat", "osiemdziesiat", "dziewiecdziesiat"};

    public  String orderingBeers(int nbOfBeers) {
        if (nbOfBeers == 0) {
            return "Woda mineralna poprosze";
        } else if (nbOfBeers == 1) {
            return "Jedno piwo poprosze";
        }

        String numberStr = "";
        String beerStr = "";
        if (nbOfBeers < 10) {
            numberStr += ones[nbOfBeers];
            beerStr = nbOfBeers < 5 ? "piwa" : "piw";
        } else if (nbOfBeers >= 10 && nbOfBeers < 20) {
            numberStr += tens[nbOfBeers - 10];
            beerStr = "piw";
        } else {
            numberStr += tens2[(nbOfBeers / 10) - 1];
            if (nbOfBeers % 10 != 0) {
                numberStr += " " + ones[nbOfBeers % 10];
            }
            int test = nbOfBeers % 10;

            beerStr = (test == 2 || test == 3 || test == 4) ? "piwa" : "piw";
        }
        return numberStr.substring(0, 1).toUpperCase() + numberStr.substring(1) + " " + beerStr + " poprosze";
    }


    public void run() {
        //"Woda mineralna poprosze"
        System.out.println(orderingBeers(0));
        //"Jedno piwo poprosze",
        System.out.println(orderingBeers(1));
        //Dwa piwa poprosze",
        System.out.println(orderingBeers(2));
        //Trzy piwa poprosze"
        System.out.println(orderingBeers(3));
        //Dwadziescia osiem piw poprosze"
        System.out.println(orderingBeers(28));

        System.out.println(orderingBeers(10));
        System.out.println(orderingBeers(11));
        System.out.println(orderingBeers(20));



    }
}
