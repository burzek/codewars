package sk.mysko.aoc2017;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author boris.brinza 04-Dec-2017.
 */
public class Day20 extends AdventOfCodeBase<Integer> {

	public static void main(String[] args) {
		Day20 day20 = new Day20();
		String input = day20.readFile("/Day20.input");
		System.out.println(day20.runPart1(input));
		System.out.println(day20.runPart2(input));


	}

	class Particle {
		int id;
		long x,y,z;
		long vx, vy, vz;
		long ax, ay, az;

		void step() {
			vx += ax; vy += ay; vz += az;
			x += vx; y += vy; z += vz;
		}
		long getDistance() {
			return Math.abs(x) + Math.abs(y) + Math.abs(z);
		}
		String getKey() {
			return String.valueOf(x) + ":" + String.valueOf(y) + ":" + String.valueOf(z);
		}
	}

	private List<Particle> parseInput(String input) {
		List<Particle> particles = new ArrayList<>();
		int id = 0;
		for (String line : input.split("\n")) {
			Particle p = new Particle();
			p.id = id++;
			String tmp = line.substring(3, line.indexOf('>'));
			String[] s = tmp.split(",");
			p.x = Integer.parseInt(s[0]);
			p.y = Integer.parseInt(s[1]);
			p.z = Integer.parseInt(s[2]);
			tmp = line.substring(line.indexOf("v=<") + 3, line.indexOf(">, a="));
			s = tmp.split(",");
			p.vx = Integer.parseInt(s[0]);
			p.vy = Integer.parseInt(s[1]);
			p.vz = Integer.parseInt(s[2]);
			int from = line.indexOf("a=<") + 3;
			tmp = line.substring(from, line.indexOf('>', from));
			s = tmp.split(",");
			p.ax = Integer.parseInt(s[0]);
			p.ay = Integer.parseInt(s[1]);
			p.az = Integer.parseInt(s[2]);
			particles.add(p);
		}

		return particles;
	}

	Map<String, Integer> buckets = new HashMap<>();


	@Override
	protected Integer runPart1(String input) {
		List<Particle> particleList = parseInput(input);
		for (int i = 0; i < 1000; i++) {		//brute force
			particleList.forEach(Particle::step);
		}
		Optional<Particle> opt = particleList.stream().min(Comparator.comparingLong(p -> p.getDistance()));
		return opt.get().id;
	}


	@Override
	protected Integer runPart2(String input) {
		List<Particle> particleList = parseInput(input);
		int destroyed = 0;
		for (int i = 0; i < 1000; i++) {		//brute force
			buckets.clear();
			particleList.forEach(x -> {
				String key = x.getKey();
				buckets.putIfAbsent(key, 0);
				buckets.computeIfPresent(key, (k, v) -> v + 1);
			});
			particleList.removeIf(x -> buckets.get(x.getKey()) > 1);
			particleList.forEach(Particle::step);

		}
		return (int) particleList.size();

	}




}
