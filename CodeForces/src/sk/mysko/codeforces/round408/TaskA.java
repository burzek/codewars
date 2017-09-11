	package sk.mysko.codeforces.round408;

	import java.io.BufferedReader;
	import java.io.InputStreamReader;

		public class TaskA {
			private class Runner {

				private void run() throws Exception {

					BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
					String[] line = reader.readLine().split(" ");
					int houses = Integer.parseInt(line[0]);
					int ownHouse = Integer.parseInt(line[1]);
					int dollars = Integer.parseInt(line[2]);

					String[] tmp = reader.readLine().split(" ");
					int[] prices = new int[tmp.length];
					int i = 0;
					for (String p : tmp) {
						prices[i++] = Integer.parseInt(p);
					}

					int id = ownHouse - 1;
					int iu = ownHouse - 1;
					while (--id > 0 && (prices[id] == 0 || prices[id] > dollars));
					while (++iu < prices.length && (prices[iu] == 0 || prices[iu] > dollars));
					int diff = 0;
					id  = id < 0 ? 0 : id;
					iu = iu >= prices.length ? prices.length - 1 : iu;
					if (prices[id] != 0 && prices[iu] != 0) {
						diff = prices[id] < prices[iu] ? ownHouse - id : iu - ownHouse;
					} else {
						diff = prices[id] == 0 ? iu - ownHouse : ownHouse - id;
					}

					System.out.println((diff + 1) * 10);
				}

			}


		/**
		 * @param args
		 */
		public static void main(String[] args) throws Exception {
			TaskA.Runner r = new TaskA().new Runner();
			r.run();
		}
	}
