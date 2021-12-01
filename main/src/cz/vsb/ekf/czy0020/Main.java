package cz.vsb.ekf.czy0020;

import cz.vsb.ekf.czy0020.dto.Athlete;
import cz.vsb.ekf.czy0020.dto.Sport;

import java.math.BigDecimal;
import java.math.BigInteger;
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
            Collections.sort(addList);
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
        
        int i=0;
        for (Athlete tempAthlete : addListToTop100) {
            top5.put(i, tempAthlete);
            i++;
        }
        
        System.out.println("letsSee: ");
        Set<Integer> set=top5.keySet();
        Map<Integer, Athlete> top5add = new HashMap<>();
        top5add=top5;
        int y =0;
        int loop1 =-1;
        int x =1;
        System.out.println(top5.keySet());
        
        for (Integer klic : set) {
            
            if(loop1>=0){
            if(top5.get(loop1).getSport().equals(top5.get(klic).getSport())){
            y++;
            }
            else{y=0;}
            if (y<5){
                top5add.put(x, top5.get(klic));
            x++;
            }
        }
            else if(loop1==-1){
            top5add.put(klic, top5.get(klic));
            }
            else{}
        loop1++;
        }
        top5add.keySet().removeIf(key -> key > 34);
        
        
        Set<Integer> set1=top5add.keySet();
        for (Integer klic : set1) {
            Athlete athletee = top5add.get(klic);
            System.out.println(athletee);
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
