import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author boris.brinza 01.dec.2020
 */
public class Day7 extends AdventOfCodeBase<Void> {

    public static void main(String[] args) {
        Day7 day7 = new Day7();
        String input = day7.readFile("/Day7.input");
        String[] lines = input.split("\n");

        Map<String, Bag> allBags = day7.parse(lines);
        day7.runPart1(allBags.values());
        day7.runPart2(allBags.get("shinygold"));

    }

    private class Bag {
        String name;
        List<Integer> bagsCount;
        List<Bag> bags;

        public Bag(String name) {
            this.name = name;
            this.bags = new ArrayList<>();
            this.bagsCount = new ArrayList<>();
        }
    }

    private void runPart1(Collection<Bag> allBags) {
        long count = allBags.stream().filter(this::containsGold).count();
        System.out.println("result 1 is:" + (count - 1));   //do not count shinygold self
    }


    private void runPart2(Bag shinyBag) {

        System.out.println("result 2 is:" + (countBags(shinyBag) - 1));
    }

    private long countBags(Bag root) {
        long count = 1;
        for (int i = 0; i < root.bags.size(); i++) {
            count += root.bagsCount.get(i) * countBags(root.bags.get(i));
        }
        return  count;
    }

    private boolean containsGold(Bag root) {
        if (root.name.equals("shinygold")) {
            return true;
        }
        for (Bag bag : root.bags) {
            if (containsGold(bag)) {
                return true;
            }
        }

        return false;
    }


    private Map<String, Bag> parse(String[] lines) {
        Map<String, Bag> allBags = new HashMap<>();
        for (String line : lines) {
            if (line.endsWith("contain no other bags.")) {
                continue;
            }

            //root bag
            int idx = line.indexOf("bags contain");
            String name = line.substring(0, idx).replaceAll(" ", "");
            allBags.putIfAbsent(name, new Bag(name));
            Bag rootBag = allBags.get(name);

            //contains these bags
            String[] container = line.substring(idx + "bags contain".length()).split(",");
            for (String s : container) {
                String[] parts = s.split(" ");
                name = parts[2] + parts[3];
                allBags.putIfAbsent(name, new Bag(name));
                rootBag.bags.add(allBags.get(name));
                rootBag.bagsCount.add(Integer.parseInt(parts[1]));
            }

        }
        return allBags;
    }


}
