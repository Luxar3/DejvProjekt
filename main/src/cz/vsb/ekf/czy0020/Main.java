package cz.vsb.ekf.czy0020;

import cz.vsb.ekf.czy0020.dto.Actor;
import cz.vsb.ekf.czy0020.dto.Application;
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
        for (int i = 1; i <= 10000; i++) {
            Athlete athlete = new Athlete();
            athlete.setId(i);
            athlete.setActive(i % 10 != 0);
            athlete.setBirthDate(LocalDate.now().minusYears(random.nextInt(50)));
            athlete.setName(names[random.nextInt(names.length)]);
            athlete.setSurname("surname-" + i);
            athlete.setLastUpdateTs(LocalDateTime.now().minusDays(random.nextInt(30)));
            athlete.setPrizeMoney(randomBigDecimal(1000000));
            athlete.setSport(randomEnum(Sport.class));
            athlete.setCountOfMedals(new Random().nextInt(21));
            athleteList.add(athlete);
        }

        List<Actor> actorList = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            Actor actor = new Actor();
            actor.setCountOfOscars(new Random().nextInt(21));
            actor.setId(i);
            actor.setName(names[random.nextInt(names.length)]);
            actorList.add(actor);
        }


        Application.run(sc, athleteList);

        Application a = new Application();
        for (Athlete tempA : a.getMostSuccess(athleteList)) {
            System.out.println(tempA);
        }
        System.out.println();
        Actor actor = new Actor();
        System.out.println(actor.getMostSuccess(actorList));


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
