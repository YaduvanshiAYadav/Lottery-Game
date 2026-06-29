import java.util.*;

public class Main {
    static Map<String, List<List<String>>> recipes = new HashMap<>();
    static Map<String, Integer> memo = new HashMap<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine().trim());
        for (int i = 0; i < n; i++) {
            String line = sc.nextLine().trim();
            String[] parts = line.split("=");
            String potion = parts[0];
            String[] ingredients = parts[1].split("\\+");

            recipes.putIfAbsent(potion, new ArrayList<>());
            recipes.get(potion).add(Arrays.asList(ingredients));
        }

        String target = sc.nextLine().trim();
        System.out.println(getCost(target));
    }

    // Recursive function with memoization
    private static int getCost(String potion) {
        if (memo.containsKey(potion)) {
            return memo.get(potion);
        }

        // If potion not in recipes → it's an item → cost = 0
        if (!recipes.containsKey(potion)) {
            memo.put(potion, 0);
            return 0;
        }

        int minCost = Integer.MAX_VALUE;
        for (List<String> ingredients : recipes.get(potion)) {
            int cost = (ingredients.size() - 1); // orbs for mixing
            for (String ing : ingredients) {
                cost += getCost(ing);
            }
            minCost = Math.min(minCost, cost);
        }

        memo.put(potion, minCost);
        return minCost;
    }
}
