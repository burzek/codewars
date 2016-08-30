package sk.mysko.advent.code;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author boris.brinza
 */
public class Day7 extends AdventOfCodeBase {

	class Wire {
		String name;
		Integer signal;
		List<Wire> outputs;
		Gate gate;

		public Wire(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
	}

	abstract class Gate {
		List<Wire> inputs;
		Wire output;

		public List<Wire> getInputs() {
			return inputs;
		}

		public void setInputs(List<Wire> inputs) {
			this.inputs = inputs;
		}

		public Wire getOutput() {
			return output;
		}

		public void setOutput(Wire output) {
			this.output = output;
		}

		public abstract void processSignals();
	}
	class NotGate extends  Gate {
		@Override
		public void processSignals() {
			output.signal = (~getInputs().get(0).signal);
		}
	}
	class AndGate extends  Gate {
		@Override
		public void processSignals() {
			output.signal = getInputs().get(0).signal & getInputs().get(1).signal;
		}
	}
	class OrGate extends Gate {
		@Override
		public void processSignals() {
			output.signal = getInputs().get(0).signal | getInputs().get(1).signal;
		}
	}
	class RShiftGate extends Gate {
		@Override
		public void processSignals() {
			output.signal = getInputs().get(0).signal >> getInputs().get(1).signal;
		}
	}
	class LShiftGate extends Gate {
		@Override
		public void processSignals() {
			output.signal = getInputs().get(0).signal << getInputs().get(1).signal;
		}
	}

	Map<String, Wire> circuit = new HashMap<>();


	private Wire getWire(String name) {
		Wire w = circuit.get(name);
		if (w == null) {
			w = new Wire(name);
			circuit.put(name, w);
		}
		return w;
	}

	private void processNot(String wireName) {
		Wire w = circuit.get(wireName);
		if (w == null) {

		}
	}
	@Override
	protected long runPart1(String input) {
		for (String line : input.split("\\s+")) {
			String[] desc = line.split(" ");
			if ("NOT".equals(desc[0])) {
//				processNot();
			}
		}
		return 0;
	}


	@Override
	protected long runPart2(String input) {
		return 0;
	}

	public static void main(String[] args) {
		Day7 day7 = new Day7();
		String input = day7.readFile("/Day7.input");
		long value = day7.runPart1(input);
		System.out.println("output is:" + value);
		long brightness = day7.runPart2(input);
		System.out.println("brightness:" + brightness);

	}

}
