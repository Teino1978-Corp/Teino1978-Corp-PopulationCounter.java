import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class PopulationCounter {
    static Map<String, Map<String, Long>> population;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        population = new LinkedHashMap<>();

        String[] data;
        while (!(data = scanner.nextLine().split("\\|"))[0].equals("report")) {
            String country = data[1];
            String city = data[0];
            long populationCount = Long.parseLong(data[2]);

            if (!population.containsKey(country)) {
                population.put(country, new LinkedHashMap<>());
            }

            population.get(country).put(city, populationCount);
        }

        population.entrySet().stream()
                .sorted((country1, country2) -> -Long.compare(Sum(country1.getValue().values()), Sum(country2.getValue().values())))
                .forEachOrdered(item -> {
                    System.out.printf("%s (total population: %d)\n", item.getKey(), Sum(item.getValue().values()));
                    item.getValue().entrySet().stream()
                            .sorted((city1, city2) -> -city1.getValue().compareTo(city2.getValue()))
                            .forEachOrdered(city -> System.out.printf("=>%s: %d\n", city.getKey(), city.getValue()));
                });
    }

    private static long Sum(Collection<Long> values) {
        long total = 0;
        for (Long value : values) {
            total+= value;
        }

        return total;
    }
}