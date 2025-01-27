import java.util.*;
import java.io.*;

public class Main {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer token, token2;

	static int n, s;
	static Car[] cars;
	static Set<Integer> list;

	static Queue<Car> que;
	static boolean[] visited;

	public static void sol() {
		que.add(cars[s]);
		list.add(s);

		int left = s - 1;
		int right = s + 1;

		while (!que.isEmpty()) {
			Car car = que.poll();
			int num = car.num;
			int fuel = car.fuel;
			int location = car.location;

			if (left >= 1 && location - cars[left].location <= fuel) {
				list.add(left);
				que.add(new Car(left, cars[left].location,
						Math.max(cars[left].fuel, fuel - (location - cars[left].location))));
				left--;
			}

			if (right <= n && cars[right].location - location <= fuel) {
				list.add(right);
				que.add(new Car(right, cars[right].location,
						Math.max(cars[right].fuel, fuel - (cars[right].location - location))));
				right++;
			}
		}
		for (int i : list) {
			output.append(i).append(" ");
		}

		System.out.println(output);
	}

	public static void init() throws Exception {
		token = new StringTokenizer(input.readLine());
		n = Integer.parseInt(token.nextToken());
		s = Integer.parseInt(token.nextToken());

		cars = new Car[n + 1];
		visited = new boolean[n + 1];
		token = new StringTokenizer(input.readLine());
		token2 = new StringTokenizer(input.readLine());
		for (int i = 1; i <= n; i++) {
			cars[i] = new Car(i, Integer.parseInt(token.nextToken()), Integer.parseInt(token2.nextToken()));
		}
		list = new TreeSet<>();
		que = new LinkedList<>();
	}

	public static void main(String[] args) throws Exception {
		init();
		sol();
	}

	static class Car {
		int num;
		int location;
		int fuel;

		public Car(int num, int location, int fuel) {
			this.num = num;
			this.location = location;
			this.fuel = fuel;
		}
	}

}