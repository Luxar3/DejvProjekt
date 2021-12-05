package cz.vsb.ekf.czy0020.dto;

import java.math.BigDecimal;
import java.util.*;

public class Application implements MostSuccess {

    public static void run(Scanner sc, List<Athlete> athleteList) {
        //Úkol číslo 2.1
        System.out.println("Do you want to filter Inactive atletes? Y/N");
        //Čekám na user imput
        String userImput = sc.next();

        //Vytvořená sublistu, aby jsme neupravovali list, který procházíme
        List<Athlete> addList = new ArrayList<>();

        //Porovnání user imputu a Filtrování neaktivních atletů
        if (userImput.toUpperCase().equals("Y")) {
            for (Athlete ath : athleteList) {
                if (ath.getActive()) {
                    addList.add(ath);
                }
            }
            for (Athlete a : addList) {
                System.out.println(a);
            }
            System.out.println("Délka listu je: " + addList.size());
        } else if (userImput.toUpperCase().equals("N")) {
            addList = athleteList;
            for (Athlete a : addList) {
                System.out.println(a);
            }
            System.out.println("Délka listu je: " + addList.size());
        } else {
            System.out.println("F.U.");
        }

        //Úkol číslo 3
        System.out.println("Do you want to see top 100 players by earnings? Y/N");
        String userImputTop100 = sc.next();

        //Vytvoření dalšího sublistu pro top 100
        List<Athlete> addListToTop100 = new ArrayList<>();

        //Porovnání user imputu a vytvoření top 100 hráčů podle earnings
        if (userImputTop100.toUpperCase().equals("Y")) {
            Collections.sort(addList, Collections.reverseOrder());
            for (int i = 0; i < 100; i++) {
                addListToTop100.add(addList.get(i));
            }
            for (Athlete a : addListToTop100) {
                System.out.println(a);
            }
            System.out.println("Délka listu je: " + addListToTop100.size());
        } else if (userImputTop100.toUpperCase().equals("N")) {
            addListToTop100 = athleteList;
            for (Athlete a : addList) {
                System.out.println(a);
            }
            System.out.println("Délka listu je: " + addList.size());
        } else {
            System.out.println("F.U.");
        }

        //Úkol číslo 4
        System.out.println("Do you want to show names and how many duplicates are in the list? Y/n: ");
        String userImputStatistics = sc.next();

        //Porovnání user imputu a Ukázání jmen a počtu duplikátů pro tyto jména
        if ("Y".equals(userImputStatistics.toUpperCase())) {
            Collections.sort(addListToTop100, (Athlete o1, Athlete o2) -> o1.getName().compareTo(o2.getName()));
            int numberOfDuplicates = 1;
            int loop = 1;
            for (Athlete tempAthlete : addListToTop100) {
                if (loop + 1 > addListToTop100.size()) {
                    System.out.println(addListToTop100.get(loop - 1).getName() + " " + numberOfDuplicates + " times");
                    break;
                }
                if (tempAthlete.getName().equals(addListToTop100.get(loop).getName())) {
                    numberOfDuplicates++;
                } else {
                    System.out.println(addListToTop100.get(loop - 1).getName() + " " + numberOfDuplicates + " times");
                    numberOfDuplicates = 1;
                }
                loop++;

            }
        } else if ("N".equals(userImputStatistics.toUpperCase())) {
            System.out.println("Why do you do this to me?");
        } else {
            System.out.println("Why did you start this app then moron");
        }

        //Úkol číslo 5-> suma všech priceMoney
        BigDecimal priceMoney = new BigDecimal(0);
        for (Athlete tempAthlete : addListToTop100) {
            priceMoney = priceMoney.add(tempAthlete.getPrizeMoney());
        }
        System.out.println("Sum of price money is: " + priceMoney.intValue());

        //Úkol číslo 6 -> z téhle mě mrdlo třeba
        Collections.sort(addListToTop100, (Athlete o1, Athlete o2) -> o1.getBirthDate().compareTo(o2.getBirthDate()));
        Collections.sort(addListToTop100, (Athlete o1, Athlete o2) -> o1.getSport().compareTo(o2.getSport()));

        Map<Integer, Athlete> top5 = new HashMap<>();

        int i = 0;
        for (Athlete tempAthlete : addListToTop100) {
            top5.put(i, tempAthlete);
            i++;
        }


        Set<Integer> set = top5.keySet();
        Map<Integer, Athlete> top5add = new HashMap<>();
        top5add = top5;
        int y = 0;
        int loop1 = -1;
        int x = 1;

        for (Integer klic : set) {

            if (loop1 >= 0) {
                if (top5.get(loop1).getSport().equals(top5.get(klic).getSport())) {
                    y++;
                } else {
                    y = 0;
                }
                if (y < 5) {
                    top5add.put(x, top5.get(klic));
                    x++;
                }
            } else if (loop1 == -1) {
                top5add.put(klic, top5.get(klic));
            } else {
            }
            loop1++;
        }
        top5add.keySet().removeIf(key -> key > 34);


        Set<Integer> set1 = top5add.keySet();
        for (Integer klic : set1) {
            Athlete athletee = top5add.get(klic);
            System.out.println(athletee);
        }

        //Ukol 7
        long start = System.nanoTime();
        athleteList.get(athleteList.size() - 1);
        long end = System.nanoTime();
        System.out.println("\n" + "It takes: " + (end - start) + " nanoseconds to load id 9999 from typical getter");
        long start1 = System.nanoTime();
        for (Athlete tempA : athleteList) {
            if (tempA.getId().equals(athleteList.size() - 1)) {
                break;
            }
        }
        long end1 = System.nanoTime();
        System.out.println("It takes: " + (end1 - start1) + " nanoseconds to load id 9999 from loop" + "\n");

        //Ukol generic types


    }

    // generic types ukol 1 2 "3?"
    @Override
    public List<? extends Athlete> getMostSuccess(List original) {
        List<Athlete> sortedByMedals = new ArrayList<>();
        Collections.sort(original, Collections.reverseOrder((Athlete o1, Athlete o2) -> o1.getCountOfOscars().compareTo(o2.getCountOfOscars())));
        for (int i = 0; i < 10; i++) {
            sortedByMedals.add((Athlete)(original.get(i)));
        }
        return sortedByMedals;
    }
}
