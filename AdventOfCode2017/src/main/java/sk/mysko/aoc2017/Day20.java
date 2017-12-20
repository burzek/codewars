package sk.mysko.aoc2017;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author boris.brinza 04-Dec-2017.
 */
public class Day20 extends AdventOfCodeBase<String> {

	public static void main(String[] args) {
		Day20 day20 = new Day20();
		String input = day20.readFile("/Day20.input");
		day20.runPart1(input);	//all is done in part1

	}

	class Particle implements Comparable<Particle> {
		int id;
		int x,y,z;
		int vx, vy, vz;
		int ax, ay, az;

		void step() {
			vx += ax; vy += ay; vz += az;
			x += vx; y += vy; z += vz;
		}
		int getDistance() {
			return Math.abs(x) + Math.abs(y) + Math.abs(z);
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


	@Override
	protected String runPart1(String input) {
		List<Particle> particleList = parseInput(input);
		for (int i = 0; i < 200_000; i++) {		//brute force
			particleList.forEach(Particle::step);
		}
		//particleList.stream().min()
		System.out.println("closest id:" + particleList.get(0).id);
		return "";
	}


	@Override
	protected String runPart2(String input) {
		return "";
	}




}
