package sk.mysko.codewars;

class TimeUtils {
    public static String convertTime(int timeDiff) {
        int days = timeDiff / (60 * 60 * 24);
        timeDiff %= (60 * 60 * 24);
        int hours = timeDiff / (60 * 60);
        timeDiff %= (60 * 60);
        int minutes = timeDiff / 60;
        timeDiff %= 60;
        int seconds = timeDiff;

        return String.format("%d %d %d %d", days, hours, minutes, seconds);

    }



    public void run() {
        System.out.println(convertTime(90061));
        System.out.println(convertTime(-90061));

    }

}