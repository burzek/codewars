/**
 * @author boris.brinza 01.dec.2020
 */
public class Day4 extends AdventOfCodeBase<Long> {

    public static void main(String[] args) {
        Day4 day4 = new Day4();
        String input = day4.readFile("/Day4.input");
        String[] lines = input.split("\n");
        System.out.println("part1:" + day4.runSolution(lines, false));
        System.out.println("part2:" + day4.runSolution(lines, true));
    }

    private static final int byr = 1;
    private static final int iyr = 2;
    private static final int eyr = 4;
    private static final int hgt = 8;
    private static final int hcl = 16;
    private static final int ecl = 32;
    private static final int pid = 64;

    private long runSolution(String[] lines, boolean includeValueCheck) {
        int count = 0;
        byte mask = 0;

        for (String line : lines) {
            if (line.isEmpty()) {
                if (mask == 127) {
                    count++;
                }
                mask = 0;
            }
            String[] items = line.split(" ");
            for (String item : items) {
                String[] keyVal = item.split(":");
                mask |= includeValueCheck ? valueCheckMask(keyVal[0], keyVal.length == 2 ? keyVal[1] : "") : baseCheckMask(keyVal[0]);
            }

        }
        //last line check
        if (mask == 127) {
            count++;
        }

        return count;

    }

    private byte baseCheckMask(String key) {
        switch (key) {
            case "byr": return byr;
            case "iyr": return iyr;
            case "eyr": return eyr;
            case "hgt": return hgt;
            case "hcl": return hcl;
            case "ecl": return ecl;
            case "pid": return pid;
        }
        return 0;
    }

    private byte valueCheckMask(String key, String val) {
        switch (key) {
            case "byr": return (Integer.parseInt(val) >= 1920 && Integer.parseInt(val) <=2002) ? byr : (byte) 0;
            case "iyr": return (Integer.parseInt(val) >= 2010 && Integer.parseInt(val) <=2020) ? iyr : (byte) 0;
            case "eyr": return (Integer.parseInt(val) >= 2020 && Integer.parseInt(val) <=2030) ? eyr : (byte) 0;
            case "hgt":
                return isValidHgt(val) ? hgt : (byte) 0;
            case "hcl":
                return isValidHcl(val) ? hcl : (byte) 0;
            case "ecl": return isValidEcl(val) ? ecl : (byte) 0;
            case "pid": return isValidPid(val) ? pid : (byte) 0;
        }
        return 0;
    }

    private boolean isValidPid(String val) {
        if (val.length() != 9) {
            return false;
        }
        try {
            Integer.parseInt(val);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    private boolean isValidEcl(String val) {
        return val.equals("amb") ||
                val.equals("blu") ||
                val.equals("brn") ||
                val.equals("gry") ||
                val.equals("grn") ||
                val.equals("hzl") ||
                val.equals("oth");
    }

    private boolean isValidHcl(String val) {
        return val.length() == 7 && val.charAt(0) == '#' &&
                val.substring(1).matches("[0-9a-z]*");
    }

    private boolean isValidHgt(String val) {
        String height = val.substring(0, val.length() - 2);
        if (val.endsWith("in")) {
            return Integer.parseInt(height) >= 59 && Integer.parseInt(height) <= 76;
        } else if (val.endsWith("cm")) {
            return Integer.parseInt(height) >= 150 && Integer.parseInt(height) <= 193;
        }
        return false;
    }

}
