package cz.vsb.ekf.czy0020;

import cz.vsb.ekf.czy0020.dto.Athlete;
import cz.vsb.ekf.czy0020.dto.Sport;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class Main {

    private static final SecureRandom random = new SecureRandom();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<Athlete> athleteList = new ArrayList<>();
        for (int i = 0; i <= 500; i++) {
            Athlete athlete = new Athlete();
            athlete.setId(i);
            athlete.setActive(i % 10 != 0);
            athlete.setBirthDate(LocalDate.now().minusYears(random.nextInt(50)));
            athlete.setName(names[random.nextInt(names.length)]);
            athlete.setSurname("surname-" + i);
            athlete.setLastUpdateTs(LocalDateTime.now().minusDays(random.nextInt(30)));
            athlete.setPrizeMoney(randomBigDecimal(1000000));
            athlete.setSport(randomEnum(Sport.class));
            athleteList.add(athlete);
        }

        System.out.println("Do you want to filter Inactive atletes? Y/N");

        String userImput = sc.next();


        List<Athlete> addList = new ArrayList<>();

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


        System.out.println("Do you want to see top 100 players by earnings? Y/N");
        String userImputTop100 = sc.next();
        List<Athlete> addListToTop100 = new ArrayList<>();


        if (userImputTop100.toUpperCase().equals("Y")) {
            Collections.sort(addList);
            for (int i = 0; i < 100; i++) {
                addListToTop100.add(addList.get(i));
            }
            for (Athlete a : addListToTop100) {
                System.out.println(a);
            }
            System.out.println("Délka listu je: " + addListToTop100.size());
        } else if (userImputTop100.toUpperCase().equals("N")) {
            for (Athlete a : addList) {
                System.out.println(a);
            }
            System.out.println("Délka listu je: " + addList.size());
        } else {
            System.out.println("F.U.");
        }

        System.out.println("Do you want to show names and how many duplicates are in the list? Y/n: ");
        String userImputStatistics = sc.next();
        if ("Y".equals(userImputStatistics.toUpperCase())) {
            Collections.sort(addList, new Comparator<Athlete>() {
                @Override
                public int compare(Athlete o1, Athlete o2) {
                    return o1.getName().compareTo(o2.getName());
                }
            });
            int numberOfDuplicates = 1;
            int loop = 1;
            for (Athlete tempAthlete : addList) {
                if (loop + 1 > addList.size()){
                    System.out.println(addList.get(loop - 1).getName() + " " + numberOfDuplicates + " times");
                    break;
                }
                if (tempAthlete.getName().equals(addList.get(loop).getName())) {
                    numberOfDuplicates++;
                } else {
                    System.out.println(addList.get(loop - 1).getName() + " " + numberOfDuplicates + " times");
                    numberOfDuplicates = 1;
                }
                loop++;
            }
        }
        else{
            System.out.println("Why did you start this app then :)");
        }

    }

    public static BigDecimal randomBigDecimal(int range) {
        BigDecimal max = new BigDecimal(range);
        BigDecimal randFromDouble = new BigDecimal(Math.random());
        BigDecimal actualRandomDec = randFromDouble.multiply(max);
        actualRandomDec.setScale(2, RoundingMode.DOWN);
        return actualRandomDec;
    }

    public static <T extends Enum<?>> T randomEnum(Class<T> clazz) {
        int x = random.nextInt(clazz.getEnumConstants().length);
        return clazz.getEnumConstants()[x];
    }

    private static String[] names = {
            "Liam",
            "Olivia",
            "Noah",
            "Emma",
            "Oliver",
            "Ava",
            "William",
            "Sophia",
            "Elijah",
            "Isabella",
            "James",
            "Charlotte",
            "Benjamin",
            "Amelia",
            "Lucas",
            "Mia",
            "Mason",
            "Harper",
            "Ethan",
            "Evelyn"
    };
}
